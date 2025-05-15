/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_1;

/**
 *
 * @author jna90
 */
public class Sort {
    //I took it from the PDF "Linear Algebra" and I just insert parameter 
    //isAsc to check if the user whant ascending or descending
    public static void bubble(int[] arr, boolean isAsc) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                boolean condition = arr[i - 1] > arr[i];
                if (!isAsc) {
                    condition = arr[i - 1] < arr[i];
                }
                if (condition) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    public static void bubble(String[] arr, boolean isAsc) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                boolean condition = arr[i - 1].compareTo(arr[i]) > 0;
                if (!isAsc) {
                    condition = arr[i - 1].compareTo(arr[i]) < 0;
                }
                if (condition) {
                    String temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    public static void selection(int [] arr, boolean isAsc) {
        int n = arr. length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                boolean condition = arr[j] < arr [minIndex];
                if (!isAsc) {
                    condition=(arr[j] > arr [minIndex]);
                }
                if (condition) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr [minIndex];
            arr [minIndex] = temp;
        }
    }

    public static void selection(String [] arr, boolean isAsc) {
        int n = arr. length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                boolean condition = arr[j].compareTo(arr[minIndex]) < 0;
                if (!isAsc) {
                    condition=arr[j].compareTo(arr[minIndex]) > 0;
                }
                if (condition) {
                    minIndex = j;
                }
            }
            String temp = arr[i];
            arr[i] = arr [minIndex];
            arr [minIndex] = temp;
        }
    }    
}
