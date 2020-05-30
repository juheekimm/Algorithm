package bj_02617_구슬;

import java.io.*;
import java.util.*;

public class Main3 {
	static int n, m, mid, cnt = 0;
	static ArrayList<Integer>[] list, reverselist;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		mid = (n + 1) / 2;
		
		list = new ArrayList[n + 1];
		reverselist = new ArrayList[n + 1];
		visit = new boolean[n + 1];
		
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
			reverselist[i] = new ArrayList<>();
		}
			
		int easy, hard;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			hard = Integer.parseInt(st.nextToken());
			easy = Integer.parseInt(st.nextToken());
			
			list[easy].add(hard);
			reverselist[hard].add(easy);
		}
		
		for (int i = 1; i <= n; i++) {
			Arrays.fill(visit, false);
			visit[i] = true;
			int sum = dfs(list, i);
			if (sum >= mid) cnt++;
		}
		
		for (int i = 1; i <= n; i++) {
			Arrays.fill(visit, false);
			visit[i] = true;
			int sum = dfs(reverselist, i);
			if (sum >= mid) cnt++;
		}
		
		System.out.println(cnt);
	}

	private static int dfs(ArrayList<Integer>[] li, int idx) {
		int sum = 0;
		
		for (int i = 0; i < li[idx].size(); i++) {
			if (visit[li[idx].get(i)]) continue;
			System.out.println("i " + i + " li[idx].get(i)" + li[idx].get(i));
			visit[li[idx].get(i)] = true;
//			sum++;
			sum += (dfs(li, li[idx].get(i)) + 1);
		}
//		System.out.println(sum);
		return sum;
	}
}