package de.htw.bemydj.networkControl;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class StreamingClient extends Thread{

    private static final String TAG = StreamingClient.class.toString();
    Socket socket;
    InetAddress hostAddr;

    public StreamingClient(InetAddress hostAddr){
        this.hostAddr = hostAddr;
        socket = new Socket();
    }

    @Override
    public void run() {
        try {
            socket.connect(new InetSocketAddress(hostAddr,8978),500);
            //TODO Receiving Logic missing
        } catch (IOException e) {
            Log.e(TAG,e.toString());
        }
    }
}
