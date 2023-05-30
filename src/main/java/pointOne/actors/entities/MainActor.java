package pointOne.actors.entities;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.actors.msgs.BootMsg;

public class MainActor extends AbstractBehavior<BootMsg> {
    public MainActor(final ActorContext<BootMsg> context) {
        super(context);
    }

    @Override
    public Receive<BootMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(BootMsg.class, this::onBootMsg)
                .build();
    }

    public static Behavior<BootMsg> create() { return Behaviors.setup(MainActor::new); }

    private Behavior<BootMsg> onBootMsg(final BootMsg msg) {
        return this;
    }

}
