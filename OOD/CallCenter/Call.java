package OOD.CallCenter;

public class Call {
    private Caller caller;
    private Employee handler;
    private Rank rank;

    public Call(Caller caller) {
        rank = Rank.Respondent;    // default rank for a call
        this.caller = caller;
    }

    public Rank getRank() {
        return rank;
    }

    public void increaseRank() {
        switch(rank) {
            case Respondent:
                rank = Rank.Manager;
                break;
            case Manager:
                rank = Rank.Director;
        }
    }

    public void connect(Employee emp) {
        handler = emp;
        handler.receiveCall(this);
    }

    public void disconnect() {
        handler.completeCall();
        handler = null;
        reply("Thank you for calling");
    }

    public void reply(String msg) {
        System.out.println(msg);
    }
}
