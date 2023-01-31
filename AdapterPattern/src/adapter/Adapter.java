package adapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Adapter implements FileOperateInterfaceV2 {
    private FileOperateInterfaceV1 adaptee;

    public Adapter(FileOperateInterfaceV1 adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public List<StaffModel> readAllStaff() {
        return adaptee.readStaffFile();
    }

    @Override
    public void listAllStaff(List<StaffModel> list) {
        adaptee.printStaffFile(list);
    }

    @Override
    public void writeByName(List<StaffModel> list) {
        list.sort(Comparator.comparing(StaffModel::getName));
        this.writeByFilename(list, "staffByName");
    }

    @Override
    public void writeByRoom(List<StaffModel> list) {
        list.sort(Comparator.comparing(StaffModel::getRoom));
        this.writeByFilename(list, "staffByRoom");
    }

    public void writeByFilename(List<StaffModel> list, String ss) {
        try {
            if (list.size() == 0) {
                System.out.println("No information to be written");
                return;
            }
            String filename = String.format("AdapterPattern/src/adapter/%s.txt", ss);
            FileWriter f = new FileWriter(filename);
            BufferedWriter bufw = new BufferedWriter(f);
            String str;
            for (StaffModel s : list) {
                str = s.getName() + ":" +
                        s.getTitle() + "," +
                        s.getRoom() + "," +
                        s.getEmail() + "," +
                        s.getLink();
                bufw.write(str);
                bufw.newLine();
            }
            bufw.flush();
            bufw.close();
            System.out.println("finish writing");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
