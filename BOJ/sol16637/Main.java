package com.ssafy.recur.BOJ.sol16637;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	//괄호쌍의 부분집합문제
	static String[] num_list;
	static int max_value = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		num_list = br.readLine().split("");
		check(num_list,0,0,3);
		bw.write(String.valueOf(max_value));
		bw.flush();
	}
	static void check(String[] result,int idx,int x,int y) {
		if (y > result.length) {
			int total=Integer.valueOf(result[0]);
			for(int i = 1; i <result.length-1;i+=2) {
				switch(result[i]) {
				case "+":
					total+=Integer.valueOf(result[i+1]);
					continue;
				case "-":
					total-=Integer.valueOf(result[i+1]);
					continue;
				case "*":
					total*=Integer.valueOf(result[i+1]);
					continue;
				}
			}
			if(max_value<total) {
				max_value=total;
			}
			return;
		}
		check(result,idx+1,x+2,y+2);
		String[] result2=calc(result,x);
		check(result2,idx+1,x+2,y+2);
	}
	static String[] calc(String[] result,int x) {
		String[] num_list_2= new String[result.length-2];
		for(int i = 0 ; i <x ; i++) {
			num_list_2[i]=result[i];
		}
		int a = Integer.valueOf(result[x]);
		int b = Integer.valueOf(result[x+2]);
		switch(result[x+1]) {
		case "+":
			num_list_2[x]=String.valueOf(a+b);
			break;
		case "-":
			num_list_2[x]=String.valueOf(a-b);
			break;
		case "*":
			num_list_2[x]=String.valueOf(a*b);
			break;
		}
		for(int i = x+1 ; i <num_list_2.length ; i++) {
			num_list_2[i]=result[i+2];
		}
		return num_list_2;
	}
}
