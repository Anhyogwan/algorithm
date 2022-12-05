package com.ssafy.recur.BOJ.sol20057;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// 토네이도의 이동방향
	// 이동방향에 따른 고정적인 계산
	// 아래를 볼때는 *-1 하고 leftx와 lefty를 바꿔줌
	// 위를 볼때는 leftx와 lefty를 바꿔줌
	// 오른족을 볼때는 leftx와 lefty에 *-1 해줌
	// 왼쪽 0 , 아래 1 , 오른쪽 2 , 위쪽 3
	static int[][] map;
	static int[] leftx = { 1, -1, 1, -1, 1, -1, 2, -2, 0 };
	static int[] lefty = { 0, 0, -1, -1, 1, 1, 0, 0, -2 };
	static int[] per = { 7, 7, 10, 10, 1, 1, 2, 2, 5 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int N;
	static int out_sand = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		int x = N / 2;
		int y = N / 2;
		int spin = 1;
		int s_c = 0;
		int move = 0;
		outer: while (true) {
			if (s_c != 0 && s_c % 2 == 0)
				spin++;
			for (int i = 0; i < spin; i++) {
				x = x + dx[move % 4];
				y = y + dy[move % 4];
				tornado(x, y, move % 4);
				if (x == 0 && y == 0)
					break outer;
			}
			move++;
			s_c++;
		}	
		System.out.println(out_sand);
	}

	static void tornado(int x, int y, int move) {

		if (move == 0) {
			int total=0;
			for (int i = 0; i < 9; i++) {
				if (x + leftx[i] >= N || x + leftx[i] < 0 || y + lefty[i] >= N || y + lefty[i] < 0)
					out_sand += ((map[x][y]* per[i] / 100) );
				else {
					map[x + leftx[i]][y + lefty[i]] += ((map[x][y]* per[i] / 100) );
				}
				 total += (map[x][y]* per[i] / 100) ;
			}
			map[x][y] = map[x][y] -total;
			if (x + dx[move] >= N || x + dx[move] < 0 || y + dy[move] >= N || y + dy[move] < 0) {
				out_sand += map[x][y];
			} else {
				map[x + dx[move]][y + dy[move]] += map[x][y];
			}
			map[x][y]=0;
		} else if (move == 1) {
			int total=0;
			for (int i = 0; i < 9; i++) {
				if (x + lefty[i]*-1 >= N || x + lefty[i]*-1 < 0 || y + leftx[i]*-1 >= N || y + leftx[i]*-1 < 0)
					out_sand += ((map[x][y] * per[i] / 100));
				else {
					map[x + lefty[i]*-1][y + leftx[i]*-1] += (((map[x][y]* per[i]) / 100) );
				}
				 total += (map[x][y]* per[i] / 100) ;
			}
			map[x][y] = map[x][y] -total;
			if (x + dx[move] >= N || x + dx[move] < 0 || y + dy[move] >= N || y + dy[move] < 0) {
				out_sand += map[x][y];
			} else {
				map[x + dx[move]][y + dy[move]] += map[x][y];
			}
			map[x][y]=0;
		} else if (move == 2) {
			int total=0;
			for (int i = 0; i < 9; i++) {
				if (x + leftx[i]*-1 >= N || x + leftx[i]*-1 < 0 || y + lefty[i]*-1 >= N || y + lefty[i]*-1 < 0)
					out_sand += ((map[x][y]* per[i] / 100) );
				else {
					map[x + leftx[i]*-1][y + lefty[i]*-1] += ((map[x][y]* per[i] / 100) );
				}
				 total += (map[x][y]* per[i] / 100) ;
			}
			map[x][y] = map[x][y] -total;
			if (x + dx[move] >= N || x + dx[move] < 0 || y + dy[move] >= N || y + dy[move] < 0) {
				out_sand += map[x][y];
			} else {
				map[x + dx[move]][y + dy[move]] += map[x][y];
			}
			map[x][y]=0;
		} else if (move == 3) {
			int total=0;
			for (int i = 0; i < 9; i++) {
				if (x + lefty[i] >= N || x + lefty[i] < 0 || y + leftx[i] >= N || y + leftx[i] < 0)
					out_sand += ((map[x][y] * per[i] / 100));
				else {
					map[x + lefty[i]][y + leftx[i]] += ((map[x][y] * per[i] / 100));
				}
				 total += (map[x][y]* per[i] / 100) ;
			}
			map[x][y] = map[x][y] -total;
			if (x + dx[move] >= N || x + dx[move] < 0 || y + dy[move] >= N || y + dy[move] < 0) {
				out_sand += map[x][y];
			} else {
				map[x + dx[move]][y + dy[move]] += map[x][y];
			}
			map[x][y]=0;
		}
	}
}
