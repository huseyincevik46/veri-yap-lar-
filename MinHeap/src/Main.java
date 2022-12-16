import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a list of numbers separated by commas: ");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }

        boolean isMinHeap = isMinHeap(numbers, 0);
        if (isMinHeap) {
            System.out.println("The list is a min heap.");
        } else {
            System.out.println("The list is not a min heap.");
        }

        buildMinHeap(numbers);
        System.out.println("The heap is: " + Arrays.toString(numbers));
    }

    public static boolean isMinHeap(int[] numbers, int index) {
        int k = 3;
        int leftChildIndex = k * index + 1;
        int rightChildIndex = k * index + 2;

        if (leftChildIndex >= numbers.length) {
            return true;
        }

        if (numbers[index] > numbers[leftChildIndex]) {
            return false;
        }

        if (rightChildIndex < numbers.length && numbers[index] > numbers[rightChildIndex]) {
            return false;
        }

        return isMinHeap(numbers, leftChildIndex) && isMinHeap(numbers, rightChildIndex);
    }

    public static void buildMinHeap(int[] numbers) {
        int k = 3;
        for (int i = numbers.length / k; i >= 0; i--) {
            minHeapify(numbers, i, numbers.length);
        }
    }

    public static void minHeapify(int[] numbers, int index, int heapSize) {
        int k = 3;
        int minIndex = index;
        for (int i = 1; i <= k; i++) {
            int childIndex = k * index + i;
            if (childIndex < heapSize && numbers[childIndex] < numbers[minIndex]) {
                minIndex = childIndex;
            }
        }
        if (minIndex != index) {
            int temp = numbers[index];
            numbers[index] = numbers[minIndex];
            numbers[minIndex] = temp;
            minHeapify(numbers, minIndex, heapSize);
        }
    }
}