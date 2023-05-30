package pointOne.actors.entities.children;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.actors.msgs.*;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DirectoryExplorer extends AbstractBehavior<DirectoryExplorerMsg> {
    private Queue<String> queue;
    public DirectoryExplorer(final ActorContext<DirectoryExplorerMsg> context) {
        super(context);
        this.queue = new LinkedBlockingQueue<>();
    }
    @Override
    public Receive<DirectoryExplorerMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(StartMsg.class, this::onStartMsg)
                .build();
    }
    public static Behavior<DirectoryExplorerMsg> create() { return Behaviors.setup(DirectoryExplorer::new); }

    private Behavior<DirectoryExplorerMsg> onStartMsg(StartMsg msg) {
        queue.add(msg.initDirectory);
        File[] files;
        while(!queue.isEmpty()) {
            files = new File(queue.remove()).listFiles();
            for(File file : files) {
                if(file.isDirectory()) {
//                    this.getContext().getLog().info("Found Directory: " + file.getAbsolutePath());
                    queue.add(file.getAbsolutePath());
                } else if (file.isFile() && file.getAbsolutePath().endsWith(".java")) {
                    msg.fileReader.tell(new FilePathReaderMsg(file.getAbsolutePath(), msg.analyser));
                }
            }
        }
        msg.mainActor.tell(new StopMsg(msg.analyser));
        return this;
    }



}
