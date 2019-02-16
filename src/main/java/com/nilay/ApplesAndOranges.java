package com.nilay;

public class ApplesAndOranges {

    public static void main(String[] args) {

        int s = 37455;

        int t = 87275;

        int a = 35609;

        int b = 89610;

        int[] apples = new int[]{19736,19374,-68796,0};

        int[] oranges = new int[]{47398,-47196,31826,0};

        countApplesAndOranges(s, t, a, b, apples, oranges);
    }

    private static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {

        long inrange_apple_count = 0;
        long inrange_orange_count = 0;

        long[] apples_distance = new long[apples.length];
        long[] oranges_distance = new long[oranges.length];

        for(int i=0; i<apples.length ;i++){
            apples_distance[i] = apples[i] + a;
        }

        for(int i=0; i<oranges.length ;i++){
            oranges_distance[i] = oranges[i] + b;
        }

        for(int i=0; i<apples_distance.length; i++){
            if(apples_distance[i] >=s && apples_distance[i]<=t){
                inrange_apple_count++;
            }
        }

        for(int i=0; i<oranges_distance.length; i++){
            if(oranges_distance[i] >=s && oranges_distance[i] <=t){
                inrange_orange_count++;
            }
        }

        System.out.println(inrange_apple_count);
        System.out.println(inrange_orange_count);
    }
}
