/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author RafalGrotkowski
 */
public class RsaMethods {

    public static String coding(final String message, final int e, final int n) {

        final Stream<Character> charStream = message.chars().mapToObj(i -> (char) i);
        StringBuilder sb = new StringBuilder();
        charStream.forEach((t) -> {
            final int asci = (int) t;
            final double pow = Math.pow(asci, e);
            final int result = (int) (pow % n);
            sb.append(result).append(" ");
        });
        return sb.toString();
    }

    public static String encoding(final String message, final int d, final int n) {

        final String[] stringCode = message.split(" ");
        final Stream<String> stream = Arrays.stream(stringCode);
        StringBuilder sb = new StringBuilder();
        stream.forEach((t) -> {
            int pot = Integer.valueOf(t);
            int wyn = 1;
            for (int q = d; q > 0; q /= 2) {
                if (q % 2 != 0) {
                    wyn = (wyn * pot) % n;
                }
                pot = (pot * pot) % n;
            }
            sb.append((char) wyn);
        });
        return sb.toString();
    }
}
