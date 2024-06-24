package aston.MyList.utils;

import java.util.Objects;

/**
 * Реализация ArrayList.
 *
 * @param <T> тип элементов, хранящихся в узле
 */
public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10; // емкость по умолчанию
    private T[] data;
    private int size;

    /**
     * Конструктор без параметров, инициализирующий список с начальной емкостью по умолчанию.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, инициализирующий список с указанной начальной емкостью.
     *
     * @param capacity начальная емкость списка
     * @throws IllegalArgumentException если указанная емкость меньше 0
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must not be less than 0");
        }

        this.data = (T[]) new Object[capacity];
    }

    /**
     * Возвращает текущий размер списка (количество элементов).
     *
     * @return текущий размер списка
     */
    public int size() {
        return this.size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return {@code true}, если список пуст, иначе {@code false}
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param value элемент для добавления
     */
    public void add(T value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    /**
     * Добавляет элемент по указанному индексу в списке. Все элементы,
     * начиная с индекса, сдвигаются вправо.
     *
     * @param index индекс, по которому нужно добавить элемент
     * @param value элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void add(int index, T value) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        this.size++;
    }

    /**
     * Удаляет элемент из списка по указанному индексу и возвращает его.
     * Все элементы справа от удаляемого сдвигаются на одну позицию влево.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        T removingElement = data[index];
        for (int i = index; i < size; i++)
            data[i] = data[i + 1];
        data[--size] = null;

        return removingElement;
    }

    /**
     * Удаляет первое вхождение указанного элемента из списка, если он присутствует.
     *
     * @param value элемент для удаления
     * @return {@code true}, если элемент был успешно удален, иначе {@code false}
     */
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, data[i])) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Возвращает элемент списка по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент списка
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        return data[index];
    }

    /**
     * Устанавливает новое значение для элемента списка по указанному индексу.
     *
     * @param index индекс элемента, который нужно обновить
     * @param value новое значение элемента
     * @return предыдущее значение элемента
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public T set(int index, T value) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }

        return data[index] = value;
    }

    /**
     * Возвращает новый список, содержащий элементы от startIndex (включительно)
     * до finishIndex (не включительно) из текущего списка.
     *
     * @param startIndex  начальный индекс (включительно) подсписка
     * @param finishIndex конечный индекс (не включительно) подсписка
     * @return новый список, содержащий указанный подсписок элементов
     * @throws IndexOutOfBoundsException если один из индексов выходит за пределы допустимого диапазона
     * @throws IllegalArgumentException  если startIndex больше finishIndex
     */
    public MyArrayList<T> subList(int startIndex, int finishIndex) {
        if (startIndex < 0 || startIndex >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + startIndex + ", Size: " + this.size);
        }
        if (finishIndex < 0 || finishIndex >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + finishIndex + ", Size: " + this.size);
        }
        if (startIndex > finishIndex) {
            throw new IllegalArgumentException("Index: startIndex " + startIndex + " > " + "finishIndex " + finishIndex);
        }

        MyArrayList<T> newList = new MyArrayList<>();
        for (int i = startIndex; i < finishIndex; i++)
            newList.add(data[i]);

        return newList;
    }

    /**
     * Внутренний метод для увеличения емкости списка при необходимости.
     *
     * @param minCapacity минимальная требуемая емкость списка
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        int currentCapacity = data.length;
        if (minCapacity > currentCapacity) {
            int newCapacity = currentCapacity * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            T[] newData = (T[]) new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    /**
     * Возвращает строковое представление списка в формате "MyArrayList: элемент1 элемент2 ...".
     *
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyArrayList: ");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
