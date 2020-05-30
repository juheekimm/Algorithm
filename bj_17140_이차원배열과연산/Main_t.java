package bj_17140_이차원배열과연산;

import java.io.*;
import java.util.*;

public class Main_t {
	
	private static class Node implements Comparable<Node> {
		int num, cnt;
		Node (int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			if(this.cnt == o.cnt) {
				return this.cnt - o.cnt;
			}
			return this.num - o.num;
		}
	}
	
	static int r, c, k, nowr, nowc, cnt[], map[][];
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[100][100];
		cnt = new int[101];

		nowr = 3; nowc = 3;
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(secondCalc());
	}

	private static int secondCalc() {
		for (int second = 0; second <= 100; second++) {
			if (map[r - 1][c - 1] == k) return second;
			if (nowr >= nowc)
				sortR();
			else
				sortC();
		}
		return -1;
	}

	private static void sortR() {
		int size, tempMaxSize = -1;
		Node temp;
		
		for (int i = 0; i < nowr; i++) {
			//개수체크
			for (int j = 0; j < nowc; j++) {
				if (map[i][j] != 0)
					cnt[map[i][j]]++;
			}
			//큐에 넣고
			addQueue();
			
			//배열에 다시 넣어준다
			size = pq.size();
			for (int j = 0; j < size * 2; j += 2) {
				temp = pq.poll();
				map[i][j] = temp.num;
				map[i][j + 1] = temp.cnt;
			}
			for (int j = size * 2; j < nowc; j++) {
				map[i][j] = 0;
			}
			if (tempMaxSize < size * 2) tempMaxSize = size * 2;
		}
		if (nowc < tempMaxSize) nowc = tempMaxSize;
		
	}

	private static void sortC() {
		int size, tempMaxSize = -1;
		Node temp;
		
		for (int j = 0; j < nowc; j++) {
			//개수체크
			for (int i = 0; i < nowr; i++) {
				if (map[i][j] != 0)
					cnt[map[i][j]]++;
			}
			//큐에 넣고
			addQueue();
			
			//배열에 다시 넣어준다
			size = pq.size();
			for (int i = 0; i < size * 2; i += 2) {
				temp = pq.poll();
				map[i][j] = temp.num;
				map[i + 1][j] = temp.cnt;
			}
			for (int i = size * 2; i < nowr; i++) {
				map[i][j] = 0;
			}
			if (tempMaxSize < size * 2) tempMaxSize = size * 2;
		}
		if (nowr < tempMaxSize) nowr = tempMaxSize;
	}

	private static void addQueue() {
		for (int i = 1; i <= 100; i++) {
			if (cnt[i] != 0) {
				pq.add(new Node(i, cnt[i]));
				cnt[i] = 0;
			}
		}
	}
}