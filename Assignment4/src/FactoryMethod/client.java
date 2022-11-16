package FactoryMethod;

import java.util.*;

public class client {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op = -1;

        List<ITStaff> staffList = new ArrayList<>();

        System.out.println("Please input an operation number:"
                + "\n1: add an IT manager"
                + "\n2: add an Developer"
                + "\n3: add an Tester"
                + "\n4: print all staff by salary order"
                + "\n5: print all staff by working order"
                + "\n0: Stop\n");
        do {
            try {
                op = input.nextInt();
                ITStaffFactoryInterface factory;
                switch (op) {
                    case 1 -> {
                        factory = new ITManagerFactory();
                        staffList.add(factory.createITStaff());
                    }
                    case 2 -> {
                        factory = new ITDeveloperFactory();
                        staffList.add(factory.createITStaff());
                    }
                    case 3 -> {
                        factory = new ITTesterFactory();
                        staffList.add(factory.createITStaff());
                    }
                    case 4 -> {
                        factory = new ITArtDesignerFactory();
                        staffList.add(factory.createITStaff());
                    }
                    case 5 -> {
                        System.out.println("All information:");
                        staffList.stream().sorted(Comparator.comparing(ITStaff::getSalary)).forEach(System.out::println);
                    }
                    case 6 -> {
                        System.out.println("All name:");
                        staffList.stream().sorted(Comparator.comparing(ITStaff::working)).forEach(System.out::println);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            }

        } while (op != 0);
        input.close();
    }

}
