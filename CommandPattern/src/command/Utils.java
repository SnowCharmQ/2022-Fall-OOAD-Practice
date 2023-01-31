package command;

public class Utils {
    public static void showState(AirConditioner[] airConditioners, Light[] lights) {
        for (AirConditioner a : airConditioners) {
            System.out.println(a);
        }
        for (Light l : lights) {
            System.out.println(l);
        }
    }
}
