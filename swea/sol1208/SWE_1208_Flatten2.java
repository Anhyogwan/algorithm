package com.ssafy.recur.swea.sol1208;

import java.util.*;
import java.io.*;

public class SWE_1208_Flatten2 {
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("data/1208.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for (int t = 1; t < T + 1; t++) {
			int N = sc.nextInt();
			int[] box = new int[101];
			int minH = 100, maxH = 1;
			for(int i = 0; i < 100; i++) {
				int h = sc.nextInt();
				box[h]++;
				if(maxH < h) maxH = h;
				if(minH > h) minH = h;
			}
			while(N > 0 && minH < maxH - 1) {
				box[maxH]--;
				box[minH]--;
				
				box[maxH - 1]++;
				box[minH + 1]++;
				
				if(box[maxH] == 0)maxH--;
				if(box[minH] == 0)minH++;
				N--;
			}
			System.out.println("#" + t + " " + (maxH - minH));
		}
		
		
		sc.close();
		
	}
}
