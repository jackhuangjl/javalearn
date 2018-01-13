package cn.lenovo.loop;

import java.util.Scanner;

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
}
