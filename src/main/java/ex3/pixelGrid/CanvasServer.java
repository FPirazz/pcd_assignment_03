package ex3.pixelGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CanvasServer extends Remote {

    SetupMessage setupClient() throws RemoteException;

    RemotePixelGridImpl getGrid() throws RemoteException;
    RemoteBrushManager getBrushManager() throws RemoteException;
    int getHeight() throws RemoteException;
    int getWidth() throws RemoteException;
    void registerClient(AddClientMessage msg) throws RemoteException;
    void refreshView() throws RemoteException;

}
