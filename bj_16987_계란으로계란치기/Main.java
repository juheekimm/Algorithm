package bj_16987_계란으로계란치기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, arr[][], maxCount = 0;
	static boolean[] broken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		broken = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		dfs(-1, 0);
		System.out.println(maxCount);
	}

	private static void dfs(int now, int cnt) {
		if (maxCount < cnt)
			maxCount = cnt;

		int next = findStart(now);
		if (next == -1)
			return;

		for (int i = 0; i < N; i++) {
			if (i != next) {
				if (!broken[i]) {
					if (arr[next][0] - arr[i][1] <= 0) {
						if (arr[i][0] - arr[next][1] < 0) { // 내계란 +상대 계란 모두 깨진 경우
							broken[next] = broken[i] = true;
							dfs(next, cnt + 2);
							broken[next] = broken[i] = false;
						} else { // 내계란만 깨진경우
							broken[next] = true;
							arr[i][0] -= arr[next][1];
							dfs(next, cnt + 1);
							broken[next] = false;
							arr[i][0] += arr[next][1];
						}
					} else {
						if (arr[i][0] - arr[next][1] <= 0) { // 상대 계란만 깨진 경우
							broken[i] = true;
							arr[next][0] -= arr[i][1];
							dfs(next, cnt + 1);
							broken[i] = false;
							arr[next][0] += arr[i][1];
						} else { // 둘다 안깨진경우
							arr[i][0] -= arr[next][1];
							arr[next][0] -= arr[i][1];
							dfs(next, cnt);
							arr[i][0] += arr[next][1];
							arr[next][0] += arr[i][1];
						}
					}
				}
			}
		}
		if (maxCount < cnt)
			maxCount = cnt;
	}

	private static int findStart(int now) {
		for (int i = now + 1; i < N; i++)
			if (!broken[i])
				return i;
		return -1;
	}
}