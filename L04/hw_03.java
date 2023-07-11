package L04;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Найдите сумму всех элементов LinkedList, сохраняя все элементы в списке. 
 * Используйте итератор
 */
public class hw_03 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(7);
        list.add(27);
        list.add(75);

        System.out.printf("%s == %d\n",list, summ(list));
        System.out.printf("%s\n",list);

    }

    static Integer summ (LinkedList<Integer> list){
        int result = 0;
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            if (object instanceof Integer){
                result += Integer.valueOf(object.toString());
            }
        }
        return result;
    }
    
}
