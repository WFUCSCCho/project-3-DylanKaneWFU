/************************************************************************
 * @file Proj3.java
 * @brief This program implements java to time different sorting algorithms
 * @author Dylan Kane
 * @date November 13, 2025
 *************************************************************************/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {

        for (int j = 0; j < a.size(); j++) System.out.print(a.get(j) + " ");
        System.out.println();

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);

            merge(a, left, mid, right);
        }

    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {

        //establish left and right pointers
        int l = 0;
        int r = 0;

        //establish left and right lists
        ArrayList<T> leftList = new ArrayList<>();
        ArrayList<T> rightList = new ArrayList<>();

        //fill list with corresponding entries from array
        for (int i = left; i <= mid; i++) leftList.add(a.get(i));
        for (int i = mid + 1; i <= right; i++) rightList.add(a.get(i));

        //merge them
        int j = left;
        while (l < leftList.size() && r < rightList.size()) {
            //compare elements and move pointers accordingly
            if (leftList.get(l).compareTo(rightList.get(r)) <= 0) {
                a.set(j, leftList.get(l));
                l++;
            } else {
                a.set(j, rightList.get(r));
                r++;
            }
            j++;
        }

        //add what is left of the lists
        for (int i = l; i < leftList.size(); i++) {
            a.set(j, leftList.get(i));
            j++;
        }
        for (int i = r; i < rightList.size(); i++) {
            a.set(j, rightList.get(i));
            j++;
        }
    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {

        if (left < right) {
            int low_index = partition(a, left, right);
            quickSort(a, left, low_index - 1);
            quickSort(a, low_index + 1, right);
        }
    }





    public static <T extends Comparable> int partition(ArrayList<T> a, int left, int right) {

        //rightmost element is the pivot
        int j = right - 1;
        int i = left;

        while (i < j && i < a.size() && j >= left) {
            //move i to the right til value greater than pivot is found
            while (i < right && a.get(i).compareTo(a.get(right)) <= 0) i++;
            //move j to the left til value lower than pivot is found
            while (j > left && a.get(j).compareTo(a.get(right)) >= 0) j--;
            if (i < j) swap(a, i, j);
        }

        swap(a, i, right);
        return i;

    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    //MODIFICATIONS: deleted left and right
    public static <T extends Comparable> void heapSort(ArrayList<T> a) {

        // first need to heapify the array
        for (int i = (a.size() / 2) - 1; i >= 0; i--) {
            heapify(a, a.size(), i);
        }

        // delete maximum element and move it to the end
        for (int i = a.size() - 1; i > 0; i--) {
            swap(a, 0, i);
            //heapify again, size of the heap decreases by i
            heapify(a, i, 0);
        }

    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int size, int curr) {

        int l = 2 * curr + 1;
        int r = 2 * curr + 2;

        //checks if heapifying should finish
        if (l >= size) return;

        //sift down
        int toSwap = curr;

        if (a.get(l).compareTo(a.get(curr)) > 0) {
            toSwap = l;
        }

        if (r < size && a.get(r).compareTo(a.get(toSwap)) > 0) {
            toSwap = r;
        }

        if (toSwap != curr) {
            swap(a, curr, toSwap);
            heapify(a, size, toSwap);
        }

    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {

        int numSwaps = 0;
        //first loop
        for (int i = 0; i < size; i++) {
            //second nested loop
            boolean swapped = false;
            for (int j = 0; j < size - 1; j++) {
                if (a.get(j + 1).compareTo(a.get(j)) < 0) {
                    swap(a, j, j + 1);
                    swapped = true;
                    numSwaps++;
                }
            }
            //end if there were no swaps in a pass
            if (!swapped) break;
        }

        //returns number of swaps
        return numSwaps;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {

        int numSwaps = 0;
        for (int i = 0; i < size; i++) {
            boolean swapped = false;
            if (i % 2 == 0) { //even
                for (int j = 0; j < a.size() - 1; j += 2) {
                    if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                        swap(a, j, j + 1);
                        numSwaps++;
                        swapped = true;
                    }
                }
            } else { //odd
                for (int k = 1; k < a.size() - 1; k += 2) {
                    if (a.get(k).compareTo(a.get(k + 1)) > 0) {
                        swap(a, k, k + 1);
                        numSwaps++;
                        swapped = true;
                    }
                }
            }
            //stop when list is sorted
            if (!swapped) break;

        }

        //returns number of swaps
        return numSwaps;
    }

    public static <T extends Comparable>  ArrayList<T> chooseArray(ArrayList<T> a, int i) {
        ArrayList<T> arrayData = new ArrayList<>(a);
        if (i == 0) Collections.sort(arrayData);
        if (i == 1) Collections.shuffle(arrayData);
        if (i == 2) Collections.reverse(arrayData);
        return arrayData;
    }

    public static void main(String [] args)  throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 3) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);
        String algorithmType = args[2];
        if (!algorithmType.equals("bubble") &&
                !algorithmType.equals("merge") &&
                !algorithmType.equals("quick") &&
                !algorithmType.equals("heap") &&
                !algorithmType.equals("transposition")) {
            System.err.println("Invalid sorting algorithm");
            System.exit(1);
        }

        //Open the input file
        FileInputStream inputFileNameStream = new FileInputStream(inputFileName);
        Scanner inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //Read dataset into an array file
        ArrayList<DataObj> arrayData = new ArrayList<>();

        for (int i = 0; i < numLines; i++) {
            String obj = inputFileNameScanner.nextLine();
            String[] objInfo;

            objInfo = obj.split(",");
            DataObj currData = new DataObj(objInfo[0],
                    Double.parseDouble(objInfo[1]),
                    Integer.parseInt(objInfo[2]),
                    Integer.parseInt(objInfo[3]),
                    objInfo[4]
            );

            arrayData.add(currData);
        }

        ArrayList<DataObj> arrayToSort;

        long startTimer;
        long endTimer;

        /*
        MAKE LISTS OF EACH SORTING TIME
            0. Shuffled
            1. Sorted
            2. Reversed
        */

        double[] sortTimes = new double[3];
        ArrayList<ArrayList<DataObj>> sortLists = new ArrayList<>();
        int[] numSwaps = new int[3];
        String[] listTitles = {"Sorted", "Shuffled", "Reversed"};

        for (int i = 0; i < 3; i++) {
            arrayToSort = chooseArray(arrayData, i);
            startTimer = System.nanoTime();
            if (algorithmType.equals("bubble")) {
                numSwaps[i] = bubbleSort(arrayToSort, arrayToSort.size());
            }
            if (algorithmType.equals("merge")) {
                mergeSort(arrayToSort, 0, arrayToSort.size() - 1);
            }
            if (algorithmType.equals("quick")) {
                quickSort(arrayToSort, 0, arrayToSort.size() - 1);
            }
            if (algorithmType.equals("heap")) {
                heapSort(arrayToSort);
            }
            if (algorithmType.equals("transposition")) {
                numSwaps[i] = transpositionSort(arrayToSort, arrayToSort.size());
            }
            endTimer = System.nanoTime();
            sortTimes[i] = (endTimer - startTimer) / 1_000_000_000.0;
            sortLists.add(new ArrayList<>(arrayToSort));
        }

        //Write to output file
        File file = new File("analysis.txt");
        boolean needsHeader = !file.exists();

        FileOutputStream analysisFile = new FileOutputStream("analysis.txt", true);
        FileOutputStream sortedFile = new FileOutputStream("sorted.txt"); //will overwrite
        PrintWriter sortedWriter = new PrintWriter(sortedFile);
        PrintWriter analysisWriter = new PrintWriter(analysisFile);

        //write results to analysis.txt file in csv format

        //check if file is empty, if so add a header
        if (needsHeader) analysisWriter.println(
                "Lines Read," +
                        "Algorithm Type" +
                        "Sorted Time," +
                        "Shuffled Time," +
                        "Reversed Time" +
                        "Sorted Swaps," +
                        "Shuffled Swaps," +
                        "Reversed Swaps");

        if (!algorithmType.equals("bubble") && !algorithmType.equals("transposition")) {
            numSwaps[0] = -1;
            numSwaps[1] = -1;
            numSwaps[2] = -1;
        }

        if (algorithmType.equals("transposition")) {
            sortTimes[0] = -1.0;
            sortTimes[1] = -1.0;
            sortTimes[2] = -1.0;
        }

        analysisWriter.printf("%d,%s,%.9f,%.9f,%.9f,%d,%d,%d\n",
                numLines,
                algorithmType,
                sortTimes[0],
                sortTimes[1],
                sortTimes[2],
                numSwaps[0],
                numSwaps[1],
                numSwaps[2]
        );

        analysisWriter.flush();
        analysisWriter.close();

        //sorted list printer
        for (int i = 0; i < 3; i++) {

            sortedWriter.printf("List Type: %s\n", listTitles[i]);

            for (int j = 0; j < numLines; j++) {
                sortedWriter.println(
                        sortLists
                                .get(i)
                                .get(j)
                                .toString());
            }
            sortedWriter.println();
        }

        sortedWriter.flush();
        sortedWriter.close();
    }
}


