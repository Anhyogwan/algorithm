package com.ssafy.recur.BOJ.sol17144;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_ver2 {
	static int R, C, T;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[] ccw = { 2, 0, 3, 1 }; // 반시계 방향
	static int[] cw = { 2, 1, 3, 0 }; // 시계 방향

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		int cleaner = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1)
					cleaner = i;
			}
		}

		for (int t = 0; t <= T; t++) {
			spread();
			clean(cleaner - 1, ccw); // 반시계
			clean(cleaner, cw); // 시계
		}
		int ans = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ans += map[i][j];
			}
		}
		System.out.println(ans);
	}

	private static void clean(int cr, int[] dir) {
		int x = cr;
		int y = 1;
		int curr = map[x][y], next;
		map[x][y] = 0;
		for (int d = 0; d < 4; d++) {
			while (true) {
				int nr = x + dr[dir[d]];
				int nc = y + dc[dir[d]];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					break;
				if (map[nr][nc] == -1)
					break;
				next = map[nr][nc];
				map[nr][nc] = curr;
				curr = next;
				x = nr;
				y = nc;
			}
		}
	}

	static class Dust {
		int r, c, n;

		public Dust(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}

	}

	private static void spread() {
		Queue<Dust> q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 4) {
					q.add(new Dust(i, j, map[i][j]));
				}
			}
		}
		while (!q.isEmpty()) {
			Dust dust = q.poll();
			int give = dust.n / 5;
			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = dust.r + dr[d];
				int nc = dust.c + dc[d];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1)
					continue;
				cnt++;
				map[nr][nc] += give;
			}
			map[dust.r][dust.c] -= give * cnt;
		}
	}

}
