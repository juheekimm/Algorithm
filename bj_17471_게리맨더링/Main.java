package bj_17471_게리맨더링;

import java.io.*;
import java.util.*;

public class Main {

	static int n, min = Integer.MAX_VALUE, combi[], people[];
	static boolean visit[], map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new boolean[n][n];
		visit = new boolean[n];
		people = new int[n];
		combi = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			people[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int tempNum = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tempNum; j++) {
				int tempIdx = Integer.parseInt(st.nextToken()) - 1;
				map[i][tempIdx] = map[tempIdx][i] = true;
			}
		}

		for (int i = 1; i < n; i++) {
			makeCombi(i);

			do {
				if (linkCheckDfs(1, i) && linkCheckDfs(0, n - i))
					difCalc();
			} while (nextPermutation());
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	// 배열 초기화 및 num만큼 1 넣어주는 함수
	private static void makeCombi(int num) {
		Arrays.fill(combi, 0);
		for (int i = 0; i < num; i++)
			combi[n - i - 1] = 1;
	}

	//선택된 애들 / 안된애들끼리 각각 연결되어있는지 체크
	private static boolean linkCheckDfs(int val, int cnt) {
		if (cnt == 1) return true;

		Arrays.fill(visit, false);
		for (int i = 0; i < combi.length; i++) {
			if (combi[i] == val) {
				visit[i] = true;
				check(i, cnt, 0);

				for (int j = 0; j < n; j++)
					if (combi[j] == val)
						if (!visit[j])
							return false;
				
				return true;	// 나랑 값이 같은애들이 다 체크되어 있다면 ok
			}
		}
		return false;
	}
	
	//같은 combi값을 가진애랑 연결되어있다면(map이 true라면) visit체크
	private static void check(int idx, int cnt, int now) {
		for (int j = 0; j < n; j++) {
			if (idx == j || visit[j]) continue;
			
			if (map[idx][j] && combi[idx] == combi[j]) {
				visit[j] = true;
				check(j, cnt, now + 1);
			}
		}
	}
	
	//차이를 구해주는 함수
	private static void difCalc() {
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < n; i++) {
			if (combi[i] == 1)
				sum1 += people[i];
			else
				sum2 += people[i];
		}
		
		if (min > Math.abs(sum1 - sum2)) {
			min = Math.abs(sum1 - sum2);

			if (min == 0) {
				System.out.println(0);
				System.exit(0);
			}
		}
	}

	private static boolean nextPermutation() {
		int i = n - 1;
		while (i > 0 && combi[i - 1] >= combi[i])
			--i;
		if (i == 0)
			return false;

		int j = n - 1;
		while (combi[i - 1] >= combi[j])
			--j;
		swap(i - 1, j);

		j = n - 1;
		while (i < j)
			swap(i++, j--);

		return true;
	}

	private static void swap(int a, int b) {
		int temp = combi[a];
		combi[a] = combi[b];
		combi[b] = temp;
	}
}