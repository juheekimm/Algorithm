package bj_13335_트럭;

import java.io.*;
import java.util.*;

public class Main_201001 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Queue<Integer> allList = new LinkedList<Integer>();
		Queue<Integer> nowBridge = new LinkedList<Integer>();
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			allList.add(Integer.parseInt(st.nextToken()));
		
		int time = 0, now = 0, nowWeight = 0;
		while (!(allList.isEmpty() && nowBridge.isEmpty())) {
			if (!nowBridge.isEmpty()) {
				if (now >= w) {
					nowWeight -= nowBridge.poll();
					if (nowBridge.isEmpty())
						now = 0;
					else
						now--;
				}
			}
			
			if (!allList.isEmpty()) {
				if (nowWeight + allList.peek() <= L) {
					if (nowBridge.isEmpty() || nowBridge.size() < w) {
						nowBridge.add(allList.peek());
						nowWeight += allList.poll();
					}
				}
			}
			now++;
			time++;
		}
		System.out.println(time);
	}
}
