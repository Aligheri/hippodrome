import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class HorseTest {
    Horse horse = new Horse("bob", 31, 283);


    @Test
    void сonstructorWithNullArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 12, 15));
    }


    @Test
    void сonstructorWithNullNameMessage() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 12, 14), "Name cannot be null.");

        assertEquals("Name cannot be null.", thrown.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {"\t", " ", "\n"})
    void сonstructorWithEmptyName(String value) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(value, 12, 15));
    }


    @ParameterizedTest
    @ValueSource(strings = {"\t", " ", "\n"})
    void сonstructorWithEmptyOrWhitespaceString(String value) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(value, 12, 14), "Name cannot be blank.");

        assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    void сonstructorWithNegativeSpeedArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("bob", -13, 15.4));
    }

    @Test
    void сonstructorWithNegativeSpeedMessage() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("bob", -12, 14.2), "Speed cannot be negative.");

        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }


    @Test
    void сonstructorWithNegativeDistanceArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("bob", 13, -15));
    }

    @Test
    void сonstructorWithNegativeDistanceArgumentMessage() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("bob", 12, -14.2), "Distance cannot be negative.");

        assertEquals("Distance cannot be negative.", thrown.getMessage());

    }

    @Test
    void getName() {
        String expected = "bob";
        assertEquals(expected, horse.getName());
    }

    @Test
    void getSpeed() {
        int expected = 31;
        int actual = (int) horse.getSpeed();

        assertEquals(expected, actual);
    }

    @Test
    void getDistance() {
        double expected = 283;
        double actual = (int) horse.getDistance();

        assertEquals(expected, actual);

    }

    @Test
    void getDistanceWithTwoParameters() {
        double expected = 0;
        Horse horse1 = new Horse("bob", 12);
        double actual = horse1.getDistance();

        assertEquals(expected, actual);
    }

    @Test
    void methodCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse.getRandomDouble(0.2, 0.9);
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 1.0, 999.999, 0.0})
    void assignDistanceWithMockedRandomDouble(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(283 + 31 * random, horse.getDistance());

        }
    }
}