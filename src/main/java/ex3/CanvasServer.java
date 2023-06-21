package ex3;

import ex3.message.AddClientMessage;
import ex3.message.SetupMessage;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CanvasServer extends Remote {

    SetupMessage setupClient() throws RemoteException;
    void registerClient(AddClientMessage msg) throws RemoteException;
    void refreshView() throws RemoteException;

}
