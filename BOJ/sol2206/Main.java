package com.ssafy.recur.BOJ.sol2206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 1은 벽
	// 최단경로 -> BFS 생각
	// 시작칸 끝칸 포함 카운트
	// 벽은 한개까지 부숴도 됨
	// 벽을 부순쪽의 BFS와 벽을 부수지 않은 쪽의 BFS의 방문체크를 해야함 -> 즉 visited 배열이 2개가 필요하다
	// 그래서 벽을 만나면 부순쪽의 visited 배열에 체크를 해주고 값을 추가해준다. -> 벽을 부쉈기 때문에 부순 경우의 수 끼리 BFS로 최단거리를 찾는것
	// 벽을 다른곳에서 부숴도 이전까지의 이동 거리랑 같이 체크가 되기 때문에 결국 같은시간에 가장 빠르게 도착하는 애가 최소값이 된다.
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] visited2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited2 = new boolean[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		bfs(0, 0, 0, 0);
		System.out.println(result);
	}

	// 새로운 벽
	// 벽을 부수고 이동 할때 분기가 갈림, 근데 분기가 갈릴경우 경로가 겹칠 수가 있음
	static int result = -1;

	static void bfs(int x, int y, int crash, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y, crash, cnt });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			if (temp[0] == N - 1 && temp[1] == M - 1) {
				result = temp[3] + 1;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				int ncrash = temp[2];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (map[nx][ny] == '1' && ncrash == 0 && !visited2[nx][ny]) {
					visited2[nx][ny] = true;
					q.offer(new int[] { nx, ny, ncrash + 1, temp[3] + 1 });
				} else if (map[nx][ny] == '0' && ncrash == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					visited2[nx][ny] = true;
					q.offer(new int[] { nx, ny, ncrash, temp[3] + 1 });
				} else if (map[nx][ny] == '0' && ncrash != 0 && !visited2[nx][ny]) {
					visited2[nx][ny] = true;
					q.offer(new int[] { nx, ny, ncrash, temp[3] + 1 });
				}

			}
		}
	}
}
