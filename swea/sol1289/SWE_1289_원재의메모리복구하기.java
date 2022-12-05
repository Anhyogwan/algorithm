package com.ssafy.recur.swea.sol1289;

import java.util.*;
import java.io.*;

public class SWE_1289_원재의메모리복구하기 {
	static int[] change = {1, 0};
	static int[] temp;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T + 1; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			arr = new int[s.length()];
			temp = new int[s.length()];
			int idx = 0;
			for(int i = 0; i < s.length(); i++) {
				arr[i] = s.charAt(i) - '0';
			}
			
			for(int i = 0; i < s.length(); i++) {
				if(arr[i] == 1) {
					idx = i;
					break;
				}
			}
			
			int cnt = 0;

			while(!(Arrays.equals(arr, temp))) {
				for(int i = idx; i < arr.length; i++) {
					if(arr[i] != temp[i]) {
						swich1(i);
						cnt++;
						break;
					}
				}
			}
					
			System.out.println("#" + t + " " + cnt);
		}
	}
	
	static void swich1(int num) {
		for(int i = num; i < temp.length; i++) {
			temp[i] = change[temp[i]];			
		}
	}

}
