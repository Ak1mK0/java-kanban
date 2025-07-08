import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private StatusList status;
    private int id;


    public Task(String name, String description, StatusList status, int id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public void updateTask(Task task) {
        setName(task.getName());
        setDescription(task.getDescription());
        setStatus(task.getStatus());
        setId(task.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }

    @Override
    public String toString() {
        return  String.format("Задача: %s. " +
                "Идендификатор задачи: %d. " +
                "Описание задачи: %s." +
                " Статус задачи: %s",
                getName(),
                getId(),
                getDescription(),
                getStatus());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
}
