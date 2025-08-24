package controllers;

import model.Epic;
import model.StatusList;
import model.Subtask;
import model.Task;

import java.io.*;
import java.nio.file.*;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private static final String HOME = System.getProperty("user.home");
    private static final Path backedDir = Paths.get(HOME, "java-kanban");
    private static final Path backed = Paths.get(HOME, "java-kanban", "backed.CSV");
    private static final String headText = "id,type,name,status,description,epic";
    private boolean load = true;

    public FileBackedTaskManager() {
        if (!Files.exists(backed)) {
            createCSVFile();
        }
    }

    private void createCSVFile() {
        try {
            Files.createDirectory(backedDir);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Путь уже существует");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createFile(backed);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Файл уже существует");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fr = new FileWriter(backed.toFile())) {
            fr.write(headText);
            fr.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        if (load) {
            saveInBacked(task);
            load = true;
        }
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        if (load) {
            saveInBacked(epic);
            load = true;
        }
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        if (load) {
            saveInBacked(subtask);
            load = true;
        }
    }

    private void saveInBacked(Task task) {
        try (FileWriter fr = new FileWriter(backed.toFile(), true)) {
            fr.write(taskToString(task));
            fr.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }

    private String taskToString(Task task) {
        String taskText = String.format("%d,%s,%s,%s,%s",
                task.getId(),
                task.getClass(),
                task.getName(),
                task.getStatus(),
                task.getDescription());
        if (task.getClass() == Subtask.class) {
            taskText = taskText + "," + ((Subtask) task).getTaskFor();
        }
        return taskText;
    }

    public void loadFromBacked(File file) {
        if (!file.exists()) {
            System.out.println("Файл не существует: " + file);
            return;
        }
        load = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while (br.ready()) {
                String text = br.readLine();
                String[] str = text.split(",");

                if (str[1].equals("class model.Task")) {
                    Task task = stringToTask(str);
                    addTask(task);
                } else if (str[1].equals("class model.Epic")) {
                    Epic epic = stringToEpic(str);
                    addEpic(epic);
                } else if (str[1].equals("class model.Subtask")) {
                    Subtask subtask = stringToSubtask(str);
                    addSubtask(subtask);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Task stringToTask(String[] str) {
        int id = Integer.parseInt(str[0]);
        String name = str[2];
        String description = str[4];
        StatusList status = StatusList.valueOf(str[3]);

        Task task = new Task(name, description, status);
        task.setId(id);

        return task;
    }

    private Epic stringToEpic(String[] str) {

        int id = Integer.parseInt(str[0]);
        String name = str[2];
        String description = str[4];
        StatusList status = StatusList.valueOf(str[3]);

        Epic epic = new Epic(name, description, status);
        epic.setId(id);

        return epic;
    }

    private Subtask stringToSubtask(String[] str) {

        int id = Integer.parseInt(str[0]);
        String name = str[2];
        String description = str[4];
        StatusList status = StatusList.valueOf(str[3]);
        int taskFor = Integer.parseInt(str[5]);

        Subtask subtask = new Subtask(name, description, status, taskFor);
        subtask.setId(id);

        return subtask;
    }
}
