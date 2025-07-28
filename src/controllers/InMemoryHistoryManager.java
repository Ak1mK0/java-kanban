package controllers;

import model.Task;

import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_SIZE = 10;
    private final LinkedList<Task> history = new LinkedList<>();

    @Override
    public void addHistory(Task task) {
        history.add(task.copy());
        if (history.size() > MAX_SIZE) {
            history.removeFirst();
        }
    }

    @Override
    public LinkedList<Task> getHistory() {
        return new LinkedList<>(history);
    }
}
