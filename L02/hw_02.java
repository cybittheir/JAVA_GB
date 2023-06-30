package L02;

import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/* 
 * Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
*/

public class hw_02 {

    static Logger i_logger;
    public static void main(String[] args) {
        int[] no_sort_arr = generator(1, 20, 10);

        createLogger();

        i_logger.info("Incomming: " + intArrToStr(":", no_sort_arr));

        int[] sorted_arr = boobleSort(no_sort_arr);

        i_logger.info("BoobleSort result: " + intArrToStr(":", sorted_arr));

        closeLogger();
    }
    
    static int [] generator (int min, int max, int size){
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(min, max);
        }
        return arr;

    }

    static int [] boobleSort(int[]arr){
        int tmp = 0;
        String result;

        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr [j + 1]){
                    tmp = arr [j + 1];
                    arr [j + 1] = arr[j];
                    arr[j] = tmp;
                    result = "Mоved (" + Integer.toString(i) + "," + Integer.toString(j) + ")  " + Integer.toString(arr[j+1]) + " <--> " + Integer.toString(arr[j]);
                } else {
                    result = "Skipped (" + Integer.toString(i) + "," + Integer.toString(j) + ")  " + Integer.toString(arr[j]) + " .... " + Integer.toString(arr[j+1]);
                }
                i_logger.info(result);
            }
        }
        return arr;
    }

    static String intArrToStr(String delim, int[]arr){
        String result = "";
        for (int i = 0; i < arr.length; i ++){
            if (i ==0){
                result += Integer.toString(arr[i]);
            } else {
                result += delim + Integer.toString(arr[i]);
            }
        }
        return result;
    }

    static void createLogger(){
        i_logger = Logger.getAnonymousLogger();
        FileHandler file_handler = null;
        try {
            file_handler = new FileHandler ("jobs.log",true);
            i_logger.addHandler(file_handler);
        } catch (Exception e){
            e.printStackTrace();
        }
        SimpleFormatter file_formatter = new SimpleFormatter();
        file_handler.setFormatter(file_formatter);
    }

    static void closeLogger(){
        for (Handler log_handler: i_logger.getHandlers()){
            log_handler.close();
        }
    }

}
