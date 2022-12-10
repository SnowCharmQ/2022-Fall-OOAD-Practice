package dependency_injection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class BeanFactoryImpl implements BeanFactory {

    private Properties injectProperties = new Properties();
    private Properties valueProperties = new Properties();

    @Override
    public void loadInjectProperties(File file) {
        try {
            InputStream is = new FileInputStream(file);
            injectProperties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadValueProperties(File file) {
        try {
            InputStream is = new FileInputStream(file);
            valueProperties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T createInstance(Class<T> clazz) {
        try {
            String className = clazz.getTypeName();
            if (injectProperties.containsKey(className)) clazz = (Class<T>) Class.forName(injectProperties.getProperty(className));
            Constructor<?> constructor = null;
            Constructor<?>[] constructors = clazz.getConstructors();
            for (Constructor<?> c : constructors) {
                Inject injectAnnotation = c.getAnnotation(Inject.class);
                if (injectAnnotation != null) constructor = c;
            }
            if (constructor == null) constructor = clazz.getConstructor();
            Parameter[] parameters = constructor.getParameters();
            Object[] objects = new Object[parameters.length];
            for (int i = 0; i < objects.length; i++) {
                boolean flag = false;
                Parameter p = parameters[i];
                Value valueAnnotation = p.getAnnotation(Value.class);
                if (valueAnnotation != null) {
                    String value = valueAnnotation.value();
                    if (valueProperties.containsKey(value)) value = valueProperties.getProperty(value);
                    String delimiter = valueAnnotation.delimiter();
                    if (delimiter == null) delimiter = ",";
                    Class<?> type = p.getType();
                    if (type.isArray() || type == List.class || type == Set.class || type == Map.class)
                        value = value.substring(1, value.length() - 1);
                    String[] split = value.split(delimiter);
                    if (type.isArray()) {
                        if (type.getComponentType() == boolean.class || type.getComponentType() == Boolean.class) {
                            boolean[] array = ParseFactory.parseBooleanArray(split);
                            objects[i] = array;
                        }else if (type.getComponentType() == int.class || type.getComponentType() == Integer.class) {
                            int[] array = ParseFactory.parseIntArray(split);
                            objects[i] = array;
                        } else {
                            String[] array = ParseFactory.parseStringArray(split);
                            objects[i] = array;
                        }
                        flag = true;
                    } else if (type == List.class) {
                        List<Object> list = ParseFactory.parseList(p, split);
                        objects[i] = list;
                        flag = true;
                    } else if (type == Set.class) {
                        Set<Object> set = ParseFactory.parseSet(p, split);
                        objects[i] = set;
                        flag = true;
                    } else if (type == Map.class) {
                        Map<Object, Object> map = ParseFactory.parseMap(p, split);
                        objects[i] = map;
                        flag = true;
                    } else {
                        for (String s : split) {
                            Object o = ParseFactory.parseType(p.getType(), s);
                            if (o != null) {
                                objects[i] = o;
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if (!flag) {
                    Object instance;
                    Class<?> type = p.getType();
                    if (type.isArray()) instance = new Object[0];
                    else if (type == List.class) instance = new ArrayList<>();
                    else if (type == Set.class) instance = new HashSet<>();
                    else if (type == Map.class) instance = new HashMap<>();
                    else if (type == boolean.class || type == Boolean.class) instance = false;
                    else if (type == int.class || type == Integer.class) instance = 0;
                    else if (type == String.class) instance = "";
                    else instance = createInstance(type);
                    objects[i] = instance;
                }
            }
            Object object = constructor.newInstance(objects);
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Value valueAnnotation = f.getAnnotation(Value.class);
                Inject injectAnnotation = f.getAnnotation(Inject.class);
                if (valueAnnotation != null) {
                    String value = valueAnnotation.value();
                    if (valueProperties.containsKey(value)) value = valueProperties.getProperty(value);
                    String delimiter = valueAnnotation.delimiter();
                    if (delimiter == null) delimiter = ",";
                    Class<?> type = f.getType();
                    if (type.isArray() || type == List.class || type == Set.class || type == Map.class)
                        value = value.substring(1, value.length() - 1);
                    String[] split = value.split(delimiter);
                    if (type.isArray()) {
                        ParseFactory.setArray(f, split, object);
                    } else if (type == List.class) {
                        ParseFactory.setList(f, split, object);
                    } else if (type == Set.class) {
                        ParseFactory.setSet(f, split, object);
                    } else if (type == Map.class) {
                        ParseFactory.setMap(f, split, object);
                    } else {
                        for (String s : split) {
                            Object o = ParseFactory.parseType(f.getType(), s);
                            if (o != null) {
                                if (f.getType() == int.class || f.getType() == Integer.class) {
                                    int i = (int) o;
                                    f.set(object, i);
                                } else if (f.getType() == boolean.class || f.getType() == Boolean.class) {
                                    boolean b = (boolean) o;
                                    f.set(object, b);
                                } else if (f.getType() == String.class) {
                                    String ss = (String) o;
                                    f.set(object, ss);
                                }
                                break;
                            }
                        }
                    }
                }
                if (injectAnnotation != null) {
                    Class<?> type = f.getType();
                    f.set(object, createInstance(type));
                }
                f.setAccessible(false);
            }
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class ParseFactory {

    public static void setArray(Field f, String[] values, Object object) throws IllegalAccessException {
        Class<?> clazz = f.getType();
        if (clazz == boolean[].class || clazz == Boolean[].class) {
            List<Boolean> list = handleBoolean(values);
            boolean[] booleans = new boolean[list.size()];
            for (int i = 0; i < booleans.length; i++) booleans[i] = list.get(i);
            f.set(object, booleans);
        } else if (clazz == int[].class || clazz == Integer[].class) {
            List<Integer> list = handleInteger(values);
            int[] ints = new int[list.size()];
            for (int i = 0;i < ints.length;i++) ints[i] = list.get(i);
            f.set(object, ints);
        } else if (clazz == String[].class) {
            if (values.length == 1 && Objects.equals(values[0], "")) {
                f.set(object, new String[0]);
                return;
            }
            List<String> list = Arrays.asList(values);
            f.set(object, list.toArray(new String[0]));
        }
    }

    public static boolean[] parseBooleanArray(String[] values) {
        List<Boolean> list = handleBoolean(values);
        boolean[] array = new boolean[list.size()];
        for (int i = 0;i < array.length;i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static int[] parseIntArray(String[] values) {
        List<Integer> list = handleInteger(values);
        int[] array = new int[list.size()];
        for (int i = 0;i < array.length;i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String[] parseStringArray(String[] values) {
        if (values.length == 1 && Objects.equals(values[0], "")) return new String[0];
        else return values;
    }

    public static void setList(Field f, String[] values, Object object) throws ClassNotFoundException, IllegalAccessException {
        Class<?> geneticType = Class.forName(((ParameterizedType) f.getGenericType())
                .getActualTypeArguments()[0].getTypeName());
        if (geneticType == boolean.class || geneticType == Boolean.class) {
            List<Boolean> list = handleBoolean(values);
            f.set(object, list);
        }
        else if (geneticType == int.class || geneticType == Integer.class) {
            List<Integer> list = handleInteger(values);
            f.set(object, list);
        }
        else if (geneticType == String.class) {
            if (values.length == 1 && Objects.equals(values[0], "")) {
                f.set(object, new ArrayList<>());
            } else {
                List<String> list = Arrays.asList(values);
                f.set(object, list);
            }
        }
    }

    public static <T> List<T> parseList(Parameter p, String[] values) throws ClassNotFoundException {
        Class<?> geneticType = Class.forName(((ParameterizedType) p.getParameterizedType())
                .getActualTypeArguments()[0].getTypeName());
        if (geneticType == boolean.class || geneticType == Boolean.class) return (List<T>) handleBoolean(values);
        else if (geneticType == int.class || geneticType == Integer.class) return (List<T>) handleInteger(values);
        else if (geneticType == String.class) {
            if (values.length == 1 && Objects.equals(values[0], "")) return new ArrayList<>();
            else return (List<T>) Arrays.asList(values);
        }
        return new ArrayList<>();
    }

    public static void setSet(Field f, String[] values, Object object) throws ClassNotFoundException, IllegalAccessException {
        Class<?> geneticType = Class.forName(((ParameterizedType) f.getGenericType())
                .getActualTypeArguments()[0].getTypeName());
        if (geneticType == boolean.class || geneticType == Boolean.class) {
            List<Boolean> list = handleBoolean(values);
            Set<Boolean> set = new HashSet<>(list);
            f.set(object, set);
        } else if (geneticType == int.class || geneticType == Integer.class) {
            List<Integer> list = handleInteger(values);
            Set<Integer> set = new HashSet<>(list);
            f.set(object, set);
        } else if (geneticType == String.class) {
            if (values.length == 1 && Objects.equals(values[0], "")) {
                f.set(object, new HashSet<>());
            } else {
                List<String> list = Arrays.asList(values);
                Set<String> set = new HashSet<>(list);
                f.set(object, set);
            }
        }
    }

    public static <T> Set<T> parseSet(Parameter p, String[] values) throws ClassNotFoundException {
        Class<?> geneticType = Class.forName(((ParameterizedType) p.getParameterizedType())
                .getActualTypeArguments()[0].getTypeName());
        if (geneticType == boolean.class || geneticType == Boolean.class) {
            List<Boolean> list = handleBoolean(values);
            Set<Boolean> set = new HashSet<>(list);
            return (Set<T>) set;
        } else if (geneticType == int.class || geneticType == Integer.class) {
            List<Integer> list = handleInteger(values);
            Set<Integer> set = new HashSet<>(list);
            return (Set<T>) set;
        } else if (geneticType == String.class) {
            if (values.length == 1 && Objects.equals(values[0], "")) {
                return new HashSet<>();
            } else {
                List<String> list = Arrays.asList(values);
                Set<String> set = new HashSet<>(list);
                return (Set<T>) set;
            }
        }
        return new HashSet<>();
    }

    public static void setMap(Field f, String[] values, Object object) throws ClassNotFoundException, IllegalAccessException {
        String keyType = ((ParameterizedType) f.getGenericType())
                .getActualTypeArguments()[0].getTypeName();
        String valType = ((ParameterizedType) f.getGenericType())
                .getActualTypeArguments()[1].getTypeName();
        Class<?> keyClass = Class.forName(keyType);
        Class<?> valClass = Class.forName(valType);
        Map<Object, Object> map = new HashMap<>();
        if (values.length == 1 && Objects.equals(values[0], "")) {
            f.set(object, map);
            return;
        }
        for (String value : values) {
            String[] split = value.split(":");
            String key, val;
            if (split.length == 2) {
                key = split[0];
                val = split[1];
            } else {
                key = split[0];
                val = "";
            }
            Object[] container = new Object[2];
            if (keyClass == int.class || keyClass == Integer.class) {
                Integer integer = parseType(keyClass, key);
                container[0] = integer;
            } else if (keyClass == boolean.class || keyClass == Boolean.class) {
                Boolean bool = parseType(keyClass, key);
                container[0] = bool;
            } else if (keyClass == String.class) {
                String string = parseType(keyClass, key);
                container[0] = string;
            }
            if (valClass == int.class || valClass == Integer.class) {
                Integer integer = parseType(valClass, val);
                container[1] = integer;
            } else if (valClass == boolean.class || valClass == Boolean.class) {
                Boolean bool = parseType(valClass, val);
                container[1] = bool;
            } else if (valClass == String.class) {
                String string = parseType(valClass, val);
                container[1] = string;
            }
            if (container[0] != null && container[1] != null) map.put(container[0], container[1]);
        }
        f.set(object, map);
    }

    public static <K, V> Map<K, V> parseMap(Parameter p, String[] values) throws ClassNotFoundException {
        String keyType = ((ParameterizedType) p.getParameterizedType())
                .getActualTypeArguments()[0].getTypeName();
        String valType = ((ParameterizedType) p.getParameterizedType())
                .getActualTypeArguments()[1].getTypeName();
        Map<K, V> map = new HashMap<>();
        Class<?> keyClass = Class.forName(keyType);
        Class<?> valClass = Class.forName(valType);
        for (String value : values) {
            String[] split = value.split(":");
            String key, val;
            if (split.length == 2) {
                key = split[0];
                val = split[1];
            } else {
                key = split[0];
                val = "";
            }
            K mapKey = parseType(keyClass, key);
            V mapVal = parseType(valClass, val);
            if (mapKey != null && mapVal != null) map.put(mapKey, mapVal);
        }
        return map;
    }

    public static <T> T parseType(Class<?> clazz, String str) {
        if (clazz == int.class || clazz == Integer.class) {
            try {
                Integer i = Integer.parseInt(str);
                return (T) i;
            } catch (NumberFormatException ignored) {
                return null;
            }
        } else if (clazz == boolean.class || clazz == Boolean.class) {
            str = str.toLowerCase();
            if (Objects.equals(str, "true")) return (T) Boolean.TRUE;
            if (Objects.equals(str, "false")) return (T) Boolean.FALSE;
            return null;
        } else if (clazz == String.class) return (T) str;
        return null;
    }

    public static List<Boolean> handleBoolean(String[] values) {
        List<Boolean> list = new ArrayList<>();
        for (String value : values) {
            String s = value.toLowerCase();
            if (Objects.equals(s, "true")) list.add(true);
            if (Objects.equals(s, "false")) list.add(false);
        }
        return list;
    }

    public static List<Integer> handleInteger(String[] values) {
        List<Integer> list = new ArrayList<>();
        for (String value : values) {
            try {
                int i = Integer.parseInt(value);
                list.add(i);
            } catch (NumberFormatException ignored) {
            }
        }
        return list;
    }
}
