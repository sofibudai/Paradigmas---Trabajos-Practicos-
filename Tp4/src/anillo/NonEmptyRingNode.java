package anillo;

public class NonEmptyRingNode extends RingNode {

    private Object elem;
    private RingNode next;
    private RingNode previous;

    public RingNode getPrevious(RingNode newNode){
        next = newNode;
        return this;
    }

    public NonEmptyRingNode(Object cargo) {
        next = this;
        previous = this;
        elem = cargo;
    }

    public NonEmptyRingNode(Object cargo, NonEmptyRingNode current) {
        this.elem = cargo;
        this.next = current;
        this.previous = current.previous;
    }

    public RingNode next() {
        return this.next;
    }

    public Object current() {
        return this.elem;
    }

    public RingNode add(Object cargo) {
        RingNode newNode = new NonEmptyRingNode(cargo, this);
        previous.getPrevious(newNode);
        this.previous = newNode;
        return newNode;
    }

    public RingNode remove() {
        if (this.next == this) {
            return new EmptyRingNode();
        } else {
            this.next.previous = this.previous;
            this.previous.next = this.next;
            return this.next;
        }
    }
}