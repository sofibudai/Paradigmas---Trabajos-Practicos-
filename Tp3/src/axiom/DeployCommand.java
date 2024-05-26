package axiom;

public class DeployCommand extends Command {
    public static final String cannotDeployWhileStatic = "Cannot deploy probe while static";
    public final char identifier = 'd';

    public void execute(Axiom axiom) {
        axiom.getSpeed().checkSpeed(cannotDeployWhileStatic);
        axiom.getProbe().deploy();
    }

    public char getIdentifier() {
        return identifier;
    }
}