package localtests;

import dependency_injection.BeanFactory;
import dependency_injection.BeanFactoryImpl;
import dependency_injection.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExtendedTest {

    private BeanFactory beanFactory;

    @BeforeEach
    public void setup() {
        this.beanFactory = new BeanFactoryImpl();
        beanFactory.loadInjectProperties(new File("Assignment5/local-inject.properties"));
        beanFactory.loadValueProperties(new File("Assignment5/local-value.properties"));
    }

    public static class TestClass {
        @Value(value = "{}")
        public Map<String, String> test1;
        @Value(value = "{}")
        public Map<String, Integer> test14;
        @Value(value = "{}")
        public Map<String, Boolean> test15;
        @Value(value = "{}")
        public Map<Integer, String> test16;

        @Value(value = "[]")
        public String[] test2;
        @Value(value = "[]")
        public boolean[] test12;
        @Value(value = "[-1110]")
        public int[] test13;

        @Value(value = "")
        public String test3;
        @Value(value = " ")
        public String test33;
        @Value(value = "[]")
        public String test5;
        @Value(value = "{}")
        public String test6;


        @Value(value = "[]")
        public List<String> test4;
        @Value(value = "[]")
        public List<Integer> test7;
        @Value(value = "[]")
        public List<Boolean> test8;

        @Value(value = "{key:value0,key:value1}")
        public Map<String, String> test9;
        @Value(value = "asd,f,gasdfasf")
        public int test10;
        @Value(value = "asd,f,gasdfasf")
        public boolean test11;
        @Value(value = "{tRUe:955,fALSe:icu,Yes:955,No:996}")
        public Map<Boolean, Integer> work;
        @Value(value = "[2345,360,kingsoft,]")
        public List<String> ro;
        @Value(value = "[never,,give,0,12,you,up]")
        public int[] rick;

    }

    @Test
    public void test() {
        TestClass instance = beanFactory.createInstance(TestClass.class);
        assertNotNull(instance);
        assertNotNull(instance.test1);
        assertEquals(0, instance.test1.size());
        assertNotNull(instance.test14);
        assertEquals(0, instance.test14.size());
        assertNotNull(instance.test15);
        assertEquals(0, instance.test15.size());
        assertNotNull(instance.test16);
        assertEquals(0, instance.test16.size());
        assertNotNull(instance.test2);
        assertEquals(0, instance.test2.length);
        assertNotNull(instance.test12);
        assertEquals(0, instance.test12.length);
        assertNotNull(instance.test13);
        assertEquals(1, instance.test13.length);
        assertNotNull(instance.test3);
        assertEquals("", instance.test3);
        assertNotNull(instance.test33);
        assertEquals(" ", instance.test33);
        assertNotNull(instance.test4);
        assertEquals(0, instance.test4.size());
        assertNotNull(instance.test7);
        assertEquals(0, instance.test7.size());
        assertNotNull(instance.test8);
        assertEquals(0, instance.test8.size());
        assertNotNull(instance.test5);
        assertEquals("[]", instance.test5);
        assertNotNull(instance.test6);
        assertEquals("{}", instance.test6);
        assertNotNull(instance.test9);
        assertEquals(1, instance.test9.size());
        assertEquals("value1", instance.test9.get("key"));
        assertEquals(0, instance.test10);
        assertFalse(instance.test11);

        assertNotNull(instance.work);
        assertEquals(1, instance.work.size());
        assertEquals(955, instance.work.get(true));

        assertNotNull(instance.ro);
        assertEquals(3, instance.ro.size());

        assertNotNull(instance.rick);
        assertEquals(2, instance.rick.length);
    }
}