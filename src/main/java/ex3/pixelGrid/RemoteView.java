package ex3.pixelGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {
    void display() throws RemoteException;

}
