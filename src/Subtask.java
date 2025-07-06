public class Subtask extends Task {
    private String epic;

    public Subtask(String name, String description, StatusList status, String epic) {
        super(name, description, status);
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epic='" + epic + '\'' +
                "} " + super.toString();
    }
}
