import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private StatusList status;


    public Task(String name, String description, StatusList status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public void updateTask(Task task) {
        setName(task.getName());
        setDescription(task.getDescription());
        setStatus(task.getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status);
    }

    @Override
    public String toString() {
        return  String.format("Задача: %s. " +
                "Описание задачи: %s." +
                " Статус задачи: %s",
                getName(),
                getDescription(),
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StatusList status) {
        this.status = status;
    }

}
