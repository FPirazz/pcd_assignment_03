package ex1.actors.entities;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import ex1.actors.ActorsAnalyser;
import ex1.actors.entities.children.DirectoryExplorer;
import ex1.actors.entities.children.FileReader;
import ex1.actors.msgs.directoryExplorer.StartMsg;
import ex1.actors.msgs.DirectoryExplorerMsg;
import ex1.actors.msgs.FileReaderMsg;
import ex1.actors.msgs.MainActorMsg;
import ex1.actors.msgs.mainActor.BootMsg;
import ex1.actors.msgs.mainActor.StopMsg;
import ex1.actors.msgs.mainActor.UpdateGUIMsg;
import ex1.main.view.AnalyserView;

import java.util.Optional;

public class MainActor extends AbstractBehavior<MainActorMsg> {
    public static Optional<AnalyserView> view;
    public static ActorsAnalyser context;
    public MainActor(final ActorContext<MainActorMsg> context) {
        super(context);
    }

    @Override
    public Receive<MainActorMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(BootMsg.class, this::onBootMsg)
                .onMessage(StopMsg.class, this::onStopMsg)
                .onMessage(UpdateGUIMsg.class, this::onUpdateGUIMsg)
                .build();
    }

    public static Behavior<MainActorMsg> create(final ActorsAnalyser contextGot,
                                                final Optional<AnalyserView> viewGot) {
        view = viewGot;
        context = contextGot;
        return Behaviors.setup(MainActor::new);
    }

    private Behavior<MainActorMsg> onUpdateGUIMsg(final UpdateGUIMsg msg) {
        view.ifPresent(view -> view.update(context.intervals, context.topFiles, context.ranges, context.maxL));
        return this;
    }

    private Behavior<MainActorMsg> onStopMsg(final StopMsg msg) {
        this.getContext().getSystem().terminate();
        return this;
    }

    private Behavior<MainActorMsg> onBootMsg(final BootMsg msg) {
        ActorRef<DirectoryExplorerMsg> directoryExplorer = this.getContext().spawn(DirectoryExplorer.create(), "DirectoryExplorer");
        ActorRef<FileReaderMsg> fileReader = this.getContext().spawn(FileReader.create(this.getContext().getSelf()), "FileReader");
        directoryExplorer.tell(new StartMsg(msg.initDirectory, fileReader, msg.analyser, this.getContext().getSelf(), msg.updateGUI));
        return this;
    }

}
