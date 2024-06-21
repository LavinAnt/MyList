package aston.MyList.utils;

public class MyLinkedListIterator<T> {
    private MyLinkedListNode<T> data;

    public MyLinkedListIterator(MyLinkedList<T> data) {
        this.data = data.getStart();
    }

    public boolean hasNext() {
        if (data != null)
            return data.next != null;
        else
            return false;
    }

    public MyLinkedListNode<T> next() {
        if (hasNext()) {
            return data = data.next;
        } else {
            return null;
        }
    }
}
