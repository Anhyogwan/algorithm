package com.ssafy.recur.swea.sol2805;

import java.util.*;
import java.io.*;

public class SWEA_2805_농작물수확하기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T + 1; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				String[] s = br.readLine().split("");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			int n = N / 2;
			int temp = n;
			int cnt = 1;
			int sum = 0;
			
			for(int i = 0; i < temp + 1; i++) {
				for(int j = n; j < temp + cnt; j++) {
					sum += map[i][j];
				}
				n--;
				cnt++;					
			}
			
			n++;
			cnt--;
			for(int i = temp + 1; i < N; i++) {
				++n;
				--cnt;
				for(int j = n; j < temp + cnt; j++) {
					sum += map[i][j];
				}
			}
			
			System.out.println("#" + t + " " + sum);
		}
	}
}
