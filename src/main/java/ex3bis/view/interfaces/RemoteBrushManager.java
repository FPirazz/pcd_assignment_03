package ex3bis.view.interfaces;

import java.awt.Graphics2D;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteBrushManager extends Remote {

    void addBrush(final RemoteBrush brush) throws RemoteException;

    void removeBrush(final RemoteBrush brush) throws RemoteException;

    List<RemoteBrush> getBrushes() throws RemoteException;

    int getColor() throws RemoteException;
}
