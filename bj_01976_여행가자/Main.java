package bj_01976_여행가자;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//200104 25m / 17000 132ms / 생각보다 쉽게 풀려서 당황함. union-find 기본 문제
public class Main {
	
	private static int n, m, parents[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		parents = new int[n];
		Arrays.fill(parents, -1);
		
		int temp, temp2;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				temp = Integer.parseInt(st.nextToken());
				if (i < j) {
					if (temp == 1) {
						union(i, j);
					}
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		temp = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 1; i < m; i++) {
			temp2 = Integer.parseInt(st.nextToken()) - 1;
			if (!union(temp, temp2)) {
				System.out.println("NO");
				System.exit(0);
			}
			temp = temp2;
		}
		System.out.println("YES");
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return false;
		}
		return true;
	}

	private static int find(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
}