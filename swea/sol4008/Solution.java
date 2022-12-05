package com.ssafy.recur.swea.sol4008;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] operator = new int[4];
	static int[] number;
	static int max_value;
	static int min_value;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			number=new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			max_value=Integer.MIN_VALUE;
			min_value=Integer.MAX_VALUE;
			dfs(1, number[0], operator.clone());
			System.out.println("#" + tc + " " + (max_value-min_value));
		}
	}

	private static void dfs(int idx, int total, int[] operator2) {
		if (idx == N ) {
			max_value= Integer.max(total, max_value);
			min_value = Integer.min(total, min_value);
			return;
		}

		if (operator2[0] != 0) {
			operator2[0]--;
			dfs(idx+1,total + number[idx],operator2.clone());
			operator2[0]++;
		}
		if (operator2[1] != 0) {
			operator2[1]--;
			dfs(idx+1,total - number[idx],operator2.clone());
			operator2[1]++;
		}
		if (operator2[2] != 0) {
			operator2[2]--;
			dfs(idx+1,total * number[idx],operator2.clone());
			operator2[2]++;
		}
		if (operator2[3] != 0) {
			operator2[3]--;
			dfs(idx+1,total / number[idx],operator2.clone());
			operator2[3]++;
		}

	}
}
