package br.pucrs;

public class Exercicio4 {
    public static long multiply(String x, String y) {
        
        // coletar os tamanhos
        int xLength = x.length();
        int yLength = y.length();

        // pegar o maior tamanho
        int maxLength = Math.max(xLength, yLength);

        // se ambos os números tiverem tamanho 1, multiplicar e retornar
        if (maxLength == 1) {
            return (long) Integer.parseInt(x) * Integer.parseInt(y);
        }

        // insere os zeros à esquerda
        while (x.length() < maxLength) { x = "0" + x; }
        while (y.length() < maxLength) { y = "0" + y; }

        // pega o meio do tamanho
        int mid = maxLength / 2;

        // vamos realizar as quatro submultiplicações
        // neste caso, usaremos substrings para a divisão!
        String xLeft = x.substring(0, mid);
        String xRight = x.substring(mid);
        String yLeft = y.substring(0, mid);
        String yRight = y.substring(mid);

        // agora, realizaremos as multiplicações. Para o terceiro, vamos usar a soma
        long p1 = multiply(xLeft, yLeft);
        long p2 = multiply(xRight, yRight);
        long p3 = multiply(addBinary(xLeft, xRight), addBinary(yLeft, yRight));

        return p1 * (1L << (2 * (maxLength - mid))) + (p3 - p1 - p2) * (1L << (maxLength - mid)) + p2;
    }

    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        // Garantir que ambas as strings tenham o mesmo comprimento
        while (a.length() < b.length()) a = "0" + a;
        while (b.length() < a.length()) b = "0" + b;

        // Agora podemos percorrer as duas strings
        int i = a.length() - 1;
        while (i >= 0) {
            int sum = (a.charAt(i) - '0') + (b.charAt(i) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
            i--;
        }

        if (carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String x = "11000"; // 24
        String y = "101000"; // 40
        System.out.println(multiply(x, y)); // Exemplo: resultado esperado é 960
    }
}