package com.ssafy.recur.BOJ.sol2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] sdoku = new int[9][9];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// 배열을 3개를 만듬
		// 가로 체크할 배열
		// 세로 체크할 배열
		// 가운데 체크할 배열
		// 없는것 체크하고
		char[] temp = new char[9];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			temp = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sdoku[i][j] = temp[j] - '0';
			}
		}
		select(0, 0);

	}

	private static void select(int x, int y) {
		if(y==9) {
			x++;
			y=0;
		}
		if (x == 9) {
			for (int l = 0; l < 9; l++) {
				for (int p = 0; p < 9; p++) {
					sb.append(sdoku[l][p]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}

		if (sdoku[x][y] == 0) {
			for (int k = 1; k <= 9; k++) {
				if (check(x, y, k)) {
					sdoku[x][y] = k;
					select(x, y + 1);
				}
				sdoku[x][y] = 0;
			}
		} else {
			select(x, y + 1);
		}

	}

	private static boolean check(int x, int y, int k) {
		for (int i = 0; i < 9; i++) {
			if(sdoku[x][i]==k) return false;
			if(sdoku[i][y]==k) return false;
		}
		for (int i = (x / 3) * 3; i < (x / 3) * 3 + 3; i++) {
			for (int j = (y / 3) * 3; j < (y / 3) * 3 + 3; j++) {
				if(sdoku[i][j]==k) return false;
			}
		}

		return true;
	}
}
