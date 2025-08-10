package controllers;
import model.Node;
import model.Task;
import java.util.ArrayList;

public interface HistoryManager {

    void add(Task task);
    void remove(Node node);

    ArrayList<Task> getHistory();

    int getSize();
}
