package br.pucrs;

import java.util.Random;

public class Exercicio2 {

    private static long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576};
        for (int size : sizes) {
            long[] array = generateRandomArray(size);
            iterations = 0;

            long startTime = System.nanoTime();
            long maxValue = maxVal1(array, array.length);
            long endTime = System.nanoTime();

            System.out.println("Tamanho do vetor: " + size);
            System.out.println("Maior valor encontrado: " + maxValue);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (nanossegundos): " + (endTime - startTime));
            System.out.println();
        }
    }

    public static long maxVal1(long[] A, int n) {
        long max = A[0];
        for (int i = 1; i < n; i++) {
            iterations++;
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    private static long[] generateRandomArray(int size) {
        long[] array = new long[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextLong();
        }
        return array;
    }
}