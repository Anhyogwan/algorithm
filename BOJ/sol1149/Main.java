package com.ssafy.recur.BOJ.sol1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연습문제 2개
// RGB 거리
// 1로만들기

// i번째 집의색은 그냥 전거랑 안같으면 되는듯
// 각 N번쨰 집이 빨 초 파로 칯ㄹ하는 비용 주어짐
// 각 집마다 셋중에 하나 선택한 것중에 최소값을 갱신해가면서 구하라는 것 같음

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] house = new int[N][3];
		int[][] dp = new int[N+1][3];
		for (int i =0; i < N; i ++) {
			st = new StringTokenizer(br.readLine());
			house[i][0]=Integer.parseInt(st.nextToken());
			house[i][1]=Integer.parseInt(st.nextToken());
			house[i][2]=Integer.parseInt(st.nextToken());
		}
		
		for (int i =0 ;i <N ; i++) {
			if (i==0) {
				dp[i][0] = house[i][0];
				dp[i][1] = house[i][1];
				dp[i][2] = house[i][2];
			}else {
				dp[i][0]= Math.min(dp[i-1][1], dp[i-1][2]) + house[i][0];
				dp[i][1]= Math.min(dp[i-1][0], dp[i-1][2]) + house[i][1];
				dp[i][2]= Math.min(dp[i-1][0], dp[i-1][1]) + house[i][2];
			}
		}
		int result=Integer.MAX_VALUE;
		for (int i = 0; i <3 ; i++) {
			
			result=Math.min(result,dp[N-1][i]);
		}
		System.out.println(result);
		
		
	}
}
