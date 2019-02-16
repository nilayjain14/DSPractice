package com.nilay;


public class RoverPosition {

    public static void main (String[] args){
        String coordinates = findRoverLocation("5 5","3 3 E","MMRMMRMRRM");
        System.out.println("final position = "+coordinates);
    }

    private static String findRoverLocation(String grid, String currentCoord, String signal) {
        String[] grid_mas = grid.split(" ");
        int grid_x = Integer.parseInt(grid_mas[0]);
        int grid_y = Integer.parseInt(grid_mas[1]);

        String[] currentCoord_mas = currentCoord.split(" ");
        int rover_x = Integer.parseInt(currentCoord_mas[0]);
        int rover_y = Integer.parseInt(currentCoord_mas[1]);
        String facing = currentCoord_mas[2];

        char[] signalSeq = signal.toCharArray();

        String finalCoordinate = newCoordinatesOfRover(rover_x,rover_y,facing,signalSeq);

        return finalCoordinate;
    }

    private static String newCoordinatesOfRover(int rover_x, int rover_y, String facing, char[] signalSeq) {
        for(char currentSignal: signalSeq){
            if(currentSignal == 'L'){
                facing = findNewRoverFacingIfL(facing);
            }
            else if(currentSignal == 'R'){
                facing = findNewRoverFacingIfR(facing);
            }
            else{
                if(facing.equals("N")){
                    rover_y++;
                }
                if(facing.equals("E")){
                    rover_x++;
                }
                if(facing.equals("W")){
                    rover_x--;
                }
                if(facing.equals("S")){
                    rover_y--;
                }
            }
        }
        return rover_x + " " + rover_y + " "+facing;
    }

    private static String findNewRoverFacingIfL(String currentFacing){
        String facing = "";
        if(currentFacing.equals("E")){
            facing = "N";
        }
        if(currentFacing.equals("N")){
            facing = "W";
        }
        if(currentFacing.equals("W")){
            facing = "S";
        }
        if(currentFacing.equals("S")) {
            facing = "E";
        }
        return facing;
    }

    private static String findNewRoverFacingIfR(String currentFacing){
        String facing = "";
        if(currentFacing.equals("E")){
            facing = "S";
        }
        if(currentFacing.equals("S")) {
            facing = "W";
        }
        if(currentFacing.equals("W")){
            facing = "N";
        }
        if(currentFacing.equals("N")){
            facing = "E";
        }
        return facing;
    }
}
