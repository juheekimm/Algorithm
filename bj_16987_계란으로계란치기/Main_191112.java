package bj_16987_계란으로계란치기;

import java.io.*;
import java.util.StringTokenizer;

//191112 보충때 배워서 푼 코드. 문제 너무 어렵게 푸는 경향 있는데 쉽게 생각하려 노력 할 것.
public class Main_191112 {
	static int n, maxCount;
	static int[] power, weight;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		power = new int[n];
		weight  = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			power[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, n);
		System.out.println(maxCount);
	}

	private static void dfs(int idx, int cnt) {
		//만약 깰 수 있는 계란이 0개 또는 1개이거나, idx가 끝까지 갔다면 종료한다
		if (idx == n || cnt <= 1) {	//cnt는 깬 계란의 수
			if (maxCount < n - cnt) maxCount = n - cnt;
			return;
		}

		//지금 계란이 이미 깨져있다면, 이번 턴 종료
		//여기서 다음 dfs를 호출해 주는 것이 포인트!
		if (power[idx] <= 0) {
			dfs(idx + 1, cnt);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (idx == i) continue;
			
			//깨려는 계란이 깨져 있으면 다음 턴으로
			if (power[i] <= 0) continue;
			
			int tempCnt = 0;
			if (power[idx] - weight[i] <= 0)	tempCnt++;
			if (power[i] - weight[idx] <= 0)	tempCnt++;

			//기존코드처럼 분기해서 빼주고 할 필요도, 깨진 계란 여부를 가지고 있을 필요도 없음
			//무조건 빼주고, 무조건 더하면 됨. 깨졌는지 여부는 그때그때 판단하면 되니까
			power[idx] -= weight[i];
			power[i] -= weight[idx];
			dfs(idx + 1, cnt - tempCnt);
			power[idx] += weight[i];
			power[i] += weight[idx];
		}
	}
}