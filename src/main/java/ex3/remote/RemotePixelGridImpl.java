package ex3.remote;

import ex3.remote.interfaces.RemotePixelGrid;

import java.rmi.RemoteException;
import java.util.Arrays;

public class RemotePixelGridImpl implements RemotePixelGrid {
	private final int nRows;
	private final int nColumns;
	private final int[][] grid;
	
	public RemotePixelGridImpl(final int nRows, final int nColumns) {
		this.nRows = nRows;
		this.nColumns = nColumns;
		grid = new int[nRows][nColumns];
	}

	public synchronized void clear() throws RemoteException {
		for (int i = 0; i < nRows; i++) {
			Arrays.fill(grid[i], 0);
		}
	}
	
	public synchronized void set(final int x, final int y, final int color) throws RemoteException {
		grid[y][x] = color;
	}
	
	public synchronized int get(int x, int y) throws RemoteException {
		return grid[y][x];
	}
	
	public synchronized int getNumRows() throws RemoteException {
		return this.nRows;
	}

	public synchronized int getNumColumns() throws RemoteException {
		return this.nColumns;
	}
	
}
