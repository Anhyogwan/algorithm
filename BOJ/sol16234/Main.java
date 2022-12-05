package com.ssafy.recur.BOJ.sol16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 처음 초기 데이터에서 연합 생성
	// 하루 지나고 가능한 연합 있는지 확인 후 가능하면 연합 생성 후 하루 보냄 ~ 반복
	// 그다음 연합 가능한지 확인후 없으면 return
	static int[][] map;
	static int N, L, R;
	static boolean[][] visited;
	static int day = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		}
		move();
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static void move() {
		visited = new boolean[N][N];
		changed = new boolean[N][N];
		boolean checked = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ArrayList<int[]> con = new ArrayList<>();
				check(i, j, con);
				if (con.size() > 1) {
					checked =true;
					moving(total / cnt, con);
				}
				total=0;
				cnt=0;
			}
		}
		if (checked) {
			day++;
			move();
		}else {
			System.out.println(day);
			return;
		}
	}

	private static void moving(int people, ArrayList<int[]> con) {
		for (int i = 0; i < con.size(); i++) {
			if (!changed[con.get(i)[0]][con.get(i)[1]]) {
				changed[con.get(i)[0]][con.get(i)[1]] = true;
				map[con.get(i)[0]][con.get(i)[1]] = people;
			}
		}
	}

	static boolean[][] changed;
	static int cnt = 0;
	static int total = 0;

	static void check(int x, int y, ArrayList<int[]> con) {
		if (visited[x][y])
			return;
		con.add(new int[] { x, y });
		total += map[x][y];
		cnt++;
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if (Math.abs(map[nx][ny] - map[x][y]) >= L && Math.abs(map[nx][ny] - map[x][y]) <= R) {
				check(nx, ny, con);
			}
		}
	}
}
