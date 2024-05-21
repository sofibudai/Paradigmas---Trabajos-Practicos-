package axiom;

public class DecreaseSpeedCommand extends Command {
    private final char identifier = 'r';

    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed("stop");
        axiom.getSpeed().decrease();
    }
    public char getIdentifier() {
        return identifier;
    }
}