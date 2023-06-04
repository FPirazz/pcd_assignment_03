package pointTwo.actorLogic.entities;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointTwo.actorLogic.msgs.ActorBrushManagerInterface;
import pointTwo.actorLogic.msgs.brush.AddBrushRequestMsg;
import pointTwo.actorLogic.msgs.brush.RemoveUserRequestMsg;
import pointTwo.actorLogic.msgs.brushManager.BootMsg;
import pointTwo.actorLogic.msgs.brushManager.RefreshViewMsg;
import pointTwo.actorLogic.msgs.brushManager.RemoveUserMsg;
import pointTwo.actorLogic.utilities.Utilities;
import pointTwo.actorLogic.msgs.ActorBrushInterface;
import pointTwo.actorLogic.msgs.brush.SetupMsg;
import pointTwo.actorLogic.msgs.brushManager.AddBrushMsg;
import pointTwo.pixelGrid.BrushManager;
import pointTwo.pixelGrid.PixelGridView;

public class BrushActor extends AbstractBehavior<ActorBrushInterface> {
    private PixelGridView view;
    private BrushManager.Brush localBrush;
    private String name;
    private ActorRef<ActorBrushManagerInterface> brushManager;
    public BrushActor(final ActorContext<ActorBrushInterface> context) {
        super(context);
        this.name = this.getContext().getSelf().path().name();
    }
    @Override
    public Receive<ActorBrushInterface> createReceive() {
        return newReceiveBuilder()
                .onMessage(SetupMsg.class, this::onSetupMsg)
                .onMessage(AddBrushRequestMsg.class, this::onAddBrushRequest)
                .onMessage(RemoveUserRequestMsg.class, this::onRemoveUserRequestMsg)
                .build();
    }
    public static Behavior<ActorBrushInterface> create() {
        return Behaviors.setup(BrushActor::new);
    }

    private Behavior<ActorBrushInterface> onRemoveUserRequestMsg(final RemoveUserRequestMsg msg) {
        brushManager.tell(new RemoveUserMsg(this.name));
        return this;
    }
    private Behavior<ActorBrushInterface> onAddBrushRequest(final AddBrushRequestMsg msg) {
        brushManager.tell(new BootMsg());
        return this;
    }
    private Behavior<ActorBrushInterface> onSetupMsg(final SetupMsg msg) {
        this.brushManager = msg.getMainBrushManagerActor();

        this.localBrush = new BrushManager.Brush(msg.getWidth() / 2,
                msg.getHeight() / 2,
                Utilities.randomColor());

        this.view = new PixelGridView(msg.getGrid(),
                msg.getBrushManager(),
                msg.getWidth(),
                msg.getHeight(),
                this.getContext().getSelf());
        this.view.addMouseMovedListener((x, y) -> {
            this.localBrush.updatePosition(x, y);
            msg.getMainBrushManagerActor().tell(new RefreshViewMsg());
        });
        this.view.addPixelGridEventListener((x, y) -> {
            msg.getGrid().set(x, y, this.localBrush.getColor());
            msg.getMainBrushManagerActor().tell(new RefreshViewMsg());
        });
        this.view.addColorChangedListener(this.localBrush::setColor);

        msg.getMainBrushManagerActor().tell(new AddBrushMsg(this.name, this.localBrush, this.view));
        return this;
    }
}
