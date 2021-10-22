package com.challenge.marsrover;;

public class Rover {

    private Plateau plateau;
    private Position position;
    private CardinalDirection facing;

    public void initiateRover(Plateau plateau, String roverCoords) throws Exception {

        String[] splittedRoverCoors = roverCoords.split(" ");
        if(splittedRoverCoors.length == 3 && Character.isLetter(splittedRoverCoors[2].charAt(0))) {
            int x = Integer.parseInt(splittedRoverCoors[0]);
            int y = Integer.parseInt(splittedRoverCoors[1]);

            CardinalDirection facing = ToFacing(splittedRoverCoors[2].toCharArray()[0]);
            Position pos = new Position(x, y);

            if (pos.IsValidCoords(plateau)) {
                this.plateau = plateau;
                this.position = pos;
                this.facing = facing;
            }
        } else {
            throw new Exception("Please enter correct coordinates for the rover");
        }

    }

    public String reportStatus() {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(reportPosition());
        return sb.toString();

    }

    public String reportPosition() {

        if (position != null && facing != null) {
            return position.toString() + " " + FromFacing(facing);
        } else {
            return "Not initialized yet";
        }

    }

    public void processInstructions(Instruction[] instructions) {

        for (Instruction i : instructions) {
            if (position != null && facing != null) {
                switch (i) {
                    case L: turnLeft(); break;
                    case M: moveForward(); break;
                    case R: turnRight(); break;
                }
            }
        }

    }

    private void turnLeft() {

        switch (facing) {
            case EAST: facing = CardinalDirection.NORTH; break;
            case NORTH: facing = CardinalDirection.WEST; break;
            case SOUTH: facing = CardinalDirection.EAST; break;
            case WEST: facing = CardinalDirection.SOUTH; break;
            default: throw new RuntimeException("Invalid Cardinal point");
        }

    }


    private void turnRight() {

        switch (facing) {
            case EAST: facing = CardinalDirection.SOUTH; break;
            case NORTH: facing = CardinalDirection.EAST; break;
            case SOUTH: facing = CardinalDirection.WEST; break;
            case WEST: facing = CardinalDirection.NORTH; break;
            default: throw new RuntimeException("Invalid Cardinal point");
        }

    }


    private void moveForward() {

        Position newPosition = position.moveForward(facing);
        if (newPosition.IsValidCoords(plateau)) {
            position = newPosition;
        }

    }


    private static CardinalDirection ToFacing(char heading) {

        switch (heading) {
            case 'N': return CardinalDirection.NORTH;
            case 'W': return CardinalDirection.WEST;
            case 'S': return CardinalDirection.SOUTH;
            case 'E': return CardinalDirection.EAST;
            default: throw new RuntimeException("Invalid Cardinal point");
        }

    }


    private static char FromFacing(CardinalDirection cardinalDirection) {

        switch (cardinalDirection) {
            case NORTH: return 'N';
            case WEST: return 'W';
            case SOUTH: return 'S';
            case EAST: return 'E';
            default: throw new RuntimeException("Invalid Heading");
        }

    }
}
