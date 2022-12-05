package com.ssafy.recur.BOJ.sol17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리멘더링 {
	// 배열을 완탐 돌리고
	// 완탐돌려서 나온 구역이 서로 연결되 있을 경우
	// 값 갱신
	static int[] ward;
	static int N;
	static int min_value;
	static boolean[][] linked;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		 
		StringTokenizer st=  new StringTokenizer(br.readLine());
		linked = new boolean[N+1][N+1];
		ward = new int[N+1];
		for (int i =1 ; i < N+1 ; i ++) {
			ward[i]= Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < N+1 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			for (int j = 0; j< len; j++) {
				linked[i][Integer.parseInt(st.nextToken())]=true;
			}
		}
		min_value = Integer.MAX_VALUE;
		subset(1,0);
		System.out.println(min_value == Integer.MAX_VALUE ? -1 : min_value);
	}
	private static void subset(int idx,int flag) {
		if(idx == N+1) {
			int fir_num=0;
			int sec_num=0;
			ArrayList<Integer> fir = new ArrayList<>();
			ArrayList<Integer> sec = new ArrayList<>();
			for (int i =1 ; i <N+1 ; i ++) {
				if((flag & 1<<i)== 0) {
					fir.add(i);
					fir_num+=ward[i];
				}else {
					sec.add(i);
					sec_num+=ward[i];
				}
			}
			if(fir.size()==0 || sec.size()==0) return;
			if(bfs(fir) && bfs(sec)) {
				min_value=Math.min(min_value, Math.abs(fir_num-sec_num));
			}
			return;
		}
		
		subset(idx+1,flag);
		subset(idx+1,flag | 1<<idx);
	}
	private static boolean bfs(ArrayList<Integer> fir) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(fir.get(0));
		boolean[] check = new boolean[fir.size()];
		check[0]=true;
		while(!queue.isEmpty()) {
			int i =queue.poll();
			for (int j = 0 ; j <fir.size() ; j++) {
				if(linked[i][fir.get(j)] && !check[j]) {
					check[j]=true;
					queue.offer(fir.get(j));
				}
			}
		}
		
		for (int i = 0 ;i < check.length ; i ++) {
			if (!check[i]) return false;
		}
		return true;
	}
}
