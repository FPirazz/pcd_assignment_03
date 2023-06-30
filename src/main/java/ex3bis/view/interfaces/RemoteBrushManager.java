package ex3bis.view.interfaces;

import java.awt.Graphics2D;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteBrushManager extends Remote {

    void addBrush(final String clientID, final RemoteBrush brush) throws RemoteException;

    void removeBrush(final String clientID) throws RemoteException;

    List<RemoteBrush> getBrushes() throws RemoteException;

    int getColor(String clientID) throws RemoteException;

    void updateBrushPosition(String clientID, int x, int y) throws RemoteException;
}
