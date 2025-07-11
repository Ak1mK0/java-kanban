package model;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Task> subtasks = new ArrayList<>();

    public Epic(String name, String description, StatusList status) {
        super(name, description, status);
        updateStatus();
    }

    public void addSubtask(Task task) {
        subtasks.add(task);
        updateStatus();
    }

    public void updateEpic(Task task) {
        setName(task.getName());
        setDescription(task.getDescription());
        setStatus(task.getStatus());

        if (!subtasks.isEmpty()) {
            for (Task subtasks : subtasks) {
                Subtask tempTask = (Subtask) subtasks;
                tempTask.setTaskFor(getName());
            }
        }

        updateStatus();
    }

    public void removeSubtask(Task task) {
        subtasks.remove(task);
        updateStatus();
    }

    public void removeAllSubtask() {
        subtasks.clear();
        updateStatus();
    }

    public void updateStatus() {
        if (subtasks.isEmpty()) {
            setStatus(StatusList.NEW);
            return;
        }
        int doneCount = 0;
        int newCount = 0;
        for (Task task : subtasks) {
            switch (task.getStatus()) {
                case StatusList.NEW:
                    newCount++;
                    if (newCount == subtasks.size()) {
                        setStatus(StatusList.NEW);
                        return;
                    } else {
                        setStatus(StatusList.IN_PROGRESS);
                        return;
                    }
                case StatusList.DONE:
                    doneCount++;
                    if (doneCount == subtasks.size()) {
                        setStatus(StatusList.DONE);
                        return;
                    }
                    else {
                        setStatus(StatusList.IN_PROGRESS);
                        return;
                    }
                default:
                    setStatus(StatusList.IN_PROGRESS);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String text = String.format("Задача: %s. " +
                        "ID: %d." +
                        " Статус задачи: %s",
                getName(),
                getId(),
                getStatus());
        if (!subtasks.isEmpty()) {
            text = text + System.lineSeparator() + "Текущие подзадачи:";
            for (Task subtask : subtasks) {
                text = text + String.format("%nЗадача: %s. " +
                                "ID: %d." +
                                " Статус задачи: %s",
                        subtask.getName(),
                        subtask.getId(),
                        subtask.getStatus());
            }
        } else {
            text = text + System.lineSeparator() + "Подзадач нет.";
        }
        return  text;
    }

    public ArrayList<Task> getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtasks, epic.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasks);
    }

    @Override
    public void setStatus(StatusList status) {
        super.setStatus(status);
    }
}
