package bj_16960;

import java.io.*;
import java.util.StringTokenizer;

//190805 25m.
public class Main {
	private static int[][] switchs;
	private static int[] lamp;
	
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
		
		for (int i = 0; i < switchs.length; i++) {
			if(lampCheck(i)) {
				System.out.println("1");
				return;
			}
		}
		System.out.println("0");
	}

	private static boolean lampCheck(int idx) {
		for (int j = 0; j < switchs[idx].length; j++) {
			//전체를 다 돌 필요 없이, 꺼야 할 램프만 체크하면 됨!
			if (lamp[switchs[idx][j]] - 1 == 0) {
				return false;
			}
		}
		return true;
	}
}
