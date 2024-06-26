package axiom;

public class DeployCommand extends Command {
    public static final String probeError = "Cannot deploy probe while static";
    public final char identifier = 'd';

    public void execute(Axiom axiom) {
        axiom.getSpeed().checkSpeed(probeError);
        axiom.getProbe().deploy();
    }

    public char getIdentifier() {
        return identifier;
    }
}