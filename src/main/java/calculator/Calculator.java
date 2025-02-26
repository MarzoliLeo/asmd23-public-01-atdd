package calculator;

import java.util.*;

public class Calculator {
    private List<Integer> numbers = new LinkedList<>();

    public void enter(int i){
        numbers.add(i);
        if (numbers.size() > 2){
            throw new IllegalStateException();
        }
    }

    public void add(){
        if (numbers.size() != 2){
            throw new IllegalStateException();
        }
        numbers.set(0, numbers.get(0) + numbers.get(1));
        numbers.remove(1);
    }

    public int getResult(){
        if (numbers.size() != 1){
            throw new IllegalStateException();
        }
        return numbers.get(0);
    }

    /* New operations */
    public void multiply() {
        if (numbers.size() != 2) {
            throw new IllegalStateException();
        }
        numbers.set(0, numbers.get(0) * numbers.get(1));
        numbers.remove(1);
    }

    public void subtract() {
        if (numbers.size() != 2) {
            throw new IllegalStateException();
        }
        if (numbers.get(0) >= numbers.get(1))
            numbers.set(0, numbers.get(0) - numbers.get(1));
        else
            throw new ArithmeticException();
        numbers.remove(1);
    }

    public void divide() {
        if (numbers.size() != 2) {
            throw new IllegalStateException();
        }
        if (numbers.get(1) != 0 && (numbers.get(0) != 0 && numbers.get(1) != 0))
            numbers.set(0, numbers.get(0) / numbers.get(1));
        else
            throw new ArithmeticException();

        numbers.remove(1);
    }

}
