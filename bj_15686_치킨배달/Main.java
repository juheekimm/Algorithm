package bj_15686_치킨배달;

import java.io.*;
import java.util.*;

//190905 50m 순서상관없으므로 combi
public class Main {
	
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<Node> chi = new ArrayList<>();
	static ArrayList<Node> home = new ArrayList<>();
	static int N, M, totalSum = Integer.MAX_VALUE, combi[], map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		combi = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1)
					home.add(new Node(i, j));
				else if (map[i][j] == 2)
					chi.add(new Node(i, j));
			}
		}
		combi(M, 0, 0);
		System.out.println(totalSum);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			int tempSum = distCalc();
			if (totalSum > tempSum) totalSum = tempSum;
			return;
		}
		
		for (int i = idx; i < chi.size(); i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
			combi[k] = 0;
		}
	}

	private static int distCalc() {
		int dist, temp, tempSum = 0;
		
		//집마다 가장 가까운 치킨거리 구하기
		for (int i = 0; i < home.size(); i++) {
			temp = Integer.MAX_VALUE;
			
			for (int j = 0; j < M; j++) {
				dist = Math.abs(home.get(i).x - chi.get(combi[j]).x) + Math.abs(home.get(i).y - chi.get(combi[j]).y);
				if (temp > dist) temp = dist;
			}
			tempSum += temp;
		}
		return tempSum;
	}
}