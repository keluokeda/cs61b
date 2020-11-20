public class LinkedListDeque<T> {


    /**
     * sentinel next指向表头，sentinel previous指向表尾
     */
    private final Node<T> sentinel = new Node<>();

    public LinkedListDeque() {
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
    }

//    public LinkedListDeque(LinkedListDeque<T> other) {
//        this();
//        for (int i = 0; i < other.size; i++) {
//            T item = other.get(i);
//            addLast(item);
//        }
//    }

    private int size = 0;

    public void addFirst(T item) {
        size++;
        Node<T> first = sentinel.next;
        Node<T> node = new Node<>();
        node.item = item;
        first.previous = node;
        node.next = first;
        node.previous = sentinel;
        sentinel.next = node;
    }

    public void addLast(T item) {
        size++;
        Node<T> last = sentinel.previous;
        Node<T> node = new Node<>();
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

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node<T> first = sentinel.next;
        first.next.previous = sentinel;
        sentinel.next = first.next;
        first.next = null;
        first.previous = null;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node<T> last = sentinel.previous;
        last.previous.next = sentinel;
        sentinel.previous = last.previous;
        last.previous = null;
        last.next = null;
        return last.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> node = sentinel;
        int i = 0;

        while (i <= index) {
            node = node.next;
            i++;
        }
        return node.item;

    }

    private T getRecursiveHelper(Node<T> currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }

        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }

    public void printDeque() {
        Node<T> currentNode = sentinel;
        while (currentNode.next != sentinel) {
            System.out.print(currentNode.next.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    private static class Node<E> {
        Node<E> previous;
        E item;
        Node<E> next;
    }

//    public static void main(String[] args) {
//        LinkedListDeque<String> linkedListDeque = new LinkedListDeque<>();
//        linkedListDeque.addLast("0");
//        linkedListDeque.addLast("1");
//        linkedListDeque.addLast("2");
//        linkedListDeque.addLast("3");
//        linkedListDeque.printDeque();
//
//        System.out.println(linkedListDeque.get(2));
//    }
}
