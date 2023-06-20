package ex1.actors.msgs.fileReader;

import akka.actor.typed.ActorRef;
import ex1.actors.msgs.DirectoryExplorerMsg;
import ex1.actors.msgs.FileReaderMsg;

public class FileReaderDoneMsg implements FileReaderMsg {
    public final ActorRef<DirectoryExplorerMsg> directoryExplorer;
    public FileReaderDoneMsg(final ActorRef<DirectoryExplorerMsg> directoryExplorer) {
        this.directoryExplorer = directoryExplorer;
    }
}
