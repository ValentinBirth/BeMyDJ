package de.htw.bemydj.networkControl;

import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server Class for sending Data to Socket.
 * Implements Runnable and needs to be started after every transfer
 */
public class StreamingServer extends Thread{

    private static final String TAG = StreamingServer.class.toString();
    private Socket socket;
    private final FileDescriptor fileDescriptor;
    private final byte[] buffer = new byte[1024];


    public StreamingServer (FileDescriptor fileDescriptor){
        this.fileDescriptor = fileDescriptor;
    }


    @Override
    public void run() {
        // TODO Sending Logic untested
            try {
                ServerSocket serverSocket = new ServerSocket(8978);
                socket = serverSocket.accept();
                OutputStream outputStream = socket.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
                int len;
                while ((len = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                Log.e(TAG,e.toString());
            } finally {
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                          Log.e(TAG,"IO Exception");
                        }
                    }
                }
            }
    }
}
