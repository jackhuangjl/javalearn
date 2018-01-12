package cn.setnx.learn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisLock {
	public static JedisPool pool = null;

	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(200);
		poolConfig.setMaxTotal(2000);
		poolConfig.setMinIdle(100);
		pool = new JedisPool(poolConfig, "192.168.229.3", 6379);
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new setnxLock(), "线程一");
		Thread t2 = new Thread(new setnxLock(), "线程二");
		Thread t3 = new Thread(new setnxLock(), "线程三");
		Thread t4 = new Thread(new setnxLock(), "线程四");
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}

}

class setnxLock implements Runnable {
	private static int count = 2000;

	public void run() {

		Jedis jedis = RedisLock.getJedis();

		long currentTime = System.currentTimeMillis() + 100 * 5;
		// 用来报存一个老值 一遍判断是否给其他客户端更改了
		long oldtime = 0;

		while (count > 0) {
			
			while (jedis.setnx("lock.queue", Long.toString(currentTime)) == 0) {

				String redisValue = jedis.get("lock.queue");

				// 当获取的值是空时 表示获得锁
				if (redisValue == null) {
					jedis.setnx("lock.queue", Long.toString(System.currentTimeMillis() + 100 * 5));
					break;
				}
				// 当数据中的时间戳比当前时间戳还要大时 证明被占用
				oldtime = Long.parseLong(redisValue);
				if (oldtime > System.currentTimeMillis()) {
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Long setnx = jedis.setnx("lock.queue", Long.toString(System.currentTimeMillis() + 100 * 5));
					// 如果为空也跳出循环 表示获得锁
					if (setnx == null || setnx != oldtime) {
						break;
					}
				}
			}
			// 执行业务
			System.out.println(Thread.currentThread().getName() + "获得了锁 进行减一" + --count);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 释放锁
		jedis.del("lock.queue");
		
		jedis.close();
	}
}