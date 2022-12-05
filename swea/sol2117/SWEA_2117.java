package com.ssafy.recur.swea.sol2117;

import java.io.*;
import java.util.*;

public class SWEA_2117 {
	static int N, M, max;
	static ArrayList<int[]> houses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			houses = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					// 집의 좌표들 추가
					if (st.nextToken().equals("1")) {
						houses.add(new int[] { i, j });
					}
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = 0;
					optimize(i, j, 1);
					if (ans < max) {
						ans = max;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void optimize(int r, int c, int range) {
		if (range > N + 1) {
			return;
		}
		int cnt = 0;
		for (int[] h : houses) {
			if (isServiceProvided(h[0], h[1], r, c, range)) cnt++;
		}
		int netIncome = getNetIncome(cnt * M, range);
		max = (netIncome >= 0) ? Math.max(cnt, max) : max;
		optimize(r, c, range + 1);
	}

	private static boolean isServiceProvided(int r1, int c1, int r2, int c2, int range) {
		return (Math.abs(r1 - r2) + Math.abs(c1 - c2)) < range;
	}

	private static int getNetIncome(int income, int range) {
		return income - (range * range + (range - 1) * (range - 1));
	}
}
