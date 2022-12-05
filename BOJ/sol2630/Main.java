package com.ssafy.recur.BOJ.sol2630;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// 전체 종이가 모두 같은색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 4개의 색종이로 나눈다
	// 색종이를 한 번 검사하고 -> 0이 존재할 경우
	static int blue;
	static int white;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		check(N, 0, 0);
		System.out.println(white + " " + blue);
	}

	static void check(int N, int x, int y) {
		boolean flag = false;
		boolean flag_2 = false;
		for (int i = x; i < x + N; i++) {
			for (int j = y; j < y + N; j++) {
				if (map[i][j] == 0) {
					flag = true;
				}
				if (map[i][j] == 1) {
					flag_2 = true;
				}
			}
		}
		if (flag == true && flag_2 == true) {
			check(N / 2, x, y);
			check(N / 2, x + N / 2, y);
			check(N / 2, x, y + N / 2);
			check(N / 2, x + N / 2, y + N / 2);
		} else if (flag == true && flag_2 == false) {
			white++;
		} else if (flag == false && flag_2 == true) {
			blue++;
		}
		return;
	}
}
