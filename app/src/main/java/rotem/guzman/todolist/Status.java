package rotem.guzman.todolist;

public enum Status {
    WAITING, IN_PROGRESS, DONE, UNKNOWN;

    public static Status getStatus (String input){

        Status selectedStatus;

        switch (input){
            case "waiting":
                selectedStatus = WAITING;
                break;
            case "in progress":
                selectedStatus = IN_PROGRESS;
                break;
            case "done":
                selectedStatus = DONE;
            default:
                selectedStatus = UNKNOWN;

        }
        return selectedStatus;
    }
}
