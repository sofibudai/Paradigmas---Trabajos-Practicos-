package axiom;

public class DecreaseSpeedCommand extends Command {
    public static final String stopError = "Cannot stop while probe is deployed";
    public final char identifier = 's';

    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed(stopError);
        axiom.getSpeed().decrease();
    }
    public char getIdentifier() {
        return identifier;
    }
}