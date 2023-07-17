package ex3.remote.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePixelGrid extends Remote {
    void set(int x, int y, int color) throws RemoteException;

    int getNumColumns() throws RemoteException;

    int getNumRows() throws RemoteException;

    int get(int column, int row) throws RemoteException;
}
