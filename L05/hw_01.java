package L05;


import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
 * 
 */
public class hw_01 {
    public static void main(String[] args) {
        Map <String, List<String>> phonesBook = new HashMap<>();    

        addRec("Иванов Егор В.", "8-921-334-56-14",phonesBook);
        addRec("Васильев Иван П.", "8-911-444-56-14",phonesBook);
        addRec("Сапожник Сергей А.", "8-931-434-56-14",phonesBook);
        addRec("Васильев Иван П.", "8-921-254-56-14",phonesBook);
        addRec("Жигулев Пётр А.", "8-921-234-54-14",phonesBook);
        addRec("Сергеев Иван З.", "8-947-236-52-14",phonesBook);
        addRec("Жигулев Пётр А.", "8-922-284-51-14",phonesBook);
        addRec("Васильев Иван П.", "8-923-934-59-14",phonesBook);

        System.out.println(findByName("Жигулев Пётр А.",phonesBook));

    }

    static void addRec(String name,String phone, Map <String, List<String>> phonesBook){
        if (phonesBook.containsKey(name)){
            List<String> phoneList = phonesBook.get(name);
            List<String> phoneNewRec = new ArrayList<>(phoneList);
            phoneNewRec.add(phone);
            phonesBook.replace(name, phoneList, phoneNewRec);
        } else {
            List<String> phoneList = new ArrayList<>();
            phoneList.add(phone);
            phonesBook.put(name,phoneList);
        }
    }

    static String findByName(String lastName, Map <String, List<String>> phonesBook){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry: phonesBook.entrySet()){
            String name = entry.getKey();
            List<String> phones = entry.getValue();
            if (name.equalsIgnoreCase((lastName))){
                sb.append(name);
                sb.append(": ");
                sb.append(phones.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }    
}
