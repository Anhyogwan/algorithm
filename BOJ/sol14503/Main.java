package com.ssafy.recur.BOJ.sol14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 청소기가 방향을 바라봄
	// 현재 위치 청소 , 현재 보고있는 방향을 기준으로 왼쪽으로 돌면서 탐색
	// 청소하지 않은곳이 존재하면 그쪽을 ㅗ회전하고 한칸 전진 후 청소
	// 주변 4방이 다 청소 되있거나 벽이면 후진
	// 주변 4방이 다 청소되있고 뒤쪽이 벽이라 후진도 못하면 종료
	// 0 : 북 , 1 : 동 , 2 : 남 , 3 : 서
	// { 0 ,3 ,1 ,2}
	static int[][] map;
	//static int[][] dp;
	static int N, M;
	static int c_cnt = 1;
	static int dire;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] direc = { 0, 3, 1, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		//dp = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		dire = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		clean(r, c,0);
		int result=0;
		for (int i = 0 ; i < N ; i ++) {
			for (int j=0 ; j<M ; j++) {
				if(map[i][j]==2) {
					result++;
				}
			}
		}
		System.out.println(result);
	}

	static void clean(int x, int y,int check) {
		map[x][y] = 2;
		if (--dire < 0)
			dire = 3;
		if (isIn(x + dx[dire], y + dy[dire])) {
			c_cnt++;
			clean(x + dx[dire], y + dy[dire],0);
			return;
		}else {
			if ( check>=3) {
				int dire2= dire;
				if (--dire2 < 0) dire2 = 3;
				if (--dire2 < 0) dire2 = 3;
				if (!isIn2(x+dx[dire2],y+dy[dire2])) return;
				c_cnt++;
				clean(x+dx[dire2],y+dy[dire2],0);
				return;
			}
			clean(x,y,check+1);
			return;
		}
	}
	static boolean isIn2(int x, int y) {
		if (map[x][y] != 1 && x < N-1 && y < M-1 && x >= 1 && y >= 1) {
			return true;
		}
		return false;
	}
	static boolean isIn(int x, int y) {
		if (map[x][y] != 2 && map[x][y] != 1 && x < N-1 && y < M-1 && x >= 1 && y >= 1) {
			return true;
		}
		return false;
	}
}
