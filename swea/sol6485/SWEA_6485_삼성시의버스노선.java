package com.ssafy.recur.swea.sol6485;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_6485_삼성시의버스노선 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int[][] bus = new int[N][2];
			for(int i = 0; i < N; i++) {
				bus[i][0] = sc.nextInt();
				bus[i][1] = sc.nextInt();
			}
			int P = sc.nextInt();
			int[] C = new int[P];
			int[] temp = new int[5000];
			
			for(int i = 0; i < P; i++) {
				C[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = bus[i][0]-1; j < bus[i][1]; j++) {
					temp[j]++;
				}
			}
			System.out.print("#" + t + " ");
			for(int i = 0; i < P; i++) {
				System.out.print(temp[C[i]-1] + " ");
			}
			System.out.println();
			
		}
	}
}
