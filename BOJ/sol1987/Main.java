package com.ssafy.recur.BOJ.sol1987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// R,C 보드
	// 요소에 대묹 ㅏ알파벳
	// 0,0에 말
	// 말은 사방탐색 가능
	// 알파벳을 체크해서 안지나온 칸
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int R, C;
	static char[][] map;
	static int max_value =Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0 ; i < R; i++) {
			map[i]=br.readLine().toCharArray();
		}
		
		boolean[] check = new boolean[100];
		dfs(0,0 ,1,check);
		System.out.println(max_value);
	}
	static void dfs(int x,int y, int cnt,boolean[] check) {
		check[map[x][y]]=true;
		for (int i = 0 ; i <4 ;i ++) {
			if (x + dx[i] >= R || x+dx[i] <0 || y+dy[i] >=C || y+dy[i]<0 || (check[map[x+dx[i]][y+dy[i]]]==true)) continue;
			dfs(x+dx[i],y+dy[i],cnt+1,check.clone());
		}
		max_value=Math.max(max_value, cnt);
	}
}
