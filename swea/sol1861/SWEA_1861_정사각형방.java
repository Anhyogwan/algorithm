package com.ssafy.recur.swea.sol1861;

import java.util.Scanner;

public class SWEA_1861_정사각형방 {
	static int cnt = 1;
	static int N, num, max, min;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			isSelected = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dfs(i, j);
					if(max < cnt) {
						max = cnt;
						min = map[i][j];
						
					}else if (max == cnt) {
						min = Math.min(min, map[i][j]);
						
					}
					cnt = 1;
				}
			}
			
			System.out.println("#" + t + " " + min + " " + max);
			
			
		}
		
	}
	
	private static void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(isSelected[nx][ny]) continue;
			if((map[nx][ny] - map[x][y]) == 1) {
				isSelected[x][y] = true;
				cnt++;
				dfs(nx, ny);
				isSelected[x][y] = false;
				return;
			}
		}
	}
}
