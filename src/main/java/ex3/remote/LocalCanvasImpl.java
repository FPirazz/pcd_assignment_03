package ex3.remote;

import ex3.remote.interfaces.RemoteBrush;
import ex3.remote.interfaces.RemoteBrushManager;
import ex3.remote.interfaces.RemotePixelGrid;
import ex3.remote.interfaces.LocalCanvas;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LocalCanvasImpl implements LocalCanvas {

    private final String clientID;
    private final PixelGridView view;

    public LocalCanvasImpl(String clientID) throws RemoteException, NotBoundException {
        this.clientID = clientID;

        Registry registry = LocateRegistry.getRegistry("localhost", 0);

        // Look up the remote object by its binding name
        RemoteBrushManager brushManager = (RemoteBrushManager) registry.lookup("brushManager");
        RemotePixelGrid pixelGrid = (RemotePixelGrid) registry.lookup("pixelGrid");

        this.view = new PixelGridView(clientID, pixelGrid, brushManager, 600, 600);
        RemoteBrush localBrush = new RemoteBrushImpl(this.clientID, ex2.actorLogic.utilities.Utilities.randomColor());
        brushManager.addBrush(clientID, localBrush);

        view.addMouseMovedListener((x, y) -> {
            brushManager.updateBrushPosition(clientID, x, y);
        });

        view.addPixelGridEventListener((x, y) -> {
            pixelGrid.set(x, y, brushManager.getColor(clientID));
        });

        view.addColorChangedListener( color -> {
            localBrush.setColor(color);
            brushManager.addBrush(clientID, localBrush);
        });

        view.setTitle("PixelArt - " + clientID);
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.display();


    }

    @Override
    public void notifyChange() throws RemoteException {
        view.refresh();
    }
}
