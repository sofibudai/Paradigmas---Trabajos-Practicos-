package anillo;

public class Ring {
    private RingNode current = new EmptyRingNode();



    public Ring next() {
        current = current.next();
        return this;
    }

    public Object current() {
        return current.current();
    }

    public Ring add(Object cargo) {
        current = current.add(cargo);
        return this;
    }

    public Ring remove() {
        current = current.remove();
        return this;
    }
}
