package anillo;

public abstract class RingNode {

    protected RingNode next;
    protected RingNode previous;

    public abstract RingNode next();
    public abstract Object current();
    public abstract RingNode add(Object cargo);
    public abstract RingNode remove();
    public abstract RingNode getPrevious(RingNode newNode);

}

