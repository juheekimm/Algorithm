package bj_01697_숨바꼭질;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int n, k, count;
	static int[] dx = {-1, 1, 2};
	static boolean[] visit;
	static Queue<int[]> q;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		k = s.nextInt();
		visit = new boolean[1000001];
		
		if (n == k) {
			System.out.println(0);
			System.exit(0);
		}

		q = new LinkedList<int[]>();
		//첫번째원소는 위치, 두번째원소는 step
		q.add(new int[] {n, 1});

		bfs();
	}

	private static void bfs() {
		int x, cnt, nx;
		
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			x = temp[0];
			cnt = temp[1];
			
			for (int d = 0; d < 3; d++) {
				if (d != 2)
					nx = x + dx[d];
				else
					nx = x * 2;
				
				if (nx < 0 || nx > 100000 || visit[nx]) continue;
				
				if (nx == k) {
					System.out.println(cnt);
					System.exit(0);
				}
				
				visit[nx] = true;
				q.add(new int[] {nx, cnt + 1});
			}
		}
	}

}
