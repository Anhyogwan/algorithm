package com.ssafy.recur.BOJ.sol17140;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 연산을 한번 진핼 할 때 마다
	static int[][] map;
	static int R,C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		int r=Integer.parseInt(st.nextToken())-1;
		int c=Integer.parseInt(st.nextToken())-1;
		int k=Integer.parseInt(st.nextToken());
		map = new int[101][101];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		R=3;
		C=3;
		int i = 0;
		int result = -1;
		while (i <= 100) {
//			for (int l = 0 ; l < map.length ; l ++) {
//				if (map[l][0]==0) break;
//				for (int j = 0 ; j < map[0].length ; j++ ) {
//					if (map[l][j]==0) break;
//					System.out.print(map[l][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			if (map[r][c] ==k) {
				result = i;
				break;
			}
			boolean check1 = false;
			if (R>= C) {
				check1 = true;
			}
			if (check1) {
				sortr();
			}else {
				sortc();
			}
			i++;
		}
		System.out.println(result);
	}

	private static void sortc() { // 열에 대해
		for (int i = 0; i < 101; i++) {
			Map<Integer, Integer> mapping = new LinkedHashMap<>();
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]);
			for (int j = 0; j < 101; j++) {
				if(map[j][i]==0) continue;
				mapping.put(map[j][i], mapping.get(map[j][i]) != null ? mapping.get(map[j][i]) + 1 : 1);
			}
			for (Entry<Integer, Integer> entry : mapping.entrySet()) {
				pq.offer(new int[] { entry.getKey(), entry.getValue() });
			}
			int idx = pq.size();
			int m = 0;
			R= Math.max(R, idx*2);
			while (!pq.isEmpty()) {
				if (m > 100 ) break;
				int[] temp = pq.poll();
				map[m++][i] = temp[0];
				map[m++][i] = temp[1];
			}
			for (int k = idx*2 ; k <101 ; k ++) {
				map[k][i] =0;
			}
		}
		return;
	}

	private static void sortr() { // 행에 대해
		for (int i = 0; i < 101; i++) {
			Map<Integer, Integer> mapping = new LinkedHashMap<>();
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]);
			for (int j = 0; j < 101; j++) {
				if(map[i][j]==0) continue;
				mapping.put(map[i][j], mapping.get(map[i][j]) != null ? mapping.get(map[i][j]) + 1 : 1);
			}
			for (Entry<Integer, Integer> entry : mapping.entrySet()) {
				pq.offer(new int[] { entry.getKey(), entry.getValue() });
			}
			int idx = pq.size();
			int m = 0;
			C= Math.max(C, idx*2);
			while (!pq.isEmpty()) {
				if (m > 100) break;
				int[] temp = pq.poll();
				map[i][m++] = temp[0];
				map[i][m++] = temp[1];
			}
			for (int k = idx*2 ; k <101 ; k ++) {
				map[i][k] =0;
			}
		}
		return;
	}
}
