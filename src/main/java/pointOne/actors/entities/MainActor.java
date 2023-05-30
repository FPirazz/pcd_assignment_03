package pointOne.actors.entities;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.actors.entities.children.DirectoryExplorer;
import pointOne.actors.entities.children.FileReader;
import pointOne.actors.msgs.*;
import pointOne.main.AbstractSourceAnalyser;

public class MainActor extends AbstractBehavior<MainActorMsg> {
    public MainActor(final ActorContext<MainActorMsg> context) {
        super(context);
    }

    @Override
    public Receive<MainActorMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(BootMsg.class, this::onBootMsg)
                .onMessage(StopMsg.class, this::onStopMsg)
                .build();
    }

    public static Behavior<MainActorMsg> create() { return Behaviors.setup(MainActor::new); }

    private Behavior<MainActorMsg> onStopMsg(final StopMsg msg) {
        msg.analyser.printTopFiles(msg.analyser.topFiles);
        msg.analyser.printIntervals(msg.analyser.intervals);
        Behaviors.stopped();
        return this;
    }

    private Behavior<MainActorMsg> onBootMsg(final BootMsg msg) {
        ActorRef<DirectoryExplorerMsg> directoryExplorer = this.getContext().spawn(DirectoryExplorer.create(), "DirectoryExplorer");
        ActorRef<FileReaderMsg> fileReader = this.getContext().spawn(FileReader.create(), "FileReader");
        directoryExplorer.tell(new StartMsg(msg.initDirectory, fileReader, msg.analyser, this.getContext().getSelf()));
        return this;
    }

}
