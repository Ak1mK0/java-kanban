import java.util.Objects;

public class Subtask extends Task {

    private String taskFor;

    public Subtask(String name, String description, StatusList status, String taskFor) {
        super(name, description, status);
        this.taskFor = taskFor;
    }

    public void updateTask(Task task) {
        Subtask tempTask = (Subtask) task;
        setName(tempTask.getName());
        setDescription(tempTask.getDescription());
        setStatus(tempTask.getStatus());
        setTaskFor(tempTask.getTaskFor());
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
                        " Описание задачи: %s." +
                        " Статус задачи: %s.",
                getName(),
                getDescription(),
                getStatus());
    }

    public String getTaskFor() {
        return taskFor;
    }

    public void setTaskFor(String taskFor) {
        this.taskFor = taskFor;
    }
}


