package com.ssafy.recur.BOJ.sol14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// N일까지 완료 할 수 있는 상담만 해야함
	// 상담 기간중에 다른 상담은 불가능
	// 
	static int[][] consulting;
	static int N;
	static int max_value = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		consulting = new int[N][2];
		
		for (int i = 0 ; i <N ; i ++) {
			consulting[i]= Arrays.stream(br.readLine().split(" " )).mapToInt(Integer::parseInt).toArray();
		}
		
		comb(0,0);
		System.out.println(max_value);
	}
	
	static void comb(int start,int flag) {
		if (start >= N) {
			int total = 0;
			for (int i = 0 ; i < N ; i ++) {
				if ((flag & 1<<i)!=0) {
					total+=consulting[i][1];
				}
			}
			if (total > max_value) {
				max_value = total;
			}
			
			return;
		}
		
		for (int i = start ; i < N; i ++ ) {
			comb(i+consulting[i][0], i + consulting[i][0] > N ? flag : flag | 1<<i );
		}
	}
}
