package L06;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Collection;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
 * Создать множество ноутбуков.
 * Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, 
 * отвечающие фильтру. Критерии фильтрации можно хранить в Map.
 * 
 * Например:
 * “Введите цифру, соответствующую необходимому критерию:
 * 1 - ОЗУ
 * 2 - Объем ЖД
 * 3 - Операционная система
 * 4 - Цвет …
 * 
 * Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
 * Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
 * Работу сдать как обычно ссылкой на гит репозиторий
 * 
 * Частые ошибки:
 * 1. Заставляете пользователя вводить все существующие критерии фильтрации
 * 2. Невозможно использовать более одного критерия фильтрации одновременно
 * 3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру, а не всем введенным пользователем
 * 4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков или добавить еще ноутбук, 
 * то программа начинает работать некорректно
 * 
 */

public class hw_01 {

    static Logger i_logger;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        String file_name = "laptops.txt";
        String path = "d:\\#CODES\\Java_GB\\L06\\";
     
        String file_path = path.trim() + file_name.trim();
   
        String[] paramMenu = new String[9];

        paramMenu[0] = "закончить выбор";
        paramMenu[1] = "тип устройства";
        paramMenu[2] = "бренд";
        paramMenu[3] = "размер экрана";
        paramMenu[4] = "семейство процессоров";
        paramMenu[5] = "объем оперативной памяти";
        paramMenu[6] = "объем жесткого диска";
        paramMenu[7] = "тип жесткого диска";
        paramMenu[8] = "операционная система";

        Set<Laptop> set = new HashSet<>();

        createLogger();

        ArrayList<String> inList = readFile(file_path);

        closeLogger();
        
        ArrayList<String> typesList = new ArrayList<>();
        ArrayList<String> brendList = new ArrayList<>();
        ArrayList<String> screenList = new ArrayList<>();
        ArrayList<String> cpuList = new ArrayList<>();
        ArrayList<String> memoryList = new ArrayList<>();
        ArrayList<String> hddSizeList = new ArrayList<>();
        ArrayList<String> diskTypeList = new ArrayList<>();
        ArrayList<String> osList = new ArrayList<>();

        for (String str: inList){
            String[] field = str.trim().split(";",0); 
            Laptop entry = new Laptop(field[0], field[1], field[2], field[3], field[4], field[5], field[6], field[7], field[8]);

            if (!typesList.contains(field[1])){typesList.add(field[1]);}
            if (!brendList.contains(field[0])){brendList.add(field[0]);}
            if (!screenList.contains(field[3])){screenList.add(field[3]);}
            if (!cpuList.contains(field[4])){cpuList.add(field[4]);}
            if (!memoryList.contains(field[5])){memoryList.add(field[5]);}
            if (!hddSizeList.contains(field[7])){hddSizeList.add(field[7]);}
            if (!diskTypeList.contains(field[6])){diskTypeList.add(field[6]);}
            if (!osList.contains(field[8])){osList.add(field[8]);}
    
            set.add(entry);
        }

        Map<String,List<String>> dict = new TreeMap<>();
        dict.put(paramMenu[1], typesList);
        dict.put(paramMenu[2], brendList);
        dict.put(paramMenu[3], screenList);
        dict.put(paramMenu[4], cpuList);
        dict.put(paramMenu[5], memoryList);
        dict.put(paramMenu[6], hddSizeList);
        dict.put(paramMenu[7], diskTypeList);
        dict.put(paramMenu[8], osList);

        System.out.println(getParam(dict,paramMenu));
        in.close();

/*
        for (Laptop entry: set){
            System.out.println(entry);
        }
*/

    }

    static Map<String, String> getParam (Map<String,List<String>> dict,String[] paramMenu){
        Map<String, String> result = new TreeMap<>();
        Boolean notRes = true;
        String s = "";

        while(notRes) {
            System.out.println("=====================================================================");
            System.out.println("Введите номера полей для поиска по одному. Или 0 для окончания выбора");
            System.out.println("=====================================================================");
            
            for (int i = 1; i < paramMenu.length;i++){
                System.out.printf("%d. %s",i,paramMenu[i]);
                if (result.get(paramMenu[i])!=null){
                    System.out.printf(" (%s)",result.get(paramMenu[i]));
                }
                System.out.println();
            }
            
            System.out.println("============================");
            System.out.printf("0. %s\n",paramMenu[0]);
            System.out.println("============================");

            s = getChoice();

            if (s.trim().isEmpty() || s.trim().equals("0")){
                notRes = false;
            } else if (Integer.valueOf(s.trim()) < paramMenu.length){
                String newChoice = getValue(paramMenu[Integer.valueOf(s.trim())],dict);
                result.put(paramMenu[Integer.valueOf(s.trim())], newChoice);
            }
        }

        return result;
    }

    public static String getChoice(){
        System.out.print("Ваш выбор: ");
        String result = in.nextLine().trim();
        return result;
    }

    static String getValue (String choice,Map<String,List<String>> dict){
        String result = "";
        Boolean notRes = true;
        String s = "";
        List<String> values = dict.get(choice);

        while(notRes) {
            
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("Выберите %s или нажмите ENTER для возврата в основное меню\n", choice);
            System.out.println("--------------------------------------------------------------------------------");

            for (int i = 0;i < values.size();i++){
                System.out.printf("%d. %s\n", i+1, values.get(i));
            }
            
            s = getChoice();

            if (s.trim().isEmpty() || s.trim().equals("0")){
                notRes = false;
            } else if (Integer.valueOf(s.trim()) <= values.size()){
                result = values.get(Integer.valueOf(s.trim()) - 1);
                notRes = false;
            }
        }

        return result;
    }

    static List<String> getList (String choice, Map<String,List<String>> dict){
        System.out.println(dict.get(choice));

        return dict.get(choice);
    }

    static ArrayList<String> readFile(String file_path){

        File file = new File(file_path);
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
            i_logger.info("Success: " + file_path + " File was opened.");
        } catch (Exception e) {
            System.out.println("File not opened");
            i_logger.warning("Error: " + file_path +  " File could not be opened.");
            closeLogger();
            System.exit(0);
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
