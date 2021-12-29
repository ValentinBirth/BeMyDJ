package de.htw.bemydj.networkControl;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamingServer extends Thread{

    private static final String TAG = StreamingServer.class.toString();
    private Socket socket;
    private ServerSocket serverSocket;

    @Override
    public void run() {
            try {
                serverSocket = new ServerSocket(8978);
                socket = serverSocket.accept();
                //TODO Sending logic missing
            } catch (IOException e) {
                Log.e(TAG,e.toString());
            }

    }
}
