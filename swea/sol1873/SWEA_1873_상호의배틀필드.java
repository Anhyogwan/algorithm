package com.ssafy.recur.swea.sol1873;

import java.util.*;
import java.io.*;

public class SWEA_1873_상호의배틀필드{
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("data/1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T + 1; t++) {
			String[] temp = br.readLine().split(" ");
			int H = Integer.parseInt(temp[0]);
			int W = Integer.parseInt(temp[1]);
			String[][] map = new String[H][W];
			int x = 0;
			int y = 0;

			for(int i = 0; i < H; i++) {
				String[] s = br.readLine().split("");
				for(int j = 0; j < W; j++) {
					map[i][j] = s[j];
					if(s[j].equals("^") || s[j].equals("v") || s[j].equals("<") || s[j].equals(">")) {
						x = i;
						y = j;
					}
				}
			}
			
			int cmdCnt = Integer.parseInt(br.readLine());
			String[] cmd = br.readLine().split("");
			
			for(int i = 0; i < cmdCnt; i++) {
				switch(cmd[i]) {
				case "U":
					map[x][y] = "^";
					if(x - 1 >= 0 && map[x - 1][y].equals(".")) {
						String tmp = map[x - 1][y];
						map[x - 1][y] = map[x][y];
						map[x][y] = tmp;
						x--;
					}
					break;
				case "D":
					map[x][y] = "v";
					if(x + 1 < H && map[x + 1][y].equals(".")) {
						String tmp = map[x + 1][y];
						map[x + 1][y] = map[x][y];
						map[x][y] = tmp;
						x++;
					}
					break;
				case "L":
					map[x][y] = "<";
					if(y - 1 >= 0 && map[x][y - 1].equals(".")) {
						String tmp = map[x][y - 1];
						map[x][y - 1] = map[x][y];
						map[x][y] = tmp;
						y--;
					}
					break;
				case "R":
					map[x][y] = ">";
					if(y +1  < W && map[x][y + 1].equals(".")) {
						String tmp = map[x][y + 1];
						map[x][y + 1] = map[x][y];
						map[x][y] = tmp;
						y++;
					}break;
				case "S":
					if(map[x][y].equals("^")) {
						for(int j = x - 1; j >= 0; j--) {
							if(map[j][y].equals("#")) {
								break;
							}
							if(map[j][y].equals("*")) {
								map[j][y] = ".";
								break;
							}
						}
					}
					else if(map[x][y].equals("v")) {
						for(int j = x + 1; j < H; j++) {
							if(map[j][y].equals("#")) {
								break;
							}
							if(map[j][y].equals("*")) {
								map[j][y] = ".";
								break;
							}
						}
					}
					else if(map[x][y].equals("<")) {
						for(int j = y - 1; j >= 0; j--) {
							if(map[x][j].equals("#")) {
								break;
							}
							if(map[x][j].equals("*")) {
								map[x][j] = ".";
								break;
							}
						}
					}
					else if(map[x][y].equals(">")) {
						for(int j = y + 1; j < W; j++) {
							if(map[x][j].equals("#")) {
								break;
							}
							if(map[x][j].equals("*")) {
								map[x][j] = ".";
								break;
							}
						}
					}
				
				}
			}
			System.out.print("#" + t + " ");
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
}
