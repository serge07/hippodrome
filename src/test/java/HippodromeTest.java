import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {



    @Mock
    List<Horse> horses;

    @Test
    @DisplayName("Constructor Should throw exception when name is null")
    void constructor_shouldThrowException_whenNameIsNull() {
        IllegalArgumentException  exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor Should throw exception when name is empty")
    void constructor_shouldThrowException_whenNameIsEmpty() {
        IllegalArgumentException  exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("getHosrses returns correct horses")
    void getHorses_returnsCorrectHorses() {
        IntStream.range(0, 30).forEach(i -> horses.add(mock(Horse.class)));
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();
        assertIterableEquals(horses, returnedHorses, "Horses should be equal");

    }

    @Test
    @DisplayName("move method call move on all horses")
    void move_callsMoveOnAllHorses() {
        IntStream.range(0, 50).forEach(i -> horses.add(mock(Horse.class)));
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses.forEach(horse -> verify(horse, times(1)).move());
    }

    @Test
    @DisplayName("getWinner returns correct winner")
    void getWinner_returnsCorrectWinner() {
        horses.add(mock(Horse.class));
        horses.add(mock(Horse.class));
        horses.add(mock(Horse.class));
        horses.add(mock(Horse.class));

        when(horses.get(0).getDistance()).thenReturn(10.0);
        when(horses.get(1).getDistance()).thenReturn(20.0);
        when(horses.get(2).getDistance()).thenReturn(30.0);
        when(horses.get(3).getDistance()).thenReturn(40.0);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(3), hippodrome.getWinner(), "Winner should be correct");
    }

}
