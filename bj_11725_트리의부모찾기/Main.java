package bj_11725_트리의부모찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, size, ans[][];
	static LinkedList<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		size = N;
		list = new LinkedList[N + 1];
		ans = new int[N + 1][3];
		
		for (int i = 0; i <= N; i++)
			list[i] = new LinkedList<Integer>();
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		int a = list[1].remove(0);
		ans[1][0] = a;
		for (int i = 0; i < list[a].size(); i++)
			if (list[a].get(i) == 1)
				list[a].remove(i);
		ans[a][2] = 1;
		size--;
		
		if (list[1].size() > 0) {
			a = list[1].remove(0);
			ans[1][1] = a;
			for (int i = 0; i < list[a].size(); i++)
				if (list[a].get(i) == 1)
					list[a].remove(i);
			ans[a][2] = 1;
			size--;
		}
		
		while (size > 1) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < 2; j++) {
					if (ans[i][j] != 0 && list[ans[i][j]].size() > 0) {
						int temp = list[ans[i][j]].remove();
						if (ans[ans[i][j]][0] == 0)
							ans[ans[i][j]][0] = temp;
						else
							ans[ans[i][j]][1] = temp;
						
						for (int l = 0; l < list[temp].size(); l++)
							if (list[temp].get(l) == ans[i][j])
								list[temp].remove(l);
						
						ans[temp][2] = ans[i][j];
						size--;
					}
				}
			}
		}
		
		for (int i = 2; i <= N; i++)
			sb.append(ans[i][2] + "\n");
		System.out.println(sb);
	}
}