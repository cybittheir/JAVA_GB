
// 1) Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n)

package L01;

import java.util.Scanner;

public class hw_01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите целое число N: ");
        int n = Integer.parseInt(input.nextLine().trim());
        input.close();

        int i = 1;
        int fact = 1;

        while (i <= n){
            fact *= i;
            i++;
        }
    
        System.out.printf("Треугольное число T(%d) = %d\n", n, (n + 1) * n / 2);
        System.out.printf("Факториал %d! = %d\n", n, fact);
    }


}
