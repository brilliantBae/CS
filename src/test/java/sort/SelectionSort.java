package sort;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/*
Selection Sort 의 구현
    구현시  주의점 : for-loop 수행 횟수에 주의!
    맨 마지막 수행은 (n-1, n) 을 비교하는 수행이 되어야 하므로 i < arr.length-1 이 되어야 한다.
*/

public class SelectionSort {
    private int index;

    @Test
    public void SelectionSortTest() {
        int[] arr = {64, 25, 12, 22, 11};
        int[] sorted  = {11,12,22,25,64};
        assertThat(selectionSort(arr), is(sorted));
    }

    public int[] selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = findMin(arr, i);
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;
        }
        return arr;
    }

    public int findMin(int[] arr, int index){
        int min = arr[index];
        for(int i = index; i < arr.length; i++){
            if(min > arr[i]){
                min = arr[i];
                this.index = i;
            }
        }
        return min;
    }
}
