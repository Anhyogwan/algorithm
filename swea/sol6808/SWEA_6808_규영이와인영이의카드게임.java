package com.ssafy.recur.swea.sol6808;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_6808_규영이와인영이의카드게임 {
	static int wCnt, lCnt, kSum, iSum;
	static ArrayList<Integer> kyu;
	static ArrayList<Integer> in;
	static int[] temp;
	static boolean[] isChecked;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			kyu = new ArrayList<>();
			in = new ArrayList<>();
			temp = new int[9];
			isChecked = new boolean[9];
			for(int i = 1; i <= 18; i++) {
				in.add(i);
			}
			for(int i = 0; i < 9; i++) {
				int n = sc.nextInt();
				kyu.add(n);
				in.remove(Integer.valueOf(n));
			}
			
			form(0);
			System.out.println("#" + t + " " + wCnt + " " + lCnt);
			wCnt = 0;
			lCnt = 0;
			
		}
	}
	
	private static void form(int cnt) {
		if(cnt == 9) {
			for(int i = 0; i <9; i++) {
				if(kyu.get(i) > temp[i]) {
					kSum += kyu.get(i) + temp[i];
				}
				else if(kyu.get(i) < temp[i]) {
					iSum += kyu.get(i) + temp[i];
				}
			}
			if(kSum > iSum) wCnt++;
			else if(kSum < iSum) lCnt++;
			kSum = 0;
			iSum = 0;
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(isChecked[i]) continue;
			isChecked[i] = true;
			temp[cnt] = in.get(i);
			form(cnt + 1);
			isChecked[i] = false;
		}
	}
}
