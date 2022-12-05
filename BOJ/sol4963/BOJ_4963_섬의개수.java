package com.ssafy.recur.BOJ.sol4963;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4963_섬의개수 {
	static boolean[][] visited;
	static int w,h;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			w= Integer.parseInt(st.nextToken());
			h= Integer.parseInt(st.nextToken());
			if (w ==0 && h==0) break;
			map = new int[h][w];
			visited =new boolean[h][w];
			for (int i = 0; i < h ; i ++) {
				map[i]= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			int cnt=0;
			for (int i = 0; i <h ; i ++) {
				for (int j = 0; j <w ; j++) {
					if(map[i][j]==1 && !visited[i][j]) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	static int[] dx= {1,-1,0,0,-1,1,-1,1};
	static int[] dy= {0,0,1,-1,1,-1,-1,1};
	
	static void bfs(int x,int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x,y});
		visited[x][y]=true;
		while(!queue.isEmpty()) {
			int[] temp=queue.poll();
			x= temp[0];
			y= temp[1];
			for (int i = 0; i <8 ; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if (nx < 0 || nx >=h || ny <0 || ny>=w || visited[nx][ny] || map[nx][ny]==0) continue;
				visited[nx][ny]=true;
				queue.offer(new int[] {nx,ny});
			}
		}
	}
}
