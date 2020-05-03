package bj_02503_숫자야구;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//191221 13008	88 문제 해결 아이디어 못잡아서 고민하다가 방법 듣고 풀었음. 구현은 40분정도 걸린 듯.
//어차피 숫자가 작고, 완탐으로 돌리면 되기 때문에 수학적으로 풀 필요 없이 그냥 4중 for문 돌려서 풀면 됨 
public class Main {
	
	static int n, totalCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		int[][] question = new int[n][5];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			question[i][0] = temp / 100;
			question[i][1] = (temp % 100) / 10;
			question[i][2] = (temp % 100) % 10;
			question[i][3] = Integer.parseInt(st.nextToken());
			question[i][4] = Integer.parseInt(st.nextToken());
		}
		
		int[] num = new int[3];
		for (int i = 1; i <= 9; i++) {
			num[0] = i;
			
			for (int j = 1; j <= 9; j++) {
				if (i == j) continue;
				num[1] = j;

				for (int k = 1; k <= 9; k++) {
					if (i == k || j == k) continue;
					num[2] = k;
					
					int count = 0;
					for (int q = 0; q < n; q++) {
						if (question[q][3] != strike(num, question[q])) break;
						if (question[q][4] != ball(num, question[q])) break;
						count++;
					}
					
					if (n == count) totalCnt++;
				}
			}
		}
		System.out.println(totalCnt);
	}

	private static int strike(int[] num, int q[]) {
		int cnt = 0;
		for (int i = 0; i < 3; i++)
			if (num[i] == q[i])
				cnt++;

		return cnt;
	}
	
	private static int ball(int[] num, int[] q) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) continue;
				if (num[i] == q[j]) cnt++;
			}
		}
		return cnt;
	}
}