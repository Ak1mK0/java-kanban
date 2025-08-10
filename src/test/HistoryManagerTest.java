package test;

import controllers.HistoryManager;
import controllers.Managers;
import model.StatusList;
import model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HistoryManagerTest {

    @Test
    void defaultHistoryManagerInitialization() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        Assertions.assertNotNull(historyManager);
    }

    @Test
    void checkHistoryLog() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task1 = new Task("NewTask1", "NewTask1 description", StatusList.NEW);
        Task task2 = new Task("NewTask2", "NewTask2 description", StatusList.DONE);
        Task task3 = new Task("NewTask3", "NewTask3 description", StatusList.IN_PROGRESS);
        task1.setId(1);
        task2.setId(2);
        task3.setId(3);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        Assertions.assertEquals(3, historyManager.getSize());
    }

    @Test
    void checkRemoveAndUpdateLastTaskInHistoryLog() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task1 = new Task("NewTask1", "NewTask1 description", StatusList.NEW);
        Task task2 = new Task("NewTask2", "NewTask2 description", StatusList.DONE);
        Task task3 = new Task("NewTask3", "NewTask3 description", StatusList.IN_PROGRESS);
        task1.setId(1);
        task2.setId(2);
        task3.setId(3);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        task2.setDescription("Update1NewTask2 description");
        historyManager.add(task2);
        task3.setDescription("Update1NewTask3 description");
        historyManager.add(task3);
        task3.setDescription("Update2NewTask3 description");
        historyManager.add(task3);
        Assertions.assertEquals(3, historyManager.getSize());
    }

    @Test
    void checkGetHistoryLog() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task1 = new Task("NewTask1", "NewTask1 description", StatusList.NEW);
        Task task2 = new Task("NewTask2", "NewTask2 description", StatusList.DONE);
        Task task3 = new Task("NewTask3", "NewTask3 description", StatusList.IN_PROGRESS);
        Task task4 = new Task("NewTask4", "NewTask4 description", StatusList.IN_PROGRESS);
        task1.setId(1);
        task2.setId(2);
        task3.setId(3);
        task4.setId(4);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.add(task4);
        System.out.println(historyManager.getHistory());

        task2.setDescription("Update1NewTask2 description");
        historyManager.add(task2);
        System.out.println(historyManager.getHistory());

        task3.setDescription("Update1NewTask3 description");
        historyManager.add(task3);
        System.out.println(historyManager.getHistory());

        Assertions.assertEquals(4, historyManager.getHistory().size());
    }

    @Test
    void nullGetHistoryCheck() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        System.out.println(historyManager.getHistory());
        Assertions.assertEquals(0, historyManager.getHistory().size());
    }
}