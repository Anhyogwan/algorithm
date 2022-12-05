package com.ssafy.recur.BOJ.sol1210;

import java.io.*;
import java.util.*;

public class BOJ_1210_Ladder1 {
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src\\data\\1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int t = 1; t < T + 1; t++) {
			int N = Integer.parseInt(br.readLine());
			int xIdx = 0, yIdx = 0;
			int[][] map = new int[100][100];
	
			for(int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					if(n == 2) {
						xIdx = i;
						yIdx = j;
					}
				}
			}
				
			while(xIdx > 0) {
				if(yIdx - 1 >= 0 && yIdx - 1 < 100 && map[xIdx][yIdx - 1] == 1) {
					while(yIdx - 1 >= 0 && yIdx - 1 < 100 && map[xIdx][yIdx - 1] == 1) {
						yIdx--;
					}
				}else if(yIdx + 1 >= 0 && yIdx + 1 < 100 && map[xIdx][yIdx + 1] == 1) {
					while(yIdx + 1 >= 0 && yIdx + 1 < 100 && map[xIdx][yIdx + 1] == 1) {
						yIdx++;
					}
				}
				if(map[xIdx - 1][yIdx] == 1) {
					xIdx--;
				}
			}
			
			
			System.out.println("#" + t + " " + yIdx);
		}
	}
}