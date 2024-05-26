package axiom;

public class TurnRightCommand extends Command {
    public static final String turn = "turn";
    public final char identifier = 'r';

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed(turn);
        axiom.setDirection(axiom.direction.turnRight());
    }
}