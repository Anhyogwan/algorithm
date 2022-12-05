package com.ssafy.recur.swea.sol6781;

import java.io.*;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1;tc<=T;tc++) {
			int[] r = new int[10];
			int[] g = new int[10];
			int[] b = new int[10];
			String[] num = br.readLine().split("");
			String[] color = br.readLine().split("");
			for (int i=0; i<9;i++) {
				if (color[i].equals("R")) {
					r[Integer.parseInt(num[i])]++;
				}
				if (color[i].equals("G")) {
					g[Integer.parseInt(num[i])]++;
				}
				if (color[i].equals("B")) {
					b[Integer.parseInt(num[i])]++;
				}
			}
			int result=0;
			for (int i = 1 ; i <10;i++) {
				if (r[i]>=3) {
					r[i]-=3;
					result+=1;
				}
				if (g[i]>=3) {
					g[i]-=3;
					result+=1;
				}
				if (b[i]>=3) {
					b[i]-=3;
					result+=1;
				}
			}
			for (int i=3;i<10;i++) {
				if (r[i-2]>=1 && r[i-1]>=1 && r[i]>=1) {
                    while(r[i-2]!=0 && r[i-1] !=0 && r[i]!=0){
                    	r[i-2]-=1;
                        r[i-1]-=1;
                        r[i]-=1;
                        result+=1;
                    }

				}
				if (g[i-2]>=1 && g[i-1]>=1 && g[i]>=1) {
                   while(g[i-2]!=0 && g[i-1] !=0 && g[i]!=0){
                    	g[i-2]-=1;
                        g[i-1]-=1;
                        g[i]-=1;
                       result+=1;
                    }
					
				}
				if (b[i-2]>=1 && b[i-1]>=1 && b[i]>=1) {
                    while(b[i-2]!=0 && b[i-1] !=0 && b[i]!=0){
					b[i-2]-=1;
					b[i-1]-=1;
					b[i]-=1;
					result+=1;
                    }
				}
			}
			if (result >=3) {
				System.out.println("#" + tc + " Win");
			}else{
				System.out.println("#" + tc + " Continue");
			}
		
		}
	}
}
