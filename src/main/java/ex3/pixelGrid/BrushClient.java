package ex3.pixelGrid;

import ex2.actorLogic.utilities.Utilities;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class BrushClient {

    private final CanvasServer server;
    private RemoteView view;
    private RemoteBrush localBrush;
    private RemoteBrushManager brushManager;
    private final String clientID;

    public BrushClient(CanvasServer server) throws RemoteException {

        this.server = server;
        this.clientID = UUID.randomUUID().toString();

        SetupMessage msg = server.setupClient();

        this.brushManager = msg.getBrushManager();

        this.localBrush = new RemoteBrushImpl(msg.getWidth() / 2,
                msg.getHeight() / 2,
                Utilities.randomColor());

        this.view = new RemoteViewImpl(msg.getGrid(),
                msg.getBrushManager(),
                msg.getWidth(),
                msg.getHeight(),
                this);

        ((RemoteViewImpl) this.view).addMouseMovedListener((x, y) -> {
            ((RemoteBrushImpl) this.localBrush).updatePosition(x, y);
            server.refreshView();
        });
        ((RemoteViewImpl) this.view).addPixelGridEventListener((x, y) -> {
            msg.getGrid().set(x, y, ((RemoteBrushImpl) this.brushManager).getColor());
            server.refreshView();
        });


        ((RemoteViewImpl) this.view).addColorChangedListener(((RemoteBrushImpl) this.localBrush)::setColor);
        UnicastRemoteObject.exportObject(this.view, 0);
        UnicastRemoteObject.exportObject(this.localBrush, 0);
        server.registerClient(new AddClientMessage(this.clientID, this.localBrush, this.view));
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
