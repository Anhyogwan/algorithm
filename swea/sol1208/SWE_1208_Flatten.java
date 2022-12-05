package com.ssafy.recur.swea.sol1208;

import java.util.*;
import java.io.*;

public class SWE_1208_Flatten {
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("data/1208.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t < 11; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] arr = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int cnt = 0;
			while(cnt < N) {
				Arrays.sort(arr);
				arr[0]++;
				arr[99]--;
				cnt++;
			}

			Arrays.sort(arr);
			int num = arr[99] - arr[0];
			System.out.println("#" + t + " " + num);
		}
		
	}
}
