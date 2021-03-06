package com.company.ADTs;

/**
 * Created by ryan on 6/22/14.
 */
public class ResizingArrayQueueOfStrings {
    private String[] q = new String[1];
    private int N = 0;
    private int C = 0;

    public int queueSize() {
        return q.length;
    }

    public boolean isEmpty() {
        return (N-C == 0);
    }

    private void grow() {
        int newLength = q.length * 2;
        String[] newArr = new String[newLength];
        for (int i=0; i<q.length; i++) {
            newArr[i] = q[i];
        }
        q = newArr;
    }

    private void shrink() {
        int newLength = (q.length / 2);
        String[] newArr = new String[newLength];
        for (int i=C; i<N; i++) {
            newArr[i-C] = q[i];
        }
        N = N - C;
        C = 0;
        q = newArr;
    }

    public void enqueue(String str) {
        if (N >= q.length) {
            grow();
        }
        q[N++] = str;
    }

    public String dequeue() {
        if ((N-C) < (q.length / 2)) {
            shrink();
        }
        return q[C++];
    }
}
