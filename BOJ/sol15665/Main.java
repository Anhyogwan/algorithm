package com.ssafy.recur.BOJ.sol15665;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static int N, M;
	static int[] numbers;
	static Set<String> set = new LinkedHashSet<>();
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers= new int[N];
		for (int i = 0; i < N ; i++) {
			numbers[i]= sc.nextInt();
		}
		Arrays.sort(numbers);
		comb(0,new int[M]);
		for (String s : set)
			sb.append(s + "\n");
		System.out.println(sb);
	}

	private static void comb(int idx,int[] result) {
		if ( idx == M) {
			String str1="";
			for (int i = 0; i < M ; i ++) {
				str1 += result[i]+ " " ;
			}
			set.add(str1);
			return;
		}
		
		for (int i = 0 ; i < N ; i ++) {
			result[idx] = numbers[i];
			comb(idx +1,result);
		}
	}
	
}
