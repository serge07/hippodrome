import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainTest {

    @Test
    @DisplayName("Method shoudl not run more than 20 Seconds")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Disabled as this test is taking too long!! please test this manually")
    void checkMoveMethod_shouldNotRunMoreThan20Seconds() throws Exception{

        List<Horse> horses = List.of(
                new Horse("Houdini", 2.4),
                new Horse("Comanche", 2.5),
                new Horse("Ranger", 2.6),
                new Horse("Elvis", 2.7),
                new Horse("Yoda", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Shelby", 3)
        );

        Hippodrome hippodrome =spy(new Hippodrome(horses));
        for (int i=0;i<100;i++) {
            hippodrome.move();
            Main.watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        verify(hippodrome, times(1)).move();
        String winnerName = hippodrome.getWinner().getName();
        System.out.println();
        System.out.println("The winner is " + winnerName + "!");



    }


}
