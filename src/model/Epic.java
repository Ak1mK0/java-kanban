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
        if (task.getClass() == Subtask.class) {
            subtasks.add(task);
            updateStatus();
        }
    }

    public void updateEpic(Task task) {
        setName(task.getName());
        setDescription(task.getDescription());
        setStatus(task.getStatus());

        if (!subtasks.isEmpty()) {
            for (Task subtasks : subtasks) {
                Subtask tempTask = (Subtask) subtasks;
                tempTask.setTaskFor(getId());
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
        boolean isProgress = false;
        boolean isNew = false;
        boolean isDone = false;
        if (subtasks.isEmpty()) {
            setStatus(StatusList.NEW);
            return;
        }
        for (Task subtask : subtasks) {
            if (subtask.getStatus().equals(StatusList.NEW)) {
                isNew = true;
            } else if (subtask.getStatus().equals(StatusList.DONE)) {
                isDone = true;
            } else {
                isProgress = true;
            }
        }
        if ((isNew && isDone) || isProgress) {
            setStatus(StatusList.IN_PROGRESS);
        } else if (isNew) {
            setStatus(StatusList.NEW);
        } else {
            setStatus(StatusList.DONE);
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
        return text;
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
