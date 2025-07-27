package model;

import java.util.Objects;

public class Subtask extends Task {

    private int taskFor;

    public Subtask(String name, String description, StatusList status, int taskFor) {
        super(name, description, status);
        this.taskFor = taskFor;
    }

    protected Subtask(Subtask copy) {
        super(copy);
        setId(copy.getId());
        this.taskFor = copy.taskFor;
    }

    @Override
    public Subtask copy() {
        return new Subtask(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return taskFor == subtask.taskFor;
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



    public int getTaskFor() {
        return taskFor;
    }

    public void setTaskFor(int taskFor) {
        this.taskFor = taskFor;
    }
}


