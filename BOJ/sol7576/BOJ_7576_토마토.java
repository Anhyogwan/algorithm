package com.ssafy.recur.BOJ.sol7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	// 토마토는 잘 익은것과 익지 않은것이 존재
	// 보관하고 하루 후에 익은 토마토의 4방 익음
	//
	static int N, M;
	static int[][] box;
	static ArrayList<int[]> tomato;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		tomato = new ArrayList<>();
		visited = new boolean[N][M];
		int cnt=0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					tomato.add(new int[] { i, j });
				}
				if(num ==-1) {
					cnt++;
				}
				box[i][j] = num;
			}
		}
		if (tomato.size()+cnt==N*M) {
			System.out.println(0);
			return; 
		}
		
		bfs();
		for (int i = 0; i <N; i++) {
			for (int j = 0; j<M ; j++) {
				if (box[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(result);
	}
	
	static int result = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < tomato.size(); i++) {
			queue.add(new int[] { tomato.get(i)[0], tomato.get(i)[1] });
			visited[tomato.get(i)[0]][tomato.get(i)[1]] = true;
		}
		int time = -1;
		while (!queue.isEmpty()) {
			time++;
			int size = queue.size();
			for (int j = 0; j < size; j++) {
				int[] temp = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = temp[0] + dx[i];
					int ny = temp[1] + dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || box[nx][ny] == -1)
						continue;
					visited[nx][ny] = true;
					queue.offer(new int[] { nx, ny });
					box[nx][ny] = 1;
				}
			}
		}
		result = time;
	}

}
