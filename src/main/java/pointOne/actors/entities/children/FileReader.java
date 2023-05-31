package pointOne.actors.entities.children;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.actors.msgs.directoryExplorer.DirectoryExplorerDoneMsg;
import pointOne.actors.msgs.fileReader.FilePathReaderMsg;
import pointOne.actors.msgs.FileReaderMsg;
import pointOne.actors.msgs.fileReader.FileReaderDoneMsg;

import java.io.File;

public class FileReader extends AbstractBehavior<FileReaderMsg>  {
    public FileReader(final ActorContext<FileReaderMsg> context) {
        super(context);
    }

    @Override
    public Receive<FileReaderMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(FilePathReaderMsg.class, this::onFilePathReaderMsg)
                .onMessage(FileReaderDoneMsg.class, this::onFileReaderDone)
                .build();
    }

    public static Behavior<FileReaderMsg> create() { return Behaviors.setup(FileReader::new); }

    private Behavior<FileReaderMsg> onFileReaderDone(FileReaderDoneMsg msg) {
        msg.directoryExplorer.tell(new DirectoryExplorerDoneMsg());
        return this;
    }

    private Behavior<FileReaderMsg> onFilePathReaderMsg(FilePathReaderMsg msg) {
//        this.getContext().getLog().info("Reading File: " + msg.path);
        int numLines = msg.analyser.countLines(new File(msg.path));
        msg.analyser.updateIntervals(numLines);
        msg.analyser.updateTopFiles(new File(msg.path), numLines);
        return this;
    }


}
