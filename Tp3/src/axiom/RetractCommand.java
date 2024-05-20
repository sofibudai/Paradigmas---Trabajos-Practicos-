package axiom;

public class RetractCommand extends Command {
    public void execute(Axiom axiom) {
        axiom.getProbe().retract();
    }

    public char getIdentifier() {
        return 'f';
    }
}