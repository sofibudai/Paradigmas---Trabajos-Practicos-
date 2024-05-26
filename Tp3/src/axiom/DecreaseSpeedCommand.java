package axiom;

public class DecreaseSpeedCommand extends Command {
    public static final String stop = "stop";
    public final char identifier = 's';

    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed(stop);
        axiom.getSpeed().decrease();
    }
    public char getIdentifier() {
        return identifier;
    }
}