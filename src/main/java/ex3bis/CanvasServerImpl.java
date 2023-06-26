package ex3bis;

import ex1.main.utility.Pair;
import ex3bis.message.AddClientMessage;
import ex3bis.message.SetupMessage;
import ex3bis.pixelGrid.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CanvasServerImpl implements CanvasServer {

    private final BrushManager brushManager;
    private final PixelGrid grid;
    private final int width;
    private final int height;
    private final Map<String, Pair<BrushManager.Brush, PixelGridView>> users;

    public CanvasServerImpl() {

        final Random rand = new Random();
        users = new HashMap<>();
        this.width = 400;
        this.height = 400;

        this.brushManager = new ex3bis.pixelGrid.BrushManager();
        this.grid = new PixelGrid(40, 40);

    }

    @Override
    public SetupMessage setupClient() throws RemoteException {
        return new SetupMessage(this.grid, this.brushManager, this.height, this.width);
    }

    @Override
    public void registerClient(AddClientMessage msg) throws RemoteException {
        users.put(msg.getClientID(), new Pair(msg.getUserBrush(), msg.getUserView()));
        this.brushManager.addBrush(msg.getUserBrush());
        // msg.getUserView().display();
    }

    @Override
    public ex3bis.pixelGrid.PixelGrid moveBrush(ex3bis.pixelGrid.BrushManager.Brush localBrush) throws RemoteException {
        return null;
    }

    @Override
    public ex3bis.pixelGrid.PixelGrid changeColor(ex3bis.pixelGrid.PixelGrid localGrid) throws RemoteException {
        // foreach user: remote call to update grid
        return null;
    }

    public static void main(String args[]) {

        try {
            CanvasServer server = new CanvasServerImpl();
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
