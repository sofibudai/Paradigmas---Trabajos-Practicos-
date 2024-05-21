package axiom;

public class DeployCommand extends Command {
    private final char identifier = 'd';

    public void execute(Axiom axiom) {
        axiom.getSpeed().checkSpeed("Cannot deploy probe while static");
        axiom.getProbe().deploy();
    }

    public char getIdentifier() {
        return identifier;
    }
}