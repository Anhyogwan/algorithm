package com.ssafy.recur.BOJ.sol15663;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<ArrayList<Integer>> set = new LinkedHashSet<>();
	static ArrayList<int[]> result = new ArrayList<>();
	static int N,M;
	static int[] numbers;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(numbers);
		comb(0,new ArrayList<Integer>(),0);
		
		for (ArrayList<Integer> i : set) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	private static void comb(int idx,ArrayList<Integer> ans , int flag ) {
		if (idx == M) {
			set.add(ans);
			return;
		}
		for (int i = 0 ; i <N ; i++) {
			if ((flag & 1<<i) !=0) continue;
			ArrayList<Integer> ans_2 = new ArrayList<>();
			ans_2.addAll(ans);
			ans_2.add( numbers[i]);
			comb(idx +1 ,ans_2,flag | 1<<i);
		}
	}
	
	
}
