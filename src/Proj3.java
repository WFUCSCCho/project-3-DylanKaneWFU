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

        if (left < right) {

            int mid = left + right / 2;

            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);

            merge(a, left, mid, right);
        }

    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {

        //establish left and right pointers
        int l = left;
        int r = mid + 1;

        ArrayList<T> sorted = new ArrayList<>();
        while (l <= mid && r <= right) {

            //compare elements and move pointers accordingly
            if (a.get(r).compareTo(a.get(l)) > 0) {
                sorted.add(a.get(l));
                l++;
            } else {
                sorted.add(a.get(r));
                r++;
            }
        }

        //add what was left of the lists
        for (int i = l; i <= mid; i++) sorted.add(a.get(i));
        for (int i = r; i <= right; i++) sorted.add(a.get(i));

        for (int i = 0; i < sorted.size(); i++)
            a.set(left + i, sorted.get(i));
        }

    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        if (left < right) {
            int low_index = partition(a, left, right);
            quickSort(a, left, low_index);
            quickSort(a, low_index + 1, right);
        }
    }


    public static <T extends Comparable> int partition(ArrayList<T> a, int left, int right) {

        int p = right;
        int j = right - 1;
        int i = left;

        while (i < j && i < a.size() && j >= left) {
            //move i to the right til value greater than pivot is found
            while (a.get(i).compareTo(a.get(p)) < 0) i++;
            //move j to the left til value lower than pivot is found
            while (j > left && a.get(j).compareTo(a.get(p)) > 0) j--;
            if (i < j) swap(a, i, j);
        }

        swap(a, i, p);
        return i;

    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // first need to heapify the array
        heapify(a, (left + right) / 2, left, right);

        // delete maximum element and move it to the end
        for (int i = a.size() - 1; i > 0; i--) {
            T tempData = a.get(0);
            a.set(0, a.get(i));
            a.set(i, tempData);
            heapify(a, 0, i);

        }

    }

    //MODIFICATIONS: added a curr variable
    public static <T extends Comparable> void heapify (ArrayList<T> a, int curr, int left, int right) {

        //checks if heapifying is finished
        if (left >= a.size()) return;

        //store c, as it will be needed for the recursive call
        int c = curr;

        //sift down
        while (true) {

            int toSwap;

            if (a.get(2 * (curr) - 1) != null) {
                toSwap = left;
                if (a.get(right) != null && a.get(left).compareTo(a.get(right)) < 0) toSwap = right;
                if (a.get(toSwap).compareTo(a.get(curr)) > 0) swap(a, curr, toSwap);
                curr = toSwap;

            } else break;

        }
        heapify(a, c - 2, (2 * (c - 1)) -1, (2 * (c - 1)));

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
                //stop when list is sorted
                if (!swapped) break;
            }

        }

        //returns number of swaps
        return numSwaps;
    }

    public static <T extends Comparable>  ArrayList<T> chooseArray(ArrayList<T> a, int i) {
        ArrayList<T> arrayData = new ArrayList<>(a);
        if (i == 0) Collections.shuffle(arrayData);
        if (i == 1) Collections.sort(arrayData);
        if (i == 2) Collections.reverse(arrayData);
        return arrayData;
    }

    public static void main(String [] args)  throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);
        String algorithmType = args[3];
        if (!algorithmType.equals("bubble") ||
                !algorithmType.equals("merge") ||
                !algorithmType.equals("quick") ||
                !algorithmType.equals("heap") ||
                !algorithmType.equals("transposition")) {
            System.err.println("Invalid sorting algorithm");
            System.exit(1);
        }


        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

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
                heapSort(arrayToSort, 0, arrayToSort.size() - 1);
            }
            if (algorithmType.equals("transposition")) {
                numSwaps[i] = transpositionSort(arrayToSort, arrayToSort.size());
            }
            endTimer = System.nanoTime();
            sortLists.add( new ArrayList<>(arrayToSort));
        }

        //Write to output file
        File file = new File("analysis.txt");
        boolean needsHeader = false;
        if (!file.exists()) needsHeader = true;

        FileOutputStream analysisFile = new FileOutputStream("analysis.txt", true);
        FileOutputStream sortedFile = new FileOutputStream("sorted.txt", false); //will overwrite
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

        if (!algorithmType.equals("bubble") || !algorithmType.equals("transposition")) {
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
                sortTimes[3],
                numSwaps[0],
                numSwaps[1],
                numSwaps[2]
        );

        analysisWriter.flush();
        analysisWriter.close();

        //sorted list printer
        for (int i = 0; i < 4; i++) {

            System.out.printf("List Type: %s\n", listTitles[i]);

            for (int j = 0; j < numLines; j++) {
                sortedWriter.println(
                        sortLists
                        .get(i)
                        .get(j)
                        .toString());
            }
            System.out.println();
        }

        sortedWriter.flush();
        sortedWriter.close();
    }

