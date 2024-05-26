package axiom;

public class TurnLeftCommand extends Command {
    public static final String turn = "turn";
    public final char identifier = 'l';

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed(turn);
        axiom.setDirection(axiom.direction.turnLeft());
    }
}