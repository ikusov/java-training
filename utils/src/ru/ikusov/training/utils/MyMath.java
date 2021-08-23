package ru.ikusov.training.utils;

/**
 * class for own "math" utilities
 */
public class MyMath {

    /**
     * check num for being prime
     * @param num - checked number
     * @return true if num is prime
     */
    public static boolean isPrime(long num) {
        boolean isPrime = true;
        for (long i=2; i<num; i++) {
            if (num%i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * returns random number from 0 to range
     * @param range
     * @return
     */
    public static int r(int range) {
        return (int) (Math.random()*range);
    }

    /**
     * returns random number from 0 to 100
     * @return
     */
    public static int r() {
        return r(100);
    }

    /**
     * returns random n-character string
     * @param n number of characters
     * @return n-character brand new random streeng
     */
    public static String rs(int n) {
        char[] s = new char[n];
        for (int i=0; i<n; i++) {
            s[i] = (char)(Math.random()*(1103-1040)+1040);
        }

        return String.valueOf(s);
    }

    /**
     * returns random 10-character string
     * @return random 10-character string
     */
    public static String rs() {
        return rs(10);
    }
}
