
public class ArrayDeque<T> {
    private static final int minCapacity = 8;

    private static final int capacity = 16; // The minimum capacity for contraction resizing
    private static final int factor = 4; // Contracting factor

    private int size = 0;


    private T[] array;

    public ArrayDeque() {
        array = (T[]) new Object[minCapacity];
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void addFirst(T item) {
        if (isArrayFull()) {
            resizeArrayWhenAdd(array.length + 1);
        }
        if (size - 1 >= 0) System.arraycopy(array, 0, array, 1, size);
        array[0] = item;
        size++;
    }

    public void addLast(T item) {
        if (isArrayFull()) {
            resizeArrayWhenAdd(array.length + 1);
        }
        array[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = array[size - 1];
        array[size - 1] = null;
        size--;
        resizeArrayWhenRemove();
        return item;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = array[0];

        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        resizeArrayWhenRemove();
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[index];
    }

    /**
     * 数组是否装满了数据
     */
    private boolean isArrayFull() {
        return size >= array.length;
    }

    /**
     * 改变数组的大小以适应要增加新的数据
     */
    private void resizeArrayWhenAdd(int newArraySize) {
        if (newArraySize >= array.length) {
            T[] currentArray = array;
            T[] newArray = (T[]) new Object[array.length * factor];
            System.arraycopy(currentArray, 0, newArray, 0, size);
            array = newArray;
            resizeArrayWhenAdd(newArraySize);
        }
    }

    private void resizeArrayWhenRemove() {
        int newArraySize = array.length / factor;
        if (newArraySize >= minCapacity && newArraySize >= size) {
            T[] currentArray = array;
            T[] newArray = (T[]) new Object[newArraySize];
            System.arraycopy(currentArray, 0, newArray, 0, size);
            array = newArray;
        }


    }

//    public static void main(String[] args) {
//        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
//        for (int i = 0; i < 100; i++) {
//            arrayDeque.addLast(i + "");
//        }
//        System.out.println("size = " + arrayDeque.size());
////        System.out.println("array size = "+Integer.valueOf(arrayDeque.array.length).toString());
//        arrayDeque.printDeque();
//
//        System.out.println(arrayDeque.removeFirst());
//        System.out.println(arrayDeque.removeFirst());
//        arrayDeque.printDeque();
//        System.out.println(arrayDeque.removeLast());
//        System.out.println(arrayDeque.removeLast());
//
//        arrayDeque.printDeque();
//
//        System.out.println(arrayDeque.get(1));
//
//    }
}
