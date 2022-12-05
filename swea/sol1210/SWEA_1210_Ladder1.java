package com.ssafy.recur.swea.sol1210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] pan = new int[100][100];
			ArrayList<Integer> start = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					if (i == 0 && pan[i][j] == 1) {
						start.add(j);
					}
				}
			}
			int result = 0;

			for (int i = 0; i < start.size(); i++) {
				if (result != 0)
					break;
				int y = start.get(i);
				int x = 0;
				while (x < pan.length) {
					if (pan[x][y] == 2) {
						result = start.get(i);
						break;
					}
					if (y + 1 < 100 && pan[x][y + 1] == 1) {
						while(y+1 <100 && pan[x][y+1]==1) {
							y+=1;
						}
						x+=1;
						continue;
					}
					if (y - 1 >= 0 && pan[x][y - 1] == 1) {
						while(y-1>=0 && pan[x][y-1]==1) {
							y-=1;
						}
						x+=1;
						continue;
					}
					x += 1;
				}
			}
			System.out.println(result);

		}
	}
}
