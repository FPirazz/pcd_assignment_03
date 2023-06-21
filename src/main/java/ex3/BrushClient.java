package ex3;

import ex2.actorLogic.utilities.Utilities;
import ex3.message.AddClientMessage;
import ex3.message.SetupMessage;
import ex3.pixelGrid.RemoteBrush;
import ex3.pixelGrid.RemoteBrushManager;
import ex3.pixelGrid.RemoteView;

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

        this.localBrush = new RemoteBrush(msg.getWidth() / 2,
                msg.getHeight() / 2,
                Utilities.randomColor());

        this.view = new RemoteView(msg.getGrid(),
                msg.getBrushManager(),
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
        this.view.addColorChangedListener(this.localBrush::setColor);
        UnicastRemoteObject.exportObject(this.view, 0);
        UnicastRemoteObject.exportObject(this.localBrush, 0);
        server.registerClient(new AddClientMessage(this.clientID, this.localBrush, this.view));
    }

    public static void main(String[] args) {
        try {
            // Obtain a reference to the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote object by its binding name
            CanvasServer server = (CanvasServer) registry.lookup("canvasServer");

            // Create the client application
            new BrushClient(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
