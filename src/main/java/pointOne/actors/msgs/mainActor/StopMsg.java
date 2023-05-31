package pointOne.actors.msgs.mainActor;

import pointOne.actors.ActorsAnalyser;
import pointOne.actors.msgs.MainActorMsg;

public class StopMsg implements MainActorMsg {
    public ActorsAnalyser analyser;
    public StopMsg(final ActorsAnalyser analyser) {
        this.analyser = analyser;
    }
}
