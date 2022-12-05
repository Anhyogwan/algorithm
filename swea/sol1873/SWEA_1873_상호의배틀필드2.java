package com.ssafy.recur.swea.sol1873;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873_상호의배틀필드2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int x = 0;
			int y = 0;
			char[][] map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}
			int N=Integer.parseInt(br.readLine());
			char[] com = br.readLine().toCharArray();
			for (int i =0; i<com.length; i ++) {
				switch(com[i]) {
				case 'S':
				case 'R':
				case 'L':
				case 'D':
				case 'U':
					
				}
			}
		}
	}}
