package bj_17825_주사위윷놀이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//191219 2019 삼성전자 CEIM 하반기 공채 2번 실패 
//시험장에서 손도 못대고 이후 설명 듣고 나서야 3일만에 품.. 그것도 짜 주신 코드 보고. 와 진짜 어렵다 

//포인트는 Node를 정점으로 갖고 있는 배열(v)을 int형 1차원 배열(redEdge)을 이용해 간선으로 연결하는 것이다
//Node.color의 값이 1이면 빨간 선으로 바로 가는 점이고, 2이면 출발점이냐 여부에 따라 파란 선으로 갈 수도 있다
//move 함수는 처음 출발할때 방향을 보고 시작한다. 첫 점만 설정해 주면 뒷부분은 상관없음
//조합으로 생각할 수 있지만, 순서가 있어야 하기 때문에(말 순서에 따라 움직이는 칸 수가 다르니까) 조합이 아니라 순열으로 풀어야 한다.
//비트마스킹으로 4^10 = 2^20을 돌렸음. 사실 줄이려면 첫 점을 1로 고정해놓으면 4^19로 줄일 수 있지만 크게 유의미하게 시간이 줄지도 않고, 코드도 복잡해진다. 왜 굳이 그렇게 짜니
//이전 점의 위치는 boolean으로 처음에 생각했지만, 굳이 그럴 필요 없이 현재 말들의 위치와 지금 내가 놓으려는 말을 비교해보면 된단다 

//삽질한 이유는.. 16이 그래프에 두개인지 모르고 다른 부분만 열심히 찾아서..

public class Main {

	private static class Node {
		int point; //점수
		int color; //점의 색깔
		Node (int point, int color) {
			this.point = point;
			this.color = color;
		}
	}

	static int maxSum;
	static Node[] v = new Node[43];
	static int[] redEdge = new int[43];	//출발점 도착점 포함.
	static int[] num = new int[10];		//입력받은 주사위 값
	static int[] horse = new int[4];	//말의 현재 위치	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 10; i++)
			num[i] = Integer.parseInt(st.nextToken());

		//정점 정보 입력
		for (int i = 0; i <= 42; i++)
			v[i] = new Node(i, 1);
		
		v[15].point = 16;
		
		v[21].point = 22;
		v[23].point = 24;
		
		v[31].point = 28;
		v[33].point = 27;
		v[35].point = 26;
		
		v[37].point = 30;
		v[39].point = 35;
		  
		v[42].point = 0;
		
		v[10].color = v[20].color = v[30].color = 2;
		
		//간선 정보 입력
		for (int i = 0; i <= 40; i += 2)
			redEdge[i] = i + 2;
		
		redEdge[13] = 15;
		redEdge[15] = 19;
		redEdge[19] = 25;
		
		redEdge[21] = 23;
		redEdge[23] = 25;
		
		redEdge[31] = 33;
		redEdge[33] = 35;
		redEdge[35] = 25;
		
		redEdge[25] = 37;
		redEdge[37] = 39;
		redEdge[39] = 40;
		
		redEdge[42] = 42;	//도착점 무한루프 느낌. 자기자리에서만 돌게 
		
		combi();
		System.out.println(maxSum);
	}

	private static void combi() {
		for (int bit = 0; bit < (1<<20); bit++) {
			int x = bit;
			
			for (int i = 0; i < 4; i++)
				horse[i] = 0;
			
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				int horseNum = x % 4;
				x /= 4;
				//이 말이 이미 도착했다면 이 for문은 종료
				if (horse[horseNum] == 42) {
					sum = -1;
					break;
				}
				
				int nextPosition = move(horse[horseNum], num[i]);
				
				boolean isExist = false;
				for (int j = 0; j < 4; j++) {
					if (horseNum == j) continue;	//인덱스 자기자신은 건너
					if (horse[j] == 42) continue;	//도착지여도 건너
					if (nextPosition == horse[j]) isExist = true;	//이미 그자리에 다른 말이 있다
				}
				
				if (!isExist) {
					horse[horseNum] = nextPosition;
					sum += v[nextPosition].point;
				} else {
					sum = -1;
					break;
				}
			}
			maxSum = maxSum < sum ? sum : maxSum;
		}
	}

	private static int move(int idx, int step) {
		if (v[idx].color == 1) {
			idx = redEdge[idx];
		} else {
			if (idx == 10) idx = 13;
			if (idx == 20) idx = 21;
			if (idx == 30) idx = 31;
		}
		step--;
		
		for (int i = 0; i < step; i++)
			idx = redEdge[idx];
		
		return idx;
	}
}