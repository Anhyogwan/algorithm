package com.ssafy.recur.swea.sol1249;

import java.util.*;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Pair{
    int first,second;
    public Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
}
class Solution{
    public static void main(String args[]) throws Exception{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(sc.readLine());
        for (int tc=1;tc<=T;tc++){
            int N=Integer.parseInt(sc.readLine());
            int[][] pan = new int[N][N];
            int[][] dp=new int[N][N];
            for (int i=0;i<N;i++){
                String[] temp=sc.readLine().split("");
                Arrays.fill(dp[i],999);
                for (int j=0;j<N;j++){
                    pan[i][j]=Integer.parseInt(temp[j]);
                }
            }
             
            dp[0][0]=0;
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0,0));
            while(queue.size()>0){
                Pair data =queue.poll();
                int x=data.first;int y=data.second;
                if (x-1>=0) {
                    if (dp[x-1][y]> pan[x-1][y]+dp[x][y]) {
                        dp[x-1][y]=pan[x-1][y]+dp[x][y];
                        queue.add(new Pair(x-1,y));
                    }
                }
                if (y-1>=0) {
                    if (dp[x][y-1]> pan[x][y-1]+dp[x][y]) {
                        dp[x][y-1]=pan[x][y-1]+dp[x][y];
                        queue.add(new Pair(x,y-1));
                    }
                }
                if (x+1<N) {
                    if (dp[x+1][y]> pan[x+1][y]+dp[x][y]) {
                        dp[x+1][y]=pan[x+1][y]+dp[x][y];
                        queue.add(new Pair(x+1,y));
                    }
                }
                if (y+1<N) {
                    if (dp[x][y+1]> pan[x][y+1]+dp[x][y]) {
                        dp[x][y+1]=pan[x][y+1]+dp[x][y];
                        queue.add(new Pair(x,y+1));
                    }
                }
                }
                 
           System.out.println("#"+ tc + " " + dp[N-1][N-1]);
             
        }
    }
}
