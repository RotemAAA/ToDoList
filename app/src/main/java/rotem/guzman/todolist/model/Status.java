package rotem.guzman.todolist.model;

public enum Status {
    WAITING("waiting"), IN_PROGRESS("in progress"), DONE("done"), UNKNOWN("unknown");

    public final String state;

    Status(String state) {
        this.state = state;
    }

    public static Status getStatus(String input) {

        Status selectedStatus;

        switch (input) {
            case "waiting":
                selectedStatus = WAITING;
                break;
            case "in progress":
                selectedStatus = IN_PROGRESS;
                break;
            case "done":
                selectedStatus = DONE;
                break;
            default:
                selectedStatus = UNKNOWN;

        }
        return selectedStatus;
    }

}
