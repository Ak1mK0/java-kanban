package controllers;

import model.Node;
import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> taskMap = new HashMap<>();
    private Node last = null;
    private Node penultimate = null;
    private Node first = null;

    @Override
    public void add(Task task) {
        Task tmp = task.copy();
        if (taskMap.containsKey(tmp.getId())) {
            remove(taskMap.get(tmp.getId()));
        }
        linkedLast(tmp);
    }

    @Override
    public void remove(Node node) {
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
