package pointOne.actors.msgs;

public class FilePathReaderMsg implements FileReaderMsg{
    public final String path;
    public FilePathReaderMsg(final String path) { this.path = path; }
}
