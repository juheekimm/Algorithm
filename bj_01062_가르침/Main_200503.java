package bj_01062_가르침;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_200503 {

	static int N, K, count = 0;
	static boolean alphabetCheck[][], combi[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (K < 5) {
			System.out.println("0");
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		}
		
		alphabetCheck = new boolean[N][26];
		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			
			for (int j = 0, len = ch.length; j < len; j++)
				alphabetCheck[i][ch[j] - 'a'] = true;
		}
		combi = new boolean[26];
		combi['a' - 'a'] = combi['c' - 'a'] = combi['i' - 'a'] = combi['n' - 'a'] = combi['t' - 'a'] = true;
		
		combi(K - 5, 0, 0);
		System.out.println(count);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			int cnt = checkWord();
			if (count < cnt) count = cnt;
			return;
		}
		
		for (int i = idx; i < 26; i++) {
			if (i == ('a' - 'a') || i == ('c' - 'a') || i == ('i' - 'a') || i == ('n' - 'a') || i == ('t' - 'a'))
				continue;
			
			combi[i] = true;
			combi(n, k + 1, i + 1);
			combi[i] = false;
		}
	}

	private static int checkWord() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			boolean readable = true;
			for (int j = 0; j < 26; j++) {
				if (alphabetCheck[i][j]) {
					if (!combi[j]) {
						readable = false;
						break;
					}
				}
			}
			if (readable) cnt++;
		}
		return cnt;
	}
}