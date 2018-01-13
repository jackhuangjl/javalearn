package cn.lenovo.loop;

import java.util.Scanner;

import org.junit.Test;

public class StringLearn {
	
	/**
	 * 
	 * ����һ���ַ���,�жϸ��ַ������Ƿ����ĳ���Ӵ�.�������,����Ӵ������г���λ��.
	 * ��:"abcbcbabcb34bcbd"��,"bcb"�Ӵ��ĳ���λ��Ϊ: 1,7,12.�ַ������Ӵ������û�����
	 */
	@Test
	public void getSubStringIndex() {

		System.out.println("�������ַ���");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();

		System.out.println("�������Ӵ�");
		sc = new Scanner(System.in);
		String subString = sc.nextLine();

		//����һ ʹ�� subString(beginIndex,endIndex)
		if (inputString != null && subString != null && inputString.length() >= subString.length()) {
			
			for (int i = 0; i < inputString.length();) {
				
				if(inputString.substring(i, i + subString.length()).equals(subString)){
					System.out.println(i);
					i = i + subString.length();
				}else{
					i++;
				}
			}

		}
		
		//������ ʹ��indexOf(str, fromIndex)
		//��ָ����λ�ÿ�ʼ�����Ӵ� ����ҵ��ͷ��ؿ�ʼ��indexֵ û�е��ҵ��ͷ���-1
		int fromIndex = 0;
		int indexOf = 0;
		while(true){
			indexOf = inputString.indexOf(subString, fromIndex);
			if(indexOf == -1){
				break;
			}
			System.out.println(indexOf);
			fromIndex = indexOf + subString.length();
		}
		
	}
}
