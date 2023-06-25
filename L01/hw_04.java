/*
 * 4) (дополнительное задание) Задано уравнение вида q + w = e, q, w, e >= 0.
 * Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69.
 * Требуется восстановить выражение до верного равенства.
 * Предложить хотя бы одно решение или сообщить, что его нет.
 */

package L01;

import java.util.Random;

public class hw_04 {

    public static void main(String[] args) {
        
        int[]nums = random (2,1,10);
        int[]summ = random (1,1,100);


        System.out.printf("Уравнение: %d? + ?%d = %d\n",nums[0],nums[1],summ[0]);

        boolean flag = false;

        for (int i = 0; i <10;i++){
            for (int j = 0; j<10;j++){
                if (nums[0]*10 + i + 10*j + nums[1] == summ[0]){
                    System.out.printf("Решение: %d%d + %d%d = %d\n",nums[0],i,j,nums[1],summ[0]);
                    flag = true;
                }
            }
        }

        if (!flag) {
            System.out.printf("Сгенерированное уравнение решения не имеет");
        }

    }

    static int[] random(int num, int min, int max) {
        int[] arr = new int[num];
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            arr[i] = random.nextInt(min, max);
        }
        return arr;
    }

    
}
