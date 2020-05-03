package bj_09466_팀프로젝트;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, arr[], count;
	static boolean visit[];
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			count = 0;
			q = new LinkedList<Integer>();
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			visit = new boolean[N + 1];
			
			int temp;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				temp = Integer.parseInt(st.nextToken());
				if (i == temp) {
					visit[i] = true;
					count++;
				} else {
					arr[i] = temp;
				}
			}
			makeTeam();
			sb.append(N - count + "\n");
		}
		System.out.println(sb);
	}

	private static void makeTeam() {
		for (int i = 1; i <= N; i++) {
			if (visit[i]) continue;
			else {
				q.add(i);
				visit[i] = true;
				int now = i;
				
				if (visit[arr[i]]) {
					visit[i] = true;
					q.poll();
					continue;
				}
				
				while (!visit[arr[now]]) {
					visit[arr[now]] = true;
					now = arr[now];
					q.add(arr[now]);
				}
				
				if (q.peek() == arr[now]) {
					count += q.size();
					q.clear();
				} else {
					while (!q.isEmpty())
						visit[q.poll()] = false;
					visit[i] = true;
				}
			}
		}
	}
}