package cn.thread.pool;
/**
 * �̳߳�ʵ�ֵ�ԭ��
 * 1.����һ��runnable���������
 * 2.����ʵ���� runnable �ӿڵ� work
 *   a. work����Ҫ����������������л�ȡ�������ִ��
 *   b. �������Ǿͻ�ȡ����ֱ��ִ�� 
 *   c. û���������ʹ��while(true) ѭ�����ߵȴ�����ĵ���
 * 3.��ʼ��һ��������Thead����work runnable ��Ϊ��������ȥ��
 * 	   ͬʱ�����߳�
 * 4.�ر��̳߳� ��Ҫʹ�õ��̵߳��жϻ��ѻ��� ��Ϊ����������������֮�� ���е��߳̾���������״̬ 
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
	// �������
	private List<Thread> threads = new ArrayList<>();
	// ��������
	private BlockingQueue<Work> Works = new ArrayBlockingQueue<Work>(20);
	// �������
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
					System.out.println("�߳� " + Thread.currentThread().getName() + "������job");
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
				// û�������������
				while(jobs.isEmpty() && RUNNING) {// ����ʹ��while������if ��Ȼ������쳣
					try {
						jobs.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}
				//��û������ Ȼ��ʹ���жϻ����߳�ʱ ������һ��������� ������쳣 ������Ҫ�ж��Ƿ�Ϊ��Ϊ��ʱ����ȡ
				if(!jobs.isEmpty())
					job = jobs.removeFirst();
				
				//�����ִ����Ҫ������ȽϺ��� ��Ȼ��ס����̫�� ���ܷǳ����� ������������
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
