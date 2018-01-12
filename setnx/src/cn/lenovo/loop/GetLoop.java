package cn.lenovo.loop;
/**
 * ʵ�� һȺ������һ�� ����һ����  ���㿪ʼ����
 * ��˭���� �պø������������� ����˾ͳ��� ѭ������
 * ֱ��ʣ��һ���� ��ֹͣ  ���ʣ�µ������
 */

import java.util.LinkedList;
import java.util.List;


public class GetLoop {
	
	private List<Integer> loop = new LinkedList<>();
	//���浱ǰ���� ���� deleteIndex = index % loop.size();
	int index = 0;
	//��¼����
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
