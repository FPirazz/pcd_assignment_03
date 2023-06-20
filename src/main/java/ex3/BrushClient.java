package ex3;

import ex3.message.SetupMessage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class BrushClient {

    private final CanvasServer server;
    private final String clientID;

    public BrushClient(CanvasServer server) throws RemoteException {

        this.server = server;
        this.clientID = UUID.randomUUID().toString();
        SetupMessage message = server.setupClient();

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
