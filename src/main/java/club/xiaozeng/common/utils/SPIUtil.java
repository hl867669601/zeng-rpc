package club.xiaozeng.common.utils;

import club.xiaozeng.common.serializer.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @time: 2022/11/29 21:03
 * @author: zengh
 * @description: 用于加载扩展的类
 */
public class SPIUtil {
    private static final Logger logger = LoggerFactory.getLogger(SPIUtil.class);
    private static Map<String, Object> SPI_OBJECTS = new ConcurrentHashMap<>();
    private static Map<String,String> EXTENSIONS = new HashMap<>();
    private static final String EXTENSION_FILE_NAME = "META-INF/extensions.spi";
    static {
        load();
    }
    public static <T> T getExtensionObject(Class<T> clazz){
        if (clazz==null){
            throw  new IllegalArgumentException();
        }
        String className = clazz.getTypeName();
        if (SPI_OBJECTS.containsKey(className)){
            return clazz.cast(SPI_OBJECTS.get(className));
        }else {
            if (!EXTENSIONS.containsKey(className)){
                logger.error("未配置此扩展类");
                throw new IllegalArgumentException("未配置此扩展类");
            }else {
                return SingletonFactory.getInstance(EXTENSIONS.get(className));
            }
        }

    }

    private static void load(){
        ClassLoader classLoader = SPIUtil.class.getClassLoader();
        URL url = classLoader.getResource(EXTENSION_FILE_NAME);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),UTF_8))) {

            String line;
            while ((line=reader.readLine())!=null){
                final int ei = line.indexOf('=');
                if (ei>0){
                    String name = line.substring(0,ei).trim();
                    String className = line.substring(ei+1).trim();
                    EXTENSIONS.put(name,className);
                }
            }
        }catch (IOException e){
            logger.error("加载SPI扩展类失败");

        }
        logger.info("加载SPI扩展类完成");
    }

    public static void main(String[] args) {
        SPIUtil spiUtil;
        Serializer serializer = SPIUtil.getExtensionObject(Serializer.class);
        System.out.println("Hhh");
    }


}
