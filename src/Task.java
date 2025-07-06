
public class Task {
    private final String name;
    private String description;
    private final int id;
    private StatusList status;
    private final static int[] primeNum = {11, 17, 23, 31};

    public Task(String name, String description, StatusList status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = hashId(name);
    }

    private int hashId(String name) {
        int hashId = 0;
        int i = 0;
        String[] words = name.split(" ");
        for (String word : words) {
            hashId = hashId + (name.hashCode() * primeNum[i]);
            i++;
            if (i == primeNum.length) {
                i = 0;
            }
        }
        return hashId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
