package L04;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список. 
 * Постараться не обращаться к листу по индексам.
 */

public class hw_01 {
    public static void main(String[] args) {
    
        LinkedList<String> list = new LinkedList<>();
        list.add("001");
        list.add("002");
        list.add("004");
        list.add("007");
        list.add("027");
        list.add("075");

        System.out.println(list);

        list = reverseList(list);
        System.out.println(list);

    }    

    static LinkedList<String> reverseList(LinkedList<String> list){
        LinkedList<String> result = new LinkedList<>();
        while (!list.isEmpty()){
            result.add(list.pollLast());
        }
        return result;
    }
}
