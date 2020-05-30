package bj_02606;

import java.util.*;

//190814 1h bfs
//47, 48라인 중요!
//그래프 단방향 / 양방향 잘 보기!
public class Main {

	static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int edge, vertex, cnt = 0;
	static boolean[] visit;
	static ArrayList<Integer>[] list;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int x, y;
		vertex = s.nextInt();
		edge = s.nextInt();
		
		visit = new boolean[vertex + 1];
		list = new ArrayList[vertex + 1];
		for (int i = 0; i <= vertex; i++)	//ArrayList 생성
			list[i] = new ArrayList<>();
		for (int i = 0; i < edge; i++) {
			x = s.nextInt();
			y = s.nextInt();
			list[x].add(y);
			//양방향 그래프니까
			list[y].add(x);
		}
		
		q.add(1);
		visit[1] = true;
		bfs();
		System.out.println(cnt);
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			int current = q.poll();
			for (Integer ver : list[current]) {
				//이부분이 살짝 어려웠음. 2-5라인이 체크되지 않게 하는것.
				//지금 큐에서 뽑은 애의 목적지. 걔를 visit체크한다.
				//1일때 2, 5를 체크함(2, 5는 큐에 들어있다)
				//2일때는 5에 다시 방문하지 않는다.(5는 1에서 목적지로서 이미 갔으니까)
				//그러나 5는 큐에 들어있는 상태이므로 방문체크는 다시 되지 않지만, 출발지로서 5로 6을 갈 수는 있다.
				if (!visit[ver]) {
					System.out.printf("current %d, ver %d\n", current, ver);
					visit[ver] = true;
					cnt++;
					q.add(ver);
				}
			}			
		}
	}
}