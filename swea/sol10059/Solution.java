package com.ssafy.recur.swea.sol10059;

import java.io.*;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] str1 = br.readLine().split("");
			String num = str1[0] + str1[1];
			String num2 = str1[2] + str1[3];
			String[] list = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
			if (Arrays.asList(list).contains(num) && Arrays.asList(list).contains(num2)) {
				System.out.println("#" + tc + " AMBIGUOUS");
			}else if(Arrays.asList(list).contains(num) && Arrays.asList(list).contains(num2)==false) {
				System.out.println("#" + tc + " MMYY");
			}else if(Arrays.asList(list).contains(num2) && Arrays.asList(list).contains(num)==false) {
				System.out.println("#" + tc + " YYMM");
			}else {
				System.out.println("#" + tc + " NA");
			}
		}
	}
}
