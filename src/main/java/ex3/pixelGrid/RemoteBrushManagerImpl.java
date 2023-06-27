package ex3.pixelGrid;

import java.io.Serializable;
import java.rmi.Remote;

public class RemoteBrushManagerImpl extends BrushManager implements RemoteBrushManager, Serializable {

        public RemoteBrushManagerImpl() {
            super();
        }

        @Override
        public void addBrush(BrushManager.Brush userBrush) {
            super.addBrush(userBrush);
        }
}
