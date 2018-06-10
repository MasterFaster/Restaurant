----------Dodawanie przycisków

		button1 = new Button("Click me");
        button1.setOnAction(e -> {
            System.out.println("hey");
            System.out.println("Siemka");
        });

----------Ustawianie panelu

		primaryStage.setTitle("Restaurant");
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();


---------zmiana pomiedzy scenami w 1 oknie

public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window =  primaryStage;

        Label label1 = new Label("Welcome to the first Scene");
        Button button1 = new Button("Got to scene 2");
        button1.setOnAction(e -> {
            window.setScene(scene2);
            System.out.println("elo");
        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,button1);
        scene1 = new Scene(layout1, 200, 200);

        Button button2 = new Button("Got to scene 1");
        button2.setOnAction(e -> window.setScene(scene1) );

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("Title heeere");
        window.show();

    }


--------Tworzenie nowego okna i zostawienie poprzedniego

 public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox(40);
        Scene scene = new Scene(layout, 200, 200);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> window.close());

        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        window.setScene(scene);
        window.setTitle(title);
        window.setMaxWidth(300);
        window.showAndWait();
    }