package com.challenge.marsrover;

import java.util.Scanner;

import static java.lang.System.in;

public class MarsRover {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(in);
        System.out.print("Enter upper-right coordinates of the plateau:");
        String upperRightCoords = scanner.nextLine();
        Plateau plateau = initiatePlateau(upperRightCoords);

        int roverNo = 0;

        while (true) {
            roverNo++;

            System.out.print("Enter coordinates for Rover"+roverNo+":");
            String dropInfo = scanner.nextLine();

            try {
                Rover rover = placeRover(plateau, dropInfo);

                System.out.print("Enter instructions for Rover" + roverNo + " :");
                String instructions = scanner.nextLine();

                Instruction[] instructionsCollection = InstructionUtils.adaptInstructions(instructions);
                rover.processInstructions(instructionsCollection);
                System.out.println("Output: " + rover.reportStatus());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private static Rover placeRover(Plateau plateau, String roverCoords) throws Exception {

        Rover rover = new Rover();
        rover.initiateRover(plateau, roverCoords);
        return rover;

    }

    private static Plateau initiatePlateau(String upperRightCoords) throws Exception {

        String[] parts = upperRightCoords.split(" ");
        if (parts.length == 2) {
            return new Plateau(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else {
            throw new Exception("Please Enter correct input");
        }

    }

}
