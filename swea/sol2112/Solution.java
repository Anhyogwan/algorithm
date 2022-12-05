package com.ssafy.recur.swea.sol2112;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int D, W, K;
	static int[][] film;
	static int tc;
	static int min_value;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min_value = Integer.MAX_VALUE;
			perm(0,0,film);
			sb.append("#" + tc + " " + min_value).append("\n");
		}
		System.out.println(sb);
	}

	private static void perm(int idx,int injection,int[][] film) {
		if (idx == D || injection>=min_value) {
			if(check2(film)) {
				min_value=Math.min(injection, min_value);
			}
			return;
		}
		int[][] film2 = new int[D][W];
		for (int i =0; i <D; i ++) {
			film2[i]=film[i].clone();
		}
		perm(idx + 1,injection,film2);
		for (int i = 0; i < W; i++) {
			film2[idx][i] = 1;
		}
		perm(idx + 1,injection +1,film2);
		for (int i = 0; i < W; i++) {
			film2[idx][i] = 0;
		}
		perm(idx + 1,injection +1,film2);

	}

	private static boolean check2(int[][] film2) {
		outer: for (int i = 0; i < W; i++) {
			int check = 0;
			int temp = film2[0][i];
			boolean kcheck = false;
			for (int j = 0; j < D; j++) {
				if (film2[j][i] != temp) {
					temp = film2[j][i];
					check = 1;
				} else {
					check++;
				}
				if (check >= K) {
					kcheck = true;
					continue outer;
				}
			}
			if (!kcheck) {
				return false;
			}
		}
		return true;
	}

}
