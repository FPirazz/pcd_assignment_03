package ex3.pixelGrid;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBrushManager extends Remote {

    void addBrush(RemoteBrush userBrush) throws RemoteException;
    void draw(Graphics2D g) throws RemoteException;
    void removeBrush(BrushManager.Brush brush) throws RemoteException;

}
