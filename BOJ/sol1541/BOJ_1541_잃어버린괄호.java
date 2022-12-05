package com.ssafy.recur.BOJ.sol1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541_잃어버린괄호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str1 = br.readLine().split("-");
		int result=0;
		String[] str2;
		for (int i = 0 ; i < str1.length; i++) {
			str2 = str1[i].split("\\+");
			int num=0;
			for (int j =0 ;j<str2.length ; j++ ) {
				num += Integer.parseInt(str2[j]);
			}
			if(i==0 ) {
				result +=num;
				continue;
			}
			result-= num;
		}
		System.out.println(result);
	}
}
