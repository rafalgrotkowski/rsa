/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import rsa.Rsa;
import rsa.RsaMethods;

/**
 *
 * @author RafalGrotkowski
 */
public class MultipleClientChat extends Thread {

    private Socket socket = null;
    private Rsa rsa = null;
    private static List<Socket> socketList = new ArrayList<>();

    public MultipleClientChat(Socket socket, Rsa rsa) {
        this.socket = socket;
        this.rsa = rsa;
        socketList.add(socket);
    }

    @Override
    public void run() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());;
            sendToAllClients(rsa.publicKey.get("e") + "_" + rsa.publicKey.get("n"));

            while (true) {
                final String textAreaValue = dataInputStream.readUTF();
                System.out.println(textAreaValue);
                sendToAllClients(textAreaValue + Constants.NEW_LINE + "Odkodowana: " + RsaMethods.encoding(textAreaValue, rsa.getPrivateKey().get("d"), rsa.getPrivateKey().get("n")));
            }

        } catch (IOException e) {
            System.err.println("MultipleClientChat.run: " + e);
        }
    }

    synchronized void sendToAllClients(final String text) {
        socketList.stream().forEach((s) -> {
            try {
                final DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
                dataOutputStream.writeUTF(text);
            } catch (IOException e) {
                System.err.println("MultipleClientChat.sendToAllClients: " + e);
            }
        });

    }
}
