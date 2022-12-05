package com.ssafy.recur.BOJ.sol17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0; i <N ; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j =0; j <N ; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
	}
}
