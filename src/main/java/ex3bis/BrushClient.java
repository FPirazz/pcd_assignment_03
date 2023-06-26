package ex3bis;

import ex2.actorLogic.utilities.Utilities;
import ex3bis.message.AddClientMessage;
import ex3bis.message.SetupMessage;
import ex3bis.pixelGrid.BrushManager.Brush;
import ex3bis.pixelGrid.BrushManager;
import ex3bis.pixelGrid.PixelGrid;
import ex3bis.pixelGrid.PixelGridView;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class BrushClient {

    private final CanvasServer server;

    private PixelGrid grid;
    private PixelGridView view;
    private BrushManager.Brush localBrush;
    private BrushManager brushManager;
    private final String clientID;

    public BrushClient(CanvasServer server) throws RemoteException {

        this.server = server;
        this.clientID = UUID.randomUUID().toString();
        SetupMessage msg = server.setupClient();
        this.brushManager = msg.getBrushManager();

        this.localBrush = new BrushManager.Brush(msg.getWidth() / 2,
                msg.getHeight() / 2,
                Utilities.randomColor());

        this.grid = msg.getGrid();

        this.view = new PixelGridView(msg.getGrid(),
                msg.getBrushManager(),
                msg.getWidth(),
                msg.getHeight());

        this.view.addMouseMovedListener((x, y) -> {
            this.localBrush.updatePosition(x, y);
            PixelGrid grid = server.moveBrush(this.localBrush);
            this.view.updateGrid(grid);
        });
        this.view.addPixelGridEventListener((x, y) -> {
            this.grid.set(x, y, this.localBrush.getColor());
            PixelGrid grid = server.changeColor(this.grid);
            this.view.updateGrid(grid);
        });
        this.view.addColorChangedListener(this.localBrush::setColor);

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
