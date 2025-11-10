import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        //divide into partitions
        int mid = (left + right)/2;

        return merge(mergeSort(a, left, mid);
    }

    //MODIFICATIONS: now returns an array list
    public static <T extends Comparable> ArrayList<T> merge(ArrayList<T> a, int left, int mid, int right) {

        //establish left and right pointers
        int l = left;
        int r = mid;

        ArrayList<T> sorted = new ArrayList<>();
        while (l < mid && r <= right) {

            //compare elements and move pointers accordingly
            if (a.get(r).compareTo(a.get(l)) > 0) {
                sorted.add(a.get(r));
                r++;
            } else {
                sorted.add(a.get(l));
                l++;
            }
        }

        //add what was left of the lists
        if (l < mid) for (int i = l; i < mid; i++) sorted.add(a.get(l));
        if (r <= right) for (int i = r; i <= right; i++) sorted.add(a.get(r));

        return sorted;
    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        //select middle element as pivot
        int mid = (left + right) / 2;

    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    //MODIFICATIONS: added a curr variable
    public static <T extends Comparable> void heapify (ArrayList<T> a, int curr, int left, int right) {

        //checks if heapifying is finished
        if (curr <= 0) return;

        //store c, as it will be needed for the recursive call
        int c = curr;

        //sift down
        try {
            while (a.get(curr).compareTo(a.get(left)) > 0 || a.get(curr).compareTo(a.get(right)) > 0) {

                //swap the minimum element
                //case 1: right child is smaller
                if (a.get(left).compareTo(a.get(right)) > 0) {
                    swap(a, curr, right);
                    curr = right;
                    left = (2 * curr);
                    right = (2 * curr) + 1;
                }

                //case 2: right child is smaller
                else {
                    swap(a, curr, left);
                    curr = left;
                    left = (2 * curr);
                    right = (2 * curr) + 1;
                }
            }
        } catch (IndexOutOfBoundsException e) { //exception will occur if either left or right child is out of bounds
            if (left <= a.size() && a.get(curr).compareTo(a.get(left)) > 0 {
                swap(a, curr, left);
            }
        }

        heapify(a, c - 1, 2 * (c - 1), (2 * (c - 1)) + 1);

    }

    // Bubble Sort
    //MODIFICATIONS: switched the return statement to a void
    public static <T extends Comparable> void bubbleSort(ArrayList<T> a, int size) {
        //first loop
        for (int i = 0; i < size; i++) {
            //second nested loop
            boolean swapped = false;
            for (int j = 0; j < size - 1; j++) {
                if (a.get(j + 1).compareTo(a.get(j)) < 0) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }
            //end if there were no swaps in a pass
            if (!swapped) break;
        }
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        for (int i = 0; i < a.size(); i++) {
            if (i % 2 == 0) { //even
                for (int j = 0; j < a.size(); j += 2) {
                    if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                        swap(a, i, j);
                    }
                }
            } else { //odd
                for (int k = 1; k < a.size(); k += 2) {
                    if (a.get(k).compareTo(a.get(k + 1)) > 0) {
                        swap(a, i, k);
                    }
                }
            }
        }
    }

    public static void main(String [] args)  throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //Read dataset into an array file

        ArrayList<DataObj> initData = new ArrayList<>();

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

            initData.add(currData);
        }


    }
}
