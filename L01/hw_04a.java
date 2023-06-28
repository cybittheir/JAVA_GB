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

        if(arr1[0].charAt(0) != '?') {
            int dig1 = Integer.valueOf(arr1[0]);
            min = (int) Math.pow(10,max_exp-1) * dig1;
        }

        if (arr2.length > max_exp) {
            max_exp = arr2.length;
        }

        if (arr2[0].charAt(0) != '?') {
            int dig2 = Integer.valueOf(arr2[0]);
        
            if (arr2.length == arr1.length){
                if (min < dig2){
                    min = (int) Math.pow(10,max_exp-1) * dig2;
                }
            }
        }

        int max = (int) Math.pow(10,max_exp);

//        System.out.printf("min: %d, max: %d\n", min , max);

        int[]summ = randoms (1,min,max);
////////////////////
        String num_str1 = String.join("",arr1);
        String num_str2 = String.join("",arr2);
//        String num_str1 = "3???08";
//        String num_str2 = "4?17??";
        arr1 = num_str1.split("", 0);
        arr2 = num_str2.split("", 0);

////////////////////////
        int summa = summ[0];
//        int summa = 850263;
        System.out.printf(">> %s + %s = %d", num_str1, num_str2, summa);
        String [] res = Integer.toString(summa).split("",0);
        boolean fail = false;

        int diff_res1 = res.length - arr1.length;

        if (diff_res1 > 0){
            arr1 = (multi("0",diff_res1) + implode(arr1, "")).split("",0);
        } else if (diff_res1 < 0 && arr1[0].charAt(0) != '?') {
            fail = true;
        }

        int diff_res2 = res.length - arr2.length;

        if (diff_res2 > 0){
            arr2 = (multi("0",diff_res2) + implode(arr2, "")).split("",0);
        } else if (diff_res2 < 0 && arr2[0].charAt(0) != '?') {
            fail = true;
        }

        int[] tmp = check(arr1[0], arr2[0], res[0],0);
        int[] tmp1 = check(arr1[0], arr2[0], res[0],1);

        if ((tmp[2] > Integer.valueOf(res[0]) && tmp1[2] > Integer.valueOf(res[0])) || (tmp[2]==-1 && tmp1[2]>9)){
            fail = true;
        } else if (tmp[2] < Integer.valueOf(res[0]) && tmp1[2] < Integer.valueOf(res[0]) && Integer.valueOf(res[0]) - tmp[2] > 1 && Integer.valueOf(res[0]) - tmp1[2] > 1) {
            fail = true;
        }

        if (!fail) {
            compare(arr1, arr2, res);
        } else {
            System.out.println("\n:: Нет решения");
        }
    
    }

    static void compare(String[] arr1, String []arr2, String[] res){
        
        int one = 0;

        String[] get_res = new String[2];

        int [] test = new int[4];
        int [] next_test = new int[4];

        for (int i = res.length-1; i > -1; i--){

            test = check(arr1[i], arr2[i], res[i], one);

            if (get_res[1] == "-10") {
                break;
            } else {

                if (test[2] > 9){one = 1;}
                else {one = 0;}

                if (test[3] == 1 && one == 0 && i > 0) {
                    next_test = check(arr1[i-1], arr2[i-1], res[i-1], one);

                    if (next_test[0] == -1){
                        one = 1;
                        next_test = check(arr1[i-1], arr2[i-1], res[i-1], one);

                        if (next_test[0] != -1){
                            test[0] += 5;
                            test[1] += 5;
                        }
                    }
                }
            }

            get_res = ChooseRes(i, test);

            System.out.println(get_res[0]);

        }
    }

    static String[] ChooseRes(int num, int[] test){

        String[] result = new String[2];
        result[1] = "";

        if (test[0] < 0){
            result[0] = String.format("\n:%d: Нет решения",num);
            result[1] = "-10";
        } else {
            result[0] = String.format("\n:%d: %d + %d = %d",num,test[0],test[1],test[2]);

            if (num == 0){
                if (test[2] > 9){
                    result[0] += String.format("\n:%d: Нет решения",num);
                } else {
                    result[0] += String.format("\n--=== !!! Bingo !!! ===--");
                }
            }
        }
        return result;
    }

    static int[] check(String val1, String val2, String res, int one){

        int[] result=new int [4];
        int ten = 0;
        
//        System.out.println("\n" + val1.trim() + "/" + val2.trim()  + "/" + res + "/" + one);

        if (val1.trim().charAt(0) != '?' && val2.trim().charAt(0) != '?'){
            int dig1 = Integer.valueOf(val1);
            int dig2 = Integer.valueOf(val2);
            int summ = dig1 + dig2 + one - Integer.valueOf(res);
            
            if (summ == 10 || summ == 0){
                result[0] = dig1;
                result[1] = dig2;
                result[2] = dig1 + dig2;
                result[3] = 0;
            } else {
                result[0] = -1;
                result[1] = -1;
                result[2] = dig1 + dig2;
                result[3] = -1;
            }

            return result;

        } else if (val2.trim().charAt(0) != '?') {

            if ((Integer.valueOf(res) - one) < Integer.valueOf(val2)) {ten = 10;}
            result[0] = ten + Integer.valueOf(res) - one - Integer.valueOf(val2);
            result[1] = Integer.valueOf(val2);
            result[2] = result[0] + result[1] + one;
            result[3] = 0;

            return result;

        } else if (val1.trim().charAt(0) != '?') {

            if ((Integer.valueOf(res) - one) < Integer.valueOf(val1)) {ten = 10;}
            result[0] = Integer.valueOf(val1);
            result[1] = ten + Integer.valueOf(res) - one - Integer.valueOf(val1);
            result[2] = result[0] + result[1] + one;
            result[3] = 0;

            return result;
        }

        result[0] = (Integer.valueOf(res) - one)/2;
        result[1] = Integer.valueOf(res) - one - result[0];
        result[2] = result[0] + result[1] + one;
        result[3] = 1;

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

            String result = array[0];
            for (int i = 1; i < array.length; i++){
                result += separator + array[i];
            }

            return result;
        }

        static String multi(String str, int mult){

            String result = "";
            for (int i = 0; i < mult; i++){
                result += str;
            }

            return result;
        }
}

