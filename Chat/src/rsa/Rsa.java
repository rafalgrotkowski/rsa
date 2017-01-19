/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author RafalGrotkowski
 */
public class Rsa {
    private static final int QUANTITY_OF_TESTS = 3;

    public Map<String, Integer> publicKey;
    private Map<String, Integer> privateKey;

    public Rsa(final int x, final int y) {
        createRsa(x, y);
    }

    public Map<String, Integer> getPrivateKey() {
        return privateKey;
    }

    private void createRsa(final int x, final int y) {

        int p = 0;
        int q = 0;
        while (p == q) {
            p = generateFirstNumber(x, y);
            q = generateFirstNumber(x, y);
        }
        final int n = calculateN(p, q);
        final int phi = calculatePhi(p, q);
        final int e = calculateE(phi);
        final int d = calculateD(e, phi);

        setPublicKey(e, n);
        setPrivateKey(d, n);
    }

    private int generateFirstNumber(final int x, final int y) {
        Random r = new Random();
        while (true) {
            int randomNumber = r.nextInt(y - x + 1) + x;
            if (TestFermata.isPrimaryNumber(randomNumber, QUANTITY_OF_TESTS)) {
                return randomNumber;
            }
        }
    }

    private int calculateN(final int p, final int q) {
        return p * q;
    }

    private int calculatePhi(final int p, final int q) {
        return (p - 1) * (q - 1);
    }

    private int calculateE(final int phi) {
        int e = 3;
        while (Euler.nwd(e, phi) != 1) {
            e += 2;
        }
        return e;
    }

    private int calculateD(final int e, final int phi) {
        return Euler.revertMod(e, phi);
    }

    private void setPublicKey(final int e, final int n) {
        publicKey = new HashMap<>();
        publicKey.put("e", e);
        publicKey.put("n", n);
    }

    private void setPrivateKey(final int d, final int n) {
        privateKey = new HashMap<>();
        privateKey.put("d", d);
        privateKey.put("n", n);
    }
}
