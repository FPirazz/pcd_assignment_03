package ex3;

import ex2.pixelGrid.BrushManager;
import ex2.pixelGrid.PixelGridView;
import ex3.message.SetupMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CanvasServer extends Remote {

    SetupMessage setupClient() throws RemoteException;
    void registerClient(String userName,
                        BrushManager.Brush userBrush,
                        PixelGridView userView) throws RemoteException;
    void refreshView() throws RemoteException;

}
