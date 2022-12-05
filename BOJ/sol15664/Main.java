package com.ssafy.recur.BOJ.sol15664;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] numbers;
	static Set<String> set = new LinkedHashSet<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers= new int[N];
		numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(numbers);
		comb(0,0,0);
		for (String s : set)
			System.out.println(s);
	}
	private static void comb(int idx, int start,int flag) {
		if (idx == M) {
			String str1="";
			for (int i = 0 ; i <N ; i++) {
				if((flag & 1<<i) !=0)
					str1 +=numbers[i] + " ";
			}
			set.add(str1);
			return;
		}
		for (int i = start ; i <N ; i++) {
			comb(idx +1 ,i+1,flag | 1<<i);
		}
	}
	

}
