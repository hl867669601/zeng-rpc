package club.xiaozeng.common.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @time: 2022/11/29 20:21
 * @author: zengh
 * @description: 线程池自定义参数
 */
@Data
@Getter
@Setter
public class ThreadPoolConfig {

    // 核心线程数
    private static final int DEFAULT_CORE_POOL_SIZE = 8;

    // 最大核心线程数
    private static final int DEFAULT_MAX_POOL_SIZE = 16;

    // keepLiveTime
    private static final int DEFAULT_KEEP_LIVE_TIME = 60;

    // 默认阻塞队列大小
    private static final int DEFAULT_BLOCKING_QUEUE_SIZE = 100;

    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;
    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;
    private int keepLiveTime = DEFAULT_KEEP_LIVE_TIME;
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(DEFAULT_BLOCKING_QUEUE_SIZE);

}
