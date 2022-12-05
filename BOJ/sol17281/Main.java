package com.ssafy.recur.BOJ.sol17281;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int max_value=Integer.MIN_VALUE;
	public static void main(String[] args) {
		int[] order= new int[9];
		boolean[] check = new boolean[10];
		check(order,check,0);
	}
	
	static void check(int[] order,boolean[] check,int idx) {
		if (idx==3) {
			check[4]=true;
			order[3]=4;
			check(order,check,idx+1);
			return;
		}
		
		if (idx == 9) {
			baseball(order);
			return;
		}
		for (int i=1 ; i <10 ; i ++) {
			if (i==4) continue;
			if (check[i]==true) continue;
			check[i]=true;
			order[idx]=i;
			check(order,check,idx+1);
			check[i]=false;
		}
	}
	static void baseball(int[] order) {
		
	}
}
