package bj_04195_친구네트워크;

import java.io.*;
import java.util.*;

//191221 56408	472 1시간정도 고민했으나 방법 생각 못함. 설명 듣고 풀었음
//문제 포인트는 54라인 parents[aRoot] += parents[bRoot]; 이거랑, 연결된 개수를 return하는게 포인트인 듯
//union-fin를 익히기 좋은 문제 같다
public class Main {

	private static int index, idx1, idx2;
	private static String s1, s2;
	private static Map<String, Integer> map;
	private static int[] parents;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			map = new HashMap<String, Integer>();
			index = 0;
			
			int r = Integer.parseInt(br.readLine());
			parents = new int[r * 2];
			Arrays.fill(parents, -1);
			
			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				s1 = st.nextToken();
				s2 = st.nextToken();
				
				if (!map.containsKey(s1)) map.put(s1, index++);
				if (!map.containsKey(s2)) map.put(s2, index++);
				
				idx1 = map.get(s1);
				idx2 = map.get(s2);
				
				sb.append(-1 * union(idx1, idx2) + "\n");
				System.out.println(Arrays.toString(parents));
			}
			
		}
		System.out.println(sb);
	}

	private static int union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		return parents[aRoot];
	}

	private static int find(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
}