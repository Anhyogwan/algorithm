package com.ssafy.recur.BOJ.sol15683;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class CCTV {
	int num;
	int x;
	int y;

	public CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15683_감시 {
	// CCTV는 5종류가 있음 ( 우 반대 직각 세방향 상하좌우
	// CCTV는 벽 통과 불가 감시할수 없는 영역은 사각지대
	// 회전 가능 90도 방향 ( 감시하려고 하는 방향이 가로 or 세로)
	// 6 -> 벽, 1~5 CCTV 번호
	// CCTV는 CCTV를 통과 할 수 있음
	// 조합 문제 라고 보는게 맞을듯 (각 cctv가 볼 수 있는 방향을 각자 검사해서 최소값을 구하는 문제
	// 방향을 통일
	static int min_value = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] CCTV = { { 0 }, { 0, 1, 2, 3 }, { 0, 1 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0 } };
	static ArrayList<CCTV> cctv;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num != 0 && num != 6) {
					cctv.add(new CCTV(num, i, j));
				}
				map[i][j] = num;
			}
		}

		check(0, 0, new int[cctv.size()]);
		System.out.println(min_value);
	}

	// 각자의 숫자에 대해서 조합을 돌려야 되는데..
	// 첫번째에서 한개를 뽑고 두번째에서 한개를 뽑고
	static void check(int idx, int start, int[] cctv_move) {
		if (idx == cctv.size()) {
			watch(cctv_move);
			return;
		}
		for (int i = start; i < cctv.size(); i++) {
			for (int j = 0; j < CCTV[cctv.get(i).num].length; j++) {
				cctv_move[idx] = CCTV[cctv.get(i).num][j];
				check(idx + 1, i + 1, cctv_move);
			}
		}
	}

	static void watch(int[] cctv_move) {
		int[][] map2 = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			map2[i] = map[i].clone();
		}
		for (int i = 0; i < cctv.size(); i++) {
			switch (cctv.get(i).num) {
			case 1:
				map2 = cctv_1(map2, cctv.get(i), cctv_move[i]);
				continue;
			case 2:
				map2 = cctv_2(map2, cctv.get(i), cctv_move[i]);
				continue;
			case 3:
				map2 = cctv_3(map2, cctv.get(i), cctv_move[i]);
				continue;
			case 4:
				map2 = cctv_4(map2, cctv.get(i), cctv_move[i]);
				continue;
			case 5:
				map2 = cctv_5(map2, cctv.get(i), cctv_move[i]);
				continue;
			}
		}
		int cnt = 0;
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2[i].length; j++) {
				if (map2[i][j] == 0) {
					cnt++;
				}
			}
		}

		min_value=Math.min(min_value, cnt);
	}

	static int[][] cctv_1(int[][] map2, CCTV c, int cctv_move) {
		if (cctv_move == 0) {
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		if (cctv_move == 1) {
			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		if (cctv_move == 2) {
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
		}
		if (cctv_move == 3) {
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
		}

		return map2;
	}

	static int[][] cctv_2(int[][] map2, CCTV c, int cctv_move) {
		if (cctv_move == 0) {
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}

		}
		if (cctv_move == 1) {
			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		return map2;
	}

	static int[][] cctv_3(int[][] map2, CCTV c, int cctv_move) {
		if (cctv_move == 0) {
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		if (cctv_move == 1) {
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
		}
		if (cctv_move == 2) {
			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
		}
		if (cctv_move == 3) {
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
		}
		return map2;
	}

	static int[][] cctv_4(int[][] map2, CCTV c, int cctv_move) {
		if (cctv_move == 0) {
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}

			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		if (cctv_move == 1) {
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}

			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		if (cctv_move == 2) {
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
			if (c.y - 1 >= 0) {
				for (int i = c.y - 1; i >= 0; i--) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		if (cctv_move == 3) {
			if (c.x + 1 < N) {
				for (int i = c.x + 1; i < N; i++) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
			if (c.x - 1 >= 0) {
				for (int i = c.x - 1; i >= 0; i--) {
					if(map2[i][c.y]==6) break;
					map2[i][c.y] = 9;
				}
			}
			if (c.y + 1 < M) {
				for (int i = c.y + 1; i < M; i++) {
					if(map2[c.x][i]==6) break;
					map2[c.x][i] = 9;
				}
			}
		}
		return map2;
	}

	static int[][] cctv_5(int[][] map2, CCTV c, int cctv_move) {
		if (c.x + 1 < N) {
			for (int i = c.x + 1; i < N; i++) {
				if(map2[i][c.y]==6) break;
				map2[i][c.y] = 9;
			}
		}
		if (c.x - 1 >= 0) {
			for (int i = c.x - 1; i >= 0; i--) {
				if(map2[i][c.y]==6) break;
				map2[i][c.y] = 9;
			}
		}

		if (c.y - 1 >= 0) {
			for (int i = c.y - 1; i >= 0; i--) {
				if(map2[c.x][i]==6) break;
				map2[c.x][i] = 9;
			}
		}
		if (c.y + 1 < M) {
			for (int i = c.y + 1; i < M; i++) {
				if(map2[c.x][i]==6) break;
				map2[c.x][i] = 9;
			}

		}
		return map2;
	}
}
