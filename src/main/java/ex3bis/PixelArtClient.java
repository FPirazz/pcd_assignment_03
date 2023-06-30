package ex3bis;

import ex3.pixelGrid.RemoteViewImpl;
import ex3bis.view.PixelGridView;
import ex3bis.view.RemoteBrushImpl;
import ex3bis.view.interfaces.*;

import javax.swing.*;
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

    public PixelArtClient(RemoteBrushManager brushManager, RemotePixelGrid pixelGrid) throws RemoteException {

        this.clientID = UUID.randomUUID().toString();
        this.brushManager = brushManager;
        this.pixelGrid = pixelGrid;
        this.view = new PixelGridView(this.pixelGrid, this.brushManager, 600, 600);

        this.localBrush = new RemoteBrushImpl(clientID, ex2.actorLogic.utilities.Utilities.randomColor());
        brushManager.addBrush(this.localBrush);

        this.view.addMouseMovedListener((x, y) -> {
            this.localBrush.updatePosition(x, y);
        });

        this.view.addPixelGridEventListener((x, y) -> {
            this.pixelGrid.set(x, y, this.brushManager.getColor());
        });

        this.view.addColorChangedListener(this.localBrush::setColor);

        new Timer(1, e -> {   // 1000 / 41 = roughly 24 refreshes per second
            this.view.refresh();
        }).start();

        this.view.display();

    }

    public static void main(String[] args) {
        try {
            // Obtain a reference to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 0);

            // Look up the remote object by its binding name
            RemoteBrushManager brushManager = (RemoteBrushManager) registry.lookup("brushManager");
            RemotePixelGrid pixelGrid = (RemotePixelGrid) registry.lookup("pixelGrid");

            // Create the client application
            new PixelArtClient(brushManager, pixelGrid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
