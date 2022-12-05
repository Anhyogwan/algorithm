package com.ssafy.recur.BOJ.sol17069;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 가로일 때 2개
	// 세로일 때 2개
	// 대각선일떄 3개
	// 가로일 경우에는 y 이전거에서 +1
	// 세로일 경우에는 x 이전거에서 +1
	// 대각선일 경우 위하고 옆에서 +1
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		long[][][] dp = new long[N][N][3];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 0번 가로 , 1 대각선, 2 세로
		for (int i = 1; i < N; i++) {
			if(map[0][i]==1) {
				break;
			}
			dp[0][i][0] = 1;
		}
//		for (int i = 0; i <N ; i++) {
//			System.out.println(dp[0][i][0]);
//		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if(map[i][j]==1) continue;
				if (map[i][j - 1] != 1) {
					dp[i][j][0] = (dp[i][j - 1][0] + dp[i][j - 1][1]);
				}
				if (map[i - 1][j] != 1 && map[i][j - 1] != 1 && map[i - 1][j - 1] != 1) {
					dp[i][j][1] = (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]);
				}
				if (map[i - 1][j] != 1) {
					dp[i][j][2] = (dp[i - 1][j][2] + dp[i - 1][j][1]);
				}
			}
		}
		long total = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		System.out.println(total);
	}
}
