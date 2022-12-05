package com.ssafy.recur.BOJ.sol2567;

import java.util.Scanner;

public class BOJ_2567_색종이2 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static int cnt, xMax, yMax;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int cnt = 0;
		
		map = new int[100][100];

		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			xMax = Math.max(xMax, x) + 10;
			yMax = Math.max(yMax, y) + 10;
			
			for(int j = x - 1; j < x + 9; j++) {
				for(int k = y - 1; k < y + 9; k++) {
					if(map[j][k] == 0) {
						map[j][k] = 1;
					}
				}
			}
			
			for(int j = 0; j < xMax + 1; j++) {
				for(int k = 0; k < yMax + 1; k++) {
					if(map[j][k] == 1) {
						dfs(j, k);
					}
				}
			}
		}
		System.out.println(cnt);
	}
	
	private static void dfs(int x, int y) {

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = x + dy[i];
			
			if(nx < 0 || nx >= 100 || ny < 0 || ny >= 100) cnt++;
			if(map[nx][ny] == 0) cnt++;
		}
	}
}
