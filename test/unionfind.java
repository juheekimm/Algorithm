package test;

public class unionfind {
	
	private static int N, parents[];
	public static void main(String[] args) {
		N = 10;
		parents = new int[N];
		
		union(1, 2);
	}
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
		
	}
	private static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]); 
	}
}
