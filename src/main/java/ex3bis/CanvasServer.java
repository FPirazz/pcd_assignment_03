package ex3bis;


import ex3bis.message.AddClientMessage;
import ex3bis.message.SetupMessage;
import ex3bis.pixelGrid.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CanvasServer extends Remote {

    SetupMessage setupClient() throws RemoteException;
    void registerClient(AddClientMessage msg) throws RemoteException;
    PixelGrid moveBrush(BrushManager.Brush localBrush) throws RemoteException;
    PixelGrid changeColor(PixelGrid localGrid) throws RemoteException;


}
