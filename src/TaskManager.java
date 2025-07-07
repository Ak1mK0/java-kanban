import java.util.*;


public class TaskManager {

    private static EnumMap<StatusList, ArrayList<Integer>> taskStatus = new EnumMap<>(StatusList.class);
    private static HashMap<Integer, Task> taskList = new HashMap<>();

    static {
        taskStatusInit();
    }

    public void addTask(String name,
                        String description,
                        StatusList status,
                        boolean isEpic,
                        String taskFor) {
        if (!isEpic && (taskFor == null)) {
            Task task = new Task(name, description, status);
            if (taskList.putIfAbsent(task.getId(), task) != null) {
                System.out.println("Такая задача уже есть");
            }
            taskStatus.get(status).add(task.getId());
        } else if (isEpic && (taskFor == null)) {
            Task epic = new Epic(name, description, status, true);
            if (taskList.putIfAbsent(epic.getId(), epic) != null) {
                System.out.println("Такая задача уже есть");
            }
            taskStatus.get(status).add(epic.getId());
        } else if (!isEpic) {
            Task subTask = new Subtask(name, description, status, taskFor);
            if (taskList.putIfAbsent(subTask.getId(), subTask) != null) {
                System.out.println("Такая задача уже есть");
            }
            taskStatus.get(status).add(subTask.getId());

        }
    }

    public Task findTaskId(int id) {
        return taskList.get(id);
    }

    public Task findTaskName(String name) {
        for (Task task : taskList.values()) {
            if (name.equals(task.getName())) {
                return task;
            }
        }
        return null;
    }

    public void removeAllTasks() {
        taskStatusInit();
        taskList = new HashMap<>();
    }








    public EnumMap<StatusList, ArrayList<Integer>> getTaskStatus() {
        return taskStatus;
    }

    public HashMap<Integer, Task> getTaskList() {
        return taskList;
    }

    private static void taskStatusInit() {
        for (StatusList status : StatusList.values()) {
            taskStatus.put((StatusList) status, new ArrayList<>());
        }
    }
}
