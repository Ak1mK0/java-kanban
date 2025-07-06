import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        Task task = new Task("Перезд", "Машина", StatusList.NEW);
        System.out.println(task);

        Task sub1 = new Subtask("Перезд 12", "Машина", StatusList.IN_PROGRESS, "123");
        Task sub2 = new Subtask("Перезд 41", "Машина", StatusList.DONE, "123");
        System.out.println(sub1);
        System.out.println(sub2);

        ArrayList<Task> sub = new ArrayList<>();
        sub.add(sub1);
        sub.add(sub2);
        Epic epic = new Epic("Перезд 455", "Машина", StatusList.IN_PROGRESS, sub);
        System.out.println(epic);

    }
}
