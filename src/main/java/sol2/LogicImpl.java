package sol2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LogicImpl implements Logic {

    private final int size;
    private List<Position> marks = new LinkedList<>();
    private boolean moving = false;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public Optional<Integer> hit(Position position) {
        if (this.isOver()){
            return Optional.empty();
        }
        if (this.moving || startMoving(position)){
            this.moving = true;
            this.moveMarks();
            return Optional.empty();
        }
        this.marks.add(position);
        return Optional.of(this.marks.size());
    }

    private boolean neighbours(Position p1, Position p2){
        return Math.abs(p1.getX()-p2.getX()) <= 1 && Math.abs(p1.getY()-p2.getY()) <= 1;
    }

    private boolean startMoving(Position position) {
        return this.marks.stream().anyMatch(p -> neighbours(p, position));
    }

    private void moveMarks() {
        this.marks = this.marks
                .stream()
                .map(p -> new Position(p.getX()+1, p.getY()-1))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<Integer> getMark(Position position) {
        return Optional.of(this.marks.indexOf(position)).filter(i -> i>=0).map(i -> i+1);
    }

    @Override
    public boolean isOver() {
        return this.marks.stream().anyMatch(p -> p.getX() == this.size || p.getY() == -1);
    }
}
