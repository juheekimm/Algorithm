package bj_16235_나무재테크;

import java.io.*;
import java.util.*;

public class Main {
	
	private static class Tree implements Comparable<Tree>{
		int x, y, age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	static int n, m, k, map[][], A[][];
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static PriorityQueue<Tree> pq = new PriorityQueue<>();
	static Queue<Tree> birth = new LinkedList<>();
	static Queue<Tree> death = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];
		A = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
			
			Arrays.fill(map[i], 5);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for (int y = 0; y < k; y++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(pq.size());
	}

	private static void spring() {
		Tree temp;
		Queue<Tree> tempQ = new LinkedList<>();
		//tempQ를 만들었으므로 그냥 while문으로 돌림
//		int size = pq.size();
//		for (int z = 0; z < size; z++) {
		while (!pq.isEmpty()) {
			temp = pq.poll();
			
			if (map[temp.x][temp.y] < temp.age) {
				death.add(temp);
			} else { 
				map[temp.x][temp.y] -= temp.age;
				temp.age++;	//안쓰는게 좋음
				tempQ.add(temp);
				if (temp.age % 5 == 0)
					birth.add(temp);
			}
		}
		pq.addAll(tempQ);
	}

	private static void summer() {
		Tree temp;
		while (!death.isEmpty()) {
			temp = death.poll();
			map[temp.x][temp.y] += temp.age / 2;
		}
	}

	private static void fall() {
		Tree temp;
		int nx, ny;
		
		while (!birth.isEmpty()) {
			temp = birth.poll();
			for (int d = 0; d < 8; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				if (nx <= 0 || nx > n || ny <= 0 || ny > n)
					continue;
				pq.add(new Tree(nx, ny, 1));
			}
		}
	}

	private static void winter() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
}