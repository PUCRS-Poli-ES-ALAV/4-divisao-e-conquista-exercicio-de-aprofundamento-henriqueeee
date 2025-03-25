package br.pucrs;

import java.util.Random;

public class Exercicio3 {

    private static long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576};
        for (int size : sizes) {
            long[] array = generateRandomArray(size);
            iterations = 0;

            long startTime = System.nanoTime();
            long maxValue = maxVal2(array, 0, array.length - 1);
            long endTime = System.nanoTime();

            System.out.println("Tamanho do vetor: " + size);
            System.out.println("Maior valor encontrado: " + maxValue);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (nanossegundos): " + (endTime - startTime));
            System.out.println();
        }
    }

    public static long maxVal2(long[] A, int init, int end) {
        iterations++;
        if (end - init <= 1) {
            return Math.max(A[init], A[end]);
        } else {
            int m = (init + end) / 2;
            long v1 = maxVal2(A, init, m);
            long v2 = maxVal2(A, m + 1, end);
            return Math.max(v1, v2);
        }
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