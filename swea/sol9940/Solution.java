package com.ssafy.recur.swea.sol9940;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		outer :for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] chk = new boolean[N + 1];
			chk[0] = true;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				chk[num] = true;
			}
			for (int i = 0; i < N; i++) {
				if (!chk[i]) {
					System.out.println("#" + tc + " No");
					continue outer;
				}
			}
			System.out.println("#" + tc + " Yes");
		}
	}
}
