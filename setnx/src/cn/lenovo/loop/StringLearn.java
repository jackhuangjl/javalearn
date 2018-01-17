package cn.lenovo.loop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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
	/**
	 * 2.����һ����������ɵ��ַ���,��:"1239586838923173478943890234092",ͳ��
	 * 	��ÿ�����ֳ��ֵĴ���		
	 */
	@Test
	public void getNumberCount(){
		
		System.out.println("������һ�������ַ�����");
		Scanner sc = new Scanner(System.in);
		
		String numberString = sc.nextLine();
		Map<String,Integer> map = new HashMap<>();
		for(int i = 0; i < numberString.length(); i ++){		
			String keyString = Character.toString(numberString.charAt(i));
			if(map.containsKey(keyString)){
				map.put(keyString, map.get(keyString) + 1);
			}else{
				map.put(keyString,1);
			}
			
/*			if(map.containsKey(Character.toString(numberString.charAt(i)))){
				map.put(Character.toString(numberString.charAt(i)), map.get(numberString.charAt(i)) + 1);
			}else{
				map.put(Character.toString(numberString.charAt(i)),1);
			}*/
		}
		Set<Entry<String, Integer>> entries = map.entrySet();
		for (Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
	
	@Test
	public void reverseString(){
		System.out.println("�������ַ���");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		
		StringBuffer stringBuffer = new StringBuffer(inputString);
		stringBuffer.reverse();
		
		System.out.println(stringBuffer);
	}
}
