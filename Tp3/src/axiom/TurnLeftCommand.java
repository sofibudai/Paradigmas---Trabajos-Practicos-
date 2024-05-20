package axiom;

public class TurnLeftCommand extends Command {
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed("turn");
        turnLeft(axiom);
    }

    public void turnLeft(Axiom axiom) {
        axiom.getRoseta().turnLeft();
        axiom.setBearing(axiom.getRoseta().getCurrentDirection());
    }

    public char getIdentifier() {
        return 'l';
    }
}