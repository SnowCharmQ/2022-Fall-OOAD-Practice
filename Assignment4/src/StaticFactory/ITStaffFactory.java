package StaticFactory;

public class ITStaffFactory {
    static ITStaff createManager() {
        return new ITManager();
    }

    static ITStaff createDeveloper() {
        return new Developer();
    }

    static ITStaff createTester() {
        return new Tester();
    }
}
