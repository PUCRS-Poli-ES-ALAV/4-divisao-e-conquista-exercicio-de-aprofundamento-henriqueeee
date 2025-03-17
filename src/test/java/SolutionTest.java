import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SolutionTest {


    @Test
    void shouldMergeSort() {

        int[] nums = new java.util.Random().ints(32, 0, 100).toArray();
        int[] expected = nums.clone();
        java.util.Arrays.sort(expected);
        Solution.mergeSort(nums, nums.length);
        Assertions.assertArrayEquals(expected, nums);

    }
}