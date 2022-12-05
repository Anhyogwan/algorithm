package com.ssafy.recur.swea.sol2819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,1,-1};
	static Set<String> set;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T ; tc++ ) {
			map = new int[4][4];
			set = new LinkedHashSet<>();
			for (int i = 0 ; i <4 ; i ++) {
				st= new StringTokenizer(br.readLine());
				for (int j =0 ; j < 4; j ++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int[] result=new int[7];
			for (int i = 0; i<4; i ++) {
				for (int j = 0; j<4 ; j++) {
					result[0]= map[i][j];
					dfs(1,i,j,result.clone());
				}
			}
			System.out.println("#" + tc + " " + set.size());
		}
				
	}
	static void dfs(int idx,int x,int y,int[] result) {
		if(idx == 7) {
			set.add(Arrays.toString(result));
			return;
		}
		for (int i = 0 ; i <4 ; i ++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if (nx <0 || nx>=4 || ny <0 || ny>=4 ) continue;
			result[idx]=map[nx][ny];
			dfs(idx+1,nx,ny,result);
		}
	}
}
