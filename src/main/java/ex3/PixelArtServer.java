package ex3;

import ex3.view.*;
import ex3.view.interfaces.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PixelArtServer {

    public static void main(String args[]) {

        try {
            RemoteBrushManager brushManager = new RemoteBrushManagerImpl();
            RemoteBrushManager brushManagerStub = (RemoteBrushManager) UnicastRemoteObject.exportObject(brushManager, 0);

            RemotePixelGrid grid = new RemotePixelGridImpl(40,40);
            RemotePixelGrid gridStub = (RemotePixelGrid) UnicastRemoteObject.exportObject(grid, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("brushManager", brushManagerStub);
            registry.rebind("pixelGrid", gridStub);

            System.out.println("Objects registered.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
