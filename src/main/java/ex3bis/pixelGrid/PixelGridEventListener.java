package ex3bis.pixelGrid;

import java.rmi.RemoteException;

public interface PixelGridEventListener {
	void selectedCell(int x, int y) throws RemoteException;
}
