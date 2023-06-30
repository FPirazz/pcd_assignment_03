package ex3bis;

import ex3bis.view.PixelGridView;
import ex3bis.view.RemoteBrushImpl;
import ex3bis.view.interfaces.*;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class PixelArtClient {

    private final RemoteBrushManager brushManager;
    private final RemotePixelGrid pixelGrid;
    private final PixelGridView view;

    private final RemoteBrush localBrush;

    private final String clientID;

    public PixelArtClient() throws RemoteException, NotBoundException {

        this.clientID = UUID.randomUUID().toString();
        Registry registry = LocateRegistry.getRegistry("localhost", 0);

        // Look up the remote object by its binding name
        RemoteBrushManager brushManager = (RemoteBrushManager) registry.lookup("brushManager");
        RemotePixelGrid pixelGrid = (RemotePixelGrid) registry.lookup("pixelGrid");
        this.brushManager = brushManager;
        this.pixelGrid = pixelGrid;
        this.view = new PixelGridView(this.clientID, this.pixelGrid, this.brushManager, 800, 800);

        this.localBrush = new RemoteBrushImpl(clientID, ex2.actorLogic.utilities.Utilities.randomColor());
        brushManager.addBrush(this.clientID, this.localBrush);

        this.view.addMouseMovedListener((x, y) -> {
            this.localBrush.updatePosition(x, y);
            this.brushManager.updateBrushPosition(this.clientID, x, y);
            this.view.refresh();
        });

        this.view.addPixelGridEventListener((x, y) -> {
            this.pixelGrid.set(x, y, this.brushManager.getColor(this.clientID));
            this.view.refresh();
        });

        this.view.addColorChangedListener( color -> {
            this.localBrush.setColor(color);
            this.brushManager.addBrush(this.clientID, this.localBrush);
        });


        new Timer(1, e -> {
            this.view.refresh();
        }).start();
        this.view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.view.display();

    }

    public static void main(String args[]) {
        try {
            // Create the client application
            new PixelArtClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
