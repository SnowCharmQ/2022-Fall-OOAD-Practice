package FactoryMethod;

public class ITTesterFactory implements ITStaffFactoryInterface{
    @Override
    public ITStaff createITStaff() {
        return new Tester();
    }
}
