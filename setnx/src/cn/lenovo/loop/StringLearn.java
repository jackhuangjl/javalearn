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
	 * 给定一个字符串,判断该字符串中是否包含某个子串.如果包含,求出子串的所有出现位置.
	 * 如:"abcbcbabcb34bcbd"中,"bcb"子串的出现位置为: 1,7,12.字符串和子串均由用户输入
	 */
	@Test
	public void getSubStringIndex() {
		System.out.println("请输入字符串");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();

		System.out.println("请输入子串");
		sc = new Scanner(System.in);
		String subString = sc.nextLine();

		//方法一 使用 subString(beginIndex,endIndex)
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
		
		//方法二 使用indexOf(str, fromIndex)
		//从指定的位置开始查找子串 如果找到就返回开始的index值 没有的找到就返回-1
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
	 * 2.给定一个由数字组成的字符串,如:"1239586838923173478943890234092",统计
	 * 	出每个数字出现的次数		
	 */
	@Test
	public void getNumberCount(){
		
		System.out.println("请输入一串数字字符串：");
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
		System.out.println("请输入字符串");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		
		StringBuffer stringBuffer = new StringBuffer(inputString);
		stringBuffer.reverse();
		
		System.out.println(stringBuffer);
	}
}
