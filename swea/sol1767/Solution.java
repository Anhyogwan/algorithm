package com.ssafy.recur.swea.sol1767;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static class Core {
		int x, y;

		public Core(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int min_value,N;
	static int[][] map;
	static ArrayList<Core> core;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();
			min_value = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1 && i != 0 && j != 0 && i!=N-1 && j !=N-1 ) {
						core.add(new Core(i, j));
					}
					map[i][j] = num;
				}
			}
			max_cnt=Integer.MIN_VALUE;
			dfs(0,map,0);
			System.out.println("#" + tc + " " + (min_value ==Integer.MAX_VALUE ? 0 : min_value));
		}
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int max_cnt;
	private static void dfs(int idx,int[][] map,int cnt) {
		if (idx == core.size()) {
			int total=0;
			for (int i =0 ;i <N ; i ++) {
				for (int j =0 ; j<N ; j++) {
					if (map[i][j]==2) total++; 
				}
			}
			if (total !=0 && cnt >=max_cnt ) {
				if (cnt == max_cnt) {
					min_value=Math.min(total, min_value);
				}else {
					min_value = total;
					max_cnt=cnt;
				}
			}
			return;
		}
		
		for (int i = 0 ; i <4 ; i ++) {
			if(check(map,core.get(idx).x,dx[i],core.get(idx).y,dy[i],i)) {
				int[][] map2 = new int[N][N];
				for (int j = 0; j <N ; j++) {
					map2[j]=map[j].clone();
				}
				int nx = core.get(idx).x+dx[i];
				int ny = core.get(idx).y+dy[i];
				while(nx >=0 && nx <N && ny >= 0 && ny<N) {
					map2[nx][ny]=2;
					nx+=dx[i];
					ny+=dy[i];
				}
				dfs(idx+1,map2,cnt+1);
			}
		}
		dfs(idx+1,map,cnt);
	}

	private static boolean check(int[][] map,int x,int dx, int y,int dy,int i) {
		if (i==0) {
			for (int j = 0; x+dx+j < N ; j ++) {
				if (map[x+dx+j][y] !=0) return false;
			}
		}
		if (i==1) {
			for (int j = 0; x+dx-j >=0 ; j ++) {
				if (map[x+dx-j][y] !=0) return false;
			}
		}
		if (i==2) {
			for (int j = 0; y+dy-j >=0 ; j ++) {
				if (map[x][y+dy-j] !=0) return false;
			}
		}
		if (i==3) {
			for (int j = 0; y+dy+j < N ; j ++) {
				if (map[x][y+dy+j] !=0) return false;
			}
		}
		return true;
	}
}
