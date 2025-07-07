public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskMeneger = new TaskManager();

        taskMeneger.addTask("таск1", "1", StatusList.NEW, false, null);
        taskMeneger.addTask("таск2", "2", StatusList.DONE, false, null);
        taskMeneger.addTask("таск3", "3", StatusList.IN_PROGRESS, false, null);

        taskMeneger.addTask("эпик1", "11", StatusList.IN_PROGRESS, true, null);
        taskMeneger.addTask("эпик2", "22", StatusList.NEW, true, null);

        taskMeneger.addTask("эпик1саб1", "11", StatusList.IN_PROGRESS, false, "эпик1");
        taskMeneger.addTask("эпик1саб2", "12", StatusList.IN_PROGRESS, false, "эпик1");

        taskMeneger.addTask("эпик2саб1", "21", StatusList.NEW, false, "эпик2");

        System.out.println("заполнение болванками");
        System.out.println(taskMeneger.getTaskStatus());
        System.out.println(taskMeneger.getTaskList());
        System.out.println("__________________");

        System.out.println("поиск по id ");
        System.out.println(taskMeneger.findTaskId(-1608546673));
        System.out.println(taskMeneger.findTaskId(1608546673));
        System.out.println("__________________");

        System.out.println("поиск по name ");
        System.out.println(taskMeneger.findTaskName("таск2"));
        System.out.println(taskMeneger.findTaskName(""));
        System.out.println("__________________");

        taskMeneger.removeAllTasks();
        System.out.println("полная очистка");
        System.out.println(taskMeneger.getTaskStatus());
        System.out.println(taskMeneger.getTaskList());


    }
}
