package L03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
3. Заполнить список названиями планет Солнечной системы в произвольном порядке с повторениями.
Вывести название каждой планеты и количество его повторений в списке.
Collections.frequency(list, 2)
Меркурий, Венера, Земля, Марс, Юпитер, Сатурн, Уран, Нептун, Плутон.
*/

public class hw_03 {
    public static void main(String[] args) {
        List <String> planets = new ArrayList<>();
        planets.add("Меркурий");
        planets.add("Венера");
        planets.add("Земля");
        planets.add("Марс");
        planets.add("Юпитер");
        planets.add("Сатурн");
        planets.add("Уран");
        planets.add("Нептун");
        planets.add("Плутон");

        System.out.println(planets);
        List <String> randomPlanets = randomList (planets, 30);
        System.out.println(randomPlanets);
        printResults(planets,randomPlanets);        
    }

    static List<String>  randomList(List<String> planets, int number){
        List <String> list = new ArrayList<>();
        Random newRandom = new Random();
        for (int i = 0; i < number; i++){
            list.add(planets.get(newRandom.nextInt(0,planets.size())));
        }
        return list;
    }

    static void printResults (List<String> values, List<String> list){
        for (String val: values){
                System.out.printf("%s => %d\n",val, Collections.frequency(list, val));
        }
    }    
}
