package controllers;
import model.Task;
import java.util.LinkedList;

public interface HistoryManager {

    <T extends Task> void addHistory(T task);

    LinkedList<? extends Task> getHistory();
}
