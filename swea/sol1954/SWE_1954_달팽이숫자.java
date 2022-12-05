package com.ssafy.recur.swea.sol1954;

import java.util.Arrays;
import java.util.Scanner;

public class SWE_1954_달팽이숫자 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t < T + 1; t++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			int temp = N;
			int cnt = 1;
			int xIdx = 0, yIdx = 0;
			while(true) {
				if(cnt > N*N) break;
				for(int i = 0; i < temp; i++) {
					map[xIdx][yIdx++] = cnt;
					cnt++;
				}
				yIdx -= 1;
				for(int i = 0; i < temp - 1; i++) {
					map[++xIdx][yIdx] = cnt;
					cnt++;
				}
				for(int i = 0; i < temp - 1; i++) {
					map[xIdx][--yIdx] = cnt;
					cnt++;
				}
				for(int i = 0; i < temp - 2; i++) {
					map[--xIdx][yIdx] = cnt;
					cnt++;
				}
				temp -= 2;
				yIdx += 1;
			}
			System.out.println("#" + t);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
