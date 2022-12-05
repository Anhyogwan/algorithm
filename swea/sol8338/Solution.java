package com.ssafy.recur.swea.sol8338;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T= Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			int result=0;
			st = new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				int num=Integer.parseInt(st.nextToken());
				if (result+num>result*num) {
					result+=num;
				}else {
					result*=num;
				}
			}
			System.out.println("#"+tc +" "+ result);
		}
	}
}
