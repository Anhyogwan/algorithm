package com.ssafy.recur.swea.sol2805;

import java.util.*;
import java.io.*;

public class SWEA_2805_농작물수확하기2 {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t < T + 1; t++) {
			int ans = 0;
			int N = sc.nextInt();
			int[][] farm = new int[N][N];
			for(int i = 0; i < N; i++) {
				char[] data = sc.next().toCharArray();
				for(int j = 0; j < data.length; j++) {
					farm[i][j] = data[j] - '0';
				}
			}
			int center = N / 2;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(Math.abs(center - i) + Math.abs(center - j) <= center) {
						ans += farm[i][j];
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}
