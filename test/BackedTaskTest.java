import controllers.FileBackedTaskManager;

import model.Epic;
import model.StatusList;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BackedTaskTest {

    @Test
    void addTasksInMeneger() {

        String HOME = System.getProperty("user.home");
        Path backed = Paths.get(HOME, "java-kanban", "backed.CSV");

        if (Files.exists(backed)) {
            try {
                Files.delete(backed);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileBackedTaskManager fb = new FileBackedTaskManager();

        Task task1 = new Task("NewTask1", "NewTask1 description", StatusList.NEW);
        Epic epic1 = new Epic("NewEpic1", "NewEpic1 description", StatusList.NEW);
        Subtask subtask1 = new Subtask("NewSubtask1", "NewSubtask1 description", StatusList.NEW, 2);

        fb.addTask(task1);
        fb.addEpic(epic1);
        fb.addSubtask(subtask1);

        Assertions.assertEquals(1, fb.getTasks().size());
        Assertions.assertEquals(1, fb.getEpics().size());
        Assertions.assertEquals(1, fb.getSubtasks().size());
    }

    @Test
    void loadFromFileChek() {
        String HOME = System.getProperty("user.home");
        Path backed = Paths.get(HOME, "java-kanban", "backed.CSV");

        if (Files.exists(backed)) {
            try {
                Files.delete(backed);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileBackedTaskManager fb = new FileBackedTaskManager();
        try (FileWriter fr = new FileWriter(backed.toFile(), true)) {
            fr.write("1,class model.Task,NewTask1,NEW,NewTask1 description");
            fr.write(System.lineSeparator());
            fr.write("2,class model.Epic,NewEpic1,NEW,NewEpic1 description");
            fr.write(System.lineSeparator());
            fr.write("3,class model.Subtask,NewSubtask1,NEW,NewSubtask1 description,2");
            fr.write(System.lineSeparator());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(0, fb.getTasks().size());
        Assertions.assertEquals(0, fb.getEpics().size());
        Assertions.assertEquals(0, fb.getSubtasks().size());

        fb.loadFromBacked(backed.toFile());

        Assertions.assertEquals(1, fb.getTasks().size());
        Assertions.assertEquals(1, fb.getEpics().size());
        Assertions.assertEquals(1, fb.getSubtasks().size());
    }

}
