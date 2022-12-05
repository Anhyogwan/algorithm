package com.ssafy.recur.BOJ.sol1244;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_1244_스위치켜고끄기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("data/1244.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] data = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			data[i] = sc.nextInt();
		}
		int stuNum = sc.nextInt(); // 학생수
		int loc = 0;
		for(int i = 0; i < stuNum; i++) {
			switch(sc.nextInt()) {
			case 1: // 남학생
				loc = sc.nextInt();
				for(int j = loc; j < N; j += loc) {
					data[j] = data[j] == 0 ? 1:0;
				}
				break;
			case 2: // 여학생
				loc = sc.nextInt();
				int l = loc, r = loc;
				while(l >= 1 && r < N) {
					if(data[l] != data[r]) break;
					
					data[l] = data[l] == 0? 1:0;
					data[r] = data[r] == 0? 1:0;
					l--; r++;
					
				}
			}
		}
		for(int i = 1; i <= N; i++) {
			System.out.printf("%d%s", data[i], i % 20 == 0 ? "\n" : " ");
		}
		sc.close();
	}

}
