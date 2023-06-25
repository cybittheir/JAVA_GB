
// 3) Реализовать простой калькулятор

package L01;

import java.util.Scanner;

public class hw_03 {
    public static void main(String[] args) {

        String digit = ""; // тип чисел. по-умолчанию целые

        Scanner input = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        String first = input.nextLine().trim();
        System.out.print("Введите второе число: ");
        String second = input.nextLine().trim();
        System.out.print("Введите действие (+|-|*|/|%): ");
        String func_sel = input.nextLine().trim();

        if (func_sel.trim().charAt(0) != '%') {
            if (first.indexOf('.') > -1 || second.indexOf('.') > -1){
                digit = "1";
            } else if (first.indexOf('.') == -1 && second.indexOf('.') == -1 && (func_sel.trim().charAt(0) != '/')){
                digit = "";
            } else {
                System.out.print("Результат целым числом (ENTER || 0) или десятичным (>0)?: ");
                digit = input.nextLine().trim();
            }
        }

        input.close();

        double result = Calc(Double.valueOf(first), Double.valueOf(second), func_sel);
        System.out.println("============");
        System.out.print("Результат: " + first + " " + func_sel + " " + second + " = "); 

        if (digit == "" || Integer.parseInt(digit) == 0){
            System.out.println((int)result);
        } else {
            System.out.println(result);
        }

    }

    static double Calc(double first, double second, String func){

        double res;

        switch(func){
            case "+":
                res = first + second;
                break;
            case "-":
                res = first - second;
                break;
            case "/":
                res = first / second;
                break;
            case "*":
                res = first * second;
                break;
            case "%":
                res = first % second;
                break;
            default:
                res = 0; 
        }

        return res;

    }
    
}
