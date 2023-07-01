package ex3;

import ex3.view.PixelGridView;
import ex3.view.RemoteBrushImpl;
import ex3.view.interfaces.*;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class PixelArtClient {

    public static void main(String args[]) throws RemoteException, NotBoundException {

        String clientID = UUID.randomUUID().toString();
        Registry registry = LocateRegistry.getRegistry("localhost", 0);

        // Look up the remote object by its binding name
        RemoteBrushManager brushManager = (RemoteBrushManager) registry.lookup("brushManager");
        RemotePixelGrid pixelGrid = (RemotePixelGrid) registry.lookup("pixelGrid");

        PixelGridView view = new PixelGridView(clientID, pixelGrid, brushManager, 600, 600);

        RemoteBrush localBrush = new RemoteBrushImpl(clientID, ex2.actorLogic.utilities.Utilities.randomColor());
        brushManager.addBrush(clientID, localBrush);

        view.addMouseMovedListener((x, y) -> {
            brushManager.updateBrushPosition(clientID, x, y);
            view.refresh();
        });

        view.addPixelGridEventListener((x, y) -> {
            pixelGrid.set(x, y, brushManager.getColor(clientID));
            view.refresh();
        });

        view.addColorChangedListener( color -> {
            localBrush.setColor(color);
            brushManager.addBrush(clientID, localBrush);
        });

        new Timer(1000, e -> {
            view.refresh();
        }).start();


        view.setTitle("PixelArt - " + clientID);
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.display();
    }

}
