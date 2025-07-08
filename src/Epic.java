import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private ArrayList<Task> subtasks = new ArrayList<>();

    public Epic(String name, String description, StatusList status, int id) {
        super(name, description, status, id);
        updateStatus();
    }

    public void addSubtask(Task task) {
        subtasks.add(task);
        updateStatus();
    }

    @Override
    public void updateTask(Task task) {
        setName(task.getName());
        setDescription(task.getDescription());
        setStatus(task.getStatus());
        setId(task.getId());

        if (!subtasks.isEmpty()) {
            for (Task subtasks : subtasks) {
                Subtask tempTask = (Subtask) subtasks;
                tempTask.setTaskFor(getName());
            }
        }
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
                    }
                    break;
                case StatusList.DONE:
                    doneCount++;
                    if (doneCount == subtasks.size()) {
                        setStatus(StatusList.DONE);
                        return;
                    }
                    break;
                case StatusList.IN_PROGRESS:
                    setStatus(StatusList.IN_PROGRESS);
                    break;
            }
        }
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
