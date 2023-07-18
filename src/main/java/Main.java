import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Horse> horses = List.of(
                new Horse("Houdini", 2.4),
                new Horse("Comanche", 2.5),
                new Horse("Ranger", 2.6),
                new Horse("Elvis", 2.7),
                new Horse("Yoda", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Shelby", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println("The winner is " + winnerName + "!");
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
