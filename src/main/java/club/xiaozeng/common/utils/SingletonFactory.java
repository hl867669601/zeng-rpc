package club.xiaozeng.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @time: 2022/11/29 22:19
 * @author: zengh
 * @description:
 */
public final class SingletonFactory {
    private static final Map<String,Object> SINGLETONS = new ConcurrentHashMap<>();

    private SingletonFactory(){

    }

    public static <T> T getInstance(String clazzName){
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) SingletonFactory.class.getClassLoader().loadClass(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (clazz==null){
            return null;
        }

        if (SINGLETONS.containsKey(clazzName)){
            return clazz.cast(SINGLETONS.get(clazzName));
        }else {
            try {
                Object o = clazz.getDeclaredConstructor().newInstance();
                SINGLETONS.put(clazzName,o);
                return clazz.cast(o);
            } catch (InstantiationException | IllegalAccessException |InvocationTargetException |NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(),e);
            }
        }
    }
}
