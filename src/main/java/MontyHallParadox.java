import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.random.RandomDataGenerator;

public class MontyHallParadox {

    private static final int COUNT_SIMULATIONS = 10000;

    public static void main(String[] args) {
        Map<Integer, Boolean> results = simulateMontyHallParadox(COUNT_SIMULATIONS);
        printStatistics(results);
    }

    private static Map<Integer, Boolean> simulateMontyHallParadox(int simulations) {
        Map<Integer, Boolean> resultMap = new HashMap<>();
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        int positiveResults = 0;
        int negativeResults = 0;

        for (int i = 1; i <= simulations; i++) {
            int chosenDoor = randomDataGenerator.nextInt(1, 3);
            int carDoor = randomDataGenerator.nextInt(1, 3);

            int openedDoor;
            do {
                openedDoor = randomDataGenerator.nextInt(1, 3);
            } while (openedDoor == chosenDoor || openedDoor == carDoor);

            boolean switchDoor = randomDataGenerator.getRandomGenerator().nextBoolean();

            boolean win = switchDoor ? chosenDoor != carDoor : chosenDoor == carDoor;

            resultMap.put(i, win);

            if (win) {
                positiveResults++;
            } else {
                negativeResults++;
            }
        }

        System.out.println("Моделирование парадокса Монти Холла");

        System.out.println("Позитивных результатов: " + positiveResults);
        System.out.println("Негативных результатов: " + negativeResults);

        return resultMap;
    }

    private static void printStatistics(Map<Integer, Boolean> results) {
        long positiveCount = results.values().stream().filter(Boolean::booleanValue).count();
        long negativeCount = results.size() - positiveCount;

        double positivePercentage = (double) positiveCount / results.size() * 100;
        double negativePercentage = (double) negativeCount / results.size() * 100;

        System.out.println("Количество циклов: " + results.size());
        System.out.println("Процент позитивных результатов: " + positivePercentage + "%)");
        System.out.println("Процент негативных результатов: " + negativePercentage + "%)");
    }
}

