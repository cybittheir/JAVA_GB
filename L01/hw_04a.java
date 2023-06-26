package L01;

/*
 * 4) (дополнительное задание) Задано уравнение вида q + w = e, q, w, e >= 0.
 * Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69.
 * Требуется восстановить выражение до верного равенства.
 * Предложить хотя бы одно решение или сообщить, что его нет.
 */

import java.util.Random;

public class hw_04a {

    public static void main(String[] args) {

        int[]nums = randoms (2,1,10000);

        String[] arr1 = quest(nums[0]);
        String[] arr2 = quest(nums[1]);
        int max_exp = arr1.length;
        int min = 1;

        if(arr1[0] != "?") {
            int dig1 = Integer.valueOf(arr1[0]);
            min = (int) Math.pow(10,max_exp-1) * dig1;
        }

        if (arr2.length > max_exp) {
            max_exp = arr2.length;
        }

        if (arr2[0] != "?") {
            int dig2= Integer.valueOf(arr2[0]);
        
            if (arr2.length == arr1.length){
                if (min < dig2){
                    min = (int) Math.pow(10,max_exp-1) * dig2;
                }
            }
        }

        int max = (int) Math.pow(10,max_exp);

        System.out.printf("min: %d, max: %d\n", min , max);

        int[]summ = randoms (1,min,max);

        String num_str1 = String.join("",arr1);
        String num_str2 = String.join("",arr2);

        int summa = summ[0];

        System.out.printf(">> %s + %s = %d", num_str1, num_str2, summa);

        compare(arr1, arr2, summa);
    
    }

    static void compare(String[] arr1, String []arr2, int summ){
        
        String [] res = Integer.toString(summ).split("",0);

        int diff = arr1.length - arr2.length;
        int diff_res = res.length - arr1.length;
        int one = 0;
        for (int i = res.length-1; i > -1; i--){

            int[]test = check(arr1[i-diff_res], arr2[i-diff_res-diff], res[i],one);

            if (test[2]>9){one = 1;}
            else {one = 0;}
            
            if (test[0] < 0){
                System.out.printf("\n:%d: Нет решения\n",i);
                break;
            } else {
                System.out.printf("\n:%d: %d + %d = %d\n",i,test[0],test[1],test[2]);
                if (i == 0){
                    System.out.println("\n--=== !!! Bingo !!! ===--");
                }
            }
        }

    }

    static int[] check(String val1,String val2,String res, int one){

        int[] result=new int [3];
        int ten = 0;
        
        if (val1 != "?" && val2 != "?"){
            int dig1 = Integer.valueOf(val1);
            int dig2 = Integer.valueOf(val2);
            int summ = dig1 + dig2 + one - Integer.valueOf(res);
            
            if (summ == 10 || summ == 0){
                result[0] = dig1;
                result[1] = dig2;
                result[2] = dig1 + dig2;
            } else {
                result[0] = -1;
                result[1] = -1;
                result[2] = -1;
            }

            return result;

        } else if (val2 != "?") {

            if ((Integer.valueOf(res) - one) < Integer.valueOf(val2)) {ten = 10;}
            result[0] = ten + Integer.valueOf(res) - one - Integer.valueOf(val2);
            result[1] = Integer.valueOf(val2);
            result[2] = result[0] + result[1] + one;

            return result;

        } else if (val1 != "?") {

            if ((Integer.valueOf(res) - one) < Integer.valueOf(val1)) {ten = 10;}
            result[0] = Integer.valueOf(val1);
            result[1] = ten + Integer.valueOf(res) - one - Integer.valueOf(val1);
            result[2] = result[0] + result[1] + one;

            return result;
        }

        result[0] = (Integer.valueOf(res) - one)/2;
        result[1] = Integer.valueOf(res) - one - result[0];
        result[2] = result[0] + result[1] + one;

        return result;
    }

    static int[] randoms(int num, int min, int max) {
            int[] arr = new int[num];
            Random randoms = new Random();

            for (int i = 0; i < num; i++) {
                arr[i] = randoms.nextInt(min, max);
            }
            return arr;
        }

        static int randQ() {
            Random rand_q = new Random();
            return rand_q.nextInt(0, 2);
            
        }

        static String[] quest(int num){
            String[] number = Integer.toString(num).split("",0);
            int j = 1;
            for (int i = 0;i < number.length; i++) {
                if (j <= number.length/2 && randQ() == 0){
                    number[i] = "?";
                    j++;
                }
            }

            return number;
        }

        static String implode(String[] array, String separator){
            String result = "1";
            return result;
        }

}

