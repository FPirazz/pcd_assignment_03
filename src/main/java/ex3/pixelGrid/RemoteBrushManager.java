package ex3.pixelGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBrushManager extends Remote {

    void addBrush(BrushManager.Brush userBrush) throws RemoteException;
}
