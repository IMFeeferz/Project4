
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class GuiServer extends Application{

	//Remove parts in relation to client
	TextField s1,s2,s3,s4, c1, gameState;
	Button serverChoice,clientChoice,b1, serverOn, serverOff;
	TextField portNo;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	Server serverConnection;
	
	BaccaratGame myGame;	
	BaccaratGameLogic myLogic;
	BaccaratInfo gameInfo;
	//Client clientConnection;
	ListView<String> listItems, listItems2;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Baccarat!");
		this.serverOn = new Button("Server On");
		this.serverOn.setStyle("-fx-pref-width: 100px");
		this.serverOn.setStyle("-fx-pref-height: 100px");
		
		this.serverOn.setOnAction(e ->{primaryStage.setScene(playBaccarat());
										primaryStage.setTitle("Play Baccarat");
//			serverConnection = new Server(data -> {
//				Platform.runLater(()->{
//					listItems.getItems().add(data.toString());
//					myGame = new BaccaratGame();
//					myGame.evaluateWinnings();
				});
//			});			
//		});
		
		this.serverOff = new Button("Server Off");
		this.serverOff.setStyle("-fx-pref-width: 100px");
		this.serverOff.setStyle("-fx-pref-height: 100px");
		
		this.serverOff.setOnAction(e ->{
			System.exit(0);
//			serverConnection = new Server(data -> {
//				Platform.runLater(()->{
//					listItems.getItems().add(data.toString());
//				});
//			});			
		});
		
		this.portNo = new TextField("Enter Port Number");
		this.portNo.setStyle("-fx-pref-width: 100px");
		this.portNo.setStyle("-fx-pref-height: 100px");

//		this.serverChoice = new Button("Server");
//		this.serverChoice.setStyle("-fx-pref-width: 300px");
//		this.serverChoice.setStyle("-fx-pref-height: 300px");
//		
//		this.serverChoice.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
//											primaryStage.setTitle("This is the Server");
//				serverConnection = new Server(data -> {
//					Platform.runLater(()->{
//						listItems.getItems().add(data.toString());
//					});
//
//				});
//											
//		});
		
		
//		this.clientChoice = new Button("Client");
//		this.clientChoice.setStyle("-fx-pref-width: 300px");
//		this.clientChoice.setStyle("-fx-pref-height: 300px");
		
//		this.clientChoice.setOnAction(e-> {primaryStage.setScene(sceneMap.get("client"));
//											primaryStage.setTitle("This is a client");
//											clientConnection = new Client(data->{
//							Platform.runLater(()->{listItems2.getItems().add(data.toString());
//											});
//							});
//							
//											clientConnection.start();
//		});
		
		this.clientBox = new VBox(100, portNo, serverOn, serverOff);
		startPane = new BorderPane();
		startPane.setPadding(new Insets(70));
		startPane.setCenter(clientBox);
		
		startScene = new Scene(startPane, 800,800);
		
		listItems = new ListView<String>();
		listItems2 = new ListView<String>();
		
		c1 = new TextField();
		b1 = new Button("Send");
		//b1.setOnAction(e->{clientConnection.send(c1.getText()); c1.clear();});
		
		sceneMap = new HashMap<String, Scene>();
		
		//sceneMap.put("Server On",  playBaccarat());
		sceneMap.put("client",  createClientGui());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		
		primaryStage.setScene(startScene);
		primaryStage.show();
		
	}
	
	public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		pane.setCenter(listItems);
	
		return new Scene(pane, 500, 400);
		
		
	}
	
	public Scene createClientGui() {
		
		clientBox = new VBox(10, c1,b1,listItems2);
		clientBox.setStyle("-fx-background-color: blue");
		return new Scene(clientBox, 400, 300);
		
	}
	
	public Scene playBaccarat() {
		//myGame = new BaccaratGame();	
		//myLogic = new BaccaratGameLogic();
		//gameInfo = new BaccaratInfo();
		BorderPane pane = new BorderPane();
		listItems = new ListView<String>();
		serverConnection = new Server(data -> {
			Platform.runLater(()->{
				listItems.getItems().add(data.toString());
			});}, portNo.getText());
		int totalClients = serverConnection.clients.size();
		//double myWinnings = 0.0;
		//myWinnings = myGame.evaluateWinnings();
		listItems.setStyle("-fx-font-size: 25;"+"-fx-border-size: 20;"+ 
				"-fx-border-color: red;");
		//listItems.getItems().add(String.valueOf(myGame.evaluateWinnings()));
		pane.setCenter(listItems);
		pane.setBottom(serverOff);
		return new Scene(pane, 500, 400);
	}

}