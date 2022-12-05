package com.ssafy.recur.BOJ.sol15655;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] arr;
	static int M;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N=sc.nextInt();
		M=sc.nextInt();
		sc.nextLine();
		arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		comb(new int[M],0,0);
	}
	static void comb(int[] number,int idx,int start) {
		if(idx==M) {
			for (int i = 0 ; i < number.length ; i ++) {
				System.out.print(number[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i =start ; i<arr.length;i++) {
			number[idx]=arr[i];
			comb(number,idx+1,i+1);
		}
	}
}
