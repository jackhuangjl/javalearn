package cn.lenovo.loop;

import org.junit.Test;

/**
 * 
 * 判断101-200之间有多少个素数，并输出所有素数。 
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
