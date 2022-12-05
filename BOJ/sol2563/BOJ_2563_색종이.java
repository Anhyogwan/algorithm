package com.ssafy.recur.BOJ.sol2563;

import java.util.Scanner;

public class BOJ_2563_색종이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int cnt = 0;
		int[][] map = new int[100][100];

		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int j = x - 1; j < x + 9; j++) {
				for(int k = y - 1; k < y + 9; k++) {
					if(map[j][k] == 0) {
						map[j][k] = 1;
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
		
	}
}
