package com.ssafy.recur.swea.sol1247;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로 { // 20504kb 1384ms
	static int[] comdist; // 회사 <->고객 거리
	static int[] homedist; // 집 <->고객 거리
	static int[][] cldist; // 고객<->고객 거리
	static int[] numbers;
	static boolean[] isSelected;
	static int N, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine()); // 고객의 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			Point company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Point home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Point[] client = new Point[N];
			for(int n=0; n<N; n++) { // N명의 고객 좌표
				client[n] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 거리 계산
			comdist = new int[N];
			homedist = new int[N];
			cldist = new int[N][N];
			for(int i=0; i<N; i++) {
				comdist[i] = Math.abs(company.x-client[i].x)+Math.abs(company.y-client[i].y);
				homedist[i] = Math.abs(home.x-client[i].x)+Math.abs(home.y-client[i].y);
				for(int j=i+1; j<N; j++) {
					cldist[i][j] = cldist[j][i] = Math.abs(client[i].x-client[j].x)+Math.abs(client[i].y-client[j].y);
				}
			}
			
			numbers = new int[N];
			isSelected = new boolean[N];
			search(0,0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	private static void search(int cnt,int dist,int c) {
		if(cnt == N) {
			// 순열이 하나 생성되었을 때 최단 거리 탐색
			dist += homedist[c];
			if(dist < ans) ans = dist;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			search(cnt+1,dist+=cldist[i][i+1],c);
			isSelected[i] = false;
		}
	}
}