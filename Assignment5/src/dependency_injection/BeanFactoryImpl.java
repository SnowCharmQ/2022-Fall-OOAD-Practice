package dependency_injection;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {

    private Map<String, String> injectMap;
    private Map<String, String> valueMap;


    @Override
    public void loadInjectProperties(File file) {
        injectMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("=");
                if (strings.length != 2) continue;
                injectMap.put(strings[0], strings[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadValueProperties(File file) {
        valueMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("=");
                if (strings.length != 2) continue;
                valueMap.put(strings[0], strings[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T createInstance(Class<T> clazz) {
        return null;
    }
}
