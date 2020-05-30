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
			//�Ÿ�
			this.dist = dist;
			//�ü��� ��ġ
			this.anch = anch;
			//���� ����
			this.nowN = nowN;
		}
	}

	static int n;
	static int m;
	static int distance;
	static int[] archer;
	// 0 : ���� ���� / 1 : ���� ����/ -1 : ���� ��
	static int[][] map;
	static int[][] copyMap;
	//�ü��� ���� ���� ������ �迭�� ǥ��. ��� �ü��� ���� ǥ���ϱ� ���� 3���� �迭 ���
	static int[][][] enemyAttact;
	static Queue<Node> q = new LinkedList<>();
	// �Ʒ��δ� ������ ���� �����Ƿ� ���Ž���� �ʿ� ����.
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		distance = s.nextInt();

		// �ü� ��ġ���� ���� �� �ޱ�, �̶� �߰��� ������ map�� ���������̹Ƿ� �ڵ����� 0 ���� ��
		map = new int[n + 1][m];

		// �ü���(�� m��) ������ �� ����.
		enemyAttact = new int[m][n + 1][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = s.nextInt();
			}
		}
		attackOne();
		archerArrange();
	}

	// �ü��� ���� Ƚ���� count�ϴ� �Լ�
	private static void attackOne() {
		//�Ѹ� �����ϱ�
		// �ü��� ���� Ƚ�� count�̹Ƿ� �ü��� ������ ��ġ�� 0���� m-1����
		for (int k = 0; k < m; k++) {
			copyMap = new int[n + 1][m];

			// map ����
			for (int i = 0; i < n + 1; i++) {
				copyMap[i] = map[i].clone();
			}

			for (int i = 0; i < n; i++) {
				if (!q.isEmpty()) {
					q = new LinkedList<>();
				}

				//���پ� �ö󰡱�
				q.add(new Node(n - i, k, 1, k, n - i));
				bfs();
//				for (int a = 0; a < n + 1; a++) {
//					for (int b = 0; b < m; b++) {
//						System.out.print(copyMap[a][b] + " ");
//					}
//					if (a == n - i - 1)
//						System.out.println(" �����̾�!");
//					System.out.println();
//				}
//				System.out.println("======================");

				//���� ���پ� �������� ���� ǥ���ϱ� ����, �Ʒ��ٿ��� ���ٷ� ���پ� ����
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

			// dx, dy ������ ���� �� ���ʺ��� Ž�� ����
			for (int i = 0; i < 3; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];

				if (nx < 0 || nx >= temp.nowN || ny < 0 || ny >= m) {
					continue;
				}
				// �Ÿ��� ����ﶧ���� ���� ����, ����� �Ÿ����� ã���� �ڵ����� ����
				if (copyMap[nx][ny] == 1 && enemyAttact[temp.anch][nx][ny] != -1) {
					enemyAttact[temp.anch][nx][ny] = -1;
					copyMap[nx][ny] = -1;
					return;
				} else {
					//ã�� ���ϸ� �Ÿ��� 1 �������Ѽ� �ٽ� Ž��
					q.add(new Node(nx, ny, ++temp.dist, temp.anch, temp.nowN));
				}
			}
		}
		return;
	}
	
	// �ü� 3���� ��ġ�ϴ� �Լ�. archer�� 3���� ��ġ �ε����� ��
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
//					System.out.println("0��° : " + archer[0] + " 1��° : " + archer[1] + " 2��°: " + archer[2]);
				}
			}
		}
		System.out.println(max);
	}
	
	private static int enemyAttactCombine() {
		int attackNum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				//3���� �ü� �� �ѹ��̶� ���� �׿��ٸ� , ���� �� count�� ++
				if (enemyAttact[archer[0]][i][j] == -1 || enemyAttact[archer[1]][i][j] == -1
						|| enemyAttact[archer[2]][i][j] == -1) {
					attackNum++;
				}
			}
		}
		return attackNum;
	}
}