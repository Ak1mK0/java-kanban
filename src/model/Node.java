package model;

public class Node {

    private Node prev;
    private Node next;
    private Task task;


    public Node(Node head, Task task, Node tail) {
        this.prev = head;
        this.task = task;
        this.next = tail;
    }

    public int getTaskId() {
        return task.getId();
    }

    public Task getTask() {
        return task;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setNext(Node node) {
        next = node;
    }

    public void setPrev(Node node) {
        prev = node;
    }
}
