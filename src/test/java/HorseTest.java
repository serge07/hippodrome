import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Doesn't harm, but if no Mockito annotations are used, using this extension is not necessary
@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    @DisplayName("Constructor Should throw exception when name is null")
    void constructor_shouldThrowException_whenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10.0, 5.0);
        });

        // Using assertEquals to check that the exception message is correct
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @DisplayName("Constructor Should throw exception when name is blank")
    /*
        The assignment asks this method to be parameterized, like this for example
    */
    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
        // empty string, only space, tab
    void constructor_shouldThrowException_whenNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 10.0, 5.0);
        });

        //using assertEquals to check that the exception message is correct
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor Should throw exception when speed is negative")
    void constructor_shouldThrowException_whenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", -10.0, 5.0);
        });
        //using assertEquals to check that the exception message is correct
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor Should throw exception when distance is negative")
    void constructor_shouldThrowException_whenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", 10.0, -5.0);
        });
        //using assertEquals to check that the exception message is correct
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Testing getName method returns correct name")
    void getName_shouldReturnCorrectName() {
        Horse horse = new Horse("River", 10.0, 5.0);
        assertEquals("River", horse.getName());
    }


    @Test
    @DisplayName("Testing getSpeed method returns correct speed")
    void getSpeed_shouldReturnCorrectSpeed() {
        Horse horse = new Horse("River", 10.0, 5.0);
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    @DisplayName("Testing getDistance method returns correct distance")
    void getDistance_shouldReturnCorrectDistance() {
        Horse horse = new Horse("River", 10.0, 5.0);
        assertEquals(5.0, horse.getDistance());
    }

    // Also, this test related to getDistance is missing
    @Test
    @DisplayName("getDistance should return 0 if a horse is created without a distance value")
    void getDistance_shouldReturn0IfNotSpecified() {
        Horse horse = new Horse("River", 10.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    @DisplayName("Testing move method affects distance correctly")
    void move_shouldAffectDistanceCorrectly() {
        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("River", 10.0, 5.0);
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            // The following assertion you have made here is failing:
            // 1. If the horse speed is 10
            // 2. The random value for moving is 0.5
            // 3. And the initial horse distance is 5
            // Then -> speed * random value (10 * 0.5 = 5), plus initial distance (5) equals 10
            // assertEquals(20.0 + 10.0 * 0.5, horse.getDistance()); --> Wrong expectation
            assertEquals(10.0, horse.getDistance());
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
        }
    }


}




