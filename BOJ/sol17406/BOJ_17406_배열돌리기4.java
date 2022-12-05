package com.ssafy.recur.BOJ.sol17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
	static int[] temp = new int[3];
	static int[] numbers;
	static boolean[] isSelected;
	static int[][] rcs;
	static int N, M, K, r, c, s;
	static int min = Integer.MAX_VALUE;
	static int[][] map, mTemp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		mTemp = new int[N + 1][M + 1];
		rcs = new int[K][3];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				mTemp[i][j] = n;
			}
		}
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rcs[i][0] = Integer.parseInt(st.nextToken());
			rcs[i][1] = Integer.parseInt(st.nextToken());
			rcs[i][2] = Integer.parseInt(st.nextToken());
		}
		numbers = new int[K];
		isSelected = new boolean[K];
		form(0);
		System.out.println(min);
		
	}
	private static void form(int cnt) {
		if(cnt == K) {
			for(int i = 0; i < K; i++) {
				int idx = numbers[i];
				r = rcs[idx][0];
				c = rcs[idx][1];
				s = rcs[idx][2];
				rotate(s);
			}
			for(int j = 1; j <= N; j++) {
				int sum = 0;
				for(int k = 1; k <= M; k++) {
					sum += map[j][k];
				}
				min = Math.min(min, sum);
			}
			for(int i = 0; i <= N; i++) {
				for(int j = 0; j <= M; j++) {
					map[i][j] = mTemp[i][j];
				}
			}
			map = mTemp;
			return;
		}
		for(int i = 0; i < K; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			numbers[cnt] = i;
			form(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	private static void rotate(int s) {
		if(s == 0) return;
		temp[0] = map[r - s][c + s];
		temp[1] = map[r + s][c + s];
		temp[2] = map[r + s][c - s];
		for(int i = c + s - 1; i >= c - s; i--) {
			map[r - s][i + 1] = map[r - s][i];
		}
		for(int i = r + s - 1; i >= r - s; i--) {
			map[i + 1][c + s] = map[i][c + s];
		}
		map[r - s + 1][c + s] = temp[0];
		for(int i = c - s; i < c + s; i++) {
			map[r + s][i] = map[r + s][i + 1];
		}
		map[r + s][c + s - 1] = temp[1];
		for(int i = r - s; i < r + s; i++) {
			map[i][c - s] = map[i + 1][c - s];
		}
		map[r + s - 1][c - s] = temp[2];
		
		rotate(s - 1);
	}
}
