package com.ssafy.recur.swea.sol4615;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2가 화이트 1이 블랙
public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int[][] pan = new int[num + 1][num + 1];
			pan[num / 2][num / 2] = 2;
			pan[num / 2 + 1][num / 2 + 1] = 2;
			pan[num / 2][num / 2 + 1] = 1;
			pan[num / 2 + 1][num / 2] = 1;
			int M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int stone = Integer.parseInt(st.nextToken());
				int[] dp = { 0, -1, 1 };
				pan[x][y] = stone;
				for (int j = 0; j < dp.length; j++) {
					for (int p = 0; p < dp.length; p++) {
						if (j == 0 && p == 0)
							continue;
						int k = 1;
						while (x + (k * dp[j]) > 0 && x + (k * dp[j]) <= num && y + (k * dp[p]) > 0
								&& y + (k * dp[p]) <= num) {
							if (pan[x + (k * dp[j])][y + (k * dp[p])]==0) {
								break;
							}
							if (pan[x + (k * dp[j])][y + (k * dp[p])] == stone) {
								for (int a = 1; a <= k; a++) {
									pan[x + (a * dp[j])][y + (a * dp[p])] = stone;
								}
								break;
							}
							k++;
						}
					}
				}
			}
			int white = 0;
			int black = 0;
			for (int j = 0; j <= num; j++) {
				for (int p = 0; p <= num; p++) {
					if (pan[j][p] == 1)
						black++;
					if (pan[j][p] == 2)
						white++;
				}
			}
			System.out.println("#" + tc + " " + black + " " + white);
		}
	}
}
