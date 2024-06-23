package aston.MyList.utils;

/**
 * Итератор для обхода элементов в связанном списке типа MyLinkedList.
 *
 * @param <T> тип элементов, хранящихся в связанном списке
 */
public class MyLinkedListIterator<T> {
    private MyLinkedListNode<T> data;

    /**
     * Конструктор итератора. Устанавливает начальное положение на первый элемент списка.
     *
     * @param data связанный список типа MyLinkedList, который нужно перебирать
     */
    public MyLinkedListIterator(MyLinkedList<T> data) {
        this.data = data.getStart();
    }

    /**
     * Проверяет наличие следующего элемента в списке.
     *
     * @return {@code true}, если следующий элемент существует, иначе {@code false}
     */
    public boolean hasNext() {
        if (data != null)
            return data.next != null;
        else
            return false;
    }

    /**
     * Возвращает следующий элемент в списке и перемещает указатель на него.
     *
     * @return следующий элемент в списке типа MyLinkedListNode
     */
    public MyLinkedListNode<T> next() {
        if (hasNext()) {
            return data = data.next;
        } else {
            return null;
        }
    }
}
