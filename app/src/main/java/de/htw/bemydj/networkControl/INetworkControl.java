package de.htw.bemydj.networkControl;

import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;

import java.net.Socket;

/**
 * interface for NetworkControl
 */
public interface INetworkControl {
    /** getter for WifiP2pManager.Channel
     * @return WifiP2pManager.Channel
     */
    WifiP2pManager.Channel getChannel();

    /** getter for WifiP2pManager
     * @return a WifiP2pManager
     */
    WifiP2pManager getWifiP2pManager();

    /** getter for IntentFilter
     * @return an IntentFilter
     */
    IntentFilter getIntentFilter();

    /** getter for a "client" socket
     * @return socket for receiving clients
     */
    Socket getClientSocket();

    /** getter for a "server" socket
     * @return socket for sending clients
     */
    Socket getServerSocket();
}
