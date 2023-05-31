package pointOne.actors.msgs.mainActor;

import pointOne.actors.ActorsAnalyser;
import pointOne.actors.msgs.MainActorMsg;

public class BootMsg implements MainActorMsg {
    public final String initDirectory;
    public final ActorsAnalyser analyser;
    public BootMsg(final String initDirectory, final ActorsAnalyser analyser) {
        this.initDirectory = initDirectory;
        this.analyser = analyser;
    }
}
