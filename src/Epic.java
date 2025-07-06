import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks;

    public Epic(String name, String description, StatusList status, ArrayList<Subtask> subtasks) {
        super(name, description, status);
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                "} " + super.toString();
    }
}
