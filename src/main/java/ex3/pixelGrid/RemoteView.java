package ex3.pixelGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {
    void refresh() throws RemoteException;

    void display() throws RemoteException;

}
