package com.ssafy.recur.BOJ.sol1992;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1992_쿼드트리 {
	static char[][] movie;
	static String result="";
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		movie = new char[N][N];
		for (int i = 0; i < N; i++) {
			movie[i] = br.readLine().toCharArray();
		}
		dfs(N, 0, 0);
		System.out.println(result);
	}

	static void dfs(int N, int x, int y) {
		if (N<0) {
			return;
		}
		char temp=movie[x][y];
		boolean flag = false;
		for (int i = x; i < x + N; i++) {
			for (int j = y; j < y + N; j++) {
				if (temp != movie[i][j]) {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			result+="(";
			dfs(N / 2, x, y);
			dfs(N / 2, x , y+ N / 2);
			dfs(N / 2, x+ N / 2, y );
			dfs(N / 2, x + N / 2, y + N / 2);
			result+=")";
		}else {
			result+= temp;
		}
	}
}
