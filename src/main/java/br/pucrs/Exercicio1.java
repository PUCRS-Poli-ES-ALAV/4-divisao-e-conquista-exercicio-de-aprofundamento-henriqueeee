package br.pucrs;

import java.util.Random;

public class Exercicio1 {

    private static long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576};
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            iterations = 0;

            long startTime = System.nanoTime();
            mergeSort(array, 0, array.length - 1);
            long endTime = System.nanoTime();

            System.out.println("Tamanho do vetor: " + size);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (nanossegundos): " + (endTime - startTime));
            System.out.println();
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            iterations++;
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            iterations++;
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            iterations++;
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}