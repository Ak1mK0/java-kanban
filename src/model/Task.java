package model;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private StatusList status;
    private int id;


    public Task(String name, String description, StatusList status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    protected Task(Task copy) {
        this.name = copy.name;
        this.description = copy.description;
        this.status = copy.status;
        setId(copy.getId());
    }

    public Task copy() {
        return new Task(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name)
                && Objects.equals(description, task.description)
                && status == task.status
                && id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status);
    }

    @Override
    public String toString() {
        return  String.format("Задача: %s. " +
                "ID: %d." +
                " Статус задачи: %s",
                getName(),
                getId(),
                getStatus());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatusList getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StatusList status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
