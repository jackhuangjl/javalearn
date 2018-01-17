package cn.learn.multiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 生产者 消费者模式
 * @author Administrator
 *
 */
public class Producer_Consumer {
	private static List<String> product = new ArrayList<>();
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Producer(product));
		Thread t2 = new Thread(new Consumer(product));
		t1.start();
		t2.start();
		
	}
}
class Producer implements Runnable{
	private List<String> product = null;
	private String content = null;
	public Producer(List<String> productList) {
		// TODO Auto-generated constructor stub
		this.product = productList;
	}
	@Override
	public void run() {
		
		while(true){
			synchronized (product) {
				while(product.isEmpty()){
					try {
						product.wait();//休眠时会释放掉锁
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				content = product.remove(0);
			}
			System.out.println("消费者消费了 " + content);
		}
	}
}
class Consumer implements Runnable{
	
	private List<String> productList = null;
	private Integer total = 0;
	
	Consumer(List<String> product){
		this.productList = product;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			String prduct = new String(total.toString());
			total++;
			synchronized (productList) {
				productList.add(prduct);
				productList.notify();
			}
		}
	}
	
}