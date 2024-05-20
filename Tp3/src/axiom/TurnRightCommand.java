package axiom;

public class TurnRightCommand extends Command {
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed("turn");
        turnRight(axiom);
    }

    public void turnRight(Axiom axiom) {
        axiom.getRoseta().turnRight();
        axiom.setBearing(axiom.getRoseta().getCurrentDirection());
    }

    public char getIdentifier() {
        return 'r';
    }
}