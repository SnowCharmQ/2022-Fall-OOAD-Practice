package adapter;

import java.util.List;

public class Adapter implements FileOperateInterfaceV2 {
    private FileOperateInterfaceV1 adaptee;

    public Adapter(FileOperateInterfaceV1 adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public List<StaffModel> readAllStaff() {
        return null;
    }

    @Override
    public void listAllStaff(List<StaffModel> list) {

    }

    @Override
    public void writeByName(List<StaffModel> list) {

    }

    @Override
    public void writeByRoom(List<StaffModel> list) {

    }
}
