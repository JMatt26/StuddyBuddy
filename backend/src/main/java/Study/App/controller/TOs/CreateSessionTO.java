package Study.App.controller.TOs;

public class CreateSessionTO {
    public SessionTO incoming;
    public SessionInformationTO incomingInfo;

    @Override
    public String toString() {
        return "CreateSessionTO [incoming=" + incoming.toString() + ", incomingInfo=" + incomingInfo.toString() + "]";
    }
}
