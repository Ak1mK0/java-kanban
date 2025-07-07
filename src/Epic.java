import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Task> subtasks;
    private boolean isEpic;

    public Epic(String name, String description, StatusList status, boolean isEpic) {
        super(name, description, status);
        this.isEpic = isEpic;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                "} " + super.toString();
    }
}
