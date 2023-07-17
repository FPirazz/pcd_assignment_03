package ex3.remote.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LocalCanvas extends Remote {

    void notifyChange() throws RemoteException;
}
