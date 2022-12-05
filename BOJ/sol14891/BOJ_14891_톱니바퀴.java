package com.ssafy.recur.BOJ.sol14891;

import java.util.*;
import java.io.*;

public class BOJ_14891_톱니바퀴{
	static int K;
	static String[] one, two, three, four;
	static String temp;
	static int[][] change;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		one = br.readLine().split("");
		two = br.readLine().split("");
		three = br.readLine().split("");
		four = br.readLine().split("");
		K = Integer.parseInt(br.readLine());
		change = new int[K][2];
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = (Integer.parseInt(st.nextToken())) % 2;
			int change = Integer.parseInt(st.nextToken());
			if(num == 1) {
				even(change, one);
				even(change, three);
				odd(change, two);
				odd(change, four);
			}else {
				even(change, two);
				even(change, four);
				odd(change, one);
				odd(change, three);
				
			}
		}
		int sum = 0;
		if(one[0] == "1") sum += 1;
		if(two[0] == "1") sum += 2;
		if(three[0] == "1") sum += 4;
		if(four[0] == "1") sum += 8;
		System.out.println(sum);
		
		
	}
	//홀수
	private static void even(int num, String[] arr) {
		switch(num) {
		case -1:
			temp = arr[0];
			for(int i = 0; i < 7; i++) {
				arr[i] = arr[i + 1];
			}
			arr[7] = temp;
			break;
		case 1:
			temp = arr[7];
			for(int i = 6; i > 0; i--) {
				arr[i] = arr[i + 1];
			}
			arr[0] = temp;
			break;
		}
	}
	//짝수
	private static void odd(int num, String[] arr) {
		switch(num) {
		case 1:
			temp = arr[0];
			for(int i = 0; i < 7; i++) {
				arr[i] = arr[i + 1];
			}
			arr[7] = temp;
			break;
		case -1:
			temp = arr[7];
			for(int i = 6; i > 0; i--) {
				arr[i] = arr[i + 1];
			}
			arr[0] = temp;
			break;
		}
	}
}
