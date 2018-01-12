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
		Thread t1 = new Thread(new setnxLock(), "�߳�һ");
		Thread t2 = new Thread(new setnxLock(), "�̶߳�");
		Thread t3 = new Thread(new setnxLock(), "�߳���");
		Thread t4 = new Thread(new setnxLock(), "�߳���");
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
		// ��������һ����ֵ һ���ж��Ƿ�������ͻ��˸�����
		long oldtime = 0;

		while (count > 0) {
			
			while (jedis.setnx("lock.queue", Long.toString(currentTime)) == 0) {

				String redisValue = jedis.get("lock.queue");

				// ����ȡ��ֵ�ǿ�ʱ ��ʾ�����
				if (redisValue == null) {
					jedis.setnx("lock.queue", Long.toString(System.currentTimeMillis() + 100 * 5));
					break;
				}
				// �������е�ʱ����ȵ�ǰʱ�����Ҫ��ʱ ֤����ռ��
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
					// ���Ϊ��Ҳ����ѭ�� ��ʾ�����
					if (setnx == null || setnx != oldtime) {
						break;
					}
				}
			}
			// ִ��ҵ��
			System.out.println(Thread.currentThread().getName() + "������� ���м�һ" + --count);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// �ͷ���
		jedis.del("lock.queue");
		
		jedis.close();
	}
}