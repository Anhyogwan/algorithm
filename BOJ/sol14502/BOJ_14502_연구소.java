package com.ssafy.recur.BOJ.sol14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
	int x;
	int y;

	public Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// 시간이 지날수록 바이러스는 퍼져나감
// 미리 예측해서 막는것은 힘들거같음
// 바이러스가 한번씩 퍼져 나갈때마다 3개의 벽을 세워서 막을 수 있는지 확인해야하는 문제같음
public class BOJ_14502_연구소 {
	static int[][] map;
	static int N, M;
	static int max_value = Integer.MIN_VALUE;
	static Queue<Virus> virus = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==2) {
					virus.offer(new Virus(i,j));
				}
			}
		}
		wall(map, 0);
		System.out.println(max_value);
	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static void wall(int[][] map, int cnt) {
		if (cnt == 3) {
			int[][] map2 = new int[N][M];
			for (int i = 0; i < N; i++) {
				map2[i]=map[i].clone();
			}
			spread(map2);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wall(map, cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}


	static void spread(int[][] map) {
		Virus vir;
		int cnt=0;
		Queue<Virus> virus_temp = new LinkedList<>();
		virus_temp.addAll(virus);
		while (!virus_temp.isEmpty()) {
			vir = virus_temp.poll();
			map =isIn(map,vir.x,vir.y,virus_temp);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		max_value = Math.max(max_value, cnt);
	}

	static int[][] isIn(int[][] map, int i, int j,Queue<Virus> virus_temp) {
		for (int k = 0; k < 4; k++) {
			if (i + dx[k] < N && i + dx[k] >= 0 && j + dy[k] < M && j + dy[k] >= 0 && map[i + dx[k]][j + dy[k]] ==0 ) {
				map[i + dx[k]][j + dy[k]] = 2;
				virus_temp.offer(new Virus(i+dx[k],j+dy[k]));
			}
		}
		return map;
	}
}
