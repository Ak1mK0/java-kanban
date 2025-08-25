package controllers;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int genId;

    @Override
    public void addTask(Task task) {
        final int id = ++genId;
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void addEpic(Epic epic) {
        final int id = ++genId;
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {

        final int id = ++genId;
        subtask.setId(id);
        subtasks.put(id, subtask);

        int taskFor = subtask.getTaskFor();
        Epic tempEpic = epics.get(taskFor);
        tempEpic.addSubtask(subtask);
    }

    @Override
    public void updateTask(Task task, int id) {
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        if (epics.containsKey(id)) {
            epic.setId(id);
            epics.get(id).updateEpic(epic);
        } else {
            addEpic(epic);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        subtask.setId(id);
        removeSubtaskById(id);
        subtasks.put(id, subtask);

        int taskFor = subtask.getTaskFor();
        Epic tempEpic = epics.get(taskFor);
        tempEpic.addSubtask(subtask);
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public Task findTaskById(int id) {
        return tasks.get(id);
    }

    @Override
    public Epic findEpicById(int id) {
        return epics.get(id);
    }

    @Override
    public Subtask findSubtaskById(int id) {
        return subtasks.get(id);
    }

    @Override
    public void removeAllTasks() {
        for (Integer id : tasks.keySet()) {
            historyManager.removeById(id);
        }
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        for (Integer id : epics.keySet()) {
            historyManager.removeById(id);
        }
        for (Integer id : subtasks.keySet()) {
            historyManager.removeById(id);
        }
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.removeAllSubtask();
        }
        for (Integer id : subtasks.keySet()) {
            historyManager.removeById(id);
        }
        subtasks.clear();
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
        historyManager.removeById(id);
    }

    @Override
    public void removeEpicById(int id) {
        List<Integer> subIdToRemove = new ArrayList<>();
        for (Subtask tmp : subtasks.values()) {
            if (tmp.getTaskFor() == id) {
                subIdToRemove.add(tmp.getId());
                historyManager.removeById(tmp.getId());
            }
        }
        subIdToRemove.forEach(subtasks.keySet()::remove);
        epics.remove(id);
        historyManager.removeById(id);
    }

    @Override
    public void removeSubtaskById(int id) {
        int taskFor = subtasks.get(id).getTaskFor();
        Epic tempTask = findEpicById(taskFor);
        tempTask.removeSubtask(subtasks.get(id));
        subtasks.remove(id);
        historyManager.removeById(id);
    }

    public int getHistorySize() {
        return historyManager.getSize();
    }

    public void setGenId(int id) {
        genId = id;
    }

    public int getGenId() {
        return genId;
    }
}
