package club.xiaozeng.common.utils;

import club.xiaozeng.common.config.ThreadPoolConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @time: 2022/11/29 20:19
 * @author: zengh
 * @description:
 */
public class ThreadPoolFactoryUtil {

    // 线程池根据前缀为key放在这里统一管理
    private static final Map<String, ExecutorService> THREAD_POOLS = new ConcurrentHashMap<>();
    // 根据默认配置和前缀创建线程池
    public static ExecutorService createThreadPoolIfAbsent(String threadNamePrefix){
        ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
        return createThreadPoolIfAbsent(threadPoolConfig,threadNamePrefix,false);
    }
    public static ExecutorService createThreadPoolIfAbsent(ThreadPoolConfig threadPoolConfig,String threadNamePrefix){
        return createThreadPoolIfAbsent(threadPoolConfig,threadNamePrefix,false);
    }

    public static ExecutorService createThreadPoolIfAbsent(ThreadPoolConfig threadPoolConfig,String threadNamePrefix,Boolean isDaemon){
        return null;
    }

    private static ExecutorService createThreadPool(ThreadPoolConfig threadPoolConfig,String threadNamePrefix,Boolean daemon){
        return null;
    }

    public static ThreadFactory createThreadFactory(String threadNamePrefix,Boolean daemon){
            if (threadNamePrefix==null) {
                threadNamePrefix = "default";
            }

            return new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t  = new Thread(r);
                        t.setDaemon(daemon);
                        return t;
                    }
                };

    }
}
