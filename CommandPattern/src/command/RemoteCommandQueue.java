package command;

import java.util.ArrayDeque;
import java.util.Queue;

public class RemoteCommandQueue {
    Queue<Command> commands;
    Command undoCommand;

    public RemoteCommandQueue() {
        this.commands = new ArrayDeque<>();
    }

    public void buttonPressed(Command command) {
        this.commands.add(command);
    }

    public void commandExecute() {
        Command command = commands.poll();
        assert command != null;
        command.execute();
        this.undoCommand = command;
    }

    public void commandUndo() {
        this.undoCommand.undo();
    }
}
