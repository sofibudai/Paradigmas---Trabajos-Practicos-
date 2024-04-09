package anillo;

public class Ring {
    private Node current;

    private static class Node {
        Object elem;
        Node next;

        Node(Object elem) {
            this.elem = elem;
        }
    }

    public Ring next() {
        if (this.current != null) {
            this.current = this.current.next;
            return this;
        }
        throw new RuntimeException();
    }

    public Object current() {
        if (this.current != null) {
            return this.current.elem;
        }
        throw new RuntimeException();
    }

    public Ring add(Object cargo) {
        Node newNode = new Node(cargo);
        if (this.current == null) {
            newNode.next = newNode;

        } else {
            newNode.next = this.current.next;
            this.current.next = newNode;
        }
        this.current = newNode;
        return this;
    }

    public Ring remove() {
        if (this.current == null) {
            throw new IllegalStateException("Cannot remove from an empty Ring");
        }
        if (this.current.next == this.current) {
            this.current = null;
        } else {
            Node nodeToRemove = this.current;
            while (this.current.next != nodeToRemove) {
                this.current = this.current.next;
            }
            this.current.next = nodeToRemove.next;
            this.current = this.current.next;
        }
        return this;
    }
}





// public Ring add(Object elem) {
//        if (this.elem == null) {
//            this.elem = elem;
//            this.next = this;
//        } else {
//            Ring newRing = new Ring();
//            newRing.elem = this.elem;
//            newRing.next = this.next;
//            this.elem = elem;
//            this.next = newRing;
//        }
//        return this;
//    }


// public Ring remove() {
//        if (this.elem == null) {
//            throw new IllegalStateException("Cannot remove from an empty Ring");
//        }
//            if (this.next == this) {
//            this.elem = null;
//            this.next = null;
//        } else {
//            this.next = this.next.remove();
//            if (this.next == this) {
//                this.next = null;
//            }
//        }
//            return this;
//}