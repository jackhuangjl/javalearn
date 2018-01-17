package cn.sortimpl;
/**
 * �鲢���� �ݹ鷽��ʵ��
 * �ȷ� ������
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
        // third ��¼��ʱ���������  
        int third = left;  
        // �����������һ��Ԫ�ص�����  
        int tmp = left;  		
		sort(arr,left,mid);
		sort(arr,mid + 1,right);
		
		while (i <= mid && j <= right) {  
            // ������������ȡ����С�ķ�����ʱ����  
            if (arr[i] <= arr[j]) {  
                tmpArr[third++] = arr[i++];  
            } else {  
                tmpArr[third++] = arr[j++];  
            }  
        }  
        // ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����  
        while (j <= right) {  
            tmpArr[third++] = arr[j++];  
        }  
        while (i <= mid) {  
            tmpArr[third++] = arr[i++];  
        }  
        // ����ʱ�����е����ݿ�����ԭ������  
        // ��ԭleft-right��Χ�����ݱ����ƻ�ԭ���飩  
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
