public class LinkedListDeque<E> {


    /**
     * sentinel next指向表头，sentinel previous指向表尾
     */
    private final Node<E> sentinel = new Node<>();

    public LinkedListDeque() {
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque<E> other){
        this();
        for(int i = 0;i < other.size;i++){
            E item = other.get(i);
            addLast(item);
        }
    }

    private int size = 0;

    public void addFirst(E item) {
        size++;
        Node<E> first = sentinel.next;
        Node<E> node = new Node<>();
        node.item = item;
        first.previous = node;
        node.next = first;
        node.previous = sentinel;
        sentinel.next = node;
    }

    public void addLast(E item) {
        size++;
        Node<E> last = sentinel.previous;
        Node<E> node = new Node<>();
        node.item = item;
        last.next = node;
        node.previous = last;
        node.next = sentinel;
        sentinel.previous = node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public E removeFirst() {
        if (size > 0) {
            size--;
        }
        Node<E> first = sentinel.next;
        first.next.previous = sentinel;
        sentinel.next = first.next;
        first.next = null;
        first.previous = null;
        return first.item;
    }

    public E removeLast() {
        if (size > 0) {
            size--;
        }
        Node<E> last = sentinel.previous;
        last.previous.next = sentinel;
        sentinel.previous = last.previous;
        last.previous = null;
        last.next = null;
        return last.item;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> node = sentinel;

        while (index < size) {
            node = node.next;
            index++;
        }
        return node.item;

    }

    private E getRecursiveHelper(Node<E> currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }

        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public E getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }

    public void printDeque() {

    }

    private static class Node<E> {
        Node<E> previous;
        E item;
        Node<E> next;
    }
}
