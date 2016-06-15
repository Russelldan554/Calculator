import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Calculator Application
 * Handles basic addition, subtraction, multiplication and division from user input.
 * @author Daniel Russell
 *
 */
public class CalculatorGui extends Application{
	
	private AnchorPane mainPane; 
	private VBox vert;
	private GridPane grid;
	
	private final int HEIGHT = 500, WIDTH = 500;
	
	//Create all the buttons for numbers and calculation on calculator screen
	private Button addButton, subtractButton, divideButton, multiplyButton, calculateButton, zeroButton, 
		oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton,
		nineButton, clearAllButton, clearCurrentButton, exitButton;
	
	//field will display input then total of calculations
	private TextField dispCalcField;
	
	//total will hold calculation then display in text dispCalcField
	//selection will hold type of calculation to be done when button is selected so we can call the proper method from the driver
	private String total, selection;
	//holds first input from user to be calculated and the second input from user
	private String inputOne, inputTwo;
	
	/**
	 * Launches the javaFx GUI
	 * @param args
	 */
	public static void main(String[] args){
		launch(args);
	}

	/**
	 * creates graphic display of calculator and starts all the calls to the driver for calculation
	 */
	@Override
	public void start(Stage stage) throws Exception {
		//create driver object to call the math functions
		CalculatorDriver calc = new CalculatorDriver();
		
		initialize();
		
		//holds main layout
		mainPane = new AnchorPane();
		
		//create vbox
		vert = new VBox();
			
		//create grid pane to hold buttons
		grid = new GridPane();
		grid.setPrefSize(500,450);
		
		//create numbers on calculator
		oneButton = new Button("1");
		twoButton = new Button("2");
		threeButton = new Button("3");
		fourButton = new Button("4");
		fiveButton = new Button("5");
		sixButton = new Button("6");
		sevenButton = new Button("7");
		eightButton = new Button("8");
		nineButton = new Button("9");
		zeroButton = new Button("0");
		
		//Size the columns in gridpane to make it fit the pane
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(25);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(25);
	    ColumnConstraints column3 = new ColumnConstraints();
	    column3.setPercentWidth(25);
	    ColumnConstraints column4 = new ColumnConstraints();
	    column4.setPercentWidth(25);
	    grid.getColumnConstraints().addAll(column1, column2, column3, column4);
	    
	    //size the rows to fit pane
	    RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(25);
	    RowConstraints row2 = new RowConstraints();
	    row2.setPercentHeight(25);
	    RowConstraints row3 = new RowConstraints();
	    row3.setPercentHeight(25);
	    RowConstraints row4 = new RowConstraints();
	    row4.setPercentHeight(25);
	    grid.getRowConstraints().addAll(row1, row2, row3, row4);
		
		//add buttons too grid
		grid.add(oneButton, 0, 2);
		oneButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(twoButton, 1, 2);
		twoButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(threeButton, 2, 2);
		threeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(fourButton, 0, 1);
		fourButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(fiveButton, 1, 1);
		fiveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(sixButton, 2, 1);
		sixButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(sevenButton, 0, 0);
		sevenButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(eightButton, 1, 0);
		eightButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(nineButton, 2, 0);
		nineButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(zeroButton, 1, 3);
		zeroButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		 
		//calculation buttons
		addButton = new Button("+");
		subtractButton = new Button("-");
		multiplyButton = new Button("x");
		divideButton = new Button("/");
		calculateButton = new Button("=");
		clearCurrentButton = new Button("C");
		clearAllButton = new Button("AC");
		
		//add calculation buttons to grid
		grid.add(addButton, 3, 0);
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(subtractButton, 3, 1);
		subtractButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(multiplyButton, 3, 2);
		multiplyButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(divideButton, 3, 3);
		divideButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(calculateButton, 0, 3);
		calculateButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		grid.add(clearAllButton, 2, 3);
		clearAllButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		//display grid lines TODO get rid of at end.
	    grid.setGridLinesVisible(false);
	    
	    //create textfield and set to uneditable
	    dispCalcField = new TextField(" ");
	    dispCalcField.setEditable(false);
	    
	    //calculation buttons actions
	    addButton.setOnAction(event ->{
	    	selection = "add";
	    	numberSelectInputTwo();
	    });
	    
	    subtractButton.setOnAction(event ->{
	    	selection = "subtract";
	    	numberSelectInputTwo();
	    });
	    
	    multiplyButton.setOnAction(event ->{
	    	selection = "multiply";
	    	numberSelectInputTwo();
	    });
	    
	    divideButton.setOnAction(event ->{
	    	selection = "divide";
	    	numberSelectInputTwo();
	    });
	    
	    clearAllButton.setOnAction(event ->{
	    	initialize();
	    	dispCalcField.setText(inputOne);
	    });
	    
	    calculateButton.setOnAction(event ->{
	    	if(inputOne != "" && inputTwo != ""){
	    		if (selection.equals("add")){
	    			total = calc.add(Double.parseDouble(inputOne), Double.parseDouble(inputTwo));
	    			dispCalcField.setText(total);
	    			initialize();
	    			numberSelectInputOne();
	    		} else if (selection.equals("subtract")){
	    			total = calc.subtract(Double.parseDouble(inputOne), Double.parseDouble(inputTwo));
	    			dispCalcField.setText(total);
	    			initialize();
	    			numberSelectInputOne();
	    		} else if (selection.equals("multiply")){
	    			total = calc.multiply(Double.parseDouble(inputOne), Double.parseDouble(inputTwo));
	    			dispCalcField.setText(total);
	    			initialize();
	    			numberSelectInputOne();
	    		} else if (selection.equals("divide")){
	    			total = calc.divide(Double.parseDouble(inputOne), Double.parseDouble(inputTwo));
	    			dispCalcField.setText(total);
	    			initialize();
	    			numberSelectInputOne();
	    		} else {
	    			total = "error";
	    			initialize();
	    			numberSelectInputOne();
	    		}
	    	} else {
	    		initialize();
	    		numberSelectInputOne();
	    	}
	    });
	   
	    //button
	    numberSelectInputOne();
	    
		//exit button
		exitButton = new Button("Exit");
		exitButton.setTooltip(new Tooltip("Select to close the application"));
	    exitButton.setOnAction(event -> 
			 System.exit(0) );
	    
	  //add grid to VBox
	    vert.getChildren().addAll(dispCalcField, grid, exitButton);
	    
		mainPane.getChildren().addAll( vert);
		
	    Scene scene = new Scene(mainPane, HEIGHT, WIDTH);
		stage.setScene(scene);
		stage.setTitle("Calculator");
	    stage.show();
	}

	/**
	 * controller for numbers buttons on keypad to add to first input
	 */
	public void numberSelectInputOne() {
		oneButton.setOnAction(event ->{
	    	inputOne += "1";
	    	dispCalcField.setText(inputOne);
	    	System.out.println("input one");
	    });
	    
	    twoButton.setOnAction(event ->{
	    	inputOne += "2";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    threeButton.setOnAction(event ->{
	    	inputOne += "3";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    fourButton.setOnAction(event ->{
	    	inputOne += "4";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    fiveButton.setOnAction(event ->{
	    	inputOne += "5";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    sixButton.setOnAction(event ->{
	    	inputOne += "6";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    sevenButton.setOnAction(event ->{
	    	inputOne += "7";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    eightButton.setOnAction(event ->{
	    	inputOne += "8";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    nineButton.setOnAction(event ->{
	    	inputOne += "9";
	    	dispCalcField.setText(inputOne);
	    });
	    
	    zeroButton.setOnAction(event ->{
	    	inputOne += "0";
	    	dispCalcField.setText(inputOne);
	    });
	}
	
	/**
	 * controller for numbers buttons on keypad to add to second input
	 */
	public void numberSelectInputTwo() {
		oneButton.setOnAction(event ->{
	    	inputTwo += "1";
	    	dispCalcField.setText(inputTwo);
	    	System.out.println("input two");
	    });
	    
	    twoButton.setOnAction(event ->{
	    	inputTwo += "2";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    threeButton.setOnAction(event ->{
	    	inputTwo += "3";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    fourButton.setOnAction(event ->{
	    	inputTwo += "4";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    fiveButton.setOnAction(event ->{
	    	inputTwo += "5";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    sixButton.setOnAction(event ->{
	    	inputTwo += "6";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    sevenButton.setOnAction(event ->{
	    	inputTwo += "7";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    eightButton.setOnAction(event ->{
	    	inputTwo += "8";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    nineButton.setOnAction(event ->{
	    	inputTwo += "9";
	    	dispCalcField.setText(inputTwo);
	    });
	    
	    zeroButton.setOnAction(event ->{
	    	inputTwo += "0";
	    	dispCalcField.setText(inputTwo);
	    });
	}

	/**
	 * initializes string variables back to empty
	 */
	public void initialize(){
		total = "";
		inputOne = "";
		inputTwo = "";
		selection = "";
	}
}
