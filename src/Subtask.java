public class Subtask extends Task {

    private String taskFor;

    public Subtask(String name, String description, StatusList status, String taskFor) {
        super(name, description, status);
        this.taskFor = taskFor;
    }


}
