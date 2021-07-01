import java.io.*;
import java.util.*;

public class Main {
	int memory[] = new int[1000];
	int register[] = new int[32];
	String path;
	File myObj ;
	int length = 0;
	static Scanner myReader;

	public Main(String path) {
		this.path = path;
		myObj = new File(path);
		initializeEmpty(memory);
		initializeEmpty(register);
	}

	public void initializeEmpty(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = -100;
		}
	}

	public void printNonEmpty(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -100)
				System.out.println("At index " + i + " Value: " + array[i]);
		}
	}

	public int getRegisterIndex(String registerName) {
		int result = 0;

		if (registerName.equals("$zero"))
			return 0;
		if (registerName.equals("$at"))
			return 1;

		if (registerName.equals("$v0"))
			return 2;

		if (registerName.equals( "$v1"))
			return 3;

		if (registerName.equals("$a0"))
			return 4;

		if (registerName.equals("$a1"))
			return 5;

		if (registerName.equals("$a2"))
			return 6;

		if (registerName.equals("$a3"))
			return 7;

		if (registerName.equals("$t0"))
			return 8;

		if (registerName.equals("$t1"))
			return 9;

		if (registerName.equals("$t2"))
			return 10;

		if (registerName.equals("$t3"))
			return 11;

		if (registerName.equals("$t4"))
			return 12;

		if (registerName.equals("$t5"))
			return 13;

		if (registerName.equals("$t6"))
			return 14;

		if (registerName.equals( "$t7"))
			return 15;

		if (registerName.equals( "$s0"))
			return 16;

		if (registerName.equals("$s1"))
			return 17;

		if (registerName.equals("$s2"))
			return 18;

		if (registerName.equals("$s3"))
			return 19;

		if (registerName.equals("$s4"))
			return 20;

		if (registerName.equals("$s5"))
			return 21;

		if (registerName.equals("$s6"))
			return 22;

		if (registerName.equals("$s7"))
			return 23;

		if (registerName.equals("$t8"))
			return 24;

		if (registerName.equals( "$t9"))
			return 25;

		return result;
	}

	public void runInstruction(String instruction) throws FileNotFoundException {
		String[] parts = instruction.split(" ");
		if (parts[0].equals("ADD")) {
			String[] operands = parts[1].split(",");
			int indexOutput = getRegisterIndex(operands[0]);
			int indexOp1 = getRegisterIndex(operands[1]);
			int indexOp2 = getRegisterIndex(operands[2]);

			register[indexOutput] = register[indexOp1] + register[indexOp2];
			printNonEmpty(register);
			System.out.println("--------Instruction Done---------");
		} 
		
		if (parts[0].equals("ADDI")) {

			String[] operands = parts[1].split(",");
			int indexOutput = getRegisterIndex(operands[0]);
			int indexOp1 = getRegisterIndex(operands[1]);
			int immediate = Integer.parseInt(operands[2]);

			register[indexOutput] = register[indexOp1] + immediate;
			printNonEmpty(register);
			System.out.println("--------Instruction Done---------");
		}
		


			if (parts[0].equals("SUB")) {
				String[] operands = parts[1].split(",");
				int indexOutput = getRegisterIndex(operands[0]);
				int indexOp1 = getRegisterIndex(operands[1]);
				int indexOp2 = getRegisterIndex(operands[2]);

				register[indexOutput] = register[indexOp1] - register[indexOp2];
				printNonEmpty(register);
				System.out.println("--------Instruction Done---------");
			} 
		

		if (parts[0].equals("BEQ")) {
			
				String[] operands = parts[1].split(",");
				int indexOp1 = getRegisterIndex(operands[0]);
				int indexOp2 = getRegisterIndex(operands[1]);

				int indexOutput = Integer.parseInt(operands[2]);/////////////////////////////////

				int count = 0;
				if( register[indexOp1] == register[indexOp2]) {
					length += indexOutput;
					myReader.close();
					myReader = new Scanner(myObj);
					
					while (count < length) {
						myReader.nextLine();
						count++;
					}
					length -= 1;
					
					
				}
				
				printNonEmpty(register);
				System.out.println("--------Instruction Done---------");
			
		}
		
		

			if (parts[0].equals("LW")) {
				String[] operands = parts[1].split(",");

				String[] s = operands[1].split("\\(");
				operands[1] = s[1].substring(0, 3);

				int indexOp1 = getRegisterIndex(operands[0]);
				int indexOp2 = getRegisterIndex(operands[1]);
				int constant = getRegisterIndex(s[0]); 
				register[indexOp1] = memory[register[indexOp2]+4*constant]; /////////////////////
				printNonEmpty(register);
				System.out.println("--------Instruction Done---------");
			}
			if (parts[0].equals("SW")) {
				String[] operands = parts[1].split(",");

				String[] s = operands[1].split("\\(");
				operands[1] = s[1].substring(0, 3);

				int indexOp1 = getRegisterIndex(operands[0]);
				int indexOp2 = getRegisterIndex(operands[1]);
				int constant = getRegisterIndex(s[0]); 
				memory[register[indexOp2]+4*constant]=register[indexOp1]; /////////////////////
				printNonEmpty(register);
				System.out.println("--------Instruction Done---------");
			}


	}
	public void runProgram() {
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				runInstruction(data);
				length++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main run = new Main("src/program.txt");

		run.runProgram();

	}
}