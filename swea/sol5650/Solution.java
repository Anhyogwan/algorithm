package com.ssafy.recur.swea.sol5650;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static class Pinball {
		int x, y, direc;

		public Pinball(int x, int y, int direc) {
			this.x = x;
			this.y = y;
			this.direc = direc;
		}
	}

	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int startx, starty, max_value, N;

	// -1 일 경우 탈출

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
				}
			}

			max_value = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int l = 0; l < 4; l++) {
							startx = i;
							starty = j;
							Pinball p = new Pinball(i, j, l);
							gamestart(p, 0);
						}
					}
				}
			}
			System.out.println("#" + tc + " " + max_value);
		}
	}

	// 1일경우 공의 방향이 0 -> 3 , 1 -> 0 , 2 -> 1 , 3 -> 2
	// 2일경우 공의 방향이 0 -> 1 , 1 -> 3 , 2 -> 0 , 3 -> 2
	// 3일경우 공의 방향이 0 -> 1 , 1 -> 2 , 2 -> 3 , 3 -> 0
	// 4일경우 공의 방향이 0 -> 2 , 1 -> 0 , 2 -> 3 , 3 -> 1
	private static void gamestart(Pinball p, int score) {
		while (true) {
			if (map[p.x][p.y] == 1) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 3;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 1;
					break;
				case 3:
					p.direc = 2;
				}
			}
			if (map[p.x][p.y] == 2) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 3;
					break;
				case 2:
					p.direc = 0;
					break;
				case 3:
					p.direc = 2;
				}
			}
			if (map[p.x][p.y] == 3) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 2;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 0;
				}
			}
			if (map[p.x][p.y] == 4) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 2;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 1;
				}
			}
			if (map[p.x][p.y] == 5) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 2;
				}
			}
			p.x += dx[p.direc];
			p.y += dy[p.direc];
			
			if (isIn2(p.x,p.y)) {
				score++;
				switch (p.direc) {
				case 0:
					p.direc = 1;
					break;
				case 1:
					p.direc = 0;
					break;
				case 2:
					p.direc = 3;
					break;
				case 3:
					p.direc = 2;
				}
				p.x += dx[p.direc];
				p.y += dy[p.direc];
			}
			if (map[p.x][p.y] > 5) {
				
				outer: for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[p.x][p.y] == map[i][j] && (p.x != i || p.y != j)) {
							p.x = i;
							p.y = j;
							break outer;
						}
					}
				}
			}
			
			if ((p.x == startx && p.y == starty) || map[p.x][p.y] == -1) {
				max_value = Math.max(max_value, score);
				return;
			}
		}
	}
	
	private static boolean isIn2(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return true;
		return false;
	}
}
