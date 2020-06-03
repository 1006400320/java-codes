# java之限流算法(计数器算法、令牌算法、漏桶算法）



## 1、 计数器算法(单机限流)

具体的实现可以是这样的：对于每次服务调用，可以通过 `AtomicInteger#incrementAndGet()` 方法来给计数器加1并返回最新值，通过这个最新值和阈值进行比较。

这种实现方式，相信大家都知道有一个弊端：如果我在单位时间1s内的前10ms，已经通过了100个请求，那后面的990ms，只能眼巴巴的把请求拒绝，我们把这种现象称为“突刺现象”

[CountRateLimiterDemo1](../../demos/src/main/java/com/linhuanjie/algorithm/ratelimiter/CountRateLimiterDemo1.java)



## 2 、令牌桶算法

令牌桶算法思想：以固定速率产生令牌，放入令牌桶，每次用户请求都得申请令牌，令牌不足则拒绝请求或等待。

![令牌桶算法示意图.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gf4kcwmggcj30a305zmxk.jpg)

### java实现令牌桶算法

[TokensRateLimiterDemo](../../demos/src/main/java/com/linhuanjie/algorithm/ratelimiter/TokensRateLimiterDemo.java)

### guava 实现令牌桶算法

[GuavaRateLimiterDemo](../../demos/src/main/java/com/linhuanjie/algorithm/ratelimiter/GuavaRateLimiterDemo.java)

[接口限流实践](https://www.cnblogs.com/LBSer/p/4083131.html)


### 集成到`zuul`网关限流实现

可以，对所有服务模块限流,也可以单独封装一个工具类限流，针对如秒杀，限购等接口限流.

```xml
<dependency>
		<groupId>com.marcosbarbero.cloud</groupId>
		<artifactId>spring-cloud-zuul-ratelimit</artifactId>
		<version>1.7.1.RELEASE</version>
</dependency>
```

```java
@Component
public class RateLimitZuulFilter extends ZuulFilter{	
	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitZuulFilter.class);
	//初始化 放入 1000令牌/s  时间窗口为 1s
	private final RateLimiter rateLimiter = RateLimiter.create(1000.0);
	@Override
	public boolean shouldFilter() {
		// 一直过滤
		return true;
	}
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx =  RequestContext.getCurrentContext();
		HttpServletResponse response = ctx.getResponse();
		if(!rateLimiter.tryAcquire()) {
			response.setContentType(MediaType.TEXT_PLAIN_VALUE);
			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
			try {
				response.getWriter().write("TOO MANY REQUESTS");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			ctx.setResponseStatusCode(200); 
			LOGGER.info("OK !!!");
		}
		return null;
	}
	@Override
	public String filterType() {
		return "pre";
	}
	@Override
	public int filterOrder() {
		return -4;
	}
}

```



## 3、 漏桶算法

漏桶可以看作是一个带有常量服务时间的单服务器队列，如果漏桶（包缓存）溢出，那么数据包会**被丢弃**。
在网络中，漏桶算法可以控制端口的流量输出速率，平滑网络上的突发流量，实现流量整形，从而为网络提供一个稳定的流量。
以固定速率从桶中流出水滴，以任意速率往桶中放入水滴，桶容量大小是不会发生改变的。

流入：以任意速率往桶中放入水滴。

流出：以固定速率从桶中流出水滴。

水滴：是唯一不重复的标识。

因为桶中的容量是固定的，如果流入水滴的速率>流出的水滴速率，桶中的水滴可能会溢出。那么溢出的水滴请求都是拒绝访问的，或者直接调用服务降级方法。前提是同一时刻。

![漏桶算法示意图.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gf4kf3ygg7j30cb08b74u.jpg)


## 4、Semaphore限流
　Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。

Semaphore的主要方法摘要：

　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。

　　void release():释放一个许可，将其返回给信号量。

　　int availablePermits():返回此信号量中当前可用的许可数。

　　boolean hasQueuedThreads():查询是否有线程正在等待获取。


参考：[java之限流算法(计数器算法、令牌算法、漏桶算法）](https://blog.csdn.net/qq_37220419/article/details/104499852)