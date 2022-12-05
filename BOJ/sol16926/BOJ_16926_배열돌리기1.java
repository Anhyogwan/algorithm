package com.ssafy.recur.BOJ.sol16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기1 {
	static int N, M, R, xCnt, yCnt, out;
	static int[][] map, temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(N % 2 == 0) out = N / 2;
		else out = (N / 2) + 1; 
		R = R % ((N * 2) + (M - 2) * 2);
		for(int i = 1; i <= R; i++) {
			xCnt = 0;
			yCnt = M;
			check(1);
			
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void check(int cnt) {
		for(int i = xCnt; i < N - xCnt; i++) {
			for(int j = xCnt; j < yCnt; j++) {
				if(i == xCnt) left(i, j, cnt);
				else if(i == N - xCnt - 1) right(i, j, cnt);
				else if(j == xCnt) down(i, j, cnt);
				else if(j == yCnt - 1) up(i, j, cnt);
				else if(i != xCnt || j != yCnt - 1) temp[i][j] = map[i][j];
				
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = temp[i][j];
			}
		}
		xCnt++;
		yCnt--;
		if(cnt == out) return;
		else {
			check(cnt + 1);
		}
	}
	
	private static void left(int x, int y, int cnt) {
		if(x == xCnt && y == xCnt) {
			temp[x + 1][y] = map[x][y];
		}else {
			temp[x][y - 1] = map[x][y];
		}
	}
	private static void right(int x, int y, int cnt) {
		if(x == N - cnt && y == yCnt - 1) {
			temp[x - 1][y] = map[x][y];
		}else {
			temp[x][y + 1] = map[x][y];
		}
			
	}
	private static void down(int x, int y, int cnt) {
		if(x == N - cnt && y == xCnt) {
			temp[x][y + 1] = map[x][y];
		}else {
			temp[x + 1][y] = map[x][y];
		}
	}
	private static void up(int x, int y, int cnt) {
		if(y == yCnt - 1 && x == xCnt) {
			temp[x][y - 1] = map[x][y];
		}else {
			temp[x - 1][y] = map[x][y];
		}
	}
}
