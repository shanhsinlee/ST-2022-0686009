import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{5, 4, 3, 2}, new int[]{2, 3, 4, 5}),
                Arguments.of(new int[]{5, 4, 3}, new int[]{3, 4, 5}),
                Arguments.of(new int[]{5, 14, 3, 1}, new int[]{1, 3, 5, 14}),
                Arguments.of(new int[]{1, 2}, new int[]{1, 2}),
                Arguments.of(new int[]{}, new int[]{})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument = {0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        for (int value: random_array){
            test.add(value);
        }

        while((s=test.poll())!=null){
            result[index] = s;
            index++;
        }

        //assertEquals(correct_array, result);
        assertArrayEquals(correct_array, result);
    }

    @Test
    public void NullPointerException_whenAddNull(){
        Exception exception = assertThrows(NullPointerException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });

        String expected = "NullPointerException";

        assertTrue(exception.toString().contains(expected));
    }

    @Test
    public void ClassCastException_whenAddDifferentTypeElement(){
        Exception exception = assertThrows(ClassCastException.class, ()->{
            PriorityQueue test = new PriorityQueue();
            test.add(1);
            test.add("a");
        });

        String expected = "ClassCastException";

        assertTrue(exception.toString().contains(expected));
    }

    @Test
    public void IllegalArgumentException_whenInitZeroSize(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });

        String expected = "IllegalArgumentException";

        assertTrue(exception.toString().contains(expected));
    }
}
