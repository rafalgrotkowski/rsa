/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.util.Random;

/**
 *
 * @author RafalGrotkowski
 */
public class TestFermata {

    public static Boolean isPrimaryNumber(int number, int quantity) {
        Random rand = new Random();

        if (number < 4) {
            return Boolean.TRUE;
        }

        for (int i = 0; i < quantity; i++) {
            int a = rand.nextInt(number - 2) + 2;
            if (powerModuloFast(a, number - 1, number) != 1) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private static int powerModuloFast(int a, int b, int m) {
        int result = 1;
        long x = a % m;

        for (int i = 1; i <= b; i <<= 1) {
            x %= m;
            if ((b & i) != 0) {
                result *= x;
                result %= m;
            }
            x *= x;
        }

        return result % m;
    }

}
