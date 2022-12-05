package com.ssafy.recur.swea.sol9778;

import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] cardList = new int[12];
		outer: for (int tc = 1; tc <= T; tc++) {
			int score = 0;
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < cardList.length; i++)
				if (i != 0 || i != 1)
					cardList[i] = 4;
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(br.readLine());
				cardList[num] -= 1;
				score += num;
			}
			int cnt = 0;
			for (int i = 2; i < cardList.length; i++) {
				if (score + i < 21) {
					if (cnt==0) cnt++;
					cnt*=cardList[i];
				}
				if (cnt > (52-N)/2) {
					System.out.println("#" +tc+" GAZUA");
					continue outer;
				}
			}
			System.out.println("#" + tc +" STOP");
		}
	}
}
