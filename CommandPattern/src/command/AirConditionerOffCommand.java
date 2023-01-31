package command;

public class AirConditionerOffCommand implements Command {

    AirConditioner airConditioner;

    public AirConditionerOffCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        this.airConditioner.off();
    }

    @Override
    public void undo() {
        this.airConditioner.on();
    }
}
