package sol2;

import java.util.List;
import java.util.Optional;

public interface Logic {

    Optional<Integer> hit(Position position);

    Optional<Integer> getMark(Position position);

    boolean isOver();


    //For testing
    List<Position> getMarks();
}
