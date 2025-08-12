import controllers.Managers;
import controllers.TaskManager;
import model.Epic;
import model.StatusList;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {

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
    void deleteEpicAndSubtaskFromHistoryManager() {
        TaskManager taskManager = Managers.getDefault();
        Epic epic1 = new Epic("NewEpic1", "NewEpic description", StatusList.IN_PROGRESS);
        Subtask sub1 = new Subtask("NewSub1", "NewSub1 description", StatusList.DONE, 1);
        Subtask sub2 = new Subtask("NewSub2", "NewSub2 description", StatusList.DONE, 1);
        Epic epic2 = new Epic("NewEpic2", "NewEpic description", StatusList.IN_PROGRESS);
        Subtask sub3 = new Subtask("NewSub3", "NewSub description", StatusList.DONE, 4);

        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(sub3);

        taskManager.getEpicById(1);
        taskManager.getSubtaskById(3);
        taskManager.getSubtaskById(5);
        taskManager.getEpicById(4);
        taskManager.getSubtaskById(2);

        Assertions.assertEquals(5, taskManager.getHistorySize());

        taskManager.removeSubtaskById(3);
        Assertions.assertEquals(4, taskManager.getHistorySize());

        taskManager.removeEpicById(1);
        Assertions.assertEquals(2, taskManager.getHistorySize());
    }

    @Test
    void checkStatusChengeInEpic() {
        TaskManager taskManager = Managers.getDefault();
        Epic epic1 = new Epic("NewEpic1", "NewEpic description", StatusList.IN_PROGRESS);
        Subtask sub1 = new Subtask("NewSub1", "NewSub1 description", StatusList.DONE, 1);
        Subtask sub2 = new Subtask("NewSub2", "NewSub2 description", StatusList.DONE, 1);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);
        Assertions.assertEquals(StatusList.DONE, epic1.getStatus());

        epic1 = new Epic("NewEpic1", "NewEpic description", StatusList.IN_PROGRESS);
        sub1 = new Subtask("NewSub1", "NewSub1 description", StatusList.NEW, 4);
        sub2 = new Subtask("NewSub2", "NewSub2 description", StatusList.NEW, 4);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);
        Assertions.assertEquals(StatusList.NEW, epic1.getStatus());

        epic1 = new Epic("NewEpic1", "NewEpic description", StatusList.IN_PROGRESS);
        sub1 = new Subtask("NewSub1", "NewSub1 description", StatusList.NEW, 7);
        sub2 = new Subtask("NewSub2", "NewSub2 description", StatusList.DONE, 7);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);
        Assertions.assertEquals(StatusList.IN_PROGRESS, epic1.getStatus());

        epic1 = new Epic("NewEpic1", "NewEpic description", StatusList.IN_PROGRESS);
        sub1 = new Subtask("NewSub1", "NewSub1 description", StatusList.IN_PROGRESS, 10);
        sub2 = new Subtask("NewSub2", "NewSub2 description", StatusList.DONE, 10);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);
        Assertions.assertEquals(StatusList.IN_PROGRESS, epic1.getStatus());

        epic1 = new Epic("NewEpic1", "NewEpic description", StatusList.IN_PROGRESS);
        sub1 = new Subtask("NewSub1", "NewSub1 description", StatusList.NEW, 13);
        sub2 = new Subtask("NewSub2", "NewSub2 description", StatusList.IN_PROGRESS, 13);
        taskManager.addEpic(epic1);
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);
        Assertions.assertEquals(StatusList.IN_PROGRESS, epic1.getStatus());
    }
}