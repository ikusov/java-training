package ru.ikusov.training.utils;

/**
 * class for own "math" utilities
 */
public class MyMath {

    /**
     * returns double powered number
     * @param base double number
     * @return double powered of double number base
     */
    public static double vkvadrate(double base) {
        return Math.pow(base, 2);
    }

    /**
     * returns triple powered number
     * @param base double number
     * @return triple powered of double number base
     */
    public static double vkube(double base) {
        return Math.pow(base, 3);
    }

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
     * @param range range of the range
     * @return random number from zer0 t0 range
     */
    public static int r(int range) {
        return (int) (Math.random()*range);
    }

    /**
     * returns random number from 0 to range
     * @param range range of the range
     * @return random number from zero to hero
     */
    public static double r(double range) {
        return Math.random()*range;
    }


    /**
     * returns random number from 0 to 100
     * @return random number from zer0 to one hundr100d
     */
    public static int r() {
        return r(100);
    }

    /**
     * returns random 10-character string
     * @return random TenCharacterString
     */
    public static String rs() {
        char[] s = new char[10];
        for (int i=0; i<10; i++) {
            s[i] = (char)(Math.random()*(1103-1040)+1040);
        }

        return String.valueOf(s);
    }
}
