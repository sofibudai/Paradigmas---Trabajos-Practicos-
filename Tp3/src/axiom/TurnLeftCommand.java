package axiom;

public class TurnLeftCommand extends Command {
    public static final String turnError = "Cannot turn while probe is deployed";
    public final char identifier = 'l';

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public void execute(Axiom axiom) {
        axiom.getProbe().checkIfDeployed(turnError);
        axiom.setDirection(axiom.direction.turnLeft());
    }
}