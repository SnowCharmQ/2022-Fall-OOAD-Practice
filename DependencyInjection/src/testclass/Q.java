package testclass;

import dependency_injection.Inject;
import dependency_injection.Value;

import java.util.List;

public class Q {
    private List<Integer> test1;
    private String[] test2;
    private int[] test3;

    @Inject
    public Q(@Value(value = "[]") List<Integer> test1, @Value(value = "{dwad1,d12df,f1f,f12f12f}") String[] test2,
             @Value(value = "[d1,d1,f1,f1]") int[] test3) {
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
    }

    public List<Integer> getTest1() {
        return test1;
    }

    public String[] getTest2() {
        return test2;
    }

    public int[] getTest3() {
        return test3;
    }
}
