package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Main extends Application {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Oracle_1"; 

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Welcome!");

        GridPane welcomeGrid = new GridPane();
        welcomeGrid.setAlignment(Pos.CENTER);
        welcomeGrid.setHgap(20);
        welcomeGrid.setVgap(20);
        welcomeGrid.setPadding(new Insets(40, 40, 40, 40));
//        welcomeGrid.setStyle("-fx-background-color: #A3C1AD;");

        Image homeBackgroundImage = new Image("https://i.pinimg.com/originals/79/15/40/7915402bea818aec1584fce63115a979.jpg");
        BackgroundImage homeBackground = new BackgroundImage(
                homeBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        Background homeBackgroundWithImage = new Background(homeBackground);
        welcomeGrid.setBackground(homeBackgroundWithImage);

        Text welcomeText = new Text("NAWINA HOTEL!");
        welcomeText.setStyle("-fx-font-size: 45px; -fx-font-weight: bold; -fx-fill: white; -fx-font-family: \"Times New Roman\";");
        welcomeGrid.add(welcomeText, 0, 0, 2, 1);
        GridPane.setHalignment(welcomeText, javafx.geometry.HPos.CENTER);

        Button letsGoButton = new Button("Click to Enter!");
        letsGoButton.setStyle("-fx-background-color: white ; -fx-text-fill: #DA9100 ; -fx-font-weight: bold; -fx-font-size: 30px; -fx-background-radius: 47;");
        HBox hbLetsGoBtn = new HBox(5);
        hbLetsGoBtn.setPadding(new Insets(0));
        hbLetsGoBtn.setAlignment(Pos.CENTER);
        hbLetsGoBtn.getChildren().add(letsGoButton);
        welcomeGrid.add(hbLetsGoBtn, 1, 2, 2, 1);

        letsGoButton.setOnAction(event -> {
        	AdminScreens();
        });

        Scene welcomeScene = new Scene(welcomeGrid, 930, 700);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    
//  ------------------------------------------- ADMIN USER LOGIN STAGE -------------------------------------------------- //
    
    
    
    public void AdminScreens() {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("User | Admin | Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(40, 40, 40, 40));

        Image AdminBackgroundImage = new Image("https://external-preview.redd.it/_IPMs180QDcnwnWOkNIhmgamF9pD1sJQLmo8IDOY-DQ.jpg?auto=webp&s=051c03c8e9c3b3040a21feb389ed1ff8412edd00");
        BackgroundImage AdminBackground = new BackgroundImage(
                AdminBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        Background AdminBackgroundWithImage = new Background(AdminBackground);
        grid.setBackground(AdminBackgroundWithImage);

        Button userLoginButton = new Button("USER");
        userLoginButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30px;");
        HBox hbBtn = new HBox(5);
        hbBtn.setPadding(new Insets(0));
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(userLoginButton);
        grid.add(hbBtn, 2, 4, 2, 1);

        Button adminLoginButton = new Button("RECEPTIONIST");
        adminLoginButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 30px;");
        HBox hbBtn2 = new HBox(5);
        hbBtn2.setPadding(new Insets(0));
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(adminLoginButton);
        grid.add(hbBtn2, 2, 6, 2, 1);

        userLoginButton.setOnAction(event -> {
            showLoginAndRegisterScreens();
        });

        adminLoginButton.setOnAction(event -> {
            showAdminControllerStage();
        });

        Scene scene = new Scene(grid, 930, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
//  ------------------------------------------- ADMIN CONTROLLER STAGE -------------------------------------------------- //
    
    private void showAdminControllerStage() {
        primaryStage.setTitle("Admin Controller");

        // Background image
        Image backgroundImage = new Image("https://image.made-in-china.com/2f0j00aiRGytqYVFbU/Chinese-Modern-Wooden-Luxury-Hotel-Lobby-Reception-Area-Furniture.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        BorderPane borderPane = new BorderPane();

        // Set background image
        borderPane.setBackground(new Background(background));

        // Create a VBox for the title and navigation buttons
        VBox contentVBox = new VBox();
        contentVBox.setAlignment(Pos.TOP_CENTER);
        contentVBox.setSpacing(26); // Increased spacing between elements

     // Set alignment for the VBox
        contentVBox.setAlignment(Pos.TOP_CENTER);

        // Title label
        Label titleLabel = new Label("RECEPTIONIST CONTROLS");
        titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-text-fill: black;");

        // Add the titleLabel directly to the contentVBox
        contentVBox.getChildren().add(titleLabel);



        
        // Create buttons for the navigation bar
        Button button1 = new Button("Guest Info");
        Button button2 = new Button("Check-In Details");
        Button button3 = new Button("Check-Out Details");
        Button button4 = new Button("Check History");

        // Set the button styles
        String buttonStyle = "-fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold; -fx-font-size: 20px; -fx-min-width: 120px; -fx-min-height: 40px;";
        button1.setStyle(buttonStyle);
        button2.setStyle(buttonStyle);
        button3.setStyle(buttonStyle);
        button4.setStyle(buttonStyle);

        // Add buttons to the navigation bar
        contentVBox.getChildren().addAll(button1, button2, button3, button4);

     // Create an HBox for the "Back" button
        HBox backButtonContainer = new HBox();
        backButtonContainer.setAlignment(Pos.CENTER);
        backButtonContainer.setPadding(new Insets(20, 20, 20, 20)); // Adjusted padding and added margin to the top
        Button backButton = new Button("Back");
        backButton.setStyle(buttonStyle + "-fx-font-size: 16px; -fx-min-width: 100px; -fx-min-height: 30px; -fx-font-family: \\\"Times New Roman\\\";"); // Adjusted style for smaller button
        backButtonContainer.getChildren().add(backButton);

        // Create a VBox to hold the content and the "Back" button
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.getChildren().addAll(contentVBox, backButtonContainer);

        // Create a BorderPane to hold the main content
        borderPane.setCenter(mainVBox); // Center the VBox in the BorderPane

        // Set background color for the BorderPane
        borderPane.setStyle("-fx-background-color: #E3D4AD;");

        // Add event handler for "Guest Info" button
        button1.setOnAction(event -> {
            showGuestInfoStage();
        });

        // Add event handler for "Check-In Details" button
        button2.setOnAction(event -> {
        	showCheckInDetailsStage();
        });

        // Add event handler for "Check-Out Details" button
        button3.setOnAction(event -> {
            showCheckOutDetailsStage();
        });

        // Add event handler for "Check History" button
        button4.setOnAction(event -> {
            showCheckHistoryStage();
        });

        // Add event handler for "Back" button
        backButton.setOnAction(event -> {
            AdminScreens();
        });

        // Add the BorderPane to the scene
        Scene scene = new Scene(borderPane, 930, 700); // Set initial scene size
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    
//  ------------------------------------------- CHECK-IN FORM STAGE -------------------------------------------------- //
    
    private void showCheckInDetailsStage() {
        primaryStage.setTitle("Check-In Form");

        // Create a BorderPane to organize the content
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));
        borderPane.setStyle("-fx-background-color: #E3D4AD;");

        // Create a VBox for the form fields
        VBox formVBox = new VBox();
        formVBox.setSpacing(10);
        formVBox.setPadding(new Insets(20));

        // Username field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: \"Times New Roman\";");
        usernameField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(usernameLabel, usernameField);

        // Email field
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: \"Times New Roman\";");
        emailField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(emailLabel, emailField);

        // Room type field with dropdown
        Label roomTypeLabel = new Label("Room Type:");
        ComboBox<String> roomTypeComboBox = new ComboBox<>();
        roomTypeComboBox.getItems().addAll("Standard", "Deluxe", "Suite");
        roomTypeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: \"Times New Roman\";");
        roomTypeComboBox.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(roomTypeLabel, roomTypeComboBox);

        // Phone number field
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: \"Times New Roman\";");
        phoneField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(phoneLabel, phoneField);

        // Address field
        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        addressLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: \"Times New Roman\";");
        addressField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(addressLabel, addressField);

        // Check-in date field with date picker
        Label checkInDateLabel = new Label("Check-In Date:");
        DatePicker checkInDatePicker = new DatePicker();
        checkInDateLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: \"Times New Roman\";");
        checkInDatePicker.setStyle("-fx-font-size: 16px;");
        VBox checkInDateContainer = new VBox(10);
        checkInDateContainer.getChildren().addAll(checkInDateLabel, checkInDatePicker);
        formVBox.getChildren().add(checkInDateContainer);

        // Check-in button
        Button checkInButton = new Button("Check In");
        checkInButton.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-family: \"Times New Roman\";");
        checkInButton.setOnAction(event -> {
            // Retrieve input values
            String username = usernameField.getText();
            String email = emailField.getText();
            String roomType = roomTypeComboBox.getValue();
            String phone = phoneField.getText();
            String address = addressField.getText();
            LocalDate checkInDate = checkInDatePicker.getValue();
            // Insert check-in details into the database
            insertCheckInDetails(username, email, roomType, phone, address, checkInDate);
            showCheckInStage();
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-family: \"Times New Roman\";");
        backButton.setOnAction(event -> {
            // Go back to the previous stage
            // Assuming you have a method showPreviousStage() to navigate back
        	showAdminControllerStage();
        });

        // Add form VBox and buttons to the center of the BorderPane
        borderPane.setCenter(formVBox);

        // Add buttons HBox below the form fields
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(checkInButton, backButton);

        // Add buttons HBox to the bottom of the BorderPane
        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20));
        buttonContainer.getChildren().addAll(buttonBox);
        borderPane.setBottom(buttonContainer);

        // Set the scene
        Scene scene = new Scene(borderPane, 930, 700); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // Method to insert check-in details into the Oracle database
    private void insertCheckInDetails(String username, String email, String roomType, String phone, String address, LocalDate checkInDate) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl"; 
        String user = "system"; 
        String password = "Oracle_1"; 
        
        String sql = "INSERT INTO checkin_details1 (username, email, room_type, phone, address, check_in_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, roomType);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.setDate(6, java.sql.Date.valueOf(checkInDate));

            statement.executeUpdate();

            System.out.println("Check-in details inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting check-in details: " + e.getMessage());
            
            
        }
    }


    
//  ------------------------------------------- CHECK-OUT FORM STAGE -------------------------------------------------- //
    
    
    
    private void showCheckOutDetailsStage() {
        primaryStage.setTitle("Check-Out Details");

        // Create a BorderPane to organize the content
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));
        borderPane.setStyle("-fx-background-color: #E3D4AD;");

        // Create a VBox for the form fields
        VBox formVBox = new VBox();
        formVBox.setSpacing(10);
        formVBox.setPadding(new Insets(20));

        // Username field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        usernameField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(usernameLabel, usernameField);

        // Email field
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        emailField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(emailLabel, emailField);

        // Room type field with dropdown
        Label roomTypeLabel = new Label("Room Type:");
        ComboBox<String> roomTypeComboBox = new ComboBox<>();
        roomTypeComboBox.getItems().addAll("Standard", "Deluxe", "Suite");
        roomTypeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        roomTypeComboBox.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(roomTypeLabel, roomTypeComboBox);

        // Phone number field
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"); 
        phoneField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(phoneLabel, phoneField);

        // Address field
        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        addressLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        addressField.setStyle("-fx-font-size: 16px;");
        formVBox.getChildren().addAll(addressLabel, addressField);

        // Check-out date field with date picker
        Label checkOutDateLabel = new Label("Check-Out Date:");
        DatePicker checkOutDatePicker = new DatePicker();
        checkOutDateLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        checkOutDatePicker.setStyle("-fx-font-size: 16px;");
        VBox checkOutDateContainer = new VBox(10);
        checkOutDateContainer.getChildren().addAll(checkOutDateLabel, checkOutDatePicker);
        formVBox.getChildren().add(checkOutDateContainer);

        // Check-out button
        Button checkOutButton = new Button("Check Out");
        checkOutButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: #E3D4AD;");
        checkOutButton.setOnAction(event -> {
            // Retrieve input values
            String username = usernameField.getText();
            String email = emailField.getText();
            String roomType = roomTypeComboBox.getValue();
            String phone = phoneField.getText();
            String address = addressField.getText();
            LocalDate checkOutDate = checkOutDatePicker.getValue();
            // Insert check-out details into the database
            insertCheckOutDetails(username, email, roomType, phone, address, checkOutDate);
            showCheckOutStage();
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: #E3D4AD;");
        backButton.setOnAction(event -> {
           
        	showAdminControllerStage();
        });

        // Add form VBox and buttons to the center of the BorderPane
        borderPane.setCenter(formVBox);

        // Add buttons HBox below the form fields
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(checkOutButton, backButton);

        // Add buttons HBox to the bottom of the BorderPane
        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20));
        buttonContainer.getChildren().addAll(buttonBox);
        borderPane.setBottom(buttonContainer);

        // Set the scene
        Scene scene = new Scene(borderPane, 930, 700); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // Method to insert check-out details into the database
    private void insertCheckOutDetails(String username, String email, String roomType, String phone, String address, LocalDate checkOutDate) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "system";
        String password = "Oracle_1";

        String sql = "DELETE FROM checkout_details (username, email, room_type, phone, address, checkout_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, roomType);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.setDate(6, java.sql.Date.valueOf(checkOutDate));

            statement.executeUpdate();

            System.out.println("Check-out details delete successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting check-out details: " + e.getMessage());
        }
    }

   
    
//  ------------------------------------------- GUEST-INFO STAGE -------------------------------------------------- //

    
    private void showGuestInfoStage(){
    	 primaryStage.setTitle("Guest Info");

    	        BorderPane borderPane = new BorderPane();
    	        borderPane.setPadding(new Insets(100));
    	        borderPane.setStyle("-fx-background-color: #E3D4AD;");


    	        TableView<GuestInfo> tableView = new TableView<>();

    	        TableColumn<GuestInfo, Integer> paymentIdColumn = new TableColumn<>("Payment ID");
    	        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));

    	        TableColumn<GuestInfo, String> roomTypeColumn = new TableColumn<>("Room Type");
    	        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));

    	        TableColumn<GuestInfo, Date> checkInDateColumn = new TableColumn<>("Check-In Date");
    	        checkInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));

    	        TableColumn<GuestInfo, Date> checkOutDateColumn = new TableColumn<>("Check-Out Date");
    	        checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

    	        TableColumn<GuestInfo, Integer> userIdColumn = new TableColumn<>("User ID");
    	        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

    	        TableColumn<GuestInfo, String> firstNameColumn = new TableColumn<>("First Name");
    	        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    	        TableColumn<GuestInfo, String> lastNameColumn = new TableColumn<>("Last Name");
    	        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

    	        TableColumn<GuestInfo, String> emailColumn = new TableColumn<>("Email");
    	        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

    	        TableColumn<GuestInfo, String> usernameColumn = new TableColumn<>("Username");
    	        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

    	        tableView.getColumns().addAll(paymentIdColumn, roomTypeColumn, checkInDateColumn,
    	                checkOutDateColumn, userIdColumn, firstNameColumn,
    	                lastNameColumn, emailColumn, usernameColumn);

    	        ObservableList<GuestInfo> data = FXCollections.observableArrayList();
    	        retrieveGuestInfoFromDatabase(data);
    	        tableView.setItems(data);

    	        Scene scene = new Scene(new VBox(tableView), 930, 700);
    	        primaryStage.setScene(scene);
    	        primaryStage.show();
    	    }

    	    private void retrieveGuestInfoFromDatabase(ObservableList<GuestInfo> data) {
    	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    	             Statement statement = connection.createStatement();
    	             ResultSet resultSet = statement.executeQuery("SELECT * FROM guestinfoAS")) {
    	            while (resultSet.next()) {
    	                int paymentId = resultSet.getInt("payment_id");
    	                String roomType = resultSet.getString("room_type");
    	                Date checkInDate = resultSet.getDate("check_in_date");
    	                Date checkOutDate = resultSet.getDate("check_out_date");
    	                int userId = resultSet.getInt("user_id");
    	                String firstName = resultSet.getString("first_name");
    	                String lastName = resultSet.getString("last_name");
    	                String email = resultSet.getString("email");
    	                String username = resultSet.getString("username");

    	                data.add(new GuestInfo(paymentId, roomType, checkInDate, checkOutDate, userId,
    	                        firstName, lastName, email, username));
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }

    	    public static class GuestInfo {
    	        private int paymentId;
    	        private String roomType;
    	        private Date checkInDate;
    	        private Date checkOutDate;
    	        private int userId;
    	        private String firstName;
    	        private String lastName;
    	        private String email;
    	        private String username;

    	        public int getPaymentId() {
    	            return paymentId;
    	        }

    	        public void setPaymentId(int paymentId) {
    	            this.paymentId = paymentId;
    	        }

    	        public String getRoomType() {
    	            return roomType;
    	        }

    	        public void setRoomType(String roomType) {
    	            this.roomType = roomType;
    	        }

    	        public Date getCheckInDate() {
    	            return checkInDate;
    	        }

    	        public void setCheckInDate(Date checkInDate) {
    	            this.checkInDate = checkInDate;
    	        }

    	        public Date getCheckOutDate() {
    	            return checkOutDate;
    	        }

    	        public void setCheckOutDate(Date checkOutDate) {
    	            this.checkOutDate = checkOutDate;
    	        }

    	        public int getUserId() {
    	            return userId;
    	        }

    	        public void setUserId(int userId) {
    	            this.userId = userId;
    	        }

    	        public String getFirstName() {
    	            return firstName;
    	        }

    	        public void setFirstName(String firstName) {
    	            this.firstName = firstName;
    	        }

    	        public String getLastName() {
    	            return lastName;
    	        }

    	        public void setLastName(String lastName) {
    	            this.lastName = lastName;
    	        }

    	        public String getEmail() {
    	            return email;
    	        }

    	        public void setEmail(String email) {
    	            this.email = email;
    	        }

    	        public String getUsername() {
    	            return username;
    	        }

    	        public void setUsername(String username) {
    	            this.username = username;
    	        }

    	        public GuestInfo(int paymentId, String roomType, Date checkInDate, Date checkOutDate,
    	                         int userId, String firstName, String lastName, String email, String username) {
    	            this.paymentId = paymentId;
    	            this.roomType = roomType;
    	            this.checkInDate = checkInDate;
    	            this.checkOutDate = checkOutDate;
    	            this.userId = userId;
    	            this.firstName = firstName;
    	            this.lastName = lastName;
    	            this.email = email;
    	            this.username = username;
    	        }
    	    }


    
    
    
    
    
    
    
    
    
//  ------------------------------------------- CHECK-IN STAGE -------------------------------------------------- //
    
    private void showCheckInStage() {
        primaryStage.setTitle("Check-In Details");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(100));
        borderPane.setStyle("-fx-background-color: #E3D4AD;");

        TableView<CheckInDetails> tableView = new TableView<>();

        TableColumn<CheckInDetails, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<CheckInDetails, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<CheckInDetails, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        TableColumn<CheckInDetails, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<CheckInDetails, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<CheckInDetails, LocalDate> checkInDateColumn = new TableColumn<>("Check-In Date");
        checkInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));

        tableView.getColumns().addAll(usernameColumn, emailColumn, roomTypeColumn, phoneColumn, addressColumn, checkInDateColumn);

        ObservableList<CheckInDetails> data = FXCollections.observableArrayList();
        retrieveCheckInDetailsFromDatabase(data);
        tableView.setItems(data);
        borderPane.setCenter(tableView);

        Scene scene = new Scene(borderPane, 930, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void retrieveCheckInDetailsFromDatabase(ObservableList<CheckInDetails> data) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM checkin_details1");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String roomType = resultSet.getString("room_type");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                LocalDate checkInDate = resultSet.getDate("check_in_date").toLocalDate();
                data.add(new CheckInDetails(username, email, roomType, phone, address, checkInDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class CheckInDetails {
        private final String username;
        private final String email;
        private final String roomType;
        private final String phone;
        private final String address;
        private final LocalDate checkInDate;

        public CheckInDetails(String username, String email, String roomType, String phone, String address, LocalDate checkInDate) {
            this.username = username;
            this.email = email;
            this.roomType = roomType;
            this.phone = phone;
            this.address = address;
            this.checkInDate = checkInDate;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public LocalDate getCheckInDate() {
            return checkInDate;
        }
    }

//  ------------------------------------------- CHECK-OUT STAGE -------------------------------------------------- //
    
    
    private void showCheckOutStage() {
        primaryStage.setTitle("Check-Out Details");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(100));
        borderPane.setStyle("-fx-background-color: #E3D4AD;");

        TableView<CheckOutDetails> tableView = new TableView<>();

        // TableColumn setup
        TableColumn<CheckOutDetails, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<CheckOutDetails, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<CheckOutDetails, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        TableColumn<CheckOutDetails, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<CheckOutDetails, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<CheckOutDetails, LocalDate> checkOutDateColumn = new TableColumn<>("Check-Out Date");
        checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        // Add columns to TableView
        tableView.getColumns().addAll(usernameColumn, emailColumn, roomTypeColumn, phoneColumn, addressColumn, checkOutDateColumn);

        // ObservableList for TableView
        ObservableList<CheckOutDetails> data = FXCollections.observableArrayList();
        retrieveCheckOutDetailsFromDatabase(data);
        tableView.setItems(data);

        // Set TableView to center of BorderPane
        borderPane.setCenter(tableView);

        // Set the scene
        Scene scene = new Scene(borderPane, 930, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void retrieveCheckOutDetailsFromDatabase(ObservableList<CheckOutDetails> data) {
    
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM checkout_details");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String roomType = resultSet.getString("room_type");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                LocalDate checkOutDate = resultSet.getDate("check_out_date").toLocalDate();
                data.add(new CheckOutDetails(username, email, roomType, phone, address, checkOutDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class CheckOutDetails {
        private final String username;
        private final String email;
        private final String roomType;
        private final String phone;
        private final String address;
        private final LocalDate checkOutDate;

        public CheckOutDetails(String username, String email, String roomType, String phone, String address, LocalDate checkOutDate) {
            this.username = username;
            this.email = email;
            this.roomType = roomType;
            this.phone = phone;
            this.address = address;
            this.checkOutDate = checkOutDate;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public LocalDate getCheckOutDate() {
            return checkOutDate;
        }
    }
//  ------------------------------------------- CHECK HISTORY STAGE -------------------------------------------------- //
    
    
    private void showCheckHistoryStage() {
        Stage checkHistoryStage = new Stage();
        checkHistoryStage.setTitle("Check History");

        // Create your content for the "Check History" stage here...

        Scene scene = new Scene(new Group(), 930, 700); // Adjust size as needed
        checkHistoryStage.setScene(scene);
        checkHistoryStage.show();
    }
 
    
//  ------------------------------------------- USER LOGIN STAGE -------------------------------------------------- //
  
    private void showLoginAndRegisterScreens() {
        primaryStage.setTitle("Login | Register");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setStyle("-fx-background-color: #E3D4AD;"); // Set background color


        Image loginBackgroundImage = new Image("https://www.milandesignagenda.com/wp-content/uploads/2014/10/Worlds-best-lighting-design-ideas-arrives-at-Milans-modern-hotels.jpg");
        BackgroundImage loginBackground = new BackgroundImage(
                loginBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        Background loginBackgroundWithImage = new Background(loginBackground);
        grid.setBackground(loginBackgroundWithImage);

        Text welcomeText = new Text("User Login");
        welcomeText.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-fill: black;");
        grid.add(welcomeText, 1, 0, 2, 1);
        GridPane.setHalignment(welcomeText, HPos.CENTER);


        Label usernameLabel = new Label("Email:");
        usernameLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 24px;");
        grid.add(usernameLabel, 0, 2);

        TextField usernameInput = new TextField();
        usernameInput.setStyle("-fx-background-color: #f2f2f2 ; -fx-font-size: 18px;"); // Set input field style
        grid.add(usernameInput, 1, 2);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 24px;");
        grid.add(passwordLabel, 0, 3);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setStyle("-fx-background-color: #f2f2f2; -fx-font-size: 18px;"); // Set input field style
        grid.add(passwordInput, 1, 3);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold; -fx-font-size: 20px;");
        HBox hbBtn = new HBox(5);
        hbBtn.setPadding(new Insets(0));
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(loginButton);
        grid.add(hbBtn, 2, 4, 2, 1);

        Text createAccountText = new Text("Don't have an account? ");
        createAccountText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: black;");
        grid.add(createAccountText, 0, 6, 2, 1);
        GridPane.setHalignment(createAccountText, HPos.LEFT);

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold; -fx-font-size: 20px;");
        HBox hbBtn2 = new HBox(5);
        hbBtn2.setPadding(new Insets(0));
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(registerButton);
        grid.add(hbBtn2, 2, 6, 2, 1);

        Text messageText = new Text("");
        messageText.setStyle("-fx-font-size: 24px;");
        GridPane messageGrid = new GridPane();
        messageGrid.add(messageText, 0, 0);
        messageGrid.setAlignment(Pos.CENTER);

        loginButton.setOnAction(event -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (authenticateUser(username, password)) {
                showGuestScreen();
            } else {
                showAlert("Login Status", "Incorrect Username or Password \nDon't have an account? Register and try again!");
            }
        });

        registerButton.setOnAction(event -> {
            registerUserScreen();
        });

        Scene scene = new Scene(grid, 930, 700);

        primaryStage.setScene(scene);
        primaryStage.show();

        Button BackButton = new Button("Back");
        BackButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");
        HBox hbBackBtn = new HBox(5);
        hbBackBtn.setPadding(new Insets(0));
        hbBackBtn.setAlignment(Pos.CENTER);
        hbBackBtn.getChildren().add(BackButton);
        grid.add(hbBackBtn, 0, 8, 1, 1);

        BackButton.setOnAction(event -> {
        	AdminScreens();
        });
    }

    
    
//    ------------------------------------------- REGISTER STAGE -------------------------------------------------- //
    

    private void registerUserScreen() {
        Stage registerStage = new Stage();
        registerStage.initModality(Modality.WINDOW_MODAL);
        registerStage.initOwner(primaryStage);
        registerStage.setTitle("Register");

        GridPane registerGrid = new GridPane();
        registerGrid.setAlignment(Pos.CENTER);
        registerGrid.setHgap(20);
        registerGrid.setVgap(20);
        registerGrid.setPadding(new Insets(45, 45, 45, 45));
        registerGrid.setStyle("-fx-background-color: #E3D4AD;");

        Image registerBackgroundImage = new Image("file:///C:/Users/nawal/.eclipse/HotelManagementSystem/Hotel/images/lobbyHotel.jpg");
        BackgroundImage registerBackground = new BackgroundImage(
                registerBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        Background registerBackgroundWithImage = new Background(registerBackground);
        registerGrid.setBackground(registerBackgroundWithImage);

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight : bold ;");
        registerGrid.add(firstNameLabel, 0, 0);

        TextField firstNameInput = new TextField();
        firstNameInput.setStyle("-fx-background-color: #f9f9f9; -fx-font-size: 18px;");
        registerGrid.add(firstNameInput, 1, 0);

        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight : bold ;");
        registerGrid.add(lastNameLabel, 0, 1);

        TextField lastNameInput = new TextField();
        lastNameInput.setStyle("-fx-background-color: #f9f9f9; -fx-font-size: 18px;");
        registerGrid.add(lastNameInput, 1, 1);

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight : bold ;");
        registerGrid.add(emailLabel, 0, 2);

        TextField emailInput = new TextField();
        emailInput.setStyle("-fx-background-color: #f9f9f9; -fx-font-size: 18px;");
        registerGrid.add(emailInput, 1, 2);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight : bold ;");
        registerGrid.add(passwordLabel, 0, 3);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setStyle("-fx-background-color: #f9f9f9; -fx-font-size: 18px;"); // Set input field style
        registerGrid.add(passwordInput, 1, 3);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight : bold ;");
        registerGrid.add(confirmPasswordLabel, 0, 4);

        PasswordField confirmPasswordInput = new PasswordField();
        confirmPasswordInput.setStyle("-fx-background-color: #f9f9f9; -fx-font-size: 18px;"); // Set input field style
        registerGrid.add(confirmPasswordInput, 1, 4);

        Button registerUserButton = new Button("Register");
        registerUserButton.setStyle("-fx-background-color: black; -fx-text-fill:#E3D4AD ; -fx-font-size: 20px; -fx-font-weight : bold ;");
        HBox hbBtn = new HBox(4);
        hbBtn.setPadding(new Insets(0));
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(registerUserButton);
        registerGrid.add(hbBtn, 2, 5);

        Text registerMessageText = new Text("");
        registerMessageText.setStyle("-fx-font-size: 18px;");
        GridPane messageGrid = new GridPane();
        messageGrid.add(registerMessageText, 0, 0);
        messageGrid.setAlignment(Pos.CENTER);

        registerUserButton.setOnAction(event -> {
            String email = emailInput.getText();
            String password = passwordInput.getText();
            String confirmPassword = confirmPasswordInput.getText();
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();

            
//            ---------------------------------------------ALERTS--------------------------------------------------
            // Check for empty fields
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert("Registration Error", "Please fill in all the fields.");
                return;
            }

            // Check for existing user
            if (isUserAlreadyRegistered(email, firstName, lastName)) {
                showAlert("Registration Error", "User with the same email or name already exists.");
                return;
            }

            // Check if password and confirm password match
            if (!password.equals(confirmPassword)) {
                showAlert("Registration Error", "Password and Confirm Password do not match.");
                return;
            }

            // Register the user
            if (registerUser(firstName, lastName, email, password)) {
                showAlert("Registration Status", "Successfully registered.");
                registerStage.close();
            } else {
                showAlert("Registration Status", "Registration failed.");
            }
        });

        Scene scene = new Scene(registerGrid, 930, 700);
        registerStage.setScene(scene);
        registerStage.show();
    }


    
    
//    ---------------------------------- Guest STAGE ----------------------------------------- //
    
    
    
    private void showGuestScreen() {
        primaryStage.setTitle("Guest");

        GridPane guestGrid = new GridPane();
        guestGrid.setAlignment(Pos.CENTER);
        guestGrid.setHgap(20);
        guestGrid.setVgap(20);
        guestGrid.setPadding(new Insets(40, 40, 40, 40));
        guestGrid.setStyle("-fx-background-color: #E3D4AD;");

        // Add background image to the Guest page
//        Image guestBackgroundImage = new Image("https://img.freepik.com/free-photo/3d-rendering-modern-luxury-hotel-office-reception-lounge_105762-1109.jpg?size=626&ext=jpg");
//        BackgroundImage guestBackground = new BackgroundImage(
//                guestBackgroundImage,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
//        );

        // Add "Choose Guest Type" label above the buttons
        Label chooseGuestTypeLabel = new Label("Choose Guest Type");
        chooseGuestTypeLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        guestGrid.add(chooseGuestTypeLabel, 2, 0, 2, 1); // Set row span to 1 to create space

        Button visitorLoginButton = new Button("Visitor");
        visitorLoginButton.setStyle("-fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold; -fx-font-size: 30px;");
        HBox hbBtn = new HBox(5);
        hbBtn.setPadding(new Insets(0));
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(visitorLoginButton);
        guestGrid.add(hbBtn, 2, 3, 2, 1); // Adjust row index to 3

        Button lodgerLoginButton = new Button("Lodger");
        lodgerLoginButton.setStyle("-fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold; -fx-font-size: 30px;");
        HBox hbBtn2 = new HBox(5);
        hbBtn2.setPadding(new Insets(0));
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(lodgerLoginButton);
        guestGrid.add(hbBtn2, 2, 5, 2, 1); // Adjust row index to 5

        visitorLoginButton.setOnAction(event -> {
            showRoomsStage();
        });

        lodgerLoginButton.setOnAction(event -> {
            showRoomsStage();
        });

        Scene scene = new Scene(guestGrid, 930, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


//  ---------------------------------- ROOM STAGE ----------------------------------------- //
    
    
    public void showRoomsStage() {
        primaryStage.setTitle("Services");

        HBox servicesLayout = new HBox(20); // Horizontal layout
        servicesLayout.setAlignment(Pos.CENTER);
        servicesLayout.setStyle("-fx-background-color: #E3D4AD;");

        GridPane standardServiceBox = createServiceBox(
                "Standard Room Service\r\n\r\n"
                + "Comfortable bedding and basic amenities.\r\n"
                + "Standard bathroom facilities.\r\n"
                + "Essential technology.",
                "https://2486634c787a971a3554-d983ce57e4c84901daded0f67d5a004f.ssl.cf1.rackcdn.com/the-georgian-terrace-hotel/media/Georgian-Terrace-Standard-Room-5980ce6762355.jpg"
        );
        
        GridPane deluxeServiceBox = createServiceBox(
                "Deluxe Room Service\r\n\r\n"
                + "Upgraded bedding and luxurious bathroom.\r\n"
                + "Stylish furnishings and modern amenities.\r\n"
                + "Enhanced room experience.",
                "https://mariamyahia.fr/hotelia/img/chambres/deluxe4.jpg"
        );
        GridPane suiteServiceBox = createServiceBox(
                "Suite Room Service\r\n\r\n"
                + "Premium bedding and personalized comfort.\r\n"
                + "Dedicated concierge and butler service.\r\n"
                + "Exclusive amenities like private dining and spa.",
                "https://i.pinimg.com/originals/d7/1f/79/d71f79e1e76221f35f5911488aeb8f0c.jpg"
        );

        servicesLayout.getChildren().addAll(standardServiceBox, deluxeServiceBox, suiteServiceBox);

        Scene scene = new Scene(servicesLayout, 930, 700); // Adjust the height as needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createServiceBox(String description, String imageName) {
        GridPane serviceGrid = new GridPane();
        serviceGrid.setAlignment(Pos.CENTER);
        serviceGrid.setVgap(10);
        serviceGrid.setStyle(" -fx-padding: 20px;");

        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setFitHeight(300); // Increase image height
        imageView.setFitWidth(400); // Increase image width
        StackPane imagePane = new StackPane();
        imagePane.getChildren().add(imageView);
        imagePane.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label(description);
        descriptionLabel.setStyle("-fx-font-size: 18px; -fx-alignment: center; -fx-font-weight: bold;");

        Button serviceButton = new Button("Book Now");
        serviceButton.setStyle("-fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold; -fx-font-size: 19px;");
        serviceButton.setOnAction(event -> {
            showPaymentStage(imageName, imageName, null, null); // Navigate to the payment stage
        });

        serviceGrid.add(imageView, 0, 0, 2, 1);
        serviceGrid.add(descriptionLabel, 0, 1);
        serviceGrid.add(serviceButton, 0, 2);

        GridPane.setColumnSpan(imageView, GridPane.REMAINING); // Span the image across all columns
        GridPane.setColumnSpan(descriptionLabel, GridPane.REMAINING); // Span the description across all columns
        GridPane.setColumnSpan(serviceButton, GridPane.REMAINING); // Span the button across all columns

        return serviceGrid;
    }



    
//  ---------------------------------- PAYMENT STAGE ----------------------------------------- //


    private void showPaymentStage(String serviceName, String roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        Stage paymentStage = new Stage();
        paymentStage.setTitle("Payment Details");

        // Create VBox layout for payment content
        VBox paymentLayout = new VBox();
        paymentLayout.setAlignment(Pos.CENTER);
        paymentLayout.setSpacing(20);
        paymentLayout.setStyle("-fx-background-color:  #E3D4AD;");

        // Labels for payment details with default values if parameters are null
        Label roomTypeLabel = new Label("Room Type: ");
        roomTypeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight : bold;");
        Label priceLabel = new Label("Price: N/A");
        priceLabel.setStyle("-fx-font-size: 20px; ");
        Label checkInLabel = new Label("Check-In Date: " + (checkInDate != null ? checkInDate.toString() : "N/A"));
        checkInLabel.setStyle("-fx-font-size: 20px; ");
        Label checkOutLabel = new Label("Check-Out Date: " + (checkOutDate != null ? checkOutDate.toString() : "N/A"));
        checkOutLabel.setStyle("-fx-font-size: 20px; ");

        // Dropdown menu for selecting room types
        ComboBox<String> roomTypeDropdown = new ComboBox<>();
        roomTypeDropdown.getItems().addAll("Standard Room Service", "Deluxe Room Service", "Suite Room Service");
        roomTypeDropdown.setValue(roomType != null ? roomType : "Standard Room Service");
        roomTypeDropdown.setStyle("-fx-font-size: 20px; width:5px;");

        // Update price label based on selected room type
        roomTypeDropdown.setOnAction(event -> {
            String selectedRoomType = roomTypeDropdown.getValue();
            double selectedPrice = 0.00; // Default value
            switch (selectedRoomType) {
                case "Standard Room Service":
                    selectedPrice = 10000.00;
                    break;
                case "Deluxe Room Service":
                    selectedPrice = 20000.00;
                    break;
                case "Suite Room Service":
                    selectedPrice = 30000.00;
                    break;
            }
            priceLabel.setText("Price: " + String.format("%.2f$", selectedPrice));
        });

        // Date picker for check-in and check-out dates
        DatePicker checkInDatePicker = new DatePicker(checkInDate);
        checkInDatePicker.setStyle("-fx-font-size: 18px;");
        checkInDatePicker.setOnAction(event -> {
            LocalDate selectedDate = checkInDatePicker.getValue();
            checkInLabel.setText("Check-In Date: " + (selectedDate != null ? selectedDate.toString() : "N/A"));
        });

        DatePicker checkOutDatePicker = new DatePicker(checkOutDate);
        checkOutDatePicker.setStyle("-fx-font-size: 18px;");
        checkOutDatePicker.setOnAction(event -> {
            LocalDate selectedDate = checkOutDatePicker.getValue();
            checkOutLabel.setText("Check-Out Date: " + (selectedDate != null ? selectedDate.toString() : "N/A"));
        });

        // Button to confirm payment
        Button confirmPaymentButton = new Button("Confirm Payment");
        confirmPaymentButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: black; -fx-text-fill: #E3D4AD; ");
        confirmPaymentButton.setOnAction(event -> {
            // Get selected values and entered dates
            String selectedRoomType = roomTypeDropdown.getValue();
            LocalDate selectedCheckInDate = checkInDatePicker.getValue();
            LocalDate selectedCheckOutDate = checkOutDatePicker.getValue();

            // Perform database insertion
            try {
                // Establish database connection (replace "url", "username", and "password" with your database connection details)
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO payment_details (room_type, check_in_date, check_out_date) VALUES (?, ?, ?)");

                // Set values for prepared statement
                preparedStatement.setString(1, selectedRoomType);
                preparedStatement.setDate(2, Date.valueOf(selectedCheckInDate));
                preparedStatement.setDate(3, Date.valueOf(selectedCheckOutDate));

                // Execute the SQL statement
                preparedStatement.executeUpdate();

                // Close the prepared statement and connection
                preparedStatement.close();
                connection.close();

                // Show feedback stage
                showFeedbackStage();

                // Close the payment stage
                paymentStage.close();

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database error
            }
        });

        // Add payment details and button to the layout
        paymentLayout.getChildren().addAll(roomTypeLabel, roomTypeDropdown, priceLabel, checkInLabel, checkOutLabel, checkInDatePicker, checkOutDatePicker, confirmPaymentButton);

        Scene scene = new Scene(paymentLayout, 930, 700);
        paymentStage.setScene(scene);
        paymentStage.show();
    }
//  ---------------------------------- FEEDBACK STAGE ----------------------------------------- //
   
    private void showFeedbackStage() {
        Stage feedbackStage = new Stage();
        feedbackStage.setTitle("Feedback");

        // Create layout for feedback content
        VBox feedbackLayout = new VBox();
        feedbackLayout.setAlignment(Pos.CENTER);
        feedbackLayout.setSpacing(30);
        feedbackLayout.setStyle("-fx-background-color: #E3D4AD;");

        // Add "FEEDBACK" text with increased font size
        Text feedbackTitle = new Text("FEEDBACK");
        feedbackTitle.setStyle("-fx-font-size: 38px; -fx-font-weight: bold; -fx-fill: black;");
        feedbackLayout.getChildren().add(feedbackTitle);

        // Add TextArea for feedback
        TextArea feedbackTextArea = new TextArea();
        feedbackTextArea.setStyle("-fx-control-inner-background: white ; -fx-text-fill: black; -fx-font-size: 18px;"); // Increase font size
        feedbackTextArea.setPrefSize(50, 350); // Set preferred width and height to create a box
        feedbackTextArea.setWrapText(true);
        feedbackTextArea.setPromptText("Write your feedback here...");
        feedbackLayout.getChildren().add(feedbackTextArea);

        // Add submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle(" -fx-font-size: 25px ; -fx-background-color: black; -fx-text-fill: #E3D4AD; -fx-font-weight: bold;");
        submitButton.setOnAction(event -> {
            // Handle submission of feedback
            String feedback = feedbackTextArea.getText();
            // Process the feedback (e.g., save to database, display, etc.)
            System.out.println("Feedback: " + feedback);
            feedbackStage.close();
            showThankYouStage();
        });
        feedbackLayout.getChildren().add(submitButton);

        Scene scene = new Scene(feedbackLayout, 930, 700); // Adjust scene size as needed
        feedbackStage.setScene(scene);
        feedbackStage.show();
    }


//  ---------------------------------------------THANK YOU STAGE--------------------------------------------------- //
    
    
    private void showThankYouStage() {
        Stage thankYouStage = new Stage();
        thankYouStage.setTitle("Thank You");

        // Create layout for thank you content
        VBox thankYouLayout = new VBox();
        thankYouLayout.setAlignment(Pos.CENTER);
        thankYouLayout.setSpacing(20);

        // Add "Thank You" text with font size set to 45
        Text thankYouText = new Text("Thank You for Visiting!");
        thankYouText.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill:black; -fx-font-family: \"Times New Roman\";");
        thankYouLayout.getChildren().add(thankYouText);

        // Set background image
        setBackground(thankYouLayout);

        Scene scene = new Scene(thankYouLayout, 930, 700); // Adjust scene size as needed
        thankYouStage.setScene(scene);
        thankYouStage.show();
    }

    private void setBackground(VBox layout) {
        Image backgroundImage = new Image("https://img.freepik.com/free-photo/3d-rendering-modern-luxury-hotel-office-reception-lounge_105762-1109.jpg?size=626&ext=jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        layout.setBackground(new javafx.scene.layout.Background(background));
    }

    
    
//  ---------------------------------------------CONNECTION CODE--------------------------------------------------- //
    

    private boolean authenticateUser(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM login_HMS WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean authenticated = resultSet.next();

            connection.close();

            return authenticated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    private boolean registerUser(String firstName, String lastName, String email, String password) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Insert the new user into the database
            String insertUserSQL = "INSERT INTO login_HMS (first_name, last_name, email, username, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);

            int rowsInserted = preparedStatement.executeUpdate();

            connection.close();

            return rowsInserted > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    private boolean isUserAlreadyRegistered(String email, String firstName, String lastName) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM login_HMS WHERE email = ? OR (first_name = ? AND last_name = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean userExists = resultSet.next();

            connection.close();

            return userExists;
        } catch (SQLException e) {
            e.printStackTrace();
            return true; 
        }
    }
  //--------------------------------Guest queries fetching----------------------------//
 // Method to fetch guest information from the database
    private ResultSet fetchGuests() {
        String sql = "SELECT * FROM guests";
        try {
            Connection connection = null;
			PreparedStatement statement = connection.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Handle error appropriately in your application
        }
    }

    // Method to insert new guest into the database
    private void insertGuest(String firstName, String lastName, String email) {
        String sql = "INSERT INTO guests (first_name, last_name, email) VALUES (?, ?, ?)";
        try {
            Connection connection = null;
			PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error appropriately in your application
        }
    }

//----------------------------
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

