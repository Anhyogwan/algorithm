package com.ssafy.recur.BOJ.sol9205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 경유지 : 편의점
	// 페스티벌이 열리는곳 : 목적지
	// 출발지 : 상근이
	static final int INF = Integer.MAX_VALUE;
	static int T,N,dist[][];
	static String result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1 ; tc<= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] start= new int[2];
			int[][] conven = new int[N+2][2];
			int[] end = new int[2];
			for (int i = 0; i <N+2 ; i ++) {
				st= new StringTokenizer(br.readLine());
				conven[i][0] = Integer.parseInt(st.nextToken());
				conven[i][1] = Integer.parseInt(st.nextToken());
			}
			result = "sad";
			bfs(conven,new boolean[N+2]);
			System.out.println(result);
		}
	}

	private static void bfs(int[][] conven,boolean visited[]) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(conven[0]);
		visited[0]=true;
		while(!queue.isEmpty()) {
			int[] temp =queue.poll();
			
			if (conven[N+1][0]== temp[0] && conven[N+1][1]==temp[1]) {
				result="happy";
				return;
			}
			
			for (int i =1; i <N+2 ; i++) {
				if(visited[i] || 1000<(Math.abs(temp[0] - conven[i][0]) + Math.abs(temp[1]-conven[i][1]))) continue;
				visited[i]=true;
				queue.offer(conven[i]);
			}
		}
		
	}
}
