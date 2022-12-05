package com.ssafy.recur.BOJ.sol15657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb= new StringBuilder();
	static int N,M;
	static int[] num;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N];;
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i <N ; i ++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		comb(0,new int[M],0);
		System.out.println(sb);
	}
	
	static void comb(int idx,int[] result,int start) {
		if (idx==M) {
			for (int i = 0 ; i < M ; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i=start; i <N ; i ++) {
			result[idx] = num[i];
			comb(idx+1,result, i);
		}
	}
}
