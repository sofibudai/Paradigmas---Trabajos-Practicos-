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
            this.current = newNode;

        } else {
            Node previousNode = this.current;
            while (previousNode.next != this.current) {
                previousNode = previousNode.next;
            }
            newNode.next = this.current;
            previousNode.next = newNode;
            this.current = newNode;
        }
        return this;
    }

    public Ring remove() {
        if (this.current == null) {
            throw new RuntimeException();
        }
        if (this.current.next == this.current) {
            this.current = null;
        } else {
            Node nodeToRemove = this.current;
            this.current = nodeToRemove.next;
            Node previousNode = this.current;
            while (previousNode.next != nodeToRemove) {
                previousNode = previousNode.next;
            }
            previousNode.next = this.current;
        }
        return this;
    }
}

