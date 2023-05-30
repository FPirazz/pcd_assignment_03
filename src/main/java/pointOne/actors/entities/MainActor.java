package pointOne.actors.entities;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.actors.entities.children.DirectoryExplorer;
import pointOne.actors.entities.children.FileReader;
import pointOne.actors.msgs.BootMsg;
import pointOne.actors.msgs.DirectoryExplorerMsg;
import pointOne.actors.msgs.FileReaderMsg;
import pointOne.actors.msgs.StartMsg;
import pointOne.main.AbstractSourceAnalyser;

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
        ActorRef<DirectoryExplorerMsg> directoryExplorer = this.getContext().spawn(DirectoryExplorer.create(), "DirectoryExplorer");
        ActorRef<FileReaderMsg> fileReader = this.getContext().spawn(FileReader.create(), "FileReader");
        directoryExplorer.tell(new StartMsg(msg.initDirectory, fileReader));
        return this;
    }

}
