package ex1.actors.msgs.fileReader;

import ex1.actors.ActorsAnalyser;
import ex1.actors.msgs.FileReaderMsg;

public class FilePathReaderMsg implements FileReaderMsg {
    public final String path;
    public final ActorsAnalyser analyser;
    public final boolean updateGUI;
    public FilePathReaderMsg(final String path, final ActorsAnalyser analyser, final boolean updateGUI) {
        this.path = path;
        this.analyser = analyser;
        this.updateGUI = updateGUI;
    }
}
