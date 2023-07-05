package L03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/*
4.* Сортировка слиянием
*/

public class hw_04 {
    public static void main(String[] args) {
        List<Integer> list = generateList(0, 20, 10);
        System.out.println(list);
        System.out.println("=====================================");
        System.out.println(mergeSort(list));

    }

    static ArrayList<Integer> generateList(int min, int max, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        Random newRandom = new Random();
        for (int i = 0; i < size; i++) {
            list.add(newRandom.nextInt(min, max));
        }
        return list;
    }

    static List<Integer> mergeSort(List<Integer> list) {
        List<Integer> tmpResult = new ArrayList<>();
        if (list.size() == 1) {
            return list;
        }
        List<Integer> tmpLeft = mergeSort(list.subList(0, (list.size() / 2)));
        List<Integer> tmpRight = mergeSort(list.subList(list.size() / 2, list.size()));
        tmpResult = mergeArrs(tmpLeft, tmpRight);
        return tmpResult;
    }

    static List<Integer> mergeArrs(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int ir = 0;
        int il = 0;
        while (il < left.size() && ir < right.size()) {
            if (left.get(il) <= right.get(ir)) {
                result.add(left.get(il));
                il++;
            } else {
                result.add(right.get(ir));
                ir++;
            }
        }
        while (il < left.size()) {
            result.add(left.get(il));
            il++;
        }
        while (ir < right.size()) {
            result.add(right.get(ir));
            ir++;
        }
        return result;
    }
}
