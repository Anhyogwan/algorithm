package com.ssafy.recur.swea.sol3975;

import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1 ; tc<=T;tc++) {
			String[] winRate = br.readLine().split(" ");
			double[] winRate_2= new double[4];
			for (int i =0 ; i < winRate.length;i++) winRate_2[i]=Integer.parseInt(winRate[i]); 
			if(winRate_2[0]/winRate_2[1] > winRate_2[2]/winRate_2[3]){
				System.out.println("#" + tc + " ALICE");
			}else if(winRate_2[0]/winRate_2[1] < winRate_2[2]/winRate_2[3]){
				System.out.println("#" + tc + " BOB");
			}else {
				System.out.println("#" + tc + " DRAW");
			}
		}
	}
}
