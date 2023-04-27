
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author sethm an application for SQL queries. what else 
 *
 */
public class ClassSearch extends Application {
	private TextField keywords = new TextField("Course, Instructor"), studentID = new TextField();
	private PasswordField password = new PasswordField();
	private Label keyLBL = new Label("Keywords"), termLBL = new Label("Term:"), depLBL = new Label("Departments:"), error = new Label();
	private ComboBox term = new ComboBox(), departments = new ComboBox();
	private String[] termList = { "Spring", "Fall", "Summer" };
	private String[] depList = {"CPS", "MTH", "ENG", " "};
	private String userPassword = "123", name;
	private Connection connection;
	private Statement statement;
	private boolean firstClick = true;
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */

	public void start(Stage primaryStage) {
		// Add oracle JDBC to project
		
//		try {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		
//		String url = null;
//		String sqlUserName = " ";
//		String sqlPassword = " ";
//		connection = DriverManager.getConnection(url, sqlUserName, sqlPassword);
//		//Create Statement object.
//		statement = connection.createStatement();
//		
//		}catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		Button okBT = new Button("OK");
		VBox.setMargin(okBT, new Insets(10, 10, 10, 10));
		VBox.setMargin(password, new Insets(10, 10, 10, 10));
		VBox.setMargin(studentID, new Insets(10, 10, 10, 10));
		Scene scene = new Scene(vbox, 200, 200);
		vbox.getChildren().add(new Label("User ID:"));
		vbox.getChildren().add(studentID);
		vbox.getChildren().add(new Label("Password: "));
		vbox.getChildren().add(password);
		vbox.getChildren().add(okBT);
		okBT.setOnAction(e -> {
			if(password.getText().equals(userPassword) ) {
//				String query = "Select * from student";
//				try {
//				ResultSet resultSet = statement.executeQuery(query);
//				while(resultSet.next()) {
//					if(studentID.equals(resultSet.getString("studentID"))) {
//						name = resultSet.getString("Fname");	
//					}	 
//				}
//				}catch(SQLException ex) {
//					ex.printStackTrace();
//				}
				primaryStage.setScene(registration(name));
				primaryStage.setTitle(name);
				primaryStage.show();
			}else {
				vbox.getChildren().add(error);
				error.setText("Invalid Username or Password");
			}
			
			
			
			
		});
		primaryStage.setScene(scene);
		primaryStage.setTitle("Log In");
		primaryStage.show();

	}
	
	private Scene registration(String name) {
		VBox vbox = new VBox();
		GridPane bottom = new GridPane();
		Pane searchResults = new Pane();
		Pane myCourses = new Pane();
		vbox.setAlignment(Pos.TOP_CENTER);
		Button search = new Button("Search");
		search.setStyle("-fx-background-color: magenta; -fx-text-fill: white;");

		Scene scene = new Scene(vbox, 600, 600);
		//adds nodes textfields/buttons 
		vbox.getChildren().add(keyLBL);
		vbox.getChildren().add(keywords);
		vbox.getChildren().add(termLBL);
		vbox.getChildren().add(term);
		vbox.getChildren().add(depLBL);
		vbox.getChildren().add(departments);
		vbox.getChildren().add(search);
		vbox.getChildren().add(bottom);
		bottom.addColumn(0, searchResults);
		bottom.addColumn(1, myCourses);
		vbox.setPadding(new Insets(15));

		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.BLACK);
		dropShadow.setOffsetX(2);
		dropShadow.setOffsetY(2);
		dropShadow.setRadius(5);
		searchResults.setPrefSize(300, 300);
		TextField results = new TextField("resluts"), courses = new TextField("Courses");
		results.setPrefSize(280, 300);
		results.setStyle("-fx-background-color: lightgrey;");
		searchResults.getChildren().add(results);
		
		
		myCourses.setPrefSize(300, 300);
		courses.setPrefSize(280, 300);
		courses.setStyle("-fx-background-color: lightgrey;");
		myCourses.getChildren().add(courses);
		
		//prevents user from editing things. 
		results.setEditable(false);
		courses.setEditable(false);
		keywords.setEditable(false);
		departments.setDisable(true);
		// Add the drop shadow effect to the pane
		searchResults.setEffect(dropShadow);
		searchResults.setStyle("-fx-background-color: gray;");
		myCourses.setEffect(dropShadow);
		myCourses.setStyle("-fx-background-color: gray;");

		term.setPrefWidth(300);
		term.setPrefHeight(30);
		keywords.setPrefWidth(100);
		keywords.setPrefHeight(30);
		departments.setPrefWidth(300);
		departments.setPrefHeight(30);

		vbox.setSpacing(15);

		ObservableList<String> options = FXCollections.observableArrayList(termList);
		term.getItems().addAll(options);
		term.setOnAction(e -> {
			keywords.setEditable(true);
			departments.setDisable(false);
		});
		
		keywords.setOnMouseClicked(e -> {
			if (firstClick) {
				keywords.clear();
				firstClick = false;
			}
			
		});
		ObservableList<String> depOptions = FXCollections.observableArrayList(depList);
		departments.getItems().addAll(depOptions);
		this.departments.setOnAction(e -> {
			//query goes here. 
		});
		
		search.setOnMouseClicked(e -> {
			String key = keywords.getText();
			if(key.toLowerCase().contains("cps")|| key.toLowerCase().contains("mth")|| key.toLowerCase().contains(" ")) {
				courses.setText("I work");
				
			}else if (key.toLowerCase().contains("instructor")||key.toLowerCase().contains("professor")) {
				//search by Instructor Name
				courses.setText("Found teacher");
			}else {
				courses.setText("Sorry, please change your search criteria");
			}
			
			System.out.println("button is clicked");
		});
		keywords.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			
			if(keyCode == KeyCode.ENTER) {
				search.fire();
			}
		});
		departments.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			
			if(keyCode == KeyCode.ENTER) {
				search.fire();
			}
		});
		return scene;
		
	}
	

	public static void main(String[] args) {
		launch(args);

	}

}
