/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

/**
 *
 * @author RafalGrotkowski
 */
public class Euler {

    public static int nwd(int a, int b) {
        if (a != b) {
            return nwd(a > b ? a - b : a, b > a ? b - a : b);
        }
        return a;
    }

    public static int revertMod(int a, int n) {
        int a0, n0, p0, p1, q, r, t;

        p0 = 0;
        p1 = 1;
        a0 = a;
        n0 = n;
        q = n0 / a0;
        r = n0 % a0;
        while (r > 0) {
            t = p0 - q * p1;
            if (t >= 0) {
                t = t % n;
            } else {
                t = n - ((-t) % n);
            }
            p0 = p1;
            p1 = t;
            n0 = a0;
            a0 = r;
            q = n0 / a0;
            r = n0 % a0;
        }
        return p1;
    }
}
