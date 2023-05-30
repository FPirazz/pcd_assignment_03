package pointOne.actors.msgs;

import pointOne.actors.ActorsAnalyser;

public class StopMsg implements MainActorMsg {
    public ActorsAnalyser analyser;
    public StopMsg(final ActorsAnalyser analyser) {
        this.analyser = analyser;
    }
}
