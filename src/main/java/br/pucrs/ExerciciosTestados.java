package br.pucrs;

import java.util.Random;

public class ExerciciosTestados {

    private static long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = {32, 2048, 1048576}; // Tamanhos do vetor para os Exercícios 1, 2 e 3
        int[] bitSizes = {4, 16, 64}; // Tamanhos de bits para o Exercício 4 (Multiplicação Binária)

        // Testando para os tamanhos dos vetores para Merge Sort, Max Value 1 e Max Value 2
        for (int size : sizes) {
            // Merge Sort (Ex 1)
            int[] array1 = generateRandomArray(size);
            iterations = 0;
            long startTime1 = System.nanoTime();
            mergeSort(array1, 0, array1.length - 1);
            long endTime1 = System.nanoTime();
            System.out.println("Exercicio 1 - Tamanho do vetor: " + size);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (milissegundos): " + (endTime1 - startTime1) / 1_000_000.0);
            System.out.println();

            // Max Value 1 (Ex 2)
            long[] array2 = generateRandomArrayLong(size);
            iterations = 0;
            long startTime2 = System.nanoTime();
            long maxValue1 = maxVal1(array2, array2.length);
            long endTime2 = System.nanoTime();
            System.out.println("Exercicio 2 - Tamanho do vetor: " + size);
            System.out.println("Maior valor encontrado: " + maxValue1);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (milissegundos): " + (endTime2 - startTime2) / 1_000_000.0);
            System.out.println();

            // Max Value 2 (Ex 3)
            iterations = 0;
            long startTime3 = System.nanoTime();
            long maxValue2 = maxVal2(array2, 0, array2.length - 1);
            long endTime3 = System.nanoTime();
            System.out.println("Exercicio 3 - Tamanho do vetor: " + size);
            System.out.println("Maior valor encontrado: " + maxValue2);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (milissegundos): " + (endTime3 - startTime3) / 1_000_000.0);
            System.out.println();
        }

        // Multiplication (Ex 4) - Testar para 4, 16 e 64 bits
        for (int bitSize : bitSizes) {
            // Gerar números binários de tamanho especificado
            String x = generateRandomBinary(bitSize);
            String y = generateRandomBinary(bitSize);

            iterations = 0;
            long startTime4 = System.nanoTime();
            long result = multiply(x, y);
            long endTime4 = System.nanoTime();

            System.out.println("Exercicio 4 - Tamanho do número: " + bitSize + " bits");
            System.out.println("Resultado da multiplicação: " + result);
            System.out.println("Número de iterações: " + iterations);
            System.out.println("Tempo gasto (milissegundos): " + (endTime4 - startTime4) / 1_000_000.0);
            System.out.println();
        }
    }

    // Merge Sort (Ex 1)
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

    // Max Value 1 (Ex 2)
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

    // Max Value 2 (Ex 3)
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

    // Multiplicação Binária (Ex 4)
    public static long multiply(String x, String y) {
        iterations++; // Contabiliza a primeira chamada da função
        int xLength = x.length();
        int yLength = y.length();
        int maxLength = Math.max(xLength, yLength);
    
        if (maxLength == 1) {
            return (long) Integer.parseInt(x, 2) * Integer.parseInt(y, 2);
        }
    
        while (x.length() < maxLength) { x = "0" + x; }
        while (y.length() < maxLength) { y = "0" + y; }
    
        int mid = maxLength / 2;
        String xLeft = x.substring(0, mid);
        String xRight = x.substring(mid);
        String yLeft = y.substring(0, mid);
        String yRight = y.substring(mid);
    
        long p1 = multiply(xLeft, yLeft); // Contabiliza essa chamada recursiva
        long p2 = multiply(xRight, yRight); // Contabiliza essa chamada recursiva
        long p3 = multiply(Long.toBinaryString(Long.parseLong(xLeft, 2) + Long.parseLong(xRight, 2)),
                           Long.toBinaryString(Long.parseLong(yLeft, 2) + Long.parseLong(yRight, 2))); // Contabiliza essa chamada recursiva
    
        return p1 * (1L << (2 * (maxLength - mid))) + (p3 - p1 - p2) * (1L << (maxLength - mid)) + p2;
    }

    // Gerador de arrays aleatórios para o exercício 1
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    // Gerador de arrays aleatórios para o exercício 2 e 3
    private static long[] generateRandomArrayLong(int size) {
        long[] array = new long[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextLong();
        }
        return array;
    }

    // Gerar números binários aleatórios com o número de bits especificado
    private static String generateRandomBinary(int bitSize) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < bitSize; i++) {
            binary.append((int) (Math.random() * 2)); // Gera 0 ou 1 aleatoriamente
        }
        return binary.toString();
    }
}