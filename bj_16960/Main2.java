package bj_16960;

import java.io.*;
import java.util.StringTokenizer;

//190805 백트래킹 연습하다가 뻘짓... depth가 1이라서 재귀 돌 필요 X.
public class Main2 {
	private static int[][] switchs;
	private static int[] lamp;
	private static boolean allLight = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		switchs = new int[Integer.parseInt(st.nextToken())][];
		lamp = new int[Integer.parseInt(st.nextToken()) + 1]; //램프 번호를 받기때문에 하나 더 크게 생성
		
		for (int i = 0; i < switchs.length; i++) {
			st = new StringTokenizer(br.readLine());
			switchs[i] = new int[Integer.parseInt(st.nextToken())];
			for (int j = 0; j < switchs[i].length; j++) {
				switchs[i][j] = Integer.parseInt(st.nextToken());
				lamp[switchs[i][j]]++;
			}
		}
		dfs(0);
		if(allLight) System.out.println("1");
		else System.out.println("0");
	}

	private static void dfs(int idx) {
		boolean isAllLight = true;
		if (idx == switchs.length) return;
		
		for (int j = 0; j < switchs[idx].length; j++) {
			lamp[switchs[idx][j]]--;
		}
		for (int j = 0; j < switchs[idx].length; j++) {
			if (lamp[switchs[idx][j]] == 0) {
				isAllLight = false;
			}
		}
		if (isAllLight) {
			allLight = true;
			return;
		}
		for (int j = 0; j < switchs[idx].length; j++) {
			//전체를 다 돌 필요 없이, 꺼야 할 램프만 체크하면 됨!
			lamp[switchs[idx][j]]++;
		}
		dfs(++idx);
	}
}
