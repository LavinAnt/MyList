package aston.MyList.utils;

public class MyLinkedListNode<T> {
    public T data;
    public MyLinkedListNode<T> next;
    public MyLinkedListNode<T> previous;

    public MyLinkedListNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public MyLinkedListNode() {
        this.data = null;
        this.next = null;
        this.previous = null;
    }

    @Override
    public String toString() {
        return "MyLinkedListNode{" +
                "data=" + data +
                '}';
    }
}
