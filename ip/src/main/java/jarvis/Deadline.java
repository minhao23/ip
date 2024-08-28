package jarvis;

public class Deadline extends Task {
    String deadline;
    public Deadline(String name, String deadline){
        super(name);
        this.deadline = deadline;
    }
    @Override
    public String toString(){
        String checkbox = this.done ? "[X]" : "[ ]";
        String result = String.format(" %s %s\n", checkbox, this.name);
        return "[D]" + result;
    }
}
