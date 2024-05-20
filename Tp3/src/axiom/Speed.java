package axiom;

public class Speed {
    private int value = 0;

    public void increase() {
        this.value += 1;
    }

    public void decrease() {
        this.value = calculateDecreasedValue();
    }

    private int calculateDecreasedValue() {
        return (this.value > 0) ? this.value - 1 : 0;
    }

    public int getValue() {
        return value;
    }
    public void checkSpeed(String action) {
        assert value != 0: action;
    }
}


