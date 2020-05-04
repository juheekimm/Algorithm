package bj_09466_팀프로젝트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	
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
				if (i == temp) {
					visit[i] = i;
					count++;
				} else {
					arr[i] = temp;
				}
			}
			makeTeam();
			sb.append(N - count + "\n");
//			System.out.println(Arrays.toString(arr));
//			System.out.println(Arrays.toString(visit));
//			System.out.println("count " + count);
		}
		System.out.println(sb);
	}

	private static void makeTeam() {
		for (int i = 1; i <= N; i++) {
			if (visit[i] == 0) {
				visit[i] = i;
				int idx = searchCycleDfs(arr[i], i);
//				System.out.println("idx " + idx);
				if (idx != -1) {
					int temp = countDfs(idx, arr[idx], 1);
//					System.out.println(">>" + temp);
					count += temp;
				}
			}
		}
	}

	private static int countDfs(int start, int idx, int cnt) {
//		System.out.println("start " + start + " idx " + idx);
		if (start == idx)
			return cnt;
//		if (visit[start] == visit[idx])
			return countDfs(start, arr[idx], cnt + 1);
	}

	private static int searchCycleDfs(int now, int idx) {
		if (visit[now] == idx)
			return now;
		else if (visit[now] != 0)
			return -1;
		
		visit[now] = idx;
		return searchCycleDfs(arr[now], idx);
		
	}
}













