package testclass;

import dependency_injection.Inject;
import dependency_injection.Value;

import java.util.List;
import java.util.Map;

public class P {
    private String test1;
    private List<Integer> test2;
    private boolean[] test3;
    private Map<String, Integer> test4;

    @Inject
    public P(@Value(value = "wda,dwa,fq2,f2d1", delimiter = "-") String test1, @Value(value = "[d1,d12d,f2]") List<Integer> test2,
             @Value(value = "{awd,d1,21d1,f1,faLse}") boolean[] test3, @Value(value = "{wda:12,lll:d1,dj291:12132}") Map<String, Integer> test4) {
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.test4 = test4;
    }

    public String getTest1() {
        return test1;
    }

    public List<Integer> getTest2() {
        return test2;
    }

    public boolean[] getTest3() {
        return test3;
    }

    public Map<String, Integer> getTest4() {
        return test4;
    }
}
