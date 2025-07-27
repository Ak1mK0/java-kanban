package Tests;

import controllers.HistoryManager;
import controllers.Managers;
import controllers.TaskManager;
import model.Epic;
import model.StatusList;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagersTest {

    @Test
    void tasksEquals() {
        Task task1 = new Task("NewTask", "NewTask description", StatusList.NEW);
        task1.setId(10);
        Task task2 = new Task("NewTask", "NewTask description", StatusList.NEW);
        task2.setId(10);

        Assertions.assertEquals(task1, task2);
    }

    @Test
    void epicsEquals() {
        Epic epic1 = new Epic("NewTask", "NewTask description", StatusList.DONE);
        epic1.setId(20);
        Subtask sub1 = new Subtask("NewSub", "NewSub description", StatusList.DONE, 20);
        epic1.addSubtask(sub1);


        Epic epic2 = new Epic("NewTask", "NewTask description", StatusList.IN_PROGRESS);
        epic2.setId(20);
        Subtask sub2 = new Subtask("NewSub", "NewSub description", StatusList.DONE, 20);
        epic2.addSubtask(sub2);

        Assertions.assertEquals(epic1, epic2);
    }

    @Test
    void subtasksEquals() {
        Subtask sub1 = new Subtask("NewSub", "NewSub description", StatusList.DONE, 1);
        sub1.setId(20);
        Subtask sub2 = new Subtask("NewSub", "NewSub description", StatusList.DONE, 1);
        sub2.setId(20);

        Assertions.assertEquals(sub1, sub2);
    }

    @Test
    void cannotPutEpicInEpic() {
        Epic epic1 = new Epic("NewTask", "NewTask description", StatusList.DONE);
        epic1.setId(10);
        Epic epic2 = new Epic("NewTask", "NewTask description", StatusList.IN_PROGRESS);
        epic2.setId(20);

        epic1.addSubtask(epic2);

        Assertions.assertEquals(0, epic1.getSubtasks().size());
    }

    @Test
    void defaultTaskManagerInitialization() {
        TaskManager taskManager = Managers.getDefault();

        Assertions.assertNotNull(taskManager);
    }

    @Test
    void defaultHistoryManagerInitialization() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        Assertions.assertNotNull(historyManager);
    }

    @Test
    void addTasksById() {
        TaskManager taskManager = Managers.getDefault();
        Task task1 = new Task("NewTask", "NewTask description", StatusList.NEW);
        Epic epic1 = new Epic("NewEpic", "NewEpic description", StatusList.IN_PROGRESS);
        Subtask sub1 = new Subtask("NewSub", "NewSub description", StatusList.DONE, 2);
        taskManager.addTask(task1);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);

        Assertions.assertEquals(task1, taskManager.getTaskById(1));
        Assertions.assertEquals(epic1, taskManager.getEpicById(2));
        Assertions.assertEquals(sub1, taskManager.getSubtaskById(3));
    }

    @Test
    void checkHistoryLog() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task("NewTask", "NewTask description", StatusList.NEW);
        historyManager.addHistory(task);
        task.setStatus(StatusList.IN_PROGRESS);
        historyManager.addHistory(task);
        Assertions.assertNotEquals(task, historyManager.getHistory().get(0));
    }
}