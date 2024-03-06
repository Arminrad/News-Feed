package org.feed;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TimerTask;

public class Feeder {

    private static final String serverAddress = "127.0.0.1";
    private static final int serverPort = 11111;

    public static void sendMessage() {
        try (
                Socket socket = new Socket(serverAddress, serverPort);
                OutputStream outputStream = socket.getOutputStream();
        ) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(new NewsItem());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    static class SendTask extends TimerTask {
        @Override
        public void run() {
            sendMessage();
        }
    }
}
