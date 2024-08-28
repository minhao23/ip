package jarvis;

import jarvis.Deadline;
import jarvis.Event;
import jarvis.Storage;
import jarvis.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> list;
    Storage storage = Storage.getInstance();

    public TaskList() {
        list = new ArrayList<>();
    }

    public void list() {
        for(int i = 0; i < list.size(); i++) {
            Task task = this.list.get(i);
            String checkbox = task.done ? "[X]" : "[ ]";
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
    public void add(String input){


        String[] words = input.split(" ");
        String[] parts = input.split(" /");
        switch(words[0]) {
        case "todo":
                String nameAndType = parts[0].substring(5);
                Task todo = new Todo(nameAndType);
                list.add(todo);
                storage.add(todo.toString());

        break;
        case "event":
                nameAndType = parts[0].substring(6);
                String from = parts[1].replace("from ", "");
                String to = parts[2].replace("to ", "");
                Task event = new Event(nameAndType, from, to);
                list.add(event);
                storage.add(event.toString());


        break;
        case "deadline":
                nameAndType = parts[0].substring(9);
                String end = parts[1].replace("by ", "by: ");
                Task deadline = new Deadline(nameAndType, end);
                list.add(deadline);
                storage.add(deadline.toString());

        break;
        default:
        System.out.println("Unknown command");
        break;
        }
    }

    public void unmark(int i) {
        this.list.get(i-1).done = false;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [X] %s\n", this.list.get(i-1).name);
        System.out.println("____________________________________________________________");
        storage.unmark(i-1);
    }

    public void mark(int i) {
        this.list.get(i-1).done = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've unmarked this task:");
        System.out.printf("  [X] %s\n", this.list.get(i-1).name);
        System.out.println("____________________________________________________________");
        storage.mark(i-1);
    }

    public String acknowledge() {
        Task task = this.list.get(this.list.size() -1);
        String end = String.format("\nnow you have %d task(s) in the list", this.list.size());
        String result = "Got it. I've added this task:\n" + task.toString() + end;
        return result;
    }

    public void handleDelete(int i) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.printf(" %s", this.list.get(i-1));
        System.out.printf("Now you have %d tasks in the list.\n", this.list.size() -1);
        System.out.println("____________________________________________________________");
        this.list.remove(i-1);
        storage.delete(i-1);

    }



}