package bj_09466_팀프로젝트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, arr[], count, visit[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			count = 0;
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			visit = new int[N + 1];
			
			int temp;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				temp = Integer.parseInt(st.nextToken());
//				if (i == temp) {
//					visit[i] = i;
//					count++;
//				} else {
				arr[i] = temp;
//				}
			}
			makeTeam();
			sb.append(N - count + "\n");
		}
		System.out.println(sb);
	}

	private static void makeTeam() {
		for (int i = 1; i <= N; i++) {
			if (visit[i] == 0) {
				visit[i] = i;
				int now = searchCycleDfs(arr[i], i);
				if (now != -1) {
					int temp = countDfs(now, arr[now], 1);
					count += temp;
				}
			}
		}
	}

	private static int countDfs(int now, int next, int cnt) {
		if (now == next)
			return cnt;
		
		return countDfs(now, arr[next], cnt + 1);
	}

	private static int searchCycleDfs(int now, int start) {
		if (visit[now] == start)
			return now;
		else if (visit[now] != 0)
			return -1;
		
		visit[now] = start;
		return searchCycleDfs(arr[now], start);
	}
}