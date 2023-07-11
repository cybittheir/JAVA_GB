package L04;

import java.util.LinkedList;

/**
 * Реализуйте очередь с помощью LinkedList со следующими методами: 
 * enqueue() - помещает элемент в конец очереди, 
 * dequeue() - возвращает первый элемент из очереди и удаляет его, 
 * first() - возвращает первый элемент из очереди, не удаляя.
 */

public class hw_02 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("001");
        list.add("002");
        list.add("004");
        list.add("007");
        list.add("027");
        list.add("075");

        System.out.println(list);

        enqueue(list, "099");
        System.out.println(list);

        System.out.println(dequeue(list));
        System.out.println(first(list));
        System.out.println(list);

    }

    static void enqueue (LinkedList<String> list, String el){
        list.addLast(el);
    }

    static String dequeue (LinkedList<String> list){
        return list.pollFirst();
    }

    static String first (LinkedList<String> list){
        return list.peekFirst();
    }

}
