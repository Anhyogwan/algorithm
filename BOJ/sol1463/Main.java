package com.ssafy.recur.BOJ.sol1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	// x가 3으로 나누어떨어지면,3으로 나누기
	// 2로 나누어 떨어지면 2로 나누기
	// 1을 뺀다
	// 최소값 -> 0보다 크고 가장 작은값
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp=new int[N+1];
		int count =0;
		for (int i =2 ; i <= N ; i++) {
			dp[i] = dp[i-1]+1;
			if(i%2 ==0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
			if(i%3 ==0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
		}
		System.out.println(dp[N]);
	}
}
