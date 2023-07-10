import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    void constructorWithNullArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void сonstructorWithNullMessage() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null),
                "Horses cannot be null.");
        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void constructorWithEmptyList() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void constructorWithEmptyListEqualsMessage() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()),
                "Horses cannot be empty.");
        assertEquals("Horses cannot be empty.", thrown.getMessage());

    }

    @Test
    void getHorsesReturnsSameList() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Horse" + (i + 1), i + 3);
            horseList.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());

    }

    @Test
    void testMoveCallsMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerReturnsHorseWithMaxDistance() {
        List<Horse> horseList = new ArrayList<>();
        Horse horse1 = new Horse("Horse 1", 18, 20);
        Horse horse2 = new Horse("Horse 2", 17, 28);
        Horse horse3 = new Horse("Horse 3", 15, 15);

        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horseList);
        Horse winner = hippodrome.getWinner();

        assertSame(horse2, winner);
    }


}