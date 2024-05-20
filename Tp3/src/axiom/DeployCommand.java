package axiom;

public class DeployCommand extends Command {
    public void execute(Axiom axiom) {
        axiom.getSpeed().checkSpeed("Cannot deploy probe while static");
        axiom.getProbe().deploy();
    }

    public char getIdentifier() {
        return 'd';
    }
}