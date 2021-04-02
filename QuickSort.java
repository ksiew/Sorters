package cse214hw4and5;

import java.util.Arrays;
import java.util.List;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {

    private List<E> elements;
    private Order   order;
    private int compare;
    private String ret;

    public QuickSort(List<E> elements, Order order) {
        this.elements = elements;
        this.order    = order;
        switch(order){
            case DECREASING:
                compare = -1;
                break;

            case INCREASING:
                compare = 1;
                break;
        }
    }

    @Override
    public List<E> getList() {
        return elements;
    }

    @Override
    public void sort() {
        ret = "";
        sort2(0,elements.size() - 1);
    }

    private void sort2(int min, int max){
        if(max - min < 1){
            return;
        }
        int n = partition(max,min,max);
        sort2(min,n - 1);
        sort2(n + 1,max);
    }

    /**
     * This method partitions the elements list for quick sorting
     *
     * @param n n is the index where the last swap took place
     * @param min is the index of first element being partitioned
     * @param max is the index of last element being partitioned
     * @return index of the last swap
     */
    private int partition(int n,int min, int max){
        int low = min + 1;
        int high = max;
        while(low < high) {
            while (low < max && low <= high && (elements.get(low).compareTo(elements.get(min)) * compare) == -1) {
                low++;
            }
            while (high > min && low <= high && (elements.get(high).compareTo(elements.get(min)) * compare) == 1) {
                high--;
            }
            if (low > high) {
                swap(high, min);
                ret = ret + "  :: " + elements.toString() + "\n";
                return high;
            } else {
                swap(low, high);
                ret = ret + elements.get(min) + " :: " + elements.toString() + "\n";
            }
        }
        if((elements.get(max).compareTo(elements.get(min)) * compare) == -1){
            swap(high, min);
        }
        return high;
    }

    /**
     * This method swaps the elements in a list of indexes n1 and n2
     *
     * @param n1 1st element being swapped
     * @param n2 2nd element being swapped
     * @return list with swapped elements
     */
    private void swap(int n1, int n2){
        E save = elements.get(n2);
        elements.set(n2,elements.get(n1));
        elements.set(n1,save);
    }

    /**
     * The method displays the original input (unsorted) list, and then, each step is shown in a new line. For example,
     * if the original list to be sorted in increasing order is [6, 4, 9, 5, 1, 8, 2, 7, 3], calling this method should
     * string as follows, exactly in the format shown:
     * <p>
     * 6 :: [6, 4, 9, 5, 1, 8, 2, 7, 3]
     * 6 :: [6, 4, 3, 5, 1, 8, 2, 7, 9]
     * 6 :: [6, 4, 3, 5, 1, 2, 8, 7, 9]
     *   :: [2, 4, 3, 5, 1, 6, 8, 7, 9]
     * <p>
     * Only the steps with the first pivot are shown above, and NOT the entire output. The last step with a specific
     * pivot does not show the pivot separately in front of the :: separator, to indicate that a different pivot will
     * be used in the next step. At each stage, you must use the first element as the pivot.
     *
     * @return the string representation of the step-wise transformation of the input list, as done with quick sort
     */
    @Override
    public String show() {
        return ret; // todo
    }

    public static void main(String[] args){
        QuickSort<Integer> list = new QuickSort<>(Arrays.asList(6, 4, 9, 5, 1, 8, 2, 7, 3), Order.INCREASING);
        System.out.println(list.getList().toString());
        list.sort();
        System.out.println(list.show());
        System.out.println(list.getList().toString());
    }
}