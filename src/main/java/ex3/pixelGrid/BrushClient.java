package ex3.pixelGrid;

import ex2.actorLogic.utilities.Utilities;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class BrushClient {

    private final CanvasServer server;
    private RemoteViewImpl view;
    private RemoteBrushImpl localBrush;
    private RemoteBrushManagerImpl brushManager;
    private final String clientID;

    public BrushClient(CanvasServer server) throws RemoteException {

        this.server = server;
        this.clientID = UUID.randomUUID().toString();
        /* SetupMessage msg = server.setupClient();
        this.brushManager = (RemoteBrushManagerImpl) msg.getBrushManager();

        this.localBrush = new RemoteBrushImpl(msg.getWidth() / 2,
                msg.getHeight() / 2,
                Utilities.randomColor());

        this.view = new RemoteViewImpl((RemotePixelGridImpl) msg.getGrid(),
                (RemoteBrushManagerImpl) msg.getBrushManager(),
                msg.getWidth(),
                msg.getHeight(),
                this);

        this.view.addMouseMovedListener((x, y) -> {
            this.localBrush.updatePosition(x, y);
            server.refreshView();
        });
        this.view.addPixelGridEventListener((x, y) -> {
            msg.getGrid().set(x, y, this.localBrush.getColor());
            server.refreshView();
        });

        */

        this.brushManager = (RemoteBrushManagerImpl) server.getBrushManager();
        this.localBrush = new RemoteBrushImpl(server.getWidth() / 2,
                server.getHeight() / 2,
                Utilities.randomColor());
        this.view = new RemoteViewImpl((RemotePixelGridImpl) server.getGrid(),
                (RemoteBrushManagerImpl) server.getBrushManager(),
                server.getWidth(),
                server.getHeight(),
                this);
        this.view.addMouseMovedListener((x, y) -> {
            this.localBrush.updatePosition(x, y);
            server.refreshView();
        });



        this.view.addPixelGridEventListener((x, y) -> {
            server.getGrid().set(x, y, this.localBrush.getColor());
            server.refreshView();
        });



        this.view.addColorChangedListener(this.localBrush::setColor);
        UnicastRemoteObject.exportObject(this.view, 0);
        UnicastRemoteObject.exportObject(this.localBrush, 0);
        server.registerClient(new AddClientMessage(this.clientID, (BrushManager.Brush) this.localBrush, this.view));
    }

    public static void main(String[] args) {
        try {
            // Obtain a reference to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 0);

            // Look up the remote object by its binding name
            CanvasServer server = (CanvasServer) registry.lookup("canvasServer");

            // Create the client application
            new BrushClient(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
