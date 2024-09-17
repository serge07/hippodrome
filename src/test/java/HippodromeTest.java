import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Annotation not needed, since we are not using Mockito annotations on this test finally.
//@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    // We remove this mock here, because we don't need to mock the list interface.
    // See comments below for further details.

    @Test
    @DisplayName("Constructor Should throw exception when name is null")
    void constructor_shouldThrowException_whenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor Should throw exception when name is empty")
    void constructor_shouldThrowException_whenNameIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("getHorses returns correct horses")
    /*
        This test fails.
        That is because the mocked Horse instances don't pass the equality test.
        Why? Because we are using a mock for the list interface.
        That mock is not properly implementing the methods of the interface (including equals or iterator)
        We don't actually need to mock the list.
        The assignment only asks to prove that the hippodrome class works correctly.
        We can and must use an actual list here, because we want the list to behave like a list.
        We don't want to override or provide specific behaviour to it.
        Therefore, there's no need to use a mock for the list.
     */
    void getHorses_returnsCorrectHorses() {
        List<Horse> horses = new ArrayList<>();
        IntStream.range(0, 30).forEach(i -> horses.add(mock(Horse.class)));
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();
        assertIterableEquals(horses, returnedHorses, "Horses should be equal");
    }

    @Test
    @DisplayName("move method call move on all horses")
    /*
        Even though this test doesn't fail, same here;
        We don't need to mock the list.
        We need though to mock the horses, so we can record the interaction with Horse instances.
     */
    void move_callsMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        IntStream.range(0, 50).forEach(i -> horses.add(mock(Horse.class)));
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses.forEach(horse -> verify(horse, times(1)).move());
    }

    @Test
    @DisplayName("getWinner returns correct winner")
    /*
        This test also fails, because of the same reason as the previous one.
        The list interface shouldn't be mocked.
     */
    void getWinner_returnsCorrectWinner() {
        List<Horse> horses = new ArrayList<>();
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
