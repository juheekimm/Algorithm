package bj_03055_탈출;

import java.util.*;
import java.io.*;
 
public class Main_191110_imp_jungol {
    private static class Node {
        int x, y, time;
        boolean isFire;
        Node (int x, int y, int time, boolean isFire) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isFire = isFire;
        }
    }
    static int r, c, endx, endy;
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static Queue<Node> q = new LinkedList<>();
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
         
        String temp;
        int humanx = 0, humany = 0;
        for (int i = 0; i < r; i++) {
//          map[i] = br.readLine().toCharArray();   //각각원소 읽어야해서 chatAt사용
            temp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = temp.charAt(j);
                 
                if (map[i][j] == 'S') {
                	humanx = i;
                	humany = j;
                } else if (map[i][j] == 'D') {
                    endx = i;
                    endy = j;
                } else if (map[i][j] == '*') {
                    q.add(new Node(i, j, 1, true));
                }
            }
        }
        q.add(new Node(humanx, humany, 1, false));
        bfs();
    }
 
    private static void bfs() {
        int nx, ny;
        Node temp;
         
        while (!q.isEmpty()) {
            temp = q.poll();
             
            for (int d = 0; d < 4; d++) {
                nx = temp.x + dx[d];
                ny = temp.y + dy[d];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c)
                    continue;
                 
                if (temp.isFire) { //불이면
                    if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
                        map[nx][ny] = '*';
                        q.add(new Node(nx, ny, temp.time + 1, true));
                    }
                } else {            //사람이면
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'S';
                        q.add(new Node(nx, ny, temp.time + 1, false));
                    } else if (map[nx][ny] == 'D') {	//대피에 성공했다면
                        System.out.println(temp.time);
                        return;
                    }
                }
            }
        }
        System.out.println("impossible");   //큐가 비었는데도 탈출하지 못했다면 출력
    }
}