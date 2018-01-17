package cn.sortimpl;
/**
 * 递归实现快排
 * 先排序  然后  进行划分
 * @author Administrator
 *
 */
public class QuickSort {
	
	public static void sort(int arr[],int begin,int end){
		
		if(begin >= end)
			return;		
		int i = begin, j = end;
		int temp = arr[begin];
	
		while(i < j){			
			while(i < j){
				if(temp > arr[j]){
					arr[i++]=arr[j];
					break;
				}
				j--;
			}
			while(i < j){
				if(arr[i] > temp){
					arr[j--]=arr[i];
					break;
				}
				i++;
			}			
		}
		arr[i] = temp;
		sort(arr,begin,i - 1);
		sort(arr,i + 1,end);
	}
	
	public static void main(String[] args) {
		int []arr = new int[10];
		int count = 9;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = count--;
		}
		QuickSort.sort(arr, 0, arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
}
