package SimpleFactory;

public class ITStaffFactory {
    ITStaff createITStaff(int choice) {
        switch (choice) {
            case 1 -> {
                return new ITManager();
            }
            case 2 -> {
                return new Developer();
            }
            case 3 -> {
                return new Tester();
            }
            default -> {
                return null;
            }
        }
    }
}
