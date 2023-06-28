package ex3.pixelGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePixelGrid extends Remote {

    void set(int x, int y, int color) throws RemoteException;

    int get(int x, int y) throws RemoteException;


    int getNumRows() throws RemoteException;


    int getNumColumns() throws RemoteException;


}
