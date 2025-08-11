package controllers;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubtask(Subtask subtask);

    void updateTask(Task task, int id);

    void updateEpic(Epic epic, int id);

    void updateSubtask(Subtask subtask, int id);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();

    Task getTaskById(int id);

    Task getEpicById(int id);

    Task getSubtaskById(int id);

    Task findTaskById(int id);

    Epic findEpicById(int id);

    Subtask findSubtaskById(int id);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubtaskById(int id);

    int getHistorySize();
}
