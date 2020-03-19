package rotem.guzman.todolist;

public class Task {

    private String task;
    private Status status;


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public Task(String task, Status status) {
        this.task = task;
        this.status = status;
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
                ", status=" + status +
                '}';
    }
}
