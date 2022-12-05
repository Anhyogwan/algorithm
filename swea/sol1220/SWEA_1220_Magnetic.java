package com.ssafy.recur.swea.sol1220;

import java.util.*;
import java.io.*;

public class SWEA_1220_Magnetic {
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("data/1220.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t < 11; t++) {
			int T = Integer.parseInt(br.readLine());
			int[][] map = new int[T][T];
			for(int i = 0; i < T; i++) {
				String[] s = br.readLine().split(" ");
				for(int j = 0; j < T; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			int cnt = 0;
			
			for(int i = 0; i < T; i++) {
				int rcnt = 0;
				int bcnt = 0;				
				for(int j = 0; j < T; j++) {
					if(map[j][i] == 1) rcnt++;
					else if(map[j][i] == 2) {
						if(rcnt == 0) continue;
						else bcnt++;
					}
					if(rcnt > 0 && bcnt > 0) {
						cnt++;
						rcnt = 0;
						bcnt = 0;
					}
						
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}
}
