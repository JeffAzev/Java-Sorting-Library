/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_1;

/**
 *
 * @author jna90
 */
public class Search {
    //The search algorithm I too from web.
    public static int linear(String arr[], String x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }
    
    // Return title getting text on the first position spliting # cause is expected the String s was builded as template BOOK_TITLE#NUMBER_OF_PAGE#ORIGINAL_INDEX
    public static String extractTitle(String s) {
        return s.split("#")[0];
    }
    
    // Return number of pages getting text on the second position spliting # cause is expected the String s was builded as template BOOK_TITLE#NUMBER_OF_PAGE#ORIGINAL_INDEX
    public static int extractNumOfPages(String s) {
        return Integer.parseInt(s.split("#")[1]);
    }
    
    // Return original index on file getting text on the last position spliting # cause is expected the String s was builded as template BOOK_TITLE#NUMBER_OF_PAGE#ORIGINAL_INDEX
    public static int extractOriginalIndex(String s) {
        return Integer.parseInt(s.split("#")[2]);
    }
    
    public static int binary(String[] arr, String x) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (extractTitle(arr[mid]).compareTo(x) == 0) {
                return mid;
            } else if (extractTitle(arr[mid]).compareTo(x) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}    