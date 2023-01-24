import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//imports

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Artem Savchenko
 *	@since 1/17/23
 */
public class SortMethods {

    /**
     *	Overrides the bubbleSort method for more accessibility
     *	@param arr		array of Integer objects to sort
     */
    public static void bubbleSort(List<City> arr) {
        bubbleSort(arr,"ASC");
    }

    /**
     * method that sorts the arraylist using bubbleSort
     * @param arr arraylist that we want to sort
     * @param order Ascending/Descending, tells whether the list should be arranged in ascending or descending order
     */
    public static void bubbleSort(List<City> arr, String order) {
        boolean doSwap = false;

        for(int outer = arr.size()-1; outer > 0; outer--)
         {
            for(int inner = 0; inner < outer; inner++)
            {
                if (order.equals("ASC")) {if(arr.get(inner).compareTo(arr.get(inner+1))>0) doSwap = true;}
                else {if(arr.get(inner).compareTo(arr.get(inner+1))<0) doSwap = true;}

                if (doSwap) swap(arr, inner, inner+1);
                doSwap = false;
            }
        }
    }

    /**
     *	Swaps two Integer objects in array arr
     *	@param arr		array of Integer objects
     *	@param x		index of first object to swap
     *	@param y		index of second object to swap
     */
    private static void swap(List<City> arr, int x, int y) {
        City temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp) ;
    }


    /**
     *	Overrides the selectionSort method for more accesibility
     *	@param arr		array of Integer objects to sort
     */
    public static void selectionSort(List<City> arr) {
        selectionSort(arr,"ASC");
    }

    /**
     * method that sorts the ArrayList using selectionSort
     * @param arr arraylist of objects that we need to sort using SelectionSort
     * @param order Ascending/Descending, tells whether the list should be arranged in ascending or descending order
     */
    public static void selectionSort(List<City> arr, String order) {

        for(int n = arr.size(); n > 1; n--)
        {
            int iMax = 0;
            for(int i = 1; i < n; i++)
            {
                if(arr.get(i).compareTo(arr.get(iMax)) > 0)
                    iMax = i;
            }
            swap(arr, iMax, n-1);

        }
        if (order.equals("DSC")) Collections.reverse(arr);

    }

    /**
     *	Overrides the insersionSort method for more accessibility
     *	@param arr		array of Integer objects to sort
     */
    public static void insertionSort(List<City> arr) {
        insertionSort(arr,"ASC","population");
    }

    /**
     * method that uses insersionSort to sort throught the ArrayList of objects
     * @param arr ArrayList of objects that we need to sort
     * @param order Ascending/Descending, tells whether the list should be arranged in ascending or descending order
     * @param orderBy Name/Population, tells whether the list should be arranged based on the 'population' field or
     *                'name' field
     */
    public static void insertionSort(List<City> arr, String order, String orderBy) {
        for(int n = 1 ; n < arr.size(); n++)
        {
            City temp = arr.get(n);
            int i = n ;
            while(i > 0 && ((orderBy.equalsIgnoreCase("name")&&temp.compareToByName(arr.get(i-1)) < 0)||
                            (!orderBy.equalsIgnoreCase("name")&&temp.compareTo(arr.get(i-1)) < 0)))
            {
                arr.set(i, arr.get(i-1));
                i--;
            }
            arr.set(i, temp);
        }
        if (order.equals("DSC")) Collections.reverse(arr);
    }

    /**
     * Overrides the mergeSort method for more accessibilioty
     * @param list ArrayList of objects
     */
    public static void mergeSort(ArrayList<City> list)
    {
        mergeSort( list,"population");
    }

    /**
     * method for sorting an array using mergeSort, calls the merge method to complete the process of merge sort.
     * @param list ArrayList of objects that we need to sort
     * @param orderBy Name/Population, tells whether the list should be arranged based on the 'population' field or
     *                    'name' field
     */
    public static void mergeSort(ArrayList<City> list, String orderBy) {
        if (list.size() > 1) {
            int mid = list.size() / 2;
            ArrayList<City> left = new ArrayList<>(list.subList(0, mid));
            ArrayList<City> right = new ArrayList<>(list.subList(mid, list.size()));
            mergeSort(left,orderBy);
            mergeSort(right,orderBy);
            merge(list, left, right, orderBy);
        }
    }

    /**
     * merges the two halves together to form a sorted ArrayList of items
     * @param list ArrayList of objects that we need to sort
     * @param left Left side/half of the ArrayList
     * @param right right side/half of the ArrayList
     * @param orderBy Name/Population, tells whether the list should be arranged based on the 'population' field or
     *                'name' field
     */
    private static void merge(ArrayList<City> list, ArrayList<City> left, ArrayList<City> right, String orderBy) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (orderBy.equals("population")){
                if (left.get(i).compareTo(right.get(j)) <= 0) {
                    list.set(k, left.get(i));
                    i++;
                } else {
                    list.set(k, right.get(j));
                    j++;
                }
            }else {
                if (left.get(i).compareToByName(right.get(j)) <= 0) {
                    list.set(k, left.get(i));
                    i++;
                } else {
                    list.set(k, right.get(j));
                    j++;
                }
            }
            k++;
        }
        while (i < left.size()) {
            list.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < right.size()) {
            list.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
