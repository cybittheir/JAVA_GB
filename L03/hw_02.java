package L03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*
2. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее арифметическое из этого списка.
Collections.max()
*/

public class hw_02 {
    public static void main(String[] args) {
        ArrayList <Integer> list = generateList(0, 20, 10);
        System.out.println(list);
        printSel(list, getChoose());
    }

    static ArrayList <Integer> generateList (int min, int max, int size){
            ArrayList<Integer> list = new ArrayList<>();
            Random newRandom = new Random();
            for (int i = 0; i < size; i++){
                list.add(newRandom.nextInt(min,max));
            }
            return list;
    }
    
    static void printSel(ArrayList<Integer> list, String choose ){
        int summ = 0;

        for (int i = list.size()-1; i >= 0; i--) {
            summ += list.get(i);
        }

        switch (choose){
            case "min":
                System.out.printf("min = %d\n",Collections.min(list));
                break;
            case "max":
                System.out.printf("max = %d\n",Collections.max(list));
                break;
            case "avg":
                System.out.printf("average = %d\n",summ/list.size());
                break;
            default:
                System.out.printf("min = %d\n",Collections.min(list));
                System.out.printf("max = %d\n",Collections.max(list));
                System.out.printf("average = %d\n",summ/list.size());
                break;
        }
    
    }

    static String getChoose(){
        Scanner in = new Scanner(System.in);
        Boolean notRes = true;
        String s = "";
        while(notRes) {
            System.out.print("Enter min/max/avg/all as your selection: ");
            s = in.nextLine().trim();
            if (s.trim().equals("min") || s.trim().equals("max") || s.trim().equals("avg") || s.trim().equals("all") ){
                notRes = false;
            }
        }
        in.close();
        return s;
    }
}
