package controllers;

import model.Node;
import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> taskMap = new HashMap<>();
    private int lastId = -1;
    private int penultimateId = -1;
    private int firstId = -1;

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
        Node tmp = taskMap.get(lastId);
        while (tmp.getPrev() != null) {
            tasksHistory.add(tmp.getTask());
            tmp = tmp.getPrev();
        }
        tasksHistory.add(tmp.getTask());
        return tasksHistory;
    }

    public void linkedLast(Task task) {
        final Node newNode;
        if (taskMap.isEmpty()) {
            newNode = new Node(null, task, null);
            lastId = task.getId();
            firstId = lastId;
        } else {
            if (lastId == task.getId()) {
                lastId = penultimateId;
            } else {
                penultimateId = lastId;
            }
            newNode = new Node(taskMap.get(lastId), task, null);
            taskMap.get(lastId).setNext(newNode);
            lastId = task.getId();
        }
        taskMap.put(newNode.getTaskId(), newNode);
    }

    public int getSize() {
        return taskMap.size();
    }
}
