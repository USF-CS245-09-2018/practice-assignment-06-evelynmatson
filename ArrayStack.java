package src;

import java.lang.reflect.Array;

public class ArrayStack implements Stack{

    Object[] arr;
    int top;

    public ArrayStack() {
        arr = new Object[64];
        top = -1;
    }

    public void push(Object item) {
        if ((top+1) == arr.length) {
            grow_array();
        }

        arr[top+1] = item;
        top++;
    }

    public Object pop() {
        if (empty()) {
            throw new IllegalStateException("There is nothing on this ArrayStack.");
        }

        Object toReturn = arr[top];
        top--;
        return toReturn;
    }

    private void grow_array() {
        Object[] newArr = new Object[arr.length*2];

        for (int i=0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
    }

    public Object peek() {
        if (empty()) {
            throw new IllegalStateException("There is nothing on this ArrayStack.");
        }

        return arr[top];
    }

    public boolean empty() {
        if (top==-1) {
            return true;
        } else {
            return false;
        }
    }
}
