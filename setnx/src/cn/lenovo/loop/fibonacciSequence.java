package cn.lenovo.loop;
/**
 * 斐波那契数列 数列（兔子生仔）
 * 
 * @author Administrator
 *
 */
public class fibonacciSequence {
	//非递归实现
	public static int getTotal(int n){
		int []arr = new int[n];
		arr[0] = 1;
		arr[1] = 1;
		for (int i = 2; i < arr.length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr[n - 1];
	}
	
	//递归实现
	public static int loopGetTotal(int n){
		if(n == 0 || n == 1)
			return 1;
		return (loopGetTotal(n - 1) + loopGetTotal(n - 2));
	}
	
	public static void main(String[] args) {
		
		int total = fibonacciSequence.getTotal(5);
		System.out.println(total);
		int looptotal = fibonacciSequence.loopGetTotal(5 - 1);
		System.out.println(looptotal);
	}
}
