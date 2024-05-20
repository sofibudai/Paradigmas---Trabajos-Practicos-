package axiom;

public class DecreaseSpeedCommand extends Command {
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed("stop");
        axiom.getSpeed().decrease();
    }
    public char getIdentifier() {
        return 's';
    }
}