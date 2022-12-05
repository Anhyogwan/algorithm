package com.ssafy.recur.swea.sol5656;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Brick {
		int x, y, crash;

		public Brick(int x, int y, int crash) {
			this.x = x;
			this.y = y;
			this.crash = crash;
		}
	}

	// 구슬위치 완탐 -> 최대 W^4 => 12^4 = 20,736 개의 경우의 수
	// 구슬이 부딪힌 위치에서 사방탐색 BFS
	static int N, W, H;
	static int[][] map;
	static int min_value;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			min_value = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			perm(0, new int[N]);

			System.out.println("#" + tc + " " + min_value);
		}

	}

	private static void perm(int idx, int[] ball) {
		if (idx == N) {
			int[][] map2 = new int[H][W];
			for (int i = 0; i < H; i++) {
				map2[i] = map[i].clone();
			}
			bfs(ball, 0, map2);
			return;
		}
		for (int i = 0; i < W; i++) {
			ball[idx] = i;
			perm(idx + 1, ball);
		}
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static void bfs(int[] ball, int num, int[][] map2) {
		if (num == N) {
			int brickCount = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map2[i][j] != 0)
						brickCount++;
				}
			}
			min_value = Math.min(min_value, brickCount);
			return;
		}
		Queue<Brick> queue = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		int i = 0;
		while (i < H) {
			if (map2[i][ball[num]] != 0) {
				visited[i][ball[num]] = true;
				queue.offer(new Brick(i, ball[num], map2[i][ball[num]]));
				break;
			}
			i++;
		}
		while (!queue.isEmpty()) {
			Brick b = queue.poll();
			for (int j = 0; j < 4; j++) {
				for (int k = 1; k < b.crash; k++) {
					int nx = b.x + dx[j] * k;
					int ny = b.y + dy[j] * k;
					if (nx < 0 || nx >= H || ny < 0 || ny >= W || visited[nx][ny] || map2[nx][ny] == 0)
						continue;
					visited[nx][ny] = true;
					if (map2[nx][ny] != 1)
						queue.offer(new Brick(nx, ny, map2[nx][ny]));
				}
			}
		}
		crash(visited, map2);
		move(map2);
		bfs(ball, num + 1, map2);
	}

	private static void move(int[][] map2) {
		for (int i = 0; i < W; i++) {
			for (int j = H - 2; j >= 0; j--) {
				if (map2[j][i] != 0 && j + 1 < H && map2[j + 1][i] == 0) {
					int k = 1;
					while (j + k < H && map2[j + k][i] == 0) k++;
					k--;
					map2[j + k][i] = map2[j][i];
					map2[j][i] = 0;
				}
			}
		}
	}

	private static void crash(boolean[][] visited, int[][] map2) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (visited[i][j])
					map2[i][j] = 0;
			}
		}
	}
}
