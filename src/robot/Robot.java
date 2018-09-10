package robot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

public class Robot {
	
	//Class variables
	private int[] currentPosition;
	
	//Class gets and sets
	public int[] getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(int[] currentPosition) {
		this.currentPosition = currentPosition;
	}	
	
	//Class constructor
	public Robot(int[] currentPosition){
		this.setCurrentPosition(currentPosition);
	}
	
	//Main function
	public static void main (String[] args) {
		////Default values-------
		//This are the values that the program uses in case the user doesn't specify any parameters 
		int[] defaultBoardSize = {5,5};
		int[] defaultCurrentPosition = {1,1};
		String defaultInstructionsFilePath = System.getProperty("user.dir")+"\\instructions.txt";
				
		////User input parameters-------
		System.out.println("[¬_¬] Robot Runner [¬_¬] ");
		System.out.println("=========================");
		System.out.println("(Type exit in any moment to close de program)");
		
		//------Get board size from the keyboard
		int[] boardSize = Robot.getValueFromKeyboard("board size", defaultBoardSize);
			
		//------Get current position from the keyboard
		int[] currentPosition = Robot.getValueFromKeyboard("current position", defaultCurrentPosition);
		
		//------Check if the robot is in the board, otherwise ask the user to re enter the position of the robot
		while(!Robot.robotIsInTheBoard(boardSize, currentPosition)) {
			System.out.println("It seems like the position that you entered for the robot, is off the board limits "+Arrays.toString(boardSize)+" please try again!");
			//Ask the robot's position again
			currentPosition = Robot.getValueFromKeyboard("current position", defaultCurrentPosition);
		}
		
		//------Get the instructions file path from the keyboard
		//If the instructions file doesn't exists the program will ask the user to re enter it
		String instructionsFilePath = Robot.getTheInstructionsFilePathFromKeyboard(defaultInstructionsFilePath);
		while(!Robot.instructionsFileExists(instructionsFilePath)) {
			System.out.println("[¬_¬] The file that you entered doesn't exist, please check it and try again");
			instructionsFilePath = Robot.getTheInstructionsFilePathFromKeyboard(defaultInstructionsFilePath);
		}

		//------Create a Robot object and move it!
		Robot robotIoet = new Robot(currentPosition);
		robotIoet.move(instructionsFilePath,boardSize);
		
		//------Exit the program
		System.out.println("Job done!");
		Robot.pressEnterToExit();
	
	}
	
	//Movement function
	public int[] move(String instructionsFilePath, int[] boardSize) {
	
		String instructions = "";
		
		//Read instructions file		
		try {
			instructions = new String(Files.readAllBytes(Paths.get(instructionsFilePath)));
		}catch(IOException e){
			//e.printStackTrace();
			System.out.println("[¬_¬] I had troubles reading the instructions file, please check it and try again!");
			Robot.pressEnterToExit();
		}

		//Get an array with the instructions
		String[] instructionsSet = instructions.split(",");
		
		//Check if the instructions set is in the correct format
		if(!Robot.instructionsSetIsCorrect(instructionsSet)) {
			System.out.println("[¬_¬] The instructions file format is wrong, pretty please, whith sugar on top, review it and try again, remember that the accepted instructions are LE,RI,DO,UP, and not spaces or breaklines are allowed, only comma is used to separate instructions!");
			Robot.pressEnterToExit();
		}
		
		System.out.println("Instructions set:");
		System.out.println(instructions);
		
		System.out.println("Robot movements:");
		
		//Moving section
		//When the robot reaches the limit of the board, he can't move forward so he ignores the command
		//i.e. when he is in the position 1,5 of a 5x5 board, and receives an "RI" order (move to the right)
		//he can't complete it because otherwise he would exit the board.
		for(int i = 0; i < instructionsSet.length; i++) {
			if(instructionsSet[i].equals("LE")&&currentPosition[0]>1){
				currentPosition[0] =currentPosition[0]-1;
			}
			else if(instructionsSet[i].equals("RI")&&currentPosition[0]<boardSize[0]){
				currentPosition[0] = currentPosition[0]+1;
			}
			else if(instructionsSet[i].equals("UP")&&currentPosition[1]>1){
				currentPosition[1] = currentPosition[1]-1;
			}
			else if(instructionsSet[i].equals("DO")&&currentPosition[1]<boardSize[1]){
				currentPosition[1] = currentPosition[1]+1;
			}
			//Print current position for each instruction
			System.out.println("("+currentPosition[0]+","+currentPosition[1]+")");
		}
		
		return currentPosition;
				
	}
	
	//Check if the instructions set is correct
	public static boolean instructionsSetIsCorrect(String[] instructionsSet){
		for(int i = 0; i < instructionsSet.length; i++){
			//Check if all instructions are written correctly
			if (instructionsSet[i].equals("LE")||instructionsSet[i].equals("RI")||instructionsSet[i].equals("UP")||instructionsSet[i].equals("DO")){
				
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	//It checks the necessary condition for both, "board size" and "current position"; it must be
	//two integer values separated by comma without spaces
	public static boolean inputIsCorrect(String valueInput){
		//An empty value indicates the user choose the default values, so it's valid
		if(valueInput.isEmpty())
			return true;
		
		//An "exit" string indicates the user want exit the program, so it's valid
		if(valueInput.equals("exit"))
			return true;
			
		//Check the length of the input, it must be 2 (height and width of the board or x y coordinates of the current position)
		String[] boardSize = valueInput.split(",");  
		if(boardSize.length != 2)
			return false;
		
		//Check if the values are numbers and if they are numbers, if they are > 0
		try {
			if(Integer.parseInt(boardSize[0])<1 || Integer.parseInt(boardSize[1])<1) {
				return false;
			}
		}catch(NumberFormatException  e){//This exception is thrown if the values are not integers
			return false;
		}
			
		//If passes all tests then return true
		return true;		
	}
	

	public static int[] getValueFromKeyboard(String variableName, int[] defaultValue) {
		//Initialize scanner object to get data from the input
		Scanner reader = new Scanner(System.in);
			
		//Ask the user to enter the value, and show the default value between square brackets
		System.out.println("Please, enter the "+ variableName +" ["+defaultValue[0]+","+defaultValue[1]+"]:");
		
		//variable that saves the keyboard input
		String inputString;
		
		//
		if(reader.hasNextLine()) {
			inputString = reader.nextLine();
		}else {
			reader.nextLine();
			inputString = reader.nextLine();
			reader.close();
		}
		
		//If the input is incorrect the user must re enter it or leave it empty to use the default values 
		//or write exit to exit the program
		while(!Robot.inputIsCorrect(inputString)) {
			System.out.println("Plase try again, only two positive integers separated by comma, or the \"exit\" token are accepted. Press enter to use the default values ["+defaultValue[0]+","+defaultValue[1]+"]:");
			inputString = reader.nextLine();
		}
		
		if(inputString.equals("exit")) {
			System.out.println("Goodbye!");
			System.exit(0);//Exits the program
		}
		
		//If the inputString is empty, the program does nothing, which doesn't overwrite the default value of boardSize array
		if(inputString.isEmpty()) {
			return defaultValue;
		}else{
			return Robot.getValueFromString(inputString);
		}
	}
		
	//Divides the input string value in an array with two elements
	//the format must be "x,y" where x and y are positive integers > 0
	//that check up is performed in the InputIsCorrect method
	public static int[] getValueFromString(String inputString){
		int[] value = new int[2];
		value[0]=Integer.parseInt(inputString.split(",")[0]);
		value[1]=Integer.parseInt(inputString.split(",")[1]);
		return value;
	}	
	
	//Verify if the current position specified for the robot, is in the board or off the board considering
	//the board dimensions
	public static boolean robotIsInTheBoard(int[] boardSize, int[] currentPosition) {
		
		if(currentPosition[0]<=boardSize[0]&&currentPosition[1]<=boardSize[1]) {
			return true;
		}else {
			return false;			
		}
	}
	
	//Get the instructions file path from the keyboard, verify if the user wants to exit or choose
	//the default instructions file path
	public static String getTheInstructionsFilePathFromKeyboard(String defaultInstructionsFilePath) {
		Scanner reader = new Scanner(System.in);
		
		String instructionsFilePath;
		
		System.out.println("Please, specify the path of the instructions file ["+defaultInstructionsFilePath+"]:");
		
		if(reader.hasNextLine()){
			instructionsFilePath = reader.nextLine();
		}else {
			reader.nextLine();
			instructionsFilePath = reader.nextLine();
			reader.close();
		}
		
		if(instructionsFilePath.equals("exit")) {
			System.out.println("Goodbye!");
			System.exit(0);
		}
		
		if(instructionsFilePath.isEmpty()) {
			return defaultInstructionsFilePath;
		}else {
			return instructionsFilePath;
		}
	}
	
	//Check if the specified instructions file exists
	public static boolean instructionsFileExists(String instructionsFilePath) {

		File instructionsFile = new File(instructionsFilePath);
		//Check if the file exists and is not a directory
		if(instructionsFile.exists()&&!instructionsFile.isDirectory()) {
			return true;
		}else {
			return false;
		}

	}

	//Ask for an enter and exit the program
	public static void pressEnterToExit(){
		System.out.println("[¬_¬] Goodbye! Please, press Enter to exit.");
		try {
			System.in.read();
			System.exit(0);
		}
		catch(IOException e){
			System.exit(0);
		}
	}
}