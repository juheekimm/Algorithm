package bj_16234_인구이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//191222 준호오빠 코드 보고 다시 풀어봄
//bfs에서 visit 쓰면 된다는점을 간과해서 더 어렵게 푼 것 같음
//굳이 큐 쓰고 사이즈 체크하지 않아도 리스트 잘 사용하면 훨씬 효율적으로 풀 수 있음!
public class Main_improve {
	
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int n, l, r, moveCnt;
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {-1, 0, 1, 0};
	private static int[][] map;
	private static boolean[][] visit;
	private static ArrayList<Node> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		visit = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while (findStart()) {
			visit = new boolean[n][n];
			moveCnt++;
		}
		System.out.println(moveCnt);
	}

	private static boolean findStart() {
		boolean unionExist = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {	//방문하지 않은 점이 있을 때 
					list.clear();
					list.add(new Node(i, j));
					visit[i][j] = true;
					bfs();
				} else {			//위에서 만약 다른 점까지 방문했다면 -> 연합이 있다는 뜻이므로 
					unionExist = true;
				}
			}
		}
		return unionExist;
	}

	private static void bfs() {
		Node temp;
		int nx, ny, sum = map[list.get(0).x][list.get(0).y], val;
		
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny]) continue;
				
				if (Math.abs(map[temp.x][temp.y] - map[nx][ny]) >= l 
						&& Math.abs(map[temp.x][temp.y] - map[nx][ny]) <= r) {
					visit[nx][ny] = true;
					sum += map[nx][ny];
					list.add(new Node(nx, ny));
				}
			}
		}
		
		val = sum / list.size();
		for (int i = 0; i < list.size(); i++)
			map[list.get(i).x][list.get(i).y] = val;
	}
}