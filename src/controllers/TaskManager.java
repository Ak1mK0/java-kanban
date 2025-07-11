package controllers;

import model.*;

import java.util.*;

public class TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int genId;

    public void addTask(Task task) {
        final int id = ++genId;
        task.setId(id);
        tasks.put(id, task);
    }

    public void addEpic(Epic epic) {
        final int id = ++genId;
        epic.setId(id);
        epics.put(id, epic);
    }

    public void addSubtask(Subtask subtask) {

        final int id = ++genId;
        subtask.setId(id);
        subtasks.put(id, subtask);

        String taskFor = subtask.getTaskFor();
        Epic tempEpic = epics.get(findEpicByName(taskFor).getId());
        tempEpic.addSubtask(subtask);

    }

    public void updateTask(Task task, int id) {
        task.setId(id);
        tasks.put(id, task);
    }

    public void updateEpic(Epic epic, int id) {
        if (epics.containsKey(id)) {
            epic.setId(id);
            epics.get(id).updateEpic(epic);
        } else {
            addEpic(epic);
        }
    }

    public void updateSubtask(Subtask subtask, int id) {
        subtask.setId(id);
        removeSubtaskById(id);
        subtasks.put(id, subtask);

        String taskFor = subtask.getTaskFor();
        Epic tempEpic = epics.get(findEpicByName(taskFor).getId());
        tempEpic.addSubtask(subtask);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Task getEpicById(int id) {
        return epics.get(id);
    }

    public Task getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Task findTaskByName(String name) {
        for (Task task : tasks.values()) {
            if (name.equals(task.getName())) {
                return task;
            }
        }
        return null;
    }

    public Epic findEpicByName(String name) {
        for (Epic epic : epics.values()) {
            if (name.equals(epic.getName())) {
                return epic;
            }
        }
        return null;
    }

    public Subtask findSubtaskByName(String name) {
        for (Subtask subtask : subtasks.values()) {
            if (name.equals(subtask.getName())) {
                return subtask;
            }
        }
        return null;
    }

    public Task findTaskById(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }

    public Epic findEpicById(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        }
        return null;
    }

    public Subtask findSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        }
        return null;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.removeAllSubtask();
        }
        subtasks.clear();
    }

    public void removeTaskById (int id) {
        tasks.remove(id);
    }

    public void removeEpicById (int id) {
        String taskFor = epics.get(id).getName();
        subtasks.values().removeIf(tempTask -> taskFor.equals(tempTask.getTaskFor()));
        epics.remove(id);
    }

    public void removeSubtaskById (int id) {
        String taskFor = subtasks.get(id).getTaskFor();
        Epic tempTask = findEpicByName(taskFor);
        tempTask.removeSubtask(subtasks.get(id));
        subtasks.remove(id);
    }
}
