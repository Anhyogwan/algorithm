package com.ssafy.recur.BOJ.sol10026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {
	// 같은 색상이 상하좌우로 인접해 있는 경우 두 글자는 같은 구역에 속한다?
	// 탐색으로 같은 구역 찾기
	static char[][] art;
	static int N;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		art = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			art[i] = br.readLine().toCharArray();
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				bfs(i, j);
				cnt++;
			}
		}
		int cnt2 = 0;
		int cnt3 = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				if (art[i][j] == 'B') {
					bfs(i, j);
					cnt3++;
				} else {
					bfs2(i, j);
					cnt2++;
				}
			}
		}
		System.out.println(cnt + " " + (cnt2 + cnt3));
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y });
		char color = art[x][y];
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || art[nx][ny] != color || visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				queue.offer(new int[] { nx, ny });
			}

		}
	}

	static void bfs2(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y });
		char color = art[x][y];
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			int temp[] = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || art[nx][ny] == 'B' || visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				queue.offer(new int[] { nx, ny });
			}

		}
	}
}
