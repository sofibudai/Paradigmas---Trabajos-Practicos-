package axiom;

public class IncreaseSpeedCommand extends Command {
    public void execute(Axiom axiom) {
        axiom.getSpeed().increase();
    }

    public char getIdentifier() {
        return 'i';
    }
}