package src;

import javax.activity.InvalidActivityException;

public class ArrayQueue implements Queue {

    private Object[] arr;
    private int head;
    private int tail;


    public Object dequeue() {
        if (empty()) {
            throw new IllegalStateException("There is nothing on the queue to dequeue.");
        }

        Object toReturn = arr[head];
        head++;
        head = head % arr.length;   // Make sure head remains a valid index
        return toReturn;
    }

    public void enqueue(Object item) {
        if ((tail +1) % arr.length == head) {
            grow_array();
        }

        arr[tail] = item;
        tail++;
        tail = tail % arr.length;
    }

    public boolean empty() {
        if (head==tail) {
            return true;
        } else {
            return false;
        }
    }

    private void grow_array() {
        Object[] newArr = new Object[arr.length*2];
        int newIndex = 0;

        while (!empty()) {
            newArr[newIndex]=dequeue();
            newIndex++;
        }
    }

}
