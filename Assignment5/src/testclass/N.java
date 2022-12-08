package testclass;

import dependency_injection.Value;

import java.util.List;
import java.util.Set;

public class N {
    @Value(value = "{3d1,d11,2e1}")
    private Set<Integer> set;

    @Value(value = "[]")
    private String str;

    @Value(value = "[]")
    private List<String> list;

    @Value(value = "[d2-d21-f3]", delimiter = "-")
    private int anInt;

    @Value(value = "[ff,dwa,21]")
    private boolean aBoolean;

    public Set<Integer> getSet() {
        return set;
    }

    public String getStr() {
        return str;
    }

    public List<String> getList() {
        return list;
    }

    public int getAnInt() {
        return anInt;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }
}
