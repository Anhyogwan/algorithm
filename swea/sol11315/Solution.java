package com.ssafy.recur.swea.sol11315;

import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		outer: for (int tc = 1; tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			char[][] pan = new char [N][N];
			for (int i=0;i<N;i++) {
				pan[i]=br.readLine().toCharArray();
			}
			for (int i=0;i<N;i++) {
				for (int j =0;j<N;j++) {
					if (pan[i][j]=='o') {
						boolean result =check(pan,i,j);
						if (result) {
							System.out.println("#" + tc + " YES");
							continue outer;
						}
					}
				}
			}
			System.out.println("#" + tc + " NO");
		}
	}
	public static boolean check(char[][] pan,int x ,int y) {
		for (int i =1;i<5;i++) {
			if (x+i>=pan.length) break;
			if (pan[x+i][y]=='.') break;
			if (i==4 && pan[x+i][y]=='o') return true;
		}
		for (int i =1;i<5;i++) {
			if (x+i>=pan.length || y-i<0) break;
			if (pan[x+i][y-i]=='.') break;
			if (i==4 && pan[x+i][y-i]=='o') return true;
		}
		for (int i =1;i<5;i++) {
			if (y+i>=pan.length) break;
			if (pan[x][y+i]=='.') break;
			if (i==4 && pan[x][y+i]=='o') return true;
		}
		for (int i =1;i<5;i++) {
			if (x+i>=pan.length || y+i>=pan.length) break;
			if (pan[x+i][y+i]=='.') break;
			if (i==4 && pan[x+i][y+i]=='o') return true;
		}
		return false;
	}
}
