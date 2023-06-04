package pointTwo.actorLogic.entities;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.main.utility.Pair;
import pointTwo.actorLogic.msgs.ActorBrushInterface;
import pointTwo.actorLogic.msgs.brush.SetupMsg;
import pointTwo.actorLogic.msgs.brushManager.AddBrushMsg;
import pointTwo.actorLogic.msgs.brushManager.RefreshViewMsg;
import pointTwo.actorLogic.msgs.brushManager.RemoveUserMsg;
import pointTwo.actorLogic.utilities.Utilities;
import pointTwo.actorLogic.msgs.ActorBrushManagerInterface;
import pointTwo.actorLogic.msgs.brushManager.BootMsg;
import pointTwo.pixelGrid.BrushManager;
import pointTwo.pixelGrid.PixelGrid;
import pointTwo.pixelGrid.PixelGridView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainBrushManagerActor extends AbstractBehavior<ActorBrushManagerInterface> {
    private final BrushManager brushManager;
    private final PixelGrid grid;
    private final int width;
    private final int height;
    private final int rows;
    private final int columns;
    private final Map<String, Pair<BrushManager.Brush, PixelGridView>> users;

    public MainBrushManagerActor(final ActorContext<ActorBrushManagerInterface> context) {
        super(context);
        final Random rand = new Random();
        users = new HashMap<>();
        this.rows = 40;
        this.columns = 40;
        this.width = 800;
        this.height = 800;

        this.brushManager = new BrushManager();
        this.grid = new PixelGrid(rows, columns);
        for (int i = 0; i < 10; i++) {
            this.grid.set(rand.nextInt(rows), rand.nextInt(columns), Utilities.randomColor());
        }
    }
    @Override
    public Receive<ActorBrushManagerInterface> createReceive() {
        return newReceiveBuilder()
                .onMessage(BootMsg.class, this::onBootMsg)
                .onMessage(AddBrushMsg.class, this::onAddBrushMsg)
                .onMessage(RefreshViewMsg.class, this::onRefreshViewMsg)
                .onMessage(RemoveUserMsg.class, this::onRemoveUserMsg)
                .build();
    }
    public static Behavior<ActorBrushManagerInterface> create() {
        return Behaviors.setup(MainBrushManagerActor::new);
    }

    private Behavior<ActorBrushManagerInterface> onRemoveUserMsg(final RemoveUserMsg msg) {
        this.brushManager.removeBrush(this.users.get(msg.getName()).first);
        this.users.get(msg.getName()).second.setVisible(false);
        this.users.get(msg.getName()).second.dispose();
        this.users.remove(msg.getName());
        if(this.users.isEmpty())
            System.exit(0);
        return this;
    }
    private Behavior<ActorBrushManagerInterface> onRefreshViewMsg(final RefreshViewMsg msg) {
        users.forEach((name, pair) -> pair.second.refresh());
        return this;
    }
    private Behavior<ActorBrushManagerInterface> onAddBrushMsg(final AddBrushMsg msg) {
        users.put(msg.getUserName(), new Pair(msg.getUserBrush(), msg.getUserView()));
        this.brushManager.addBrush(msg.getUserBrush());
        msg.getUserView().display();
    return this;
    }
    private Behavior<ActorBrushManagerInterface> onBootMsg(final BootMsg msg) {
        final Random rand = new Random();
        ActorRef<ActorBrushInterface> user = this.getContext()
                .spawn(BrushActor.create(), "BrushUser" + rand.nextInt());
        user.tell(new SetupMsg(this.grid, this.brushManager, this.height, this.width, this.getContext().getSelf()));
        return this;
    }
}
