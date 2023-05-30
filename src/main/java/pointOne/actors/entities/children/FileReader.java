package pointOne.actors.entities.children;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import pointOne.actors.msgs.DirectoryExplorerMsg;
import pointOne.actors.msgs.FilePathReaderMsg;
import pointOne.actors.msgs.FileReaderMsg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader extends AbstractBehavior<FileReaderMsg>  {
    public FileReader(final ActorContext<FileReaderMsg> context) {
        super(context);
    }

    @Override
    public Receive<FileReaderMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(FilePathReaderMsg.class, this::onFilePathReaderMsg)
                .build();
    }

    public static Behavior<FileReaderMsg> create() { return Behaviors.setup(FileReader::new); }

    private Behavior<FileReaderMsg> onFilePathReaderMsg(FilePathReaderMsg msg) {
        this.getContext().getLog().info("Reading File: " + msg.path);
        //READ FILE HERE BEGIN

        //END
        return this;
    }

    private int countLines(File file) {
        int numLines = 0;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            while (reader.readLine() != null) {
                numLines = numLines + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLines;
    }

}
