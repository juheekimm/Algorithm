package bj_17135;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//190710
public class Main_my {
	private static class Node {
		int x, y, dist, anch, nowN;

		Node(int x, int y, int dist, int anch, int nowN) {
			this.x = x;
			this.y = y;
			//거리
			this.dist = dist;
			//궁수의 위치
			this.anch = anch;
			//현재 높이
			this.nowN = nowN;
		}
	}

	static int n;
	static int m;
	static int distance;
	static int[] archer;
	// 0 : 적이 없음 / 1 : 적이 있음/ -1 : 잡은 적
	static int[][] map;
	static int[][] copyMap;
	//궁수당 잡은 적을 이차원 배열로 표시. 모든 궁수에 대해 표시하기 위해 3차원 배열 사용
	static int[][][] enemyAttact;
	static Queue<Node> q = new LinkedList<>();
	// 아래로는 내려갈 일이 없으므로 사방탐색은 필요 없음.
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		distance = s.nextInt();

		// 궁수 위치까지 한줄 더 받기, 이때 추가된 한줄은 map이 전역변수이므로 자동으로 0 값이 들어감
		map = new int[n + 1][m];

		// 궁수당(총 m명) 공격한 적 정보.
		enemyAttact = new int[m][n + 1][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = s.nextInt();
			}
		}
		attackOne();
		archerArrange();
	}

	// 궁수당 공격 횟수를 count하는 함수
	private static void attackOne() {
		//한명씩 공격하기
		// 궁수당 공격 횟수 count이므로 궁수의 가능한 위치인 0부터 m-1까지
		for (int k = 0; k < m; k++) {
			copyMap = new int[n + 1][m];

			// map 복사
			for (int i = 0; i < n + 1; i++) {
				copyMap[i] = map[i].clone();
			}

			for (int i = 0; i < n; i++) {
				if (!q.isEmpty()) {
					q = new LinkedList<>();
				}

				//한줄씩 올라가기
				q.add(new Node(n - i, k, 1, k, n - i));
				bfs();
//				for (int a = 0; a < n + 1; a++) {
//					for (int b = 0; b < m; b++) {
//						System.out.print(copyMap[a][b] + " ");
//					}
//					if (a == n - i - 1)
//						System.out.println(" 지금이야!");
//					System.out.println();
//				}
//				System.out.println("======================");

				//적이 한줄씩 내려가는 것을 표현하기 위해, 아래줄에서 윗줄로 한줄씩 복사
				copyMap[n - i - 1] = copyMap[n - i].clone();
			}

//			for (int i = 0; i < n + 1; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(enemyAttact[k][i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("k: " + k + "------------------------------------------------");
		}
		return;
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.dist > distance) {
				return;
			}

			// dx, dy 구조에 따라 맨 왼쪽부터 탐색 시작
			for (int i = 0; i < 3; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];

				if (nx < 0 || nx >= temp.nowN || ny < 0 || ny >= m) {
					continue;
				}
				// 거리가 가까울때부터 먼저 돌고, 가까운 거리에서 찾으면 자동으로 종료
				if (copyMap[nx][ny] == 1 && enemyAttact[temp.anch][nx][ny] != -1) {
					enemyAttact[temp.anch][nx][ny] = -1;
					copyMap[nx][ny] = -1;
					return;
				} else {
					//찾지 못하면 거리를 1 증가시켜서 다시 탐색
					q.add(new Node(nx, ny, ++temp.dist, temp.anch, temp.nowN));
				}
			}
		}
		return;
	}
	
	// 궁수 3명을 배치하는 함수. archer에 3명의 위치 인덱스가 들어감
	private static void archerArrange() {
		int max = 0;
		int temp;
		for (int i = 0; i <= m - 3; i++) {
			archer = new int[3];
			archer[0] = i;
			for (int j = i + 1; j <= m - 2; j++) {
				archer[1] = j;
				for (int k = j + 1; k <= m - 1; k++) {
					archer[2] = k;
					temp = enemyAttactCombine();
					max = max < temp ? temp : max;
//					System.out.println("0번째 : " + archer[0] + " 1번째 : " + archer[1] + " 2번째: " + archer[2]);
				}
			}
		}
		System.out.println(max);
	}
	
	private static int enemyAttactCombine() {
		int attackNum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//3명의 궁수 중 한번이라도 적을 죽였다면 , 죽인 적 count를 ++
				if (enemyAttact[archer[0]][i][j] == -1 || enemyAttact[archer[1]][i][j] == -1
						|| enemyAttact[archer[2]][i][j] == -1) {
					attackNum++;
				}
			}
		}
		return attackNum;
	}
}