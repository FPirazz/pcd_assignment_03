package ex3;

import ex1.main.utility.Pair;
import ex3.message.AddClientMessage;
import ex3.message.SetupMessage;
import ex3.pixelGrid.RemoteBrush;
import ex3.pixelGrid.RemoteBrushManager;
import ex3.pixelGrid.RemotePixelGrid;
import ex3.pixelGrid.RemoteView;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CanvasServerImpl implements CanvasServer{

    private final RemoteBrushManager brushManager;
    private final RemotePixelGrid grid;
    private final int width;
    private final int height;
    private final Map<String, Pair<RemoteBrush, RemoteView>> users;

    public CanvasServerImpl(RemoteBrushManager brushManager, RemotePixelGrid pixelGrid) {

        final Random rand = new Random();
        users = new HashMap<>();
        this.width = 400;
        this.height = 400;

        this.brushManager = brushManager;
        this.grid = pixelGrid;

    }

    @Override
    public SetupMessage setupClient() throws RemoteException {
        return new SetupMessage(this.grid, this.brushManager, this.height, this.width);
    }

    @Override
    public void registerClient(AddClientMessage msg) throws RemoteException {
        users.put(msg.getClientID(), new Pair(msg.getUserBrush(), msg.getUserView()));
        this.brushManager.addBrush(msg.getUserBrush());
        msg.getUserView().display();
    }

    @Override
    public void refreshView() throws RemoteException {
        users.forEach((name, pair) -> pair.second.refresh());
    }

    public static void main(String args[]) {

        try {
            RemotePixelGrid pixelGrid = new RemotePixelGrid(40, 40);
            UnicastRemoteObject.exportObject(pixelGrid, 0);
            RemoteBrushManager brushManager = new RemoteBrushManager();
            UnicastRemoteObject.exportObject(brushManager, 0);
            CanvasServer server = new CanvasServerImpl(brushManager, pixelGrid);
            CanvasServer canvasStub = (CanvasServer) UnicastRemoteObject.exportObject(server, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("canvasServer", canvasStub);

            System.out.println("Objects registered.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
