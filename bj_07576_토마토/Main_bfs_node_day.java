package bj_07576_토마토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//200108 15m, node에 day를 들고다니게 해서 풀었음. 나는 map보다는 이게 더 직관적인것같아서 이게 더 편했음.
public class Main_bfs_node_day {
	
	private static class Node {
		int x, y, day;
		Node (int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	private static int m, n, tomatoCnt, map[][], maxDay;
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
					q.add(new Node(i, j, 1));
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
			System.out.println(maxDay);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != 0) continue;
				
				map[nx][ny] = 1;
				tomatoCnt++;
				q.add(new Node(nx, ny, temp.day + 1));
				maxDay = maxDay < temp.day ? temp.day : maxDay;
			}
		}
	}
}