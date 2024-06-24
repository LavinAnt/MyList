package aston.MyList.utils;


import java.util.Objects;

/**
 * Реализация LinkedList.
 *
 * @param <T> тип элементов, хранящихся в узле
 */
public class MyLinkedList<T> {
    private final MyLinkedListNode<T> start;  // Начальный узел списка
    private final MyLinkedListNode<T> finish; // Завершающий узел списка
    private int size;                         // Размер списка

    /**
     * Конструктор создает пустой двусвязный список.
     * Начальный и завершающий узлы инициализируются,
     * и начальный узел связывается с завершающим.
     */
    public MyLinkedList() {
        this.start = new MyLinkedListNode<>(null);
        this.finish = new MyLinkedListNode<>(null);
        this.start.next = this.finish;
        this.finish.previous = this.start;
    }

    /**
     * Возвращает начальный узел списка.
     *
     * @return начальный узел
     */
    public MyLinkedListNode<T> getStart() {
        return start;
    }

    /**
     * Возвращает завершающий узел списка.
     *
     * @return завершающий узел
     */
    public MyLinkedListNode<T> getFinish() {
        return finish;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return размер списка
     */
    public long size() {
        return size;
    }

    /**
     * Проверяет, является ли список пустым.
     *
     * @return true, если список пустой; false в противном случае
     */
    public boolean isEmpty() {
        return this.start.next == this.finish;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param data элемент для добавления
     */
    public void add(T data) {
        MyLinkedListNode<T> newNode = new MyLinkedListNode<>(data);
        MyLinkedListNode<T> lastNode = this.finish.previous;
        lastNode.next = newNode;
        newNode.previous = lastNode;
        newNode.next = finish;
        finish.previous = newNode;
        this.size++;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index индекс, по которому нужно добавить элемент
     * @param data  элемент для добавления
     * @return true, если элемент успешно добавлен
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public boolean add(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        MyLinkedListNode<T> newNode = new MyLinkedListNode<>(data);
        MyLinkedListNode<T> currentNode = getNodeAtIndex(index);
        newNode.previous = currentNode.previous;
        currentNode.previous.next = newNode;
        newNode.next = currentNode;
        currentNode.previous = newNode;
        this.size++;

        return true;
    }

    /**
     * Удаляет первое вхождение указанного элемента из списка.
     *
     * @param data элемент для удаления
     * @return true, если элемент успешно удален; false в противном случае
     */
    public boolean remove(T data) {
        MyLinkedListNode<T> currentNode = this.getStart().next;
        while (currentNode != this.finish) {
            if (Objects.equals(data, currentNode.data)) {
                currentNode.previous.next = currentNode.next;
                currentNode.next.previous = currentNode.previous;
                this.size--;
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента для удаления
     * @return true, если элемент успешно удален
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public boolean remove(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        MyLinkedListNode<T> currentNode = getNodeAtIndex(index);
        currentNode.previous.next = currentNode.next;
        currentNode.next.previous = currentNode.previous;
        this.size--;

        return true;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент списка по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        MyLinkedListNode<T> currentNode = getNodeAtIndex(index);

        return currentNode.data;
    }

    /**
     * Заменяет элемент по указанному индексу новым элементом.
     *
     * @param index индекс элемента для замены
     * @param data  новый элемент
     * @return старый элемент, который был заменен
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public T set(int index, T data) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        MyLinkedListNode<T> currentNode = getNodeAtIndex(index);
        T oldData = currentNode.data;
        currentNode.data = data;

        return oldData;
    }

    /**
     * Создает и возвращает подсписок элементов списка от startIndex до finishIndex.
     *
     * @param startIndex  начальный индекс подсписка
     * @param finishIndex конечный индекс подсписка
     * @return новый экземпляр MyLinkedList, содержащий подсписок элементов
     * @throws IndexOutOfBoundsException если startIndex или finishIndex выходит за границы списка
     * @throws IllegalArgumentException  если startIndex больше finishIndex
     */
    public MyLinkedList<T> subList(int startIndex, int finishIndex) {
        if (startIndex < 0 || startIndex >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + startIndex + ", Size: " + this.size);
        }
        if (finishIndex < 0 || finishIndex >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + finishIndex + ", Size: " + this.size);
        }
        if (startIndex > finishIndex) {
            throw new IllegalArgumentException("Index: startIndex " + startIndex + " > " + "finishIndex " + finishIndex);
        }

        MyLinkedList<T> newList = new MyLinkedList<>();
        MyLinkedListNode<T> currentNode = this.getStart().next;
        for (int i = 0; i < finishIndex; i++) {
            if (i >= startIndex)
                newList.add(currentNode.data);
            currentNode = currentNode.next;
        }

        return newList;
    }

    /**
     * Возвращает узел списка по указанному индексу.
     *
     * @param index индекс узла
     * @return узел списка по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    private MyLinkedListNode<T> getNodeAtIndex(int index) {
        MyLinkedListNode<T> currentNode = this.getStart().next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyLinkedList: ");
        MyLinkedListNode<T> current = start.next;
        while (current != finish) {
            result.append(current.data).append(" ");
            current = current.next;
        }

        return result.toString();
    }
}
