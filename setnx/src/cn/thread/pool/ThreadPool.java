package cn.thread.pool;
/**
 * 线程池实现的原理
 * 1.定义一个runnable的任务队列
 * 2.定义实现了 runnable 接口的 work
 *   a. work类主要是用来在任务队列中获取任务进行执行
 *   b. 有任务是就获取任务直接执行 
 *   c. 没有任务就是使用while(true) 循环休眠等待任务的到来
 * 3.初始化一定数量的Thead（把work runnable 作为参数传进去）
 * 	   同时开启线程
 * 4.关闭线程池 需要使用到线程的中断唤醒机制 因为当所有任务运行完之后 所有的线程均是在休眠状态 
 *
 *
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {

	private static final Exception IllegalArgumentException = null;
	private int threadInitSize = 0;
	private int threadTotal = 0;
	private int threadMax = 0;
	// 任务队列
	private List<Thread> threads = new ArrayList<>();
	// 工作队列
	private BlockingQueue<Work> Works = new ArrayBlockingQueue<Work>(20);
	// 任务队列
	private List<Runnable> jobs = new LinkedList<>();

	ThreadPool(int initThread) throws InterruptedException {
		for (int i = 0; i < initThread; i++) {
			
			Work Work = new Work((LinkedList<Runnable>) jobs);
			Works.put(Work);
			
			Thread t = new Thread(Work);
			t.start();
			
			threads.add(t);
			threadTotal++;
		}
	}

	public void exec(Runnable job) throws Exception {
		if (job == null)
			throw IllegalArgumentException;
		synchronized (jobs) {
			jobs.add(job);
			jobs.notify();
		}
	}

	public void shutdownPool() {
		System.out.println("stop");
		for (Work work : Works) {
			work.shutdown();
		}
		for (Thread thread : threads) {
			thread.interrupt();
		}
	}

	public static void main(String[] args) throws Exception {
		ThreadPool pool = new ThreadPool(5);
		for (int i = 0; i < 10; i++) {
			pool.exec(new Runnable() {
				public void run() {
					System.out.println("线程 " + Thread.currentThread().getName() + "在运行job");
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
			});
		}
		System.in.read();
		
		pool.shutdownPool();
	}
}

class Work implements Runnable {

	private volatile boolean RUNNING = true;
	private LinkedList<Runnable> jobs = null;

	Work(LinkedList<Runnable> jobs) {
		this.jobs = jobs;
	}

	@Override
	public void run() {
		while (RUNNING) {
			Runnable job = null;
			synchronized (jobs) {
				// 没有任务进行休眠
				while(jobs.isEmpty() && RUNNING) {// 必须使用while不能用if 不然会出现异常
					try {
						jobs.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				//当没有任务 然后使用中断唤醒线程时 会运行一遍这个代码 会出现异常 所以需要判断是否为空为空时不获取
				if(!jobs.isEmpty())
					job = jobs.removeFirst();
				
				//任务的执行需要放外面比较合适 不然锁住任务太久 性能非常不好 不允许这种做
/*				if(job != null)
					job.run();*/
			}
			if(job != null)
				job.run();
		}
	}

	public void shutdown() {
		RUNNING = false;
		System.out.println("interrupted");
	}

}
