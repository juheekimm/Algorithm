package bj_16938_캠프준비;

import java.util.*;

public class Main {
	static int n, l, r, x, count = 0;
	static int[] level;
	static boolean[] visit;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		l = s.nextInt();
		r = s.nextInt();
		x = s.nextInt();
		level = new int[n];
		
		for (int i = 0; i < n; i++)
			level[i] = s.nextInt();
		
		dfs(0, 0, 0);
		System.out.println(count);
	}
	
	//dfs에서 매개변수로 이전까지의 합계를 들고다니면?
	private static void dfs(int idx, int sum, int cnt) {
//		백트래킹을 적용한 이상, 다 돌고 돌아왔을 때 list에 남아있는 값은 없다.
//		if (cnt == 0)
//			list = new ArrayList<>();
		if (cnt >= 2) {
			if (r < sum) { //합이 최대보다 커져버리면 return;
				return;
			} else { //합이 최대보다 작거나 같고
				Collections.sort(list);
				if (l <= sum && list.get(cnt - 1) - list.get(0) >= x) { //최소보다 크면서 차이 조건도 만족하면
					count++;
				}
			}
		}
			
		for (int i = idx; i < n; i++) {
			sum += level[i];
			list.add(level[i]);
			
			dfs(i + 1, sum, cnt + 1);
			
			//백트래킹 부분
			sum -= level[i];
			for (int j = 0; j <= cnt; j++) {
				if (list.get(j) == level[i]) {
					list.remove(j);
					break;
				}
			}
		}
	}
}