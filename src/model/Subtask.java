package model;

import java.util.Objects;

public class Subtask extends Task {

    private String taskFor;

    public Subtask(String name, String description, StatusList status, String taskFor) {
        super(name, description, status);
        this.taskFor = taskFor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return Objects.equals(taskFor, subtask.taskFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taskFor);
    }

    public String toString() {
        return  String.format("Задача: %s." +
                        " ID: %d." +
                        " Статус задачи: %s." +
                        " Подзадача для: %s.",
                getName(),
                getId(),
                getStatus(),
                getTaskFor());
    }

    public String getTaskFor() {
        return taskFor;
    }

    public void setTaskFor(String taskFor) {
        this.taskFor = taskFor;
    }
}


