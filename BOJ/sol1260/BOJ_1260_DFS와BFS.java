package com.ssafy.recur.BOJ.sol1260;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS {
	static boolean[] visited;
	static int[][] list;
	static int N,M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		list = new int[1001][1001];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num =Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			list[num][num2]= list[num2][num]=1;
		}
		visited = new boolean[1001];
		dfs(start);
		System.out.println();
		visited = new boolean[1001];
		bfs(start);
	}

	static void dfs(int cur) {
		visited[cur] = true;
		System.out.print(cur + " ");
		for (int i = 1; i <list[cur].length ; i++) {
			if (visited[i]==false && list[cur][i]!=0)
				dfs(i);
		}
	}

	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(start);
		while (!queue.isEmpty()) {
			int num = queue.poll();
			if (!visited[num]) {
				visited[num] = true;
				System.out.print(num + " ");
				for (int i = 1; i < list[num].length ; i ++) {
					if ( !visited[i] && list[num][i]==1) {
						queue.offer(i);
					}
				}
			}
		}
	}
}
