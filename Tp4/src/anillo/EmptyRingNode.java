package anillo;

public class EmptyRingNode extends RingNode {

     public RingNode next(){
        throw new RuntimeException();
    }

    public Object current() {
        throw new RuntimeException();
    }

    public RingNode add(Object cargo) {
        return new NonEmptyRingNode(cargo);
    }

    public RingNode remove() {
        throw new RuntimeException();
    }

    public RingNode getPrevious(RingNode newNode){
        throw new RuntimeException();
    }
}

