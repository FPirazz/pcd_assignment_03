package ex3;

import ex2.actorLogic.utilities.Utilities;
import ex3.pixelGrid.BrushManager;
import ex3.pixelGrid.PixelGridView;
import ex3.message.SetupMessage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class BrushClient {

    private final CanvasServer server;
    private PixelGridView view;
    private BrushManager.Brush localBrush;
    private ex3.pixelGrid.BrushManager brushManager;
    private final String clientID;

    public BrushClient(CanvasServer server) throws RemoteException {

        this.server = server;
        this.clientID = UUID.randomUUID().toString();
        SetupMessage msg = server.setupClient();
        this.brushManager = msg.getBrushManager();

        this.localBrush = new BrushManager.Brush(msg.getWidth() / 2,
                msg.getHeight() / 2,
                Utilities.randomColor());

        this.view = new PixelGridView(msg.getGrid(),
                msg.getBrushManager(),
                msg.getWidth(),
                msg.getHeight(),
                this);


    }

    public static void main(String[] args) {
        try {
            // Obtain a reference to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote object by its binding name
            CanvasServer server = (CanvasServer) registry.lookup("CanvasServer");

            // Create the client application
            new BrushClient(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
