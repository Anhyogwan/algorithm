package com.ssafy.recur.swea.sol1949;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N,K;
	static int[][] map;
	static int maxRootLength;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc =1 ;tc<=T; tc++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int max_point=0;
			map = new int[N][N];
			maxRootLength= Integer.MIN_VALUE;
			for(int i =0; i <N ; i++) {
				st= new StringTokenizer(br.readLine());
				for (int j = 0; j<N ; j++) {					
					int point = Integer.parseInt(st.nextToken());
					map[i][j]=point;
					max_point = Math.max(max_point,point);
				}
			}
			for (int i = 0; i <N ; i++) {
				for (int j =0 ; j<N ; j++) {
					if(map[i][j]==max_point)
						makeroot(i,j,1,false,new boolean[N][N],map);
				}
			}
			System.out.println("#" + tc + " " + maxRootLength);
		}
	}
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	// K 깊이만큼 반복문을 돌려서 깎고 K체크를 false를 주고 계속 dfs
	private static void makeroot(int x,int y,int idx,boolean kcheck,boolean[][] visited,int[][] map) {
		if (idx==1) {
			visited[x][y]= true;
		}
		
		for (int i = 0 ;i < 4 ; i++) {
			int nx = x +dx[i];
			int ny = y + dy[i];
			// 범위 안에 있고, 방문하지 않은 곳이고, map[nx][ny]보다 작으면
			if (isIn(nx,ny) && !visited[nx][ny] && map[x][y] >map[nx][ny]) {
				visited[nx][ny]=true;
				makeroot(nx,ny,idx+1,kcheck,visited,map);
				visited[nx][ny]=false;
			}else if(isIn(nx,ny) && !kcheck && !visited[nx][ny] && (map[x][y]==map[nx][ny] || map[x][y]>map[nx][ny]-K)){
				visited[nx][ny]=true;
				kcheck=true;
				int temp = map[nx][ny];
				map[nx][ny]=map[x][y]-1;
				makeroot(nx,ny,idx+1,kcheck,visited,map);
				kcheck=false;
				map[nx][ny]=temp;
				visited[nx][ny]=false;
			}
		}
		maxRootLength=Math.max(maxRootLength, idx);
	}
	private static boolean isIn(int nx, int ny) {
		if(nx< 0  || nx>=N || ny<0 || ny>=N) return false;
		return true;
	}
}
