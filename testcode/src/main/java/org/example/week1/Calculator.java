package org.example.week1;

import java.util.Objects;

public class Calculator {
    private CalculatorType calculatorType;

    public Calculator(CalculatorType calculatorType) {
        this.calculatorType = calculatorType;
    }

    public int add(Integer x, Integer y) {
        validateNull(x, y);
        return validateResult(x+y);
    }

    public int subtract(Integer x, Integer y) {
        validateNull(x, y);
        return validateResult(x-y);
    }

    public int multiply(Integer x, Integer y) {
        validateNull(x, y);
        return validateResult(x*y);
    }

    public int divide(Integer x, Integer y) {
        validateNull(x, y);
        validateDivisor(y);
        return validateResult((double) x/y);
    }

    protected void validateNull(Integer x, Integer y) {
        if(Objects.isNull(x) || Objects.isNull(y)){
            throw new IllegalArgumentException("NULL");
        }
    }

    protected int validateResult(double result) {
        int resultInteger = validateInteger(result);
        validateLimit(resultInteger);

        return resultInteger;
    }

    private int validateInteger(double result) {
        if (result != (int)result) {
            throw new IllegalArgumentException("Invalid integer value");
        }

        return (int)result;
    }

    private void validateLimit(int result) {
        if (result < 0 || result > 100000) {
            throw new IllegalArgumentException("Invalid limit");
        }
    }

    private void validateDivisor(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor Zero");
        }
    }
}
