package com.challenge.marsrover;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public boolean isEqual(Position other) {

        return x == other.x && y == other.y;

    }

    @Override
    public String toString() {

        return x + " " + y;

    }

    public boolean IsValidCoords(Plateau p) {

        if (x < 0 || x > p.getxCoordinate()) { return false; }

        if (y < 0 || y > p.getyCoordinate()) { return false; }

        return true;

    }

    public Position moveForward(CardinalDirection cardinalDirection) {

        switch (cardinalDirection) {
            case EAST: return new Position(x + 1, y);
            case NORTH: return new Position(x, y + 1);
            case SOUTH: return new Position(x, y - 1);
            case WEST: return new Position(x - 1, y);
            default: throw new RuntimeException("Invalid Position");
        }

    }

}
