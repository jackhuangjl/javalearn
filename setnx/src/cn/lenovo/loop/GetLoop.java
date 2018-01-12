package cn.lenovo.loop;
/**
 * 实现 一群人排在一起 给出一个数  从零开始数数
 * 到谁报数 刚好给给出的数整除 这个人就出列 循环进行
 * 直到剩下一个人 就停止  求出剩下的这个人
 */

import java.util.LinkedList;
import java.util.List;


public class GetLoop {
	
	private List<Integer> loop = new LinkedList<>();
	//保存当前索引 根据 deleteIndex = index % loop.size();
	int index = 0;
	//记录数数
	int count = 0;
	
	public GetLoop(List<Integer> loopList){
		this.loop = loopList;
	}
	
	public Integer getTarget(int target){		
		while(loop.size() != 1){
			if((count % target) == 0){
				loop.remove(index % loop.size());
				index--;
			}
			index++;
			count++;
		}	
		return loop.get(0);
	}
	
	public static void main(String[] args) {
		List<Integer> testList = new LinkedList<>();
		testList.add(1);
		testList.add(2);
		testList.add(3);	
		testList.add(4);
		testList.add(5);
		GetLoop loop = new GetLoop(testList);		
		System.out.println(loop.getTarget(3));
		
	}
	
}
