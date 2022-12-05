package com.ssafy.recur.swea.sol14510;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N,max_length,min_day;
	static int[] tree;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T =Integer.parseInt(br.readLine());
		for (int tc=1 ;tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			st= new StringTokenizer(br.readLine());
			max_length=Integer.MIN_VALUE;
			min_day=Integer.MAX_VALUE;
			tree= new int[N];
			for(int i = 0; i< N ; i++) {
				int num = Integer.parseInt(st.nextToken());
				tree[i]=num;
				max_length=Math.max(max_length, num);
			}
			
			subset(0,1,tree.clone());
			System.out.println("#" + tc + " " + min_day);
		}
	}
	private static void subset(int idx, int day, int[] tree2) {
		if(idx>= tree2.length || day>min_day) {
			min_day = Math.min( min_day,day==1 ? 0 : day-1);
			return;
		}
		
		if(day %2 ==1 && tree2[idx]<max_length) {
			if(tree2[idx]+2==max_length) {
				for(int i = 0; i <tree2.length;i++) {
					if (tree2[i]<max_length && i != idx) {
						tree2[idx]+=2;
						tree2[i]+=1;
						subset(i,day +2 , tree2);
						tree2[idx]-=2;
						tree2[i]-=1;
						return;
					}
				}
				tree2[idx]+=2;
				subset(idx+1,day +1 , tree2);
				tree2[idx]-=2;
				return;
			}
			tree2[idx]+=1;
			subset(idx,day+1,tree2);
			tree2[idx]-=1;
		}else if(tree2[idx]+2 <= max_length) {
			tree2[idx]+=2;
			subset(idx,day +1 , tree2);
			tree2[idx]-=2;
		}else {
			subset(idx+1,day,tree2);
		}
	}
}
