package controllers;

import exception.ManagerSaveException;
import model.Epic;
import model.StatusList;
import model.Subtask;
import model.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private static final String HOME = System.getProperty("user.home");
    private static final Path backedDir = Paths.get(HOME, "java-kanban");
    private static final Path backed = Paths.get(HOME, "java-kanban", "backed.CSV");
    private static final String headText = "id,type,name,status,description,epic";
    private boolean load = true;

    public FileBackedTaskManager() {
        if (!Files.exists(backed)) {
            createDirToFile();
            createCSVFile();
        }
    }

    private void createDirToFile() {
        if (Files.notExists(backedDir)) {
            try {
                Files.createDirectory(backedDir);
            } catch (IOException e) {
                throw new ManagerSaveException("Ошибка при создании директории: ", e);
            }
        }
    }

    private void createCSVFile() {
        try {
            Files.createFile(backed);
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при создании файла: ", e);
        }
        try (FileWriter fr = new FileWriter(backed.toFile())) {
            fr.write(headText);
            fr.write(System.lineSeparator());
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при записи в файл: ", e);
        }
    }

    private void deleteCSVFile() {
        if (Files.exists(backed)) {
            try {
                Files.delete(backed);
            } catch (IOException e) {
                throw new ManagerSaveException("Ошибка при удалении файла: ", e);
            }
        }
    }

    private void saveInBacked() {
        if (load) {
            deleteCSVFile();
            createCSVFile();
            try (FileWriter fr = new FileWriter(backed.toFile(), true)) {
                if (!getTasks().isEmpty()) {
                    for (Task task : getTasks()) {
                        fr.write(taskToString(task));
                        fr.write(System.lineSeparator());
                    }
                }
                if (!getEpics().isEmpty()) {
                    for (Epic epic : getEpics()) {
                        fr.write(taskToString(epic));
                        fr.write(System.lineSeparator());
                    }
                }
                if (!getSubtasks().isEmpty()) {
                    for (Subtask subtask : getSubtasks()) {
                        fr.write(taskToString(subtask));
                        fr.write(System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                throw new ManagerSaveException("Ошибка при записи в файл: ", e);
            }
        }
    }

    public void loadFromBacked(File file) {
        load = false;
        int maxId = 0;

        if (!file.exists()) {
            System.out.println("Файл не существует: " + file);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String text = br.readLine();
            while (br.ready()) {
                text = br.readLine();
                String[] str = text.split(",");

                if (getGenId() != Integer.parseInt(str[0])) {
                    setGenId(Integer.parseInt(str[0]) - 1);
                    if (maxId < Integer.parseInt(str[0])) {
                        maxId = Integer.parseInt(str[0]);
                    }
                }

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
            throw new ManagerSaveException("Ошибка загрузке из файла: ", e);
        }
        setGenId(maxId);
        load = true;
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

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        saveInBacked();
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        saveInBacked();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        saveInBacked();
    }

    @Override
    public void updateTask(Task task, int id) {
        super.updateTask(task, id);
        saveInBacked();
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        super.updateEpic(epic, id);
        saveInBacked();
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        super.updateSubtask(subtask, id);
        saveInBacked();
    }

    @Override
    public void removeAllTasks() {
        super.removeAllTasks();
        saveInBacked();
    }

    @Override
    public void removeAllEpics() {
        super.removeAllEpics();
        saveInBacked();
    }

    @Override
    public void removeAllSubtasks() {
        super.removeAllSubtasks();
        saveInBacked();
    }

    @Override
    public void removeTaskById(int id) {
        super.removeTaskById(id);
        saveInBacked();
    }

    @Override
    public void removeEpicById(int id) {
        super.removeEpicById(id);
        saveInBacked();
    }

    @Override
    public void removeSubtaskById(int id) {
        super.removeSubtaskById(id);
        saveInBacked();
    }
}

