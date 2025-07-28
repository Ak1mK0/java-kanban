package controllers;
import model.Task;
import java.util.LinkedList;

public interface HistoryManager {

    void addHistory(Task task);

    LinkedList<? extends Task> getHistory();
}
