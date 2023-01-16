package zad1;

import java.math.BigDecimal;

// A functional interface. It has only one abstract method.
@FunctionalInterface
public interface Calculation {
    /**
     * This function takes two BigDecimal values and returns a BigDecimal value.
     *
     * @param firstNum The first number to be used in the calculation.
     * @param secondNum The second number to be used in the calculation.
     *
     * @return A BigDecimal
     */
    BigDecimal calculation (BigDecimal firstNum, BigDecimal secondNum);
}
