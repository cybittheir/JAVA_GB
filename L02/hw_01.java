/* 
 * Получить исходную json строку из файла, используя FileReader или Scanner
 * Дана json строка вида:
 * [{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
 * "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
 * "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]
 * 
 * Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
 * Студент Иванов получил 5 по предмету Математика.
 * Студент Петрова получил 4 по предмету Информатика.
 * Студент Краснов получил 5 по предмету Физика.
 * 
 * Используйте StringBuilder для подготовки ответа. Далее создайте метод, который запишет
 * результат работы в файл. Обработайте исключения и запишите ошибки в лог файл с помощью Logger.
 *
 * Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
 * 
*/

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class hw_01 {

    static Logger i_logger;

    public static void main(String[] args) {

        String file_name = "table.json";
        String path = "d:\\#CODES\\Java_GB\\L02\\";
        String file_out = "table.txt";
     
        String file_path = path.trim() + file_name.trim();
        String result_path = path.trim() + file_out.trim();
     

        createLogger();

        String input_str = readFile(file_path);

        String [] basic = advSplit(input_str, "[]");

        String[][] nextArr = new String[basic.length][];

        for (int i = 0; i < basic.length; i++){

            nextArr[i] = advSplit(basic[i], "{}");
            
            for (int j = 0; j < nextArr[i].length;j++){
                
                if (nextArr[i][j] != null && nextArr[i][j].trim() != ""){
                
                    String [] someArr = nextArr[i][j].trim().split(",", 0);

                    String lineOut = parsePerson(someArr);

                    System.out.println(lineOut);

                    writeToFile(result_path, lineOut);

                }
                

            }
            
        }

        closeLogger();

    }           

    static void writeToFile(String full_path, String text){

        try (FileWriter write_to = new FileWriter(full_path,true)){

            write_to.write(text);
            write_to.write("\n");
            write_to.flush();
            write_to.close();

        } catch (Exception e) {
            // TODO: handle exception
            i_logger.warning("Unable to open " + full_path + " for record");
            closeLogger();
            System.exit(0);
        }

    }

    static String parsePerson(String [] someArr){

        String [] tabArr = new String[someArr.length];
                    
        for (int k = 0; k < someArr.length;k++){

            if (someArr[k] != "" && someArr[k].trim() != "") {

                String [] paramArr = someArr[k].trim().split(":", 0);

                switch (paramArr[0].replaceAll("\\\\\"", "")){
                    case "фамилия":
                        tabArr[0] = paramArr[1].replaceAll("\\\\\"", "");
                    case "оценка":
                        tabArr[1] = paramArr[1].replaceAll("\\\\\"", "");
                    case "предмет":
                        tabArr[2] = paramArr[1].replaceAll("\\\\\"", "");
                    default:

                }

            }

        }

        StringBuilder result = new StringBuilder();

        result.append("Студент(ка) ").append(tabArr[0]).append(" получил(а) оценку ").append(tabArr[1]).append(" по предмету ").append(tabArr[2]).append(".");

        return result.toString();

    }
    
    static void showResult(String[][] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.printf("%d. Студент(ка) %s получил(а) %s по предмету %s.\n",i, arr[i][0],arr[i][1],arr[i][2]);
        }
    }

    static String readFile(String file_path){

        File file = new File(file_path);
        StringBuilder result = new StringBuilder();

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()) {
                result.append(scanner.nextLine().trim());
            }
            i_logger.info("Success: " + file_path + " File was opened.");
        } catch (Exception e) {
            System.out.println("File not opened");
            i_logger.warning("Error: " + file_path +  " File could not be opened.");
            closeLogger();
            System.exit(0);
        }

        return result.toString();

    }

    static String[] advSplit(String line, String match){
        
        String[] sep = match.trim().split("", 0);
        String[] step1 = line.trim().split("\\" + sep[0].trim(), 0);
        String[] result = new String[step1.length - 1];
        
        int k = 0;

        for (int i = 0; i < step1.length;i++){

            if (step1[i] != null && step1[i].trim() != "") {

                String[] step2 = step1[i].trim().split(sep[1].trim(), 0);

                if (step2[0] != null && step2[0].trim() != ""){

                    result[k] = step2[0];
                    k++;

                }
            }
        }
        
        return result;
    }

    static void createLogger(){

        i_logger = Logger.getAnonymousLogger() ;
        FileHandler log_handler = null;
        
        try {
            log_handler = new FileHandler ("jobs.log",true);
            i_logger.addHandler(log_handler);
        } catch (Exception e){
            e.printStackTrace();
        }

        SimpleFormatter log_formatter = new SimpleFormatter();
        if (log_handler != null) {
            log_handler.setFormatter(log_formatter);
        }
    }

    static void closeLogger(){
        for (Handler log_handler: i_logger.getHandlers()){
            log_handler.close();
        }
    }

}
