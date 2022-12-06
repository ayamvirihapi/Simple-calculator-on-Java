import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        HashMap<String, Integer> romanToArabicConvert = new HashMap<>();
        romanToArabicConvert.put("I", 1);
        romanToArabicConvert.put("II", 2);
        romanToArabicConvert.put("III", 3);
        romanToArabicConvert.put("IV", 4);
        romanToArabicConvert.put("V", 5);
        romanToArabicConvert.put("VI", 6);
        romanToArabicConvert.put("VII", 7);
        romanToArabicConvert.put("VIII", 8);
        romanToArabicConvert.put("IX", 9);
        romanToArabicConvert.put("X", 10);

        ArabicToRomanConvert arabicConvert = new ArabicToRomanConvert();

        System.out.println("Enter arithmetic expression:");
        String input = myInput.nextLine();
        String[] arithmeticExpression = input.split(" ");

        try {
            if (arithmeticExpression.length > 3) {
                throw new InputRangeException();
            }
            String firstArgument = arithmeticExpression[0];
            String arithmeticOperation = arithmeticExpression[1];
            String secondArgument = arithmeticExpression[2];

            boolean isRomanNumerals = romanToArabicConvert.containsKey(firstArgument) & romanToArabicConvert.containsKey(secondArgument);

            if (isRomanNumerals) {
                int firstArabicArgument = romanToArabicConvert.get(firstArgument);
                int secondArabicArgument = romanToArabicConvert.get(secondArgument);

                switch (arithmeticOperation) {
                    case "+": System.out.println(arabicConvert.arabicToRomanConvert(firstArabicArgument + secondArabicArgument)); break;
                    case "-":
                        if (firstArabicArgument - secondArabicArgument < 1) {
                            throw new RomanNumeralsResultRangeException();
                        }
                        System.out.println(arabicConvert.arabicToRomanConvert(firstArabicArgument - secondArabicArgument)); break;
                    case "*": System.out.println(arabicConvert.arabicToRomanConvert(firstArabicArgument * secondArabicArgument)); break;
                    case "/":
                        if (firstArabicArgument / secondArabicArgument < 1) {
                            throw new RomanNumeralsResultRangeException();
                        }
                        System.out.println(arabicConvert.arabicToRomanConvert(firstArabicArgument / secondArabicArgument)); break;
                }

            } else if (romanToArabicConvert.containsKey(firstArgument) | romanToArabicConvert.containsKey(secondArgument)) {
                throw new DifferentNumberSystemsException();
            } else {

                int firstArgumentInt = Integer.parseInt(arithmeticExpression[0]);
                int secondArgumentInt = Integer.parseInt(arithmeticExpression[2]);

                if (firstArgumentInt > 10 | firstArgumentInt < 1 | secondArgumentInt > 10 | secondArgumentInt < 1) {
                    throw new InputArabicNumeralsRangeException();
                }
                switch (arithmeticOperation) {
                    case "+" -> System.out.println(firstArgumentInt + secondArgumentInt);
                    case "-" -> System.out.println(firstArgumentInt - secondArgumentInt);
                    case "*" -> System.out.println(firstArgumentInt * secondArgumentInt);
                    case "/" -> System.out.println(firstArgumentInt / secondArgumentInt);
                }
            }
        } catch (InputRangeException e) {
            System.out.println("The format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *).");
        } catch (RomanNumeralsResultRangeException e) {
            System.out.println("The result of an operation with Roman numerals can't be less than 1.");
        } catch (InputArabicNumeralsRangeException e) {
            System.out.println("Input arabic numbers must be between 1 and 10 inclusive.");
        } catch (DifferentNumberSystemsException e) {
            System.out.println("You can't use different number systems at the same time.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("String is not a mathematical operation.");
        } catch (NumberFormatException e) {
            System.out.println("Number must be integers.");
        }
    }
}

class ArabicToRomanConvert {
    String arabicToRomanConvert(int arabicNumber) {
        String[] M = {"", "M", "MM", "MMM"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return M[arabicNumber/1000] + C[(arabicNumber%1000)/100] + X[(arabicNumber%100)/10] + I[arabicNumber%10];
    }
}