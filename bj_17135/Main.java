package bj_17135;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//190726 BufferedReader ������
public class Main {
	private static class Node {
		int x, y, dist;
		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static int n, m, distance, maxCount = -1, count;
	static int[] archer;
	static int[][] map;
	static int[][] copyMap;
	static Queue<Node> q = new LinkedList<>();
	// �Ʒ��δ� ������ ���� �����Ƿ� ���Ž���� �ʿ� ����.
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		distance = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permutation();
		System.out.println(maxCount);
	}

	private static void permutation() {
		for (int i = 0; i <= m - 3; i++) {
			archer = new int[3];
			archer[0] = i;
			for (int j = i + 1; j <= m - 2; j++) {
				archer[1] = j;
				for (int k = j + 1; k <= m - 1; k++) {
					archer[2] = k;
					count = 0;
					attack();
					maxCount = maxCount < count ? count : maxCount;
				}
			}
		}
	}

	private static void attack() {
		copyMap = new int[n + 2][m];
		for (int i = 0; i < n; i++) {
			copyMap[i + 1] = map[i].clone();
		}
		for (int i = 0; i < n; i++) {
			//��� �� �ü��� ��ġ�� �� 0���� �̸� �ʱ�ȭ�ϱ�
			Arrays.fill(copyMap[n - i + 1], 0);
			for (int j = 0; j < 3; j++) {
				q.add(new Node(n - i + 1, archer[j], 1));
				shoot();
				if (!q.isEmpty()) {
					q = new LinkedList<>();
				}
			}
			for (int k = 0; k < n + 2; k++) {
				for (int j = 0; j < m; j++) {
					if (copyMap[k][j] == 2)
						copyMap[k][j] = 0;	 
				}
			}
		}
	}

	private static void shoot() {
		while(!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.dist > distance)
				return;
			for (int d = 0; d < 3; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				//�������°� �����Ƿ� nx>= �� ������ �� �ʿ� ����.
				if (nx < 0 || ny < 0 || ny >= m) continue;
				
				if (copyMap[nx][ny] == 1) {			//���� ��Ҵٸ� 2�� ����
					copyMap[nx][ny] = 2;
					count++;
					return;
				} else if(copyMap[nx][ny] == 2) {	//������ ���� ���� �� ��Ҵٸ� count���� �ʰ� ���ϸ� �Ѵ�.
					return;
				} else {							//��������� �ٽ� q�� �ֱ�
					q.add(new Node(nx, ny, temp.dist + 1));
				}
			}
		}
	}
}