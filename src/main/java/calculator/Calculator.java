package calculator;

import java.util.*;

public class Calculator {
    private List<Integer> numbers = new LinkedList<>();

    private int resAdd = 0;
    private int resMul = 0;
    private int resSub = 0;
    private int resDiv = 0;

    public void enter(int i){
        numbers.add(i);
        if (numbers.size() > 2){
            throw new IllegalStateException();
        }
    }

    public int add(){
        if (numbers.size() != 2){
            throw new IllegalStateException();
        }
        numbers.set(0, numbers.get(0) + numbers.get(1));
        int res = numbers.get(0);
        numbers.remove(1);
        return res;
    }

    public int getResult(){
        if (numbers.size() != 1){
            throw new IllegalStateException();
        }
        return numbers.get(0);
    }

    /* New operations */
    public int multiply() {
        if (numbers.size() != 2) {
            throw new IllegalStateException();
        }
        numbers.set(0, numbers.get(0) * numbers.get(1));
        int res = numbers.get(0);
        numbers.remove(1);
        return res;
    }

    public int subtract() {
        if (numbers.size() != 2) {
            throw new IllegalStateException();
        }
        if (numbers.get(0) >= numbers.get(1))
            numbers.set(0, numbers.get(0) - numbers.get(1));
        else
            throw new ArithmeticException();
        int res = numbers.get(0);
        numbers.remove(1);
        return res;
    }

    public int divide() {
        if (numbers.size() != 2) {
            throw new IllegalStateException();
        }
        if (numbers.get(1) != 0 && (numbers.get(0) != 0 && numbers.get(1) != 0))
            numbers.set(0, numbers.get(0) / numbers.get(1));
        else
            throw new ArithmeticException();

        int res = numbers.get(0);
        numbers.remove(1);
        return res;
    }


    /* Aux methods */
    public int executeOperation(String op)
    {
        return switch (op) {
            case "+" -> resAdd = add();
            case "-" -> resSub = subtract();
            case "*" -> resMul = multiply();
            case "/" -> resDiv = divide();
            default -> 0;
        };
    }

}
