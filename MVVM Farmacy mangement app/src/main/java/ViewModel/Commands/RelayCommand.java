package ViewModel.Commands;


public class RelayCommand {
    private final Runnable action;

    public RelayCommand(Runnable action) {
        this.action = action;
    }

    public void execute() {
        action.run();
    }
}