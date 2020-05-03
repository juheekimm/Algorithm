package bj_02531_회전초밥;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, d, k, c, arr[], visit[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		visit = new int[d + 1];
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		System.out.println(slide());
	}

	private static int slide() {
		int total = 0, max = 0;
		for (int i = 0; i < k; i++) {
			if (visit[arr[i]] == 0) total++;
			visit[arr[i]]++;
		}
//		if (visit[c] == 0) total++;
		max = total;
		
		for (int i = 1; i < N; i++) {
			if (max <= total) {
				if (visit[c] == 0)
					max = total + 1;
				else
					max = total;
			}
			visit[arr[i - 1]]--;
			if (visit[arr[i - 1]] == 0) total--;
			
			if (visit[arr[(i + k - 1) % N]] == 0) total++;
			visit[arr[(i + k - 1) % N]]++;
			
		}
		return max;
	}
}