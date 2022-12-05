package com.ssafy.recur.swea.sol5688;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			double N = Math.cbrt(Integer.parseInt(br.readLine()));
			if (N==Math.floor(N)) {
				System.out.println("#" + tc + " " +(int)N);
			}else {
				System.out.println("#" + tc + " " + -1);
			}
		}
	}
}
