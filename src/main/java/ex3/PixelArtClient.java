package ex3;

import ex3.remote.LocalCanvasImpl;
import ex3.remote.RemoteBrushImpl;
import ex3.remote.interfaces.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class PixelArtClient {

    public static void main(String args[]) throws RemoteException, NotBoundException {

        String clientID = UUID.randomUUID().toString();

        RemoteBrush localBrush = new RemoteBrushImpl(clientID, ex2.actorLogic.utilities.Utilities.randomColor());

        LocalCanvasImpl localCanvas = new LocalCanvasImpl(clientID);
        LocalCanvas canvasStub = (LocalCanvas) UnicastRemoteObject.exportObject(localCanvas, 0);
        Registry registry = LocateRegistry.getRegistry("localhost", 0);
        RemoteBrushManager brushManager = (RemoteBrushManager) registry.lookup("brushManager");
        brushManager.addCanvas(canvasStub);


    }

}
