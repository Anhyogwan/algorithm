package com.ssafy.recur.BOJ.sol1342;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static ArrayList<String> result = new ArrayList<>();
	static char[] str1;
	static int cnt=0;
	public static void main(String[] args) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine().toCharArray();
		perm(0,new char[str1.length],0);
		bw.write(String.valueOf(cnt));
		bw.flush();
	}
	private static void perm(int idx, char[] cs,int flag) {
		if(idx == str1.length) {
			cnt++;
			return;
		}
		
		for (int i = 0 ; i < str1.length; i++) {
			if((flag & 1<<i) != 0 || (idx!= 0 && cs[idx-1] == str1[i])) continue;
			cs[idx]=str1[i];
			perm(idx+1,cs, flag | 1<<i);
		}
	}
}
