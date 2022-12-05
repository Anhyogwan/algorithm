package com.ssafy.recur.BOJ.sol1790;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberContinuation2 {
	// 1 ~ 9 까지는 1자리 9개
	// 10 ~ 99까지는 2자리 90
	// 100 ~ 999 까지는 3자리 900
	// 1000 ~ 9999 4자리 9000
	// 10000 ~ 99999 5자리 90000
	// 100000 ~ 999999 6자리 900000
	// 1000000 ~ 9999999 7자리 ...
	// 10000000 ~ 99999999 8자리 ...
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st= new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		long k = Integer.parseInt(st.nextToken());
//		long N_Count=0;
//		long Pre_Ncount=0;
//		int i = 1;
//		long j = 9;
//		long start = 1;
//		long l=0;
//		// 
//		while(N!=0) {
//			if (N/9==0) {
//				N_Count+=(i*N*start);
//			}else {
//				N_Count+= (i*j);
//			}
//			N/=10;
//			Pre_Ncount=N_Count;
//			if (N_Count > k) {
//				k=k-Pre_Ncount; //시작지점부터의 차이
//				l=(k/i)-1;
//				k%=i;
//				start+=l;
//				System.out.println(String.valueOf(start).charAt((int)k+1 >= i ? i-((int)k+1) : (int)k+1));
//				break;
//			}
//			start*=10;
//			i++;
//			j*=10;
//		}
//		if (N_Count < k) {
//			System.out.println(-1);
//		}
//	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long k = Integer.parseInt(st.nextToken());
		int i = 1;
		long j = 9;
		long start = 0;
		long l = 0;
		while (true) {
			if (k <= (i * j)) {
				l = (k-1) / i; // 2
				k = (k-1) % i; // 0
				break;
			}
			k -= (i * j);
			start+=j;
			i++;
			j *= 10;
		}
		start = (start +1) + l;
		//System.out.println(start);
		if (N < start) {
			System.out.println(-1);
		}else {
			System.out.println(String.valueOf(start).charAt((int)k));
		}
	}
}
