package rotem.guzman.todolist;

public class Task {
    private String task;

    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                '}';
    }
}
