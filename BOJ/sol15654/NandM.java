package com.ssafy.recur.BOJ.sol15654;

import java.util.Arrays;
import java.util.Scanner;

public class NandM {
	static int[] arr;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		int M= sc.nextInt();
		sc.nextLine();
		arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		check2(new int[M],M,0,new boolean[N]);
	}
	
	static void check2(int[] number,int M,int idx,boolean[] check) {
		if (M==idx) {
			for (int i = 0 ; i<number.length; i++) {
				System.out.print(number[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 0 ; i <N ; i ++) {
			if(check[i]) continue;
			check[i]=true;
			number[idx]=arr[i];
			check2(number,M,idx+1,check);
			check[i]=false;
		}
	}
}
