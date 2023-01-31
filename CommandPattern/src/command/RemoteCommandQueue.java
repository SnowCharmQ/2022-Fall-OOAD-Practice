package command;

import java.util.ArrayDeque;
import java.util.Queue;

public class RemoteCommandQueue {
    Queue<Command> commands;

    public RemoteCommandQueue() {
        this.commands = new ArrayDeque<>();
    }

    public void buttonPressed(Command command) {
        commands.add(command);
    }

    public void commandExecute() {
        Command command = commands.poll();
        assert command != null;
        command.execute();
    }
}
