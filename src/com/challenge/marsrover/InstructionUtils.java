package com.challenge.marsrover;

import java.util.ArrayList;

public class InstructionUtils {

        public static Instruction[] adaptInstructions(String instructionString) throws Exception {

            ArrayList<Instruction> result = new ArrayList<>();

        for (char c: instructionString.toCharArray()) {
            switch (c) {
                case 'L': result.add(Instruction.L); break;
                case 'M': result.add(Instruction.M); break;
                case 'R': result.add(Instruction.R); break;
                default: throw new Exception("Please provide correct formatted instructions");
            }
        }

        return result.toArray(new Instruction[result.size()]);

    }

}
