package sort;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void bubbleSortTest() {
        int[] arr  = {2,1,4,0,3};
        int[] sortedArr = {0,1,2,3,4};
        assertThat(bubbleSort(arr), is(sortedArr));
    }

    public int[] bubbleSort(int[] arr){
        int len = arr.length;
        int temp = 0;
        for(int i=0; i < len; i++){
            for(int j=0; j < len - 1 - i; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}
