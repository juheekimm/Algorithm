package bj_01062_가르침;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, K, combi[], max = 0;
	private static char[][] str;
	private static boolean[] alpha;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		str = new char[N][16];
		for (int i = 0; i < N; i++)
			str[i] = br.readLine().toCharArray();
		
		if (K < 5) {
			System.out.println(0);
			return;
		}
		
		alpha = new boolean[26];
		alpha[0] = alpha[2] = alpha[8] = alpha[13] = alpha[19] = true;
		combi = new int[K - 5];
		
		combi(K - 5, 0, 0);
		System.out.println(max);
	}

	private static boolean combi(int n, int k, int idx) {
		if (n == k) {
			countWord();
			if (max == N) return true;
			return false;
		}
		
		for (int i = idx; i < 26; i++) {
			if (alpha[i]) continue;
			
			combi[k] = i;
			alpha[i] = true;
			if (combi(n, k + 1, i + 1)) return true;
			alpha[i] = false;
			combi[k] = 0;
		}
		
		return false;
	}

	private static void countWord() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < str[i].length; j++) {
				if (!alpha[str[i][j] - 'a']) {
					flag = false;
					break;
				}
			}
			if (flag) count++;
		}
		if (max < count) max = count;
	}
}