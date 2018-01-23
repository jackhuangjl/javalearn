package cn.sortimpl;
/**
 * 
 * ≤Â»Î≈≈–ÚÀ„∑®
 * @author Administrator
 *
 */
public class InsertSort {

	public static void sort(Integer[] arr) {

		for (int i = 1; i < arr.length; i++) {
			int j = i;
			int temp = arr[j];
			for (; j > 0 && temp < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

	public static void main(String[] args) {
		Integer[] arr = { 34, 8, 64, 51, 32, 21 };
		sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
