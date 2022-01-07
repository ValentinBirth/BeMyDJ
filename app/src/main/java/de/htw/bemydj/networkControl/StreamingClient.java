package de.htw.bemydj.networkControl;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client Class for receiving Data from Socket.
 * Implements Runnable and needs to be started after every transfer
 */
public class StreamingClient extends Thread{

    private static final String TAG = StreamingClient.class.toString();
    private final Socket socket;
    private InetAddress hostAddr;
    private byte[] buffer = new byte[1024];

    public StreamingClient(InetAddress hostAddr){
        this.hostAddr = hostAddr;
        socket = new Socket();
    }

    @Override
    public void run() {
        //TODO Receiving Logic untested
        try {
            socket.connect(new InetSocketAddress(hostAddr,8978),500);
            final File f = new File(Environment.getExternalStorageDirectory() + "/"
                    + "de.htw.bemydj/wifip2pshared-" + System.currentTimeMillis()
                    + ".mp3");
            File dirs = new File(f.getParent());
            if (!dirs.exists())
                dirs.mkdirs();
            f.createNewFile();
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            socket.close();
        } catch (IOException e) {
            Log.e(TAG,e.toString());
        } finally {
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
