package L03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* 
1. Пусть дан произвольный список целых чисел, удалить из него четные числа
*/

public class hw_01 {
    public static void main(String[] args) {
        List<Integer> list = generateList(0, 20, 10);
        System.out.println(list);
        removeEven(list);
        System.out.println(list);
    }

    static ArrayList<Integer> generateList(int min, int max, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        Random newRandom = new Random();
        for (int i = 0; i < size; i++) {
            list.add(newRandom.nextInt(min, max));
        }
        return list;
    }

    static void removeEven(List<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }
}
