package com.ssafy.recur.BOJ.sol3040;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_3040_백설공주 {

	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] dwarf;
	public static void main(String[] args) throws Exception{
		dwarf = new int[9];
		for (int i = 0 ; i < 9 ; i ++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		check(0,0,0);
	}
	static void check(int flag , int idx,int start) throws IOException {
		if (idx ==7) {
			int total=0;
			for (int i=0; i <9 ; i ++) {
				if((flag&1<<i) ==0 ) continue;
				total+=dwarf[i];
			}
			if (total==100) {
				for (int i=0; i <9 ; i ++) {
					if((flag&1<<i) ==0 ) continue;
					bw.write(String.valueOf(dwarf[i]) + "\n");
				}
				bw.flush();
			}
			return;
		}
		for (int i = start; i <9 ; i ++) {
			check(flag | 1<<i,idx+1,i+1);
		}
	}
}
