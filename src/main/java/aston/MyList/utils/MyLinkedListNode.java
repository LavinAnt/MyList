package aston.MyList.utils;

/**
 * Узел для связанного списка типа MyLinkedList.
 *
 * @param <T> тип данных, хранящихся в узле
 */
public class MyLinkedListNode<T> {
    /**
     * Данные, хранящиеся в узле.
     */
    public T data;

    /**
     * Ссылка на следующий узел в списке.
     */
    public MyLinkedListNode<T> next;

    /**
     * Ссылка на предыдущий узел в списке.
     */
    public MyLinkedListNode<T> previous;

    /**
     * Конструктор узла с заданными данными.
     *
     * @param data данные, которые будет хранить узел
     */
    public MyLinkedListNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    /**
     * Конструктор узла без данных (данные устанавливаются в null).
     */
    public MyLinkedListNode() {
        this.data = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * Возвращает строковое представление узла, содержащее его данные.
     *
     * @return строковое представление узла
     */
    @Override
    public String toString() {
        return "MyLinkedListNode{" +
                "data=" + data +
                '}';
    }
}
