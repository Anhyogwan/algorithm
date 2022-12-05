package com.ssafy.recur.BOJ.sol14890;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 가로 세로의 길을 지나갈 수 있음 - > 양쪽으로 두번 검사
	// 낮은칸이 두 칸이 연속될 경우 다리를 놓을 수 있음
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			boolean[] result = new boolean[N];
			boolean[] result2 = new boolean[N];
			// 검사하면서 위치가 다르지 않을경우 이어지는 숫자의 갯수 카운팅
			// 검사하다가 위치가 다를경우 더 높은지 낮은지 확인 훅
			// 더 높을경우 해당 위치에 사다리 놓기 더 낮을경우에는 카운팅 초기화
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
				}
			}
			for (int i = 0; i < N; i++) {
				int check = 0;
				int temp = 0;
				boolean[] runway = new boolean[N];
				outer: for (int j = 0; j < N; j++) {
					if (j == 0) {
						temp = map[i][j];
						check++;
						continue;
					} else {
						temp = map[i][j - 1];
					}
					if (result[i] == true) {
						continue;
					}
					if (map[i][j] > temp + 1 || map[i][j] < temp - 1) {
						result[i] = true;
						continue;
					}
					if (map[i][j] == temp + 1 && check >= L) {
						int k = j - 1;
						int c = 0;
						while (c != L) {
							if (runway[k] == true) {
								result[i] = true;
								continue outer;
							}
							k--;
							c++;
						}
						c = 0;
						k = j - 1;
						while (c != L) {
							runway[k] = true;
							k--;
							c++;
						}
						check = 1;
					} else if (temp == map[i][j]) {
						check++;
					} else if (map[i][j] == temp - 1 && j + L - 1 < N) {

						for (int k = j; k < j + L; k++) {
							if (map[i][k] != temp - 1) {
								result[i] = true;
								continue outer;
							}
						}
						for (int k = j; k < j + L; k++) {
							runway[k] = true;
						}
					} else {
						result[i] = true;
					}
				}
			}

			for (int j = 0; j < N; j++) {
				int check = 0;
				int temp = 0;
				boolean[] runway = new boolean[N];
				outer2: for (int i = 0; i < N; i++) {
					if (i == 0) {
						temp = map[i][j];
						check++;
						continue;
					} else {
						temp = map[i - 1][j];
					}
					if (result2[j] == true) {

						continue;
					}
					if (map[i][j] > temp + 1 || map[i][j] < temp - 1) {
						result2[j] = true;
						continue;
					}
					if (map[i][j] == temp + 1 && check >= L) {
						int k = i - 1;
						int c = 0;
						while (c != L) {
							if (runway[k] == true) {
								result2[j] = true;
								continue outer2;
							}
							k--;
							c++;
						}
						k = i - 1;
						c = 0;
						while (c != L) {
							runway[k] = true;
							k--;
							c++;
						}
						check = 1;
					} else if (temp == map[i][j]) {
						check++;
					} else if (map[i][j] == temp - 1 && i + L - 1 < N) {
						for (int k = i; k < i + L; k++) {
							if (map[k][j] != temp - 1) {
								result2[j] = true;
								continue outer2;
							}
						}
						for (int k = i; k < i + L; k++) {
							runway[k] = true;
						}
					} else {
						result2[j] = true;
					}
				}
			}
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (!result[i])
					cnt++;
				if (!result2[i])
					cnt++;
			}
			System.out.println("#" + tc +" " +cnt);
		}
	}
}
