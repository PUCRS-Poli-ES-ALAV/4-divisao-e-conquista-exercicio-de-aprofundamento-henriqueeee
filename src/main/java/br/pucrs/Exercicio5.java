package br.pucrs;

public class Exercicio5 {
    public static long multiply(int x, int y, int n) {
        

        // se ambos os números tiverem tamanho 1, multiplicar e retornar
        if (n == 1) {
            return x * y;
        } else {


        // pega o meio do tamanho
        int m = n / 2;
        int a = x >> m;
        int b = x & ((1 << m) - 1);
        int c = y >> m;
        int d = y & ((1 << m) - 1);
    

        // agora, realizaremos as multiplicações. Para o terceiro, vamos usar a soma
        long e = multiply(a, c, m);
        long f = multiply(b, d, m);
        long g = multiply(b, c, m);
        long h = multiply(a, d, m);
        return (e << (2 * m)) + ((g + h) << m) + f;
        }
    }

    public static void main(String[] args) {
        int x = 30;
        int y = 32;
        int n = 6;
        long res =  (multiply(x, y, n));
        System.out.println(res);
    }
}