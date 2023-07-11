package L05;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * 
 * *На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
 * 
 */

public class hw_04 {

    public static void main(String[] args) {

        String y0[] = new String[8];
        y0[0] = "a";
        y0[1] = "b";
        y0[2] = "c";
        y0[3] = "d";
        y0[4] = "e";
        y0[5] = "f";
        y0[6] = "g";
        y0[7] = "h";

        LinkedList<List<Integer>> clearTable = table();
//        System.out.println(clearTable.toString()); // для отладки
        boolean fin = true;
        int r = 1;
        while (fin){
            LinkedList<List<Integer>> roundTable = new LinkedList<>(clearTable);
            System.out.printf("\n======== attempt # %d ========\n",r);
            for (int i = 1; i < 9;i++){
                int newPoint = generatePos(roundTable.size());
                System.out.printf("point: %s => ",roundTable.get(newPoint));
                System.out.printf("| %s%d | ",y0[roundTable.get(newPoint).get(0)],roundTable.get(newPoint).get(1) + 1);
                roundTable.removeAll(crosses(roundTable.get(newPoint)));
                System.out.printf("%d(%d):: \n", i, roundTable.size());
//                System.out.println(roundTable.toString()); // для отладки
                if (roundTable.size() == 0 && i < 8){
                    break;
                } else if(i == 8){
                    System.out.println("\n --=== !!! Bingo !!! ===--");
                    fin = false;
                    break;
                }
            }
            r++;
        }
    }

    static Integer generatePos(int index) {
        Random newRandom = new Random();
        int point = newRandom.nextInt(0, index);
        return point;
    }

    static LinkedList<List<Integer>> table(){
        LinkedList<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j);
                result.add(list);
            }
        }

        return result;
    }

    static LinkedList<List<Integer>> crosses(List<Integer> list){
        LinkedList<List<Integer>> result = new LinkedList<>();
        int x = list.get(0);
        int y = list.get(1);

        for (int i = 0; i < 8; i++){
            List<Integer> crossList = new ArrayList<>();
            crossList.add(x);
            crossList.add(i);
            result.add(crossList);
            List<Integer> crossList2 = new ArrayList<>();
            crossList2.add(i);
            crossList2.add(y);
            result.add(crossList2);
        }
        int x1,x2,y1,y2;

        if (x < y) {
            x1 = 0;
            y1 = y - x;
            if (x + y < 8) {
                x2 = x + y;
                y2 = 0;
            } else {
                x2 = 7;
                y2 = x + y - 7;
            }
        } else if (x > y){
            x1 = x - y;
            y1 = 0;
            if (x + y < 8) {
                x2 = x + y;
                y2 = 0;
            } else {
                x2 = 7;
                y2 = x + y - 7;
            }
        } else {
            x1 = 0;
            y1 = 0;
            if (x > 3) {
                x2 = 7;
                y2 = (2 * x) - 7;
            } else {
                x2 = x * 2;
                y2 = 0;
            }
        }

        while (x1 < 8 && y1 < 8){

            List<Integer> crossList = new ArrayList<>();
            crossList.add(x1);
            crossList.add(y1);
            if (!result.contains(crossList)) {
                result.add(crossList);
            }
            x1 += 1;
            y1 += 1;
        
        }

        while (x2 >=0 && y2 < 8){

            List<Integer> crossList = new ArrayList<>();
            crossList.add(x2);
            crossList.add(y2);
            if (!result.contains(crossList)) {
                result.add(crossList);
            }
            x2 -= 1;
            y2 += 1;
        
        }

        return result;
    }
}
