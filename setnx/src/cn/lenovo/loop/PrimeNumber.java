package cn.lenovo.loop;

import org.junit.Test;

/**
 * 
 * �ж�101-200֮���ж��ٸ���������������������� 
 * @author Administrator
 *
 */
public class PrimeNumber {

	@Test
	public void getPrimeNumber(){
		int totalNumber = 0;
		int i = 0,j = 0;
		for(i = 101; i < 200; i++){
			
			int count = 0;
			for(j = 1; j <= i; j++){
				
				if(i%j == 0){
					count++;
				}
			}
			if(count == 2){
				totalNumber++;
			}
		}
		System.out.println(totalNumber);
	}
}
