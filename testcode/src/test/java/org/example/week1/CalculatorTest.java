package org.example.week1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    DigitalCalculator digitalCalculator = new DigitalCalculator();
    EngineeringCalculator engineeringCalculator = new EngineeringCalculator();

    @Test
    @DisplayName("두 개의 정수를 입력하면 두 정수의 더한 값을 출력한다.")
    void add_check() {
        int result = digitalCalculator.add(1, 4);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("입력 값에 Null이 존재할 경우 예외가 발생한다.")
    void addWithNullInput() {
        Assertions.assertThatThrownBy(
                        () -> {digitalCalculator.add(null, 2);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("NULL");
    }

    @Test
    @DisplayName("결과 값이 100,000 초과일 경우 예외가 발생한다.")
    void addResultOver100000() {
        Assertions.assertThatThrownBy(
                        () -> {digitalCalculator.add(100000, 1);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid limit");
    }

    @Test
    @DisplayName("결과 값이 100,000 초과일 경우 예외가 발생한다.")
    void mulResultOver100000() {
        Assertions.assertThatThrownBy(
                        () -> {digitalCalculator.multiply(100001, 1);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid limit");
    }

    @Test
    @DisplayName("결과 값이 0 미만일 경우 예외가 발생한다.")
    void subResultUnder0() {
        Assertions.assertThatThrownBy(
                        () -> {digitalCalculator.subtract(1, 3);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid limit");
    }

    @Test
    @DisplayName("결과 값이 정수가 아닐 경우 예외가 발생한다.")
    void divResultNotInteger() {
        Assertions.assertThatThrownBy(
                        () -> {digitalCalculator.divide(7, 3);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid integer value");
    }

    @Test
    @DisplayName("나누는 값이 0일 경우 예외가 발생한다.")
    void divDivisorZero() {
        Assertions.assertThatThrownBy(
                        () -> {digitalCalculator.divide(7, 0);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Divisor Zero");
    }

    @Test
    @DisplayName("직각 삼각형의 밑변과 높이를 입력하면, 직각 삼각형의 넓이를 출력한다.")
    void triangle_check() {
        int result = engineeringCalculator.calculateRightTriangleWidth(7, 8);
        Assertions.assertThat(result).isEqualTo(28);
    }

    @Test
    @DisplayName("직각 삼각형의 넓이가 정수가 아닐 경우 예외가 발생한다.")
    void triangleResultNotInteger() {
        Assertions.assertThatThrownBy(
                        () -> {engineeringCalculator.calculateRightTriangleWidth(7, 3);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid integer value");
    }

    @Test
    @DisplayName("직각 삼각형의 넓이가 100,000 초과일 경우 예외가 발생한다.")
    void triangleResultOver100000() {
        Assertions.assertThatThrownBy(
                        () -> {engineeringCalculator.calculateRightTriangleWidth(100001, 2);}
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid limit");
    }
}