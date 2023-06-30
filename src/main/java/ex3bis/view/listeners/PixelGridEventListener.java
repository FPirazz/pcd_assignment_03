package ex3bis.view.listeners;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PixelGridEventListener {
	void selectedCell(int x, int y) throws RemoteException;
}
