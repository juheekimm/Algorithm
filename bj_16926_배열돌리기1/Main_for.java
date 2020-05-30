package bj_16926_배열돌리기1;
import java.util.Scanner;

public class Main_for {
	static int n;
	static int m;
	static int[][] map;
	static int turnNum;
	
	//190717 1h
	//104304	1472
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		turnNum = s.nextInt();
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = s.nextInt();
			}
		}

		turnMap();
		print();
	}
	
	private static void turnMap() {
		int forNum = Math.min(n, m) / 2;
		int nLast = n - 1;
		int mLast = m - 1;
		
		int[] temp = new int[forNum];
		
		for (int tNum = 0; tNum < turnNum; tNum++) {
			for (int t = 0 ; t < forNum; t++) {
				temp[t] = map[t][t];
				//������
				for (int j = t; j < mLast - t; j++) {
					map[t][j] = map[t][j + 1];
				}
				//�ǿ�������
				for (int i = t; i < nLast - t; i++) {
					map[i][mLast - t] = map[i + 1][mLast - t];
				}
				//�ǾƷ���
				for (int j = mLast - t; j > t; j--) {
					map[nLast - t][j] = map[nLast - t][j - 1];
				}
				//�ǿ�����
				for (int i = nLast - t; i > t; i--) {
					map[i][t] = map[i - 1][t];
				}
				//�����ٿ��ʿ���
				map[t + 1][t] = temp[t];
			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}