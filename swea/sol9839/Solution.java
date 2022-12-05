package com.ssafy.recur.swea.sol9839;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] num_list = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				num_list[i] = num;
			}

			int max_value = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				outer: for (int j = i+1; j < N; j++) {
					int mul = num_list[i] * num_list[j];
					String result = String.valueOf(mul);
					for (int p = 1; p < result.length(); p++) {
						if (result.charAt(p-1)+ 1 != result.charAt(p) ) {
							continue outer;
						}
					}
					max_value = mul > max_value ? mul : max_value;
				}
			}

			if (max_value != Integer.MIN_VALUE) {
				System.out.println("#" + tc + " " + max_value);
			} else {
				System.out.println("#" + tc + " " + -1);
			}
		}
	}
}
