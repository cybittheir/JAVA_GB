package L05;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * Пусть дан список сотрудников: 
 * 
 * Иван Иванов, 
 * Светлана Петрова, 
 * Кристина Белова, 
 * Анна Мусина, 
 * Анна Крутова, 
 * Иван Юрин, 
 * Петр Лыков, 
 * Павел Чернов, 
 * Петр Чернышов, 
 * Мария Федорова, 
 * Марина Светлова, 
 * Мария Савина, 
 * Мария Рыкова, 
 * Марина Лугова, 
 * Анна Владимирова, 
 * Иван Мечников, 
 * Петр Петин, 
 * Иван Ежов. 
 * 
 * Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений. 
 * Отсортировать по убыванию популярности. Для сортировки использовать TreeMap.
 */

public class hw_02 {

    static Logger i_logger;
    public static void main(String[] args) {
        
        String file_name = "list.txt";
        String path = "d:\\#CODES\\Java_GB\\L05\\";
     
        String file_path = path.trim() + file_name.trim();
   
        createLogger();

        String input_str = readFile(file_path);

        String[] persons = input_str.trim().split(",",0);
       
        Map<String, List<String>> table = new HashMap<>();

        for (String name: persons){

            String[] names = name.trim().split(" ");

            if(table.containsKey(names[0])){
                List<String> person = table.get(names[0]);
                person.add(name);
            } else {
                List<String> person = new LinkedList<>();
                person.add(name);
                table.put(names[0],person);
            }

        }

        System.out.println("\n=== Prepared: Sorted by First Name ===");
        System.out.println(table);

        Map<String, List<String>> result = new TreeMap<>(Comparator.reverseOrder());
        
        Iterator<Map.Entry<String, List<String>>> entries = table.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();

            result.put(entry.getValue().size() + ": " + entry.getKey(), entry.getValue());
        }

        System.out.println("\n=== Final: Sorted by numbers of repetitions ===");

        System.out.println(result);

        closeLogger();

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
