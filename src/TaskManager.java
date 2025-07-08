import java.util.*;


public class TaskManager {

    private HashMap<Integer, Task> taskList = new HashMap<>();

    public void addTask(Task task) {
        if (task.getClass() != Subtask.class) {
            taskList.put((task.getId()), task);
        } else {
            taskList.put((task.getId()), task);

            Subtask tempSubTask = (Subtask) task;
            String taskFor = tempSubTask.getTaskFor();

            Epic tempEpic = (Epic) findTaskByName(taskFor);
            tempEpic.addSubtask(task);
        }
    }

    public void updateTask(int id, Task task) {
        taskList.get(id).updateTask(task);
    }

    public Task findTaskByName(String name) {
        for (Task task : taskList.values()) {
            if (name.equals(task.getName())) {
                return task;
            }
        }
        return null;
    }

    public Task findTaskById(int id) {
        for (Task task : taskList.values()) {
            if (id == task.getId()) {
                return task;
            }
        }
        return null;
    }

    public void printAll() {
        for (Task task : taskList.values()) {
            System.out.println(task);
        }
    }

    public void printById(int id) {
            System.out.println(findTaskById(id));
    }

    public void removeAll() {
        taskList = new HashMap<>();
    }

    public void removeById(int id) {
        Task task = findTaskById(id);
        if (task.getClass() != Epic.class) {
            taskList.remove(id);
        } else {
            for (Task tempTask : ((Epic) task).getSubtasks()) {
                taskList.remove(tempTask.getId());
            }
            taskList.remove(id);
        }
    }
}
