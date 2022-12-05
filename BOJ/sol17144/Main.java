package com.ssafy.recur.BOJ.sol17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class FineDust {
	int x;
	int y;
	int spread;

	public FineDust(int x, int y, int spread) {
		this.x = x;
		this.y = y;
		this.spread = spread;
	}
}

class Cleaner {
	int x;
	int y;

	public Cleaner(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	// 공기청정기는 반드시 1열에 두칸 차지함
	// 미세먼지는 초마다 4방향으로 퍼짐 퍼지는 양은 x,y/5 , 남은 양은 x,y - (x,y/5 * 확산된 방향의 개수 -> 범위 밖이면
	// 확산이 안된다는 뜻)
	// 공기청정기 바람은 위쪽 반시계 순환, 아래는 시꼐방향
	// 바람이 불면 바람의 방향로 한칸씩 이동
	// 공기청정기로 들어가면 미세먼지 사라짐

	// 미세먼지의 좌표를 전부 구해서 리스트에 넣기
	// 재귀를 돌려서 1초마다 리스트에 있는 미세먼지 좌표들로 확산 시행 ( -1 이거나 범위 밖이면 패스)
	// 사방탐색을 하기 전에 미리 1/5 계산한다음 사방탐색 하면서 그만큼 빼줌 ( 미서먼지 확산은 동시에 일어나기 때문에 데이터 입력 받을때
	// 1/5 저장하고 확산할 대 마다 업데이트 해야할듯?)
	// 바람 불면 공기청정기 방향부터 위 아래 한칸씩 밀어줘여할듯?
	static int R, C, T;
	static int[][] map;
	static Cleaner[] cleaner = new Cleaner[2];
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1) {
					cleaner[idx++] = new Cleaner(i, j);
				}
				map[i][j] = num;
			}
		}
		clean(0);
	}

	private static void clean(int time) {
		if (time == T) {
			int remain = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0)
						remain += map[i][j];
				}
			}
			System.out.println(remain);
			return;
		}
		ArrayList<FineDust> fd = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					fd.add(new FineDust(i, j, map[i][j]/5));
				}
			}
		}
		for (int i = 0; i < fd.size(); i++) {
			spread(fd.get(i).x, fd.get(i).y, fd.get(i).spread);
		}
		moveCleaner(cleaner);
		clean(time + 1);

	}

	private static void moveCleaner(Cleaner[] cleaner) {
		int nx = cleaner[0].x - 1;
		int ny = cleaner[0].y;
		for (int i = nx; i > 0; i--) {
			map[nx][ny] = map[--nx][ny];
		}
		for (int i = ny; i < C - 1; i++) {
			map[nx][ny] = map[nx][++ny];
		}
		for (int i = 0; i < cleaner[0].x; i++) {
			map[nx][ny] = map[++nx][ny];
		}
		for (int i = C - 1; i > 1; i--) {
			map[nx][ny] = map[nx][--ny];
		}
		map[nx][ny] = 0;

		nx = cleaner[1].x + 1;
		ny = cleaner[1].y;
		for (int i = nx; i < R - 1; i++) {
			map[nx][ny] = map[++nx][ny];
		}
		for (int i = 0; i < C - 1; i++) {
			map[nx][ny] = map[nx][++ny];
		}
		for (int i = R - 1; i > cleaner[1].x; i--) {
			map[nx][ny] = map[--nx][ny];
		}
		for (int i = C - 1; i > 1; i--) {
			map[nx][ny] = map[nx][--ny];
		}

		map[nx][ny] = 0;
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	private static void spread(int x, int y, int spread) {
		for (int i = 0; i < 4; i++) {
			if (x + dx[i] >= R || x + dx[i] < 0 || y + dy[i] >= C || y + dy[i] < 0 || map[x + dx[i]][y + dy[i]] == -1)
				continue;
			map[x + dx[i]][y + dy[i]] += spread;
			map[x][y] -= spread;
		}
	}
}
