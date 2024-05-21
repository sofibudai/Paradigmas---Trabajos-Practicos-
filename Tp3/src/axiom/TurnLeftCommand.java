package axiom;

public class TurnLeftCommand extends Command {
    private final char identifier = 'l';

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed("turn");
        axiom.setDirection(axiom.direction.turnLeft());
    }
}