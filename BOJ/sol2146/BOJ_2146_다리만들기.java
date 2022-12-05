package com.ssafy.recur.BOJ.sol2146;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146_다리만들기 {
	static int[][] map;
	static int N;
	// 맵은 bfs 를 써서 다 체크
	// 체크한 대륙을 bfs탐색을 돌리면서 사방에 0이 존재하면 거기서 다른 대륙까지 bfs
	// 그 카운팅이 가장 낮은거 출력

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited= new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j])
					bfs1(i, j);
			}
		}
		System.out.println(min_value);
	}

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] visited; // 
	private static void bfs1(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> queue2= new LinkedList<>();
		queue.offer(new int[] { i, j });
		boolean[][] visited2 = new boolean[N][N];
		visited2[i][j]=true;
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			for (int k =0; k<4 ; k++) {
				int nx = temp[0]+dx[k];
				int ny = temp[1]+dy[k];
				if(nx <0 || nx >=N || ny <0 || ny>=N || visited[nx][ny]) continue;
				if(map[nx][ny]==1) {
					visited[nx][ny]=true;
					visited2[nx][ny]=true;
					queue.offer(new int[] {nx,ny});
				}else if(map[nx][ny]==0) {
					queue2.offer(new int[] {nx,ny});
				}
			}
		}
		while(!queue2.isEmpty()) {
			boolean[][] visited3= new boolean[N][N];
			for (int l =0;l<N;l++) {
				visited3[l]=visited2[l].clone();
			}
			int[] temp=queue2.poll();
		//	System.out.println(temp[0] +" " + temp[1]);
			bfs2(temp,visited3);
		}
	}
	// 만약 visited2가 체크가 안되있는 1에 도착했을 경우 그게 다리임
	static int min_value = Integer.MAX_VALUE;
	private static void bfs2(int[] temp2,boolean[][] visited2) {
		Queue<int[]> queue2= new LinkedList<>();
		int[] temp3 = {temp2[0],temp2[1],1};
		queue2.offer(temp3);
		visited2[temp2[0]][temp2[1]] =true;
		while (!queue2.isEmpty()) {
			int[] temp = queue2.poll();
			if((!visited2[temp[0]][temp[1]]) && map[temp[0]][temp[1]]==1) {
				//System.out.println("ㄱㄱㄱ" + temp[0] + " " + temp[1] + " " + temp[2]);
				min_value = Math.min(min_value, temp[2]);
				break;
			}
			for (int k =0; k<4 ; k++) {
				int nx2 = temp[0]+dx[k];
				int ny2 = temp[1]+dy[k];
				if(nx2 <0 || nx2 >=N || ny2 <0 || ny2 >=N || visited2[nx2][ny2]) continue;
				if(map[nx2][ny2]==0) {
					visited2[nx2][ny2]=true;
					queue2.offer(new int[] {nx2,ny2,temp[2]+1});
				}else {
					queue2.offer(new int[] {nx2,ny2,temp[2]});
				}
			}
		}
	}
}
