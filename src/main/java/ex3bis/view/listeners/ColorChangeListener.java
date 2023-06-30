package ex3bis.view.listeners;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ColorChangeListener {
    void colorChanged(int color) throws RemoteException;
}
