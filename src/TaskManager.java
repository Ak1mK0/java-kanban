import java.util.*;


public class TaskManager {

    private HashMap<Integer, Task> taskList = new HashMap<>();
    private int id;

    public void addTask(Task task, int id) {

        if (task.getClass() != Subtask.class) {
            taskList.put((id), task);
        } else {
            taskList.put((id), task);

            Subtask tempSubTask = (Subtask) task;
            String taskFor = tempSubTask.getTaskFor();

            Epic tempEpic = (Epic) findTaskByName(taskFor);
            tempEpic.addSubtask(task);
        }
    }

    public void updateTask(Task task, int id) {
        if (task.getClass() != Subtask.class) {
            taskList.get(id).updateTask(task);
        } else {
            Subtask tempSubtask = (Subtask) taskList.get(id);
            if (!tempSubtask.getTaskFor().equals(((Subtask) task).getTaskFor())) {
                Epic tempEpic = (Epic) findTaskByName(tempSubtask.getTaskFor());
                tempEpic.removeSubtask(tempSubtask);

                tempEpic = (Epic) findTaskByName(((Subtask) task).getTaskFor());
                tempEpic.addSubtask(task);
            }
            taskList.get(id).updateTask(task);
        }
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
        return taskList.get(id);
    }

    public void printAll() {
        for (Task task : taskList.values()) {
            System.out.println(task);
        }
    }

    public void printById(int id) {
            System.out.println(taskList.get(id));
            if (findTaskById(id).getClass() == Epic.class) {
                ((Epic) taskList.get(id)).printSubtasks();
            }
    }

    public void removeAll() {
        taskList = new HashMap<>();
    }

    public void removeById(int id) {
        Task task = findTaskById(id);
        if (task.getClass() == Task.class) {
            taskList.remove(id);
        } else if (task.getClass() == Epic.class) {
            for(Task subtask : ((Epic) task).getSubtasks()) {
                String Name = subtask.getName();
                for (int tempId : taskList.keySet()) {
                    if (taskList.get(tempId).getName().equals(Name))
                        taskList.remove(tempId);
                }
            }
            taskList.remove(id);
        } else {
            Epic tempEpic = (Epic) findTaskByName(((Subtask) task).getTaskFor());
            tempEpic.removeSubtask(task);

            taskList.remove(id);
        }

    }
}
