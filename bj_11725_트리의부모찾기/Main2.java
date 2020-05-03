package bj_11725_트리의부모찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2 {
	
	static int N, size, ans[][];
	static LinkedList<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
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
			System.out.println("a " + a + " b " + b);
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
			System.out.println("N?" + N);
		}
		
		while (size > 1) {
			for (int i = 1; i <= N; i++) {
				for (int j = list[i].size() - 1; j >= 0; j--) {
					int temp = list[i].remove(j);	//4
					System.out.println("temp>>>" + temp);
					for (int k = list[temp].size() - 1; k >= 0; k--) {
						int temp2 = list[temp].remove(k);
						if (ans[temp][0] == 0) {
							ans[temp][0] = temp2;
							System.out.println("i " + i + " j " + j + " temp " + temp + " temp2 " + temp2);
						}
						else
							ans[temp][1] = temp2;

						for (int l = 0; l < list[temp2].size(); l++)
							if (list[temp2].get(l) == temp)
								list[temp2].remove(l);
						size--;
						System.out.println(temp2 + " ???" + temp);
						ans[temp2][2] = temp;
					}
					System.out.println(size);
				}
			}
		}
		for (int i = 2; i <= N; i++) {
			System.out.println(Arrays.toString(ans[i]));
		}
	}

}
