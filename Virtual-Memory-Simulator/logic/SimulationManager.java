package org.example.logic;

import com.sun.source.doctree.SummaryTree;
import org.example.model.MyPMTable;
import org.example.model.MyPageTable;
import org.example.model.MyTLB;

public class SimulationManager {
    public int physicalPageSize;
    public int tlbSize;
    public int addressLength;
    public String[] instructionArray;
    MyTLB tlb;
    MyPMTable pmTable;
    MyPageTable pageTable;
    public SimulationManager(int physicalPage,int tlbSize,String instuction,String[] array,MyTLB tlb,MyPageTable pageTable,MyPMTable pmTable,int adressLength)
    {
        this.instructionArray=array;
        this.physicalPageSize=physicalPage;
        this.tlbSize=tlbSize;
        this.addressLength=adressLength;

        this.tlb = tlb;
        this.pmTable = pmTable;
        this.pageTable = pageTable;
    }

    public String hexToBinary(String hex, int binaryLength) {
        if (hex=="") {
            return "";
        }
        else {

            StringBuilder binary = new StringBuilder();

            String[] hexToBinaryMap = {
                    "0000", "0001", "0010", "0011",
                    "0100", "0101", "0110", "0111",
                    "1000", "1001", "1010", "1011",
                    "1100", "1101", "1110", "1111"
            };

            for (char hexChar : hex.toCharArray()) {
                if (!Character.isDigit(hexChar) && (hexChar < 'A' || hexChar > 'F')) {
                    throw new IllegalArgumentException("Invalid hexadecimal character: " + hexChar);
                }

                if (Character.isDigit(hexChar)) {
                    binary.append(hexToBinaryMap[Integer.parseInt(String.valueOf(hexChar))]);
                } else {
                    binary.append(hexToBinaryMap[Character.toUpperCase(hexChar) - 'A' + 10]);
                }

            }

            int currentLength = binary.length();
            if (currentLength < binaryLength) {
                binary.insert(0, "0".repeat(binaryLength - currentLength));
            } else if (currentLength > binaryLength) {
                binary.delete(0, currentLength - binaryLength);
            }
            return binary.toString();
        }
    }
    public void runSimulation() {
        System.out.println("Binary representations of instructions:");

        for (int i = 0; i < instructionArray.length; i++) {
            String decimalInstruction = instructionArray[i];
            String binaryRepresentation = hexToBinary(decimalInstruction,addressLength);

            System.out.println("Instruction " + i + ": Decimal - " + decimalInstruction + ", Binary - " + binaryRepresentation);
        }
    }

    public String binaryToHex(String s) {
        if (s != null && !s.isEmpty()) {
            int decimalValue = Integer.parseInt(s, 2);
            String hexString = Integer.toHexString(decimalValue);
            return hexString;
        } else {
            throw new IllegalArgumentException("Invalid binary string");
        }
    }
}
