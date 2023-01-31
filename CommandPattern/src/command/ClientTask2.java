package command;

import java.util.InputMismatchException;
import java.util.Scanner;

import static command.Utils.showState;

public class ClientTask2 {
    public static void main(String[] args) {
        AirConditioner roomAirConditioner = new AirConditioner("bedRoom");
        AirConditioner livingAirConditioner = new AirConditioner("livingRoom");
        Light roomLight = new Light("bedRoom");
        Light livingLight = new Light("livingRoom");

        Command[] commands = new Command[8];
        commands[0] = new AirConditionerOnCommand(roomAirConditioner);
        commands[1] = new AirConditionerOffCommand(roomAirConditioner);
        commands[2] = new AirConditionerOnCommand(livingAirConditioner);
        commands[3] = new AirConditionerOffCommand(livingAirConditioner);
        commands[4] = new LightOnCommand(roomLight);
        commands[5] = new LightOffCommand(roomLight);
        commands[6] = new LightOnCommand(livingLight);
        commands[7] = new LightOffCommand(livingLight);

        RemoteCommandQueue remoteCommandQueue = new RemoteCommandQueue();
        Scanner in = new Scanner(System.in);
        System.out.println("Please input operation number to add a command: 1-9, " +
                "[1,3,5,7] is on command, [2,4,6,8] is off command, " +
                "9 is to show state, 10 is to execute command, 11 is to undo previous command. " +
                "Terminate by 0:");
        int op = 0;
        do {
            try {
                op = in.nextInt();
                switch (op) {
                    case 1, 2, 3, 4, 5, 6, 7, 8 -> remoteCommandQueue.buttonPressed(commands[op - 1]);
                    case 9 -> showState(new AirConditioner[]{roomAirConditioner, livingAirConditioner},
                            new Light[]{roomLight, livingLight});
                    case 10 -> remoteCommandQueue.commandExecute();
                    case 11 -> remoteCommandQueue.commandUndo();
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
                in.nextLine();
            }
        } while (op != 0);
        in.close();
    }
}
