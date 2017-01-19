/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import rsa.Rsa;

/**
 *
 * @author RafalGrotkowski
 */
public class ChatServer {

    public static void main(String[] args) {

        boolean listening = true;

        final Rsa rsa = new Rsa(Constants.X, Constants.Y);

        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT_NUMBER)) {
            System.out.println("Serwer start");
            while (listening) {
                new MultipleClientChat(serverSocket.accept(), rsa).start();
                System.out.println("Dodano klienta");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + Constants.PORT_NUMBER + e);
            System.exit(-1);
        }
    }

}
