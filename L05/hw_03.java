package L05;

import java.util.Random;
import java.util.Arrays;

/**
 * 
 * *Реализовать алгоритм пирамидальной сортировки (HeapSort)
 * 
*/
public class hw_03 {
    public static void main(String[] args) {
    
        Integer [] newArray = genArray(0,99,14);
        printResult(newArray);
        printResult(heapSort(newArray));

    }

    static Integer[] heapSort (Integer[] inArray){

        int k = 0;
        Integer[]result = new Integer[inArray.length];
        Integer[]job = Arrays.copyOf(inArray, inArray.length);
        while (k < inArray.length){
            int i = job.length/2 - 1;
            while (i >= 0 ){
                for (int root = i; root > i/2 - 1;root--){
                    job = triCompare(job, root);
                }
                result[k]= job[0];
                i = i/2 - 1;
            }
            k++;
            job = Arrays.copyOfRange(job, 1, job.length);

            if (k == inArray.length -1){
                result[k]= job[0];
            }

        }
        return result;
    }

    static Integer[] triCompare(Integer[] inArray,int root){

        int useSlave;
        int tmp;
        int slave1  = root * 2 + 1;
        int slave2  = root * 2 + 2;

        if (slave1 >= inArray.length) {
            return inArray;
        } else if (slave2 >= inArray.length){
            slave2 = slave1;
        }

//        System.out.printf(" %d (%d/%d) %d|%d|%d <==> ",inArray[root],inArray[slave1],inArray[slave2],slave1,root,slave2);
        
        if (inArray[slave2] > inArray[slave1]){
            useSlave = slave1;
        } else {
            useSlave = slave2;
        }
        
        if (inArray[root] > inArray[useSlave]){
            tmp = inArray[useSlave];
            inArray[useSlave] = inArray[root];
            inArray[root] = tmp;
        } else {
            if (useSlave == slave2){
                tmp = inArray[slave2];
                inArray[slave2] = inArray[slave1];
                inArray[slave1] = tmp;
            }
        }
//        System.out.printf(" %d [%d]\n",inArray[root],root);

        return inArray;
    }

    static Integer[] genArray (int min, int max, int size){

        Integer[] result = new Integer[size];
        Random newRandom = new Random();
        for (int i = 0; i < size; i++){
            result[i] = newRandom.nextInt(min, max);
        }
        return result;

    }

    static void printResult(Integer[] inArray){
        System.out.printf("%d",inArray[0]);
        for (int i = 1; i < inArray.length; i++){
            System.out.printf(", %d",inArray[i]);
        }
        System.out.println();
    }

}
