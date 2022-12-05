package com.ssafy.recur.BOJ.sol2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int x, y;
	static int[][] pan;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		pan = new int[x][y];
		for (int i = 0; i < x; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < y; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs(0);

	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static void bfs(int cnt) {
		int resultCount=0;
		for (int i=0 ; i <x ; i++) {
			for (int j = 0; j<y; j++) {
				if(pan[i][j]==1) {
					resultCount++;
				}
			}
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0,0});
		boolean[][] visited = new boolean[x][y];
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int i = 0; i <4 ; i++) {
				int nx = temp[0]+dx[i];
				int ny = temp[1]+dy[i];
				if (nx >= x || nx <0 || ny >= y || ny <0 || pan[nx][ny]!=0 || visited[nx][ny]) continue;
				visited[nx][ny]=true;
				queue.offer(new int[] {nx,ny});
			}
		}
		for (int k = 0; k< x;k++) {
			for(int j =0; j<y ; j++) {
				if (visited[k][j]) {
					pan[k][j]=0;
					for(int i = 0; i <4 ; i++) {
						int nx = k+dx[i];
						int ny = j+dy[i];
						if (nx >= x || nx <0 || ny >= y || ny <0) continue;						
						pan[nx][ny]=0;
					}
				}
			}
		}
		boolean chk = false;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(pan[i][j]!=0) {
					chk=true;
					break;
				}
			}
		}
		if(!chk) {
			System.out.println(cnt+1);
			System.out.println(resultCount);
			return;
		}
		bfs(cnt +1);
	}
}
