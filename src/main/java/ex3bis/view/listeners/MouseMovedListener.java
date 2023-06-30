package ex3bis.view.listeners;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MouseMovedListener {
    void mouseMoved(int x, int y) throws RemoteException;
}
