package robot;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RobotTest {
	
	//------Tests to instructionsSetIsCorrect method
	@Test
	public void testInstructionsSetIsCorrect1(){
		
		String[] instructionsSet = {"DO","LE","RI","UP","UP","UP","UP","DO","LE","RI"}; 
		
		boolean actual = Robot.instructionsSetIsCorrect(instructionsSet);
				
		assertTrue(actual);
		
	}
	
	@Test
	public void testInstructionsSetIsCorrect2(){
		
		String[] instructionsSet = {"DO ","LE","RI"}; 
		
		boolean actual = Robot.instructionsSetIsCorrect(instructionsSet);
				
		assertFalse(actual);
		
	}
	
	@Test
	public void testInstructionsSetIsCorrect3(){
		
		String[] instructionsSet = {""}; 
		
		boolean actual = Robot.instructionsSetIsCorrect(instructionsSet);
				
		assertFalse(actual);
		
	}
	
	//------Tests to move method
	@Test
	public void testMove1() {
		
		System.out.println("--Movement test 1--");
		
		int[] currentPosition = {1,1};
		
		String instructionsFile = System.getProperty("user.dir")+"\\instructions.txt";
		int[] boardSize = {5,5};
		
		Robot robotIoet = new Robot(currentPosition);
				
		int[] expected = {3,2};
		int[] actual = robotIoet.move(instructionsFile,boardSize);
		
		assertArrayEquals(expected, actual);
		
		System.out.println("");
		
	}
	
	@Test
	public void testMove2() {
		
		System.out.println("--Movement test 2--");
		
		int[] currentPosition = {1,1};
		
		String instructionsFile = System.getProperty("user.dir")+"\\instructions_test_1.txt";
		int[] boardSize = {5,5};
		
		Robot robotIoet = new Robot(currentPosition);
				
		int[] expected = {2,5};
		int[] actual = robotIoet.move(instructionsFile,boardSize);
		
		assertArrayEquals(expected, actual);
		
		System.out.println("");
				
	}
	
	@Test
	public void testMove3() {
		
		System.out.println("--Movement test 3--");
		
		int[] currentPosition = {1,1};
		
		String instructionsFile = System.getProperty("user.dir")+"\\instructions_test_2.txt";
		int[] boardSize = {5,5};
		
		Robot robotIoet = new Robot(currentPosition);
				
		int[] expected = {2,3};
		int[] actual = robotIoet.move(instructionsFile,boardSize);
		
		assertArrayEquals(expected, actual);
		
		System.out.println("");
		
	}
	@Test
	public void testMove4() {
		
		System.out.println("--Movement test 4--");
		
		int[] currentPosition = {32,45};
		
		String instructionsFile = System.getProperty("user.dir")+"\\instructions_test_3.txt";
		int[] boardSize = {100,60};
		
		Robot robotIoet = new Robot(currentPosition);
				
		int[] expected = {43,49};
		int[] actual = robotIoet.move(instructionsFile,boardSize);
		
		assertArrayEquals(expected, actual);
		
		System.out.println("");
		
	}
	
	//------Tests to inputIsCorrect method
	@Test
	public void testInputIsCorrect1(){
		String boardSizeInput = "1.3,3";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertFalse(actual);
		
	}
	@Test
	public void testInputIsCorrect2(){
		String boardSizeInput = "3,7";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertTrue(actual);
		
	}
	@Test
	public void testInputIsCorrect3(){
		String boardSizeInput = "5,-5";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertFalse(actual);
		
	}
	@Test
	public void testInputIsCorrect4(){
		String boardSizeInput = "5,5,8";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertFalse(actual);
		
	}
	@Test
	public void testInputIsCorrect5(){
		String boardSizeInput = "5,0";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertFalse(actual);
		
	}
	@Test
	public void testInputIsCorrect6(){
		String boardSizeInput = "5,a";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertFalse(actual);
		
	}
	@Test
	public void testInputIsCorrect7(){
		String boardSizeInput = "";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertTrue(actual);
		
	}
	@Test
	public void testInputIsCorrect8(){
		String boardSizeInput = "exit";
		
		boolean actual = Robot.inputIsCorrect(boardSizeInput);
		
		assertTrue(actual);
		
	}
	//------Tests to getValueFromKeyboard method
	@Test
	public void testGetValueFromKeyboard1() {

		int[] defaultValue = {4,8};
		
		//Simulate a keyboard input
		String simulatedKeyboardInput = "10,10\n";
		InputStream in = new ByteArrayInputStream(simulatedKeyboardInput.getBytes());
		System.setIn(in);
		
		int[] actual = Robot.getValueFromKeyboard("board size", defaultValue);
		int[] expected = {10,10};
			
		assertArrayEquals(actual,expected);
	}
	@Test
	public void testGetValueFromKeyboard2() {

		int[] defaultValue = {6,9};
		
		//Simulate a keyboard input
		String simulatedKeyboardInput = "\n";
		InputStream in = new ByteArrayInputStream(simulatedKeyboardInput.getBytes());
		System.setIn(in);
		
		int[] actual = Robot.getValueFromKeyboard("board size", defaultValue);
		int[] expected = {6,9};
			
		assertArrayEquals(actual,expected);
	}
	
	//------Tests to getValueFromString method
	@Test
	public void testGetValueFromString1(){
		String inputString = "1,8";
		
		int[] actual = Robot.getValueFromString(inputString);
		int[] expected = {1,8};
		
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testGetValueFromString2(){
		String inputString = "10,9";
		
		int[] actual = Robot.getValueFromString(inputString);
		int[] expected = {10,9};
		
		assertArrayEquals(expected, actual);
	}
	
	//------Tests to robotIsInTheBoard method 
	@Test
	public void testRobotIsInTheBoard1(){
		int[] currentPosition = {3,3};
		int[] boardSize = {10,8};
		
		boolean actual = Robot.robotIsInTheBoard(boardSize,currentPosition);
		
		assertTrue(actual);
		
	}
	
	@Test
	public void testRobotIsInTheBoard2(){
		int[] currentPosition = {20,14};
		int[] boardSize = {7,7};
		
		boolean actual = Robot.robotIsInTheBoard(boardSize,currentPosition);
		
		assertFalse(actual);
		
	}
	
	@Test
	public void testRobotIsInTheBoard3(){
		int[] currentPosition = {10,10};
		int[] boardSize = {10,10};
		
		boolean actual = Robot.robotIsInTheBoard(boardSize,currentPosition);
		
		assertTrue(actual);
		
	}
	
	//------Tests to the getTheInstructionsFilePathFromKeyboard method
	@Test
	public void testGetTheInstructionsFilePathFromKeyboard1(){
		
		String defaultInstructionsFilePath = System.getProperty("user.dir")+"\\instructions.txt";
		
		//Simulate a keyboard input
		String simulatedKeyboardInput = "C:\\instructions_test_4.txt\n";
		InputStream in = new ByteArrayInputStream(simulatedKeyboardInput.getBytes());
		System.setIn(in);
		
		String actual = Robot.getTheInstructionsFilePathFromKeyboard(defaultInstructionsFilePath);
		String expected = "C:\\instructions_test_4.txt";
		
		assertEquals(expected, actual);
	}
	@Test
	public void testGetTheInstructionsFilePathFromKeyboard2(){
		
		String defaultInstructionsFilePath = System.getProperty("user.dir")+"\\instructions.txt";
		
		//Simulate a keyboard input
		String simulatedKeyboardInput = "\n";
		InputStream in = new ByteArrayInputStream(simulatedKeyboardInput.getBytes());
		System.setIn(in);
		
		String actual = Robot.getTheInstructionsFilePathFromKeyboard(defaultInstructionsFilePath);
		String expected = System.getProperty("user.dir")+"\\instructions.txt";
		
		assertEquals(expected, actual);
		
	}
	
	//------Tests to the method instructionsFileExists
	@Test
	public void testInstructionsFileExists1() {
		
		//Test an existent file
		String instructionsFilePath = System.getProperty("user.dir")+"\\instructions.txt";
		
		boolean actual = Robot.instructionsFileExists(instructionsFilePath);
		
		assertTrue(actual);
		
	}
	@Test
	public void testInstructionsFileExists2() {
		
		//Test a non existent file
		String instructionsFilePath = "non_existent_file.txt";
		
		boolean actual = Robot.instructionsFileExists(instructionsFilePath);
		
		assertFalse(actual);
	}
	@Test
	public void testInstructionsFileExists3() {
		
		//Test a directory
		String instructionsFilePath = System.getProperty("user.dir");
		
		boolean actual = Robot.instructionsFileExists(instructionsFilePath);
		
		assertFalse(actual);
	}
}
