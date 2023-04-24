


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
 * @author sethm
 *
 */
public class ClassSearch extends Application {
	private TextField keywords = new TextField(), departments = new TextField(),studentID = new TextField();
	private PasswordField password = new PasswordField();
	private Label keyLBL = new Label("Keywords"), termLBL = new Label("Term:"), depLBL = new Label("Departments:"), error = new Label();
	private ComboBox term = new ComboBox();
	private String[] termList = { "Spring", "Fall", "Summer" };
	private String userPassword = "123", name;
	

	/**
	 * @param args
	 */

	public void start(Stage primaryStage) {
		// Add oracle JDBC to project
		//Class.forName(" ");
		
		String url = null;
		String sqlUserName = " ";
		String sqlPassword = " ";
		
		//Create Statement object.
		//Statement statement = connection.createStatement();
		
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
				String query = "Select Fname || ' ' || Lname from student where " + studentID.getText() + " = studentID;";
				//ResultSet resultSet = statement.executeQuery(query);
				//name = resultSet;
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
		

		results.setEditable(false);
		courses.setEditable(false);
		keywords.setEditable(false);
		departments.setEditable(false);
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
			departments.setEditable(true);
			
		});

		keywords.setOnKeyTyped(e -> {

		});
		departments.setOnKeyTyped(e -> {

		});
		return scene;
		
	}
	

	public static void main(String[] args) {
		launch(args);

	}

}
