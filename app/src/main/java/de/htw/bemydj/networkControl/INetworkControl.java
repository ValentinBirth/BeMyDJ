package de.htw.bemydj.networkControl;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;

import java.net.Socket;
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

    /** getter for broadcastreceiver
     * @return an broadcastreceiver
     */
    BroadcastReceiver getBroadCastReceiver();

    /** getter for discover peers listener
     * @return DiscoverPeersListener
     */
    MyDiscoverPeersListener getDiscoverPeersListener();

    /** getter for an connection listener
     * @return ConnectListener
     */
    MyConnectListener getConnectListener();

    /** getter for an peer list listener
     * @return PeerListListener
     */
    MyPeerListListener getPeerListListener();

    /** getter for an channel listener
     * @return ChannelListener
     */
    MyChannelListener getChannelListener();

    /** getter for an group info listener
     * @return GroupInfoListener
     */
    MyGroupInfoListener getGroupInfoListener();

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
    Socket getClientSocket();

    /** getter for a "server" socket
     * @return socket for sending clients
     */
    Socket getServerSocket();
}
