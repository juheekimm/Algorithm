package bj_07576_토마토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//200108 15m, node에 day 안들고다니고 map에 저장하는 방식으로 해봄. 시간은 비슷 
//근데 q에 들어가는 토마토가 하나도 없을 경우 -1로 나오기 때문에 maxDay = 1로 초기화 필요 
public class Main_bfs_map {
	
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int m, n, tomatoCnt, map[][], maxDay = 1;
	private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
	private static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		int zeroCnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					q.add(new Node(i, j));
				else if (map[i][j] == 0)
					zeroCnt++;
			}
		}
		
		//가지치기 없는게 더 빠름 
//		if (zeroCnt == 0) {
//			System.out.println(0);
//			System.exit(0) ;
//		}
		
		bfs();

		if (tomatoCnt != zeroCnt)
			System.out.println("-1");
		else
			System.out.println(maxDay - 1);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != 0) continue;
				
				map[nx][ny] = map[temp.x][temp.y] + 1;
				q.add(new Node(nx, ny));
				tomatoCnt++;
				maxDay = maxDay < map[nx][ny] ? map[nx][ny] : maxDay;
			}
		}
	}
}