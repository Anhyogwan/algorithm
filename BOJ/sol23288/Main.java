package com.ssafy.recur.BOJ.sol23288;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 동쪽으로 이동 할 때 -> 3이 아래 4가 위 6이 4자리 1이 3자리 -> 동쪽 이동은 앞자리 두개 계속 교체 -> 0번 요소가 주사위
	// 바닥 요소랑 교체되고 나머지 한 요소가 oppo 요소로
	// 주사위 밑으로 가야하는 요소가 -> 현재 밑바닥의 oppo 요소가됨 , 그 반대편이 주사위 밑바닥에 있던 요소
	// 시계방향이면 +1 , 반시계방향이면 +2
	static int N, M;
	static int[][] map;
	static int[] oppo = { 0, 6, 5, 4, 3, 2, 1 };
	static int move = 1;
	static int[] dice = { 5, 4, 2, 3 };
	static int now = 6;
	static int result=0;
	static int x,y;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			//System.out.println(Arrays.toString(map[i]));
		}
		x = 0;
		y = 0;
		for (int i = 0; i < K; i++) {
			moving();
		}
		System.out.println(result);
	}

	static void moving() {
		boolean[][] visited = new boolean[N][M];
		if (move % 4 == 0 && x - 1 < 0) { // 서쪽 -> y-1
			move = 2;
		} else if (move % 4 == 1 && y + 1 >= M) { // 동쪽 -> y+1
			move = 3;
		} else if (move % 4 == 2 && x + 1 >= N) { // 남족 -> x+1
			move = 0;
		} else if (move % 4 == 3 && y - 1 < 0) { // 북쪽 -> x-1
			move = 1;
		}
		if (move%4==0) {
			x--;
		}else if (move%4==1) {
			y++;
		}else if (move%4==2) {
			x++;
		}else if (move%4==3) {
			y--;
		}
		int temp = dice[move % 4];
		int temp2=0;
		for (int i = 0; i < 4; i++) {
			if (dice[i] == oppo[dice[move % 4]]) {
				temp2 = dice[i];
				dice[i] = oppo[now];
				break;
			}
		}
		dice[move % 4] = now;
		now = temp2;
		//System.out.println(now);
		if (now> map[x][y]) {
			move++;
		}else if(now< map[x][y]) {
			if ( --move<0) move=3;
		}
//		System.out.println(Arrays.toString(dice));
//		System.out.println(x+ " " + y + " " + map[x][y] + " " + move);
		score(visited,x,y,map[x][y]);
	}
	static int[] dx= {0,1,-1,0};
	static int[] dy= {1,0,0,-1};
	static void score(boolean[][]visited, int x,int y,int num) {
		int cnt=0;
		visited[x][y]=true;
		Queue<int[]> bfs = new LinkedList<>();
		bfs.offer(new int[] {x,y});
		while(!bfs.isEmpty()) {
			int nx=bfs.peek()[0];
			int ny=bfs.poll()[1];
			cnt ++;
			for (int i = 0; i <4 ; i ++) {
				if (nx+dx[i] >=N || nx+dx[i]<0 || ny+dy[i] >=M || ny+dy[i]<0) continue;
				if (visited[nx+dx[i]][ny+dy[i]]==false && map[nx+dx[i]][ny+dy[i]]==num) {
					visited[nx+dx[i]][ny+dy[i]]=true;
					bfs.offer(new int[] {nx+dx[i],ny+dy[i]});
				}
			}
		}
		result+=(num*cnt);
	}
	
}
