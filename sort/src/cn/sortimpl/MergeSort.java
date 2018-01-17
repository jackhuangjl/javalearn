package cn.sortimpl;
/**
 * 归并排序 递归方法实现
 * 先分 后排序
 * @author Administrator
 *
 */
public class MergeSort {

	public static void sort(int arr[],int left,int right){
		int[] tmpArr = new int[arr.length];
		
		if(left >= right )
			return;
		int mid = (left + right)/2;		
		int i = left, j = mid + 1;		
        // third 记录临时数组的索引  
        int third = left;  
        // 缓存左数组第一个元素的索引  
        int tmp = left;  		
		sort(arr,left,mid);
		sort(arr,mid + 1,right);
		
		while (i <= mid && j <= right) {  
            // 从两个数组中取出最小的放入临时数组  
            if (arr[i] <= arr[j]) {  
                tmpArr[third++] = arr[i++];  
            } else {  
                tmpArr[third++] = arr[j++];  
            }  
        }  
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）  
        while (j <= right) {  
            tmpArr[third++] = arr[j++];  
        }  
        while (i <= mid) {  
            tmpArr[third++] = arr[i++];  
        }  
        // 将临时数组中的内容拷贝回原数组中  
        // （原left-right范围的内容被复制回原数组）  
        while (tmp <= right) {  
            arr[tmp] = tmpArr[tmp++];  
        }  
	}
	public static void main(String[] args) {
		
        int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
        sort(data,0,data.length - 1);
        for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " " );
		}
	}
}
