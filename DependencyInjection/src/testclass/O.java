package testclass;

import dependency_injection.Value;

public class O {
    @Value(value = "[hd-12-f3-12d3-d31-12]", delimiter = "-")
    private int[] arr1;

    @Value(value = "[4r3,f32]")
    private int[] arr2;

    @Value(value = "{d1,1d2,f231,1213}")
    private boolean[] arr3;

    @Value(value = "[falFsd,TrUe]")
    private boolean[] arr4;

    public int[] getArr1() {
        return arr1;
    }

    public int[] getArr2() {
        return arr2;
    }

    public boolean[] getArr3() {
        return arr3;
    }

    public boolean[] getArr4() {
        return arr4;
    }
}
