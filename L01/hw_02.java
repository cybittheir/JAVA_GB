
// 2) Вывести все простые числа от 1 до 1000

package L01;

public class hw_02 {
    public static void main(String[] args) {
        int [] res = new int [1001];
        for (int i = 1; i <= 1000; i++) {
            res[i] = 1;
            for (int j = i/2; j > 1 ; j--){
                if (i != j && i % j == 0){
                    res[i] = 0;
                    continue;
                }
            }
            
            if (i == 1) {
                System.out.print(i);
            }
             else if (res[i] > 0) {
                System.out.print(", " + i);
            }
        }
    }    
}
