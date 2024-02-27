package org.example.week1;

public class EngineeringCalculator extends Calculator{
    public EngineeringCalculator() {
        super(CalculatorType.ENGINEERING);
    }

    public int calculateRightTriangleWidth(Integer width, Integer height) {
        validateNull(width, height);
        return validateResult((double) width*height/2);
    }
}
