package ex3.pixelGrid;

import java.rmi.RemoteException;

public interface MouseMovedListener {
    void mouseMoved(int x, int y) throws RemoteException;
}
