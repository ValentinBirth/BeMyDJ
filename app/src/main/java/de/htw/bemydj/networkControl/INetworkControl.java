package de.htw.bemydj.networkControl;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.util.List;

import de.htw.bemydj.djData.UserImpl;

/**
 * interface for NetworkControl
 */
public interface INetworkControl {

    /** starts wifi p2p discovery
     */
    void startPeerDiscovery();

    /** stops wifi p2p discovery
     */
    void stopPeerDiscovery();

    /** connect to given device
     * @param deviceAddress address of the device
     */
    void connect (String deviceAddress);

    /** getter for IntentFilter
     * @return an IntentFilter
     */
    IntentFilter getIntentFilter();

    /** getter for broadcastreceiver
     * @return an broadcastreceiver
     */
    BroadcastReceiver getBroadCastReceiver();

    /** getter for an list of peers
     * @return list with peers
     */
    List<WifiP2pDevice> getPeerList();

    /** getter for an list of peer for recycler View
     * @return list with peers
     */
    List<UserImpl> getAvailablePeerList();

    /** getter for an list of peers in the group for recycler View
     * @return list with peers
     */
    List<UserImpl> getGroupPeerList();

    /** getter for a "client" socket
     * @return socket for receiving clients
     */
    StreamingClient getClientSocket();

    /** getter for a "server" socket
     * @return socket for sending clients
     */
    StreamingServer getServerSocket();

    /**
     * creates an Server Socket
     * @param fileDescriptor fd to the file which should be transferred
     */
    void createServerSocket(FileDescriptor fileDescriptor);

    /**
     * creates an Client Socket
     * @param groupOwnerAdress GroupOwner of P2P Group
     */
    void createClientSocket(InetAddress groupOwnerAdress);
}
