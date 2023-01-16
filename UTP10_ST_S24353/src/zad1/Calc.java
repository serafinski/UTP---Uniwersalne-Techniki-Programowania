/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

public class Calc {

    // A variable that stores the first number from the equation.
    BigDecimal firstNum;

    // A variable that stores the second number from the equation.
    BigDecimal secondNum;

    // A hash map that stores the operators as keys and the calculations as values.
    HashMap<String, Calculation> hashMap;


    /**
     * It takes a string, splits it into an array, and then uses a hashmap to call the appropriate function based on the
     * operator
     *
     * @param cmd The equation to be calculated.
     *
     * @return The result of the calculation.
     */
    public String doCalc(String cmd) {
        // It splits the string into an array.
        String[] equation = cmd.split("\\s+");
        // It creates a new hashmap.
        hashMap = new HashMap<>();

        // It tries to assign the first number from the equation to the firstNum variable, the operator
        // to the operator variable, and the second number to the secondNum variable. Then it creates four new Calculation
        // objects and assigns them to the appropriate operators. Then it returns the result of the calculation.
        try{
            firstNum = new BigDecimal(equation[0]);
            String operator = equation[1];
            secondNum = new BigDecimal(equation[2]);

            Calculation add = BigDecimal::add;
            Calculation subtract = BigDecimal::subtract;
            Calculation multiply = BigDecimal::multiply;

            //Switched, because the result should be shown with an accuracy of at least 7 decimal places.
            Calculation divide = (firstNum, secondNum) -> firstNum.divide(secondNum, MathContext.DECIMAL64);

            // It adds the operator "+" as a key and the Calculation object add as a value to the hashmap.
            hashMap.put("+",add);

            // It adds the operator "-" as a key and the Calculation object subtract as a value to the hashmap.
            hashMap.put("-",subtract);

            // It adds the operator "*" as a key and the Calculation object multiply as a value to the hashmap.
            hashMap.put("*",multiply);

            // It adds the operator "/" as a key and the Calculation object divide as a value to the hashmap.
            hashMap.put("/",divide);

            // It returns the result of the calculation.
            return String.valueOf(hashMap.get(operator).calculation(firstNum,secondNum));


        // It catches an exception and throws a new RuntimeException with the message "Invalid command to calc".
        } catch (Exception e) {
            throw new RuntimeException("Invalid command to calc");
        }
    }
}
