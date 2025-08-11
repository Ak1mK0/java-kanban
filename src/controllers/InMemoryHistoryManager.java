package controllers;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> taskMap = new HashMap<>();
    private Node last = null;
    private Node penultimate = null;
    private Node first = null;

    private class Node {

        private Node prev;
        private Node next;
        private final Task task;


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

    @Override
    public void add(Task task) {
        Task tmp = task.copy();
        if (taskMap.containsKey(tmp.getId())) {
            remove(taskMap.get(tmp.getId()));
        }
        linkedLast(tmp);
    }

    @Override
    public void removeById(int id) {
        if (taskMap.containsKey(id)) {
            remove(taskMap.get(id));
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> tasksHistory = new ArrayList<>(taskMap.size());
        Node tmp = first;
        while (tmp != null) {
            tasksHistory.add(tmp.getTask());
            tmp = tmp.getNext();
        }
        return tasksHistory;
    }

    private void remove(Node node) {
        Node prev = node.getPrev();
        Node next = node.getNext();
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        taskMap.remove(node.getTaskId());
    }

    private void linkedLast(Task task) {
        final Node newNode;
        if (taskMap.isEmpty()) {
            newNode = new Node(null, task, null);
            last = newNode;
            first = last;
        } else {
            if (last.getTask().equals(task)) {
                last = penultimate;
            } else {
                penultimate = last;
            }
            newNode = new Node(last, task, null);
            last.setNext(newNode);
            last = newNode;
        }
        taskMap.put(newNode.getTaskId(), newNode);
    }

    public int getSize() {
        return taskMap.size();
    }
}
