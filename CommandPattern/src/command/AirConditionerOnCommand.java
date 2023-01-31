package command;

public class AirConditionerOnCommand implements Command {

    AirConditioner airConditioner;

    public AirConditionerOnCommand(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        this.airConditioner.on();
    }

    @Override
    public void undo() {
        this.airConditioner.off();
    }
}
