package com.ssafy.recur.BOJ.sol1010;

import java.util.Scanner;

public class BOJ_1010_다리놓기 {
	static int N, M, cnt;
	static int[][] count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			count = new int[31][31];
			N = sc.nextInt();
			M = sc.nextInt();
			for(int j = 0; j <= 30; j++) {
				count[j][0] = 1;
			}
			for(int j = 1; j <= M; j++) {
				for(int k = 1; k <= N; k++) {
					if(j == 1) count[j][k] = k;
					if(j == k) count[j][k] = 1;
					else dp(j, k);
				}
			}

			System.out.println(count[M][N]);
		}
	}
	
	private static void dp(int x, int y) {
		count[x][y] = count[x - 1][y - 1] + count[x - 1][y];
	}
}
