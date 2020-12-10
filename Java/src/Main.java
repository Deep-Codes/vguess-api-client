import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Main extends Application {
    String randomGenWord;
    String asterisk;
    String choiceSelected;
    String genWord;
    int initDashed = 1;
    String hint = "hint to show";
    String word = "dashed word to show";
    Label dashWord = new Label(word);
    Label hintWord  = new Label(hint);
    Label lifeLabel = new Label("Lives Remaining: 6");
    TextField userInput = new TextField();
    Button startGameBtn = new Button("Start Game");
    int livesRemaining = 6;
    Boolean isGameOver = false;
    int levelCounter = 1;
    int scoreCounter = 0;
    int questionCounter = 1;
    Label levelLabel = new Label("Level: "+levelCounter);
    Label scoreLabel = new Label("Score: "+scoreCounter);
    Label gameOverScoreLabel = new Label();
    ImageView sprite ;




    public String[] getData (String lang) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7000/api/"+lang))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JsonObject[] resJavaObj = new Gson().fromJson(response.body(), JsonObject[].class);
        Random rand = new Random();
        int random = (int) (Math.random() * (resJavaObj.length));
        JsonObject quesObj = new Gson().fromJson(String.valueOf(resJavaObj[random]), JsonObject.class);
        randomGenWord = quesObj.get("word").toString().replace("\"","");
        hint = quesObj.get("hint").toString();

        return new String[]{randomGenWord , hint};
    }




    @Override
    public void start(Stage stage) throws Exception {
        /*
            TO DO Error List!!!!!
            Error in states after first game
            Mystery of Home Button in About Page
            Mystery of Home Button in Game Page
            Set fixed Width & Height
         */

        // Steps to Create JavaFx Scenes

        // 1. Create a layout ( Anchor Pane )
        // Must end with 'Page'
        // AnchorPane landingPage = new AnchorPane();

        // 2. Creating a Scene
        // Must end with 'Scene'
        //  landingScene = new Scene(parent);

        // 3. Add Elements in the Layout
        // landingScene.getChildren().add(logo);

        // 4. Setting up Styling
        // landingScene.getStylesheets().add("stylesheets/landingPage.css");

        // Adding a Style
        // test.getStyleClass().add("test");

        // 4. Setting up the Scene into the Stage
        // stage.setScene(landingScene);

        // Game Hierarchy
        // Landing Scene ->
        // Start Game ->

        // Not to be altered
        stage.setTitle("vGuess");
        stage.setWidth(700);
        stage.setHeight(550);

        /* START OF THE LANDING PAGE */

        // Parent layouts
        VBox landingPage = new VBox();
        landingPage.setAlignment(Pos.CENTER);
        landingPage.setSpacing(20);
        landingPage.getStyleClass().add("landingPage");

        VBox gamePage = new VBox();
        gamePage.setAlignment(Pos.CENTER);
        gamePage.setSpacing(20);
        gamePage.getStyleClass().add("gamePage");

        // Game Over Page
        VBox gameOverPage = new VBox();
        gameOverPage.setAlignment(Pos.CENTER);
        gameOverPage.setSpacing(20);
        gameOverPage.getStyleClass().add("gameOverPage");

        // Help Page
        VBox helpPage = new VBox();
        helpPage.setAlignment(Pos.CENTER);
        helpPage.setSpacing(20);
        helpPage.getStyleClass().add("helpPage");

        Button helpButton = new Button("Help");
        helpButton.getStyleClass().add("helpButton");
        startGameBtn.getStyleClass().add("startGameBtn");
        startGameBtn.setDisable(true);

        Button aboutButton = new Button("About");
        aboutButton.getStyleClass().add("helpButton");

        HBox helpAndAbout = new HBox(helpButton,aboutButton);
        helpAndAbout.setSpacing(20);
        helpAndAbout.setAlignment(Pos.BASELINE_CENTER);

        HBox scoreAndLevel = new HBox(scoreLabel,levelLabel);
        scoreAndLevel.setSpacing(20);
        scoreAndLevel.setAlignment(Pos.BASELINE_CENTER);

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("homeButton");

        Button secondHomeButton = new Button("Home");
        secondHomeButton.getStyleClass().add("homeButton");

        // About Page
        VBox aboutPage  = new VBox();
        aboutPage.setAlignment(Pos.CENTER);
        aboutPage.setSpacing(20);
        aboutPage.getChildren().add(secondHomeButton);
        aboutPage.getStyleClass().add("aboutPage");

        ImageView aboutHead = new ImageView("about.png");
        aboutPage.getChildren().add(aboutHead);
        aboutHead.getStyleClass().add("aboutHead");

        Label aboutMade = new Label("Project Completed with Java,JavaFx and CSS");
        aboutPage.getChildren().add(aboutMade);
        aboutMade.getStyleClass().add("aboutMade");

        Label aboutLabel = new Label(
                "1) 19101A0004 Mohit Santosh \n\n" +
                        "2) 19101A0028 Aman Singh \n\n" +
                        "3) 19101A0029 Kaartik Nayak \n\n" +
                        "4) 19101A0033 Deepankar Bhade\n");
        aboutLabel.getStyleClass().add("aboutLabel");
        aboutPage.getChildren().add(aboutLabel);

        Label aboutVersion = new Label("VGUESS v1.0.0");
        aboutPage.getChildren().add(aboutVersion);
        aboutVersion.getStyleClass().add("aboutVersion");


        // Scenes
        Scene landingScene = new Scene(landingPage);
        landingScene.getStylesheets().add("stylesheets/landingPage.css");
        Scene gameScene = new Scene(gamePage);
        gameScene.getStylesheets().add("stylesheets/landingPage.css");
        Scene gameOverScene = new Scene(gameOverPage);
        gameOverScene.getStylesheets().add("stylesheets/landingPage.css");
        Scene helpScene = new Scene(helpPage);
        helpScene.getStylesheets().add("stylesheets/landingPage.css");

        Scene aboutScene = new Scene(aboutPage);
        aboutScene.getStylesheets().add("stylesheets/landingPage.css");


        // vGuess LOGO
        ImageView logo  = new ImageView("logo1.png");
        landingPage.getChildren().add(logo);
        //Game Over Logo
        ImageView gameOverImage = new ImageView("gameOver.png");
        gameOverPage.getChildren().add(gameOverImage);
        gameOverPage.getChildren().add(gameOverScoreLabel);
        gameOverScoreLabel.getStyleClass().add("gameOverScoreLabel");
        gameOverPage.getChildren().add(secondHomeButton);

        //  Language
        Label selectedLang = new Label("Selected Language : None");
        selectedLang.getStyleClass().add("selectedLang");
        landingPage.getChildren().add(selectedLang);
        selectedLang.setLayoutX(260);
        selectedLang.setLayoutY(180);

        // Menu Items
        MenuItem lang1 = new MenuItem("Java");
        MenuItem lang2 = new MenuItem("JavaScript");
        MenuItem lang3 = new MenuItem("Python");
        MenuItem lang4 = new MenuItem("HTML");
        MenuItem lang5 = new MenuItem("CSS");

        MenuButton menu1 = new MenuButton("Select a Language",null,lang1,lang2,lang3,lang4,lang5);
        landingPage.getChildren().add(menu1);

        lang1.setOnAction(actionEvent -> {
            startGameBtn.setDisable(false);
            startGameBtn.getStyleClass().add("clickedStartGameBtn");
            choiceSelected = lang1.getText();
            System.out.println(choiceSelected);
            selectedLang.setText("Selected Language : "+lang1.getText());
        });
        lang2.setOnAction(actionEvent -> {
            startGameBtn.setDisable(false);
            startGameBtn.getStyleClass().add("clickedStartGameBtn");
            choiceSelected = lang2.getText();
            System.out.println(choiceSelected);
            selectedLang.setText("Selected Language : "+lang2.getText());
        });
        lang3.setOnAction(actionEvent -> {
            startGameBtn.setDisable(false);
            startGameBtn.getStyleClass().add("clickedStartGameBtn");
            choiceSelected = lang3.getText();
            System.out.println(choiceSelected);
            selectedLang.setText("Selected Language : "+lang3.getText());
        });
        lang4.setOnAction(actionEvent -> {
            startGameBtn.setDisable(false);
            startGameBtn.getStyleClass().add("clickedStartGameBtn");
            choiceSelected = lang4.getText();
            System.out.println(choiceSelected);
            selectedLang.setText("Selected Language : "+lang4.getText());
        });
        lang5.setOnAction(actionEvent -> {
            startGameBtn.setDisable(false);
            startGameBtn.getStyleClass().add("clickedStartGameBtn");
            choiceSelected = lang5.getText();
            System.out.println(choiceSelected);
            selectedLang.setText("Selected Language : "+lang5.getText());
        });

        landingPage.getChildren().add(startGameBtn);

        landingPage.getChildren().add(helpAndAbout);

        helpButton.setOnAction(actionEvent -> {
            //Change Scene
            stage.setScene(helpScene);
            //Help Page
            Label helpLabel = new Label("Welcome to Vguess!!!!\n\n" +
                    "This Application is designed to help you learn your programming syntax\n\n\n" +
                    "Here are the Rules\n" +
                    "1). Chose the language you want to learn.\n\n" +
                    "2). You will be given a random word related to the Language.\n\n" +
                    "3). The Hint to the word will also be given.\n\n" +
                    "4). You will have to guess the words before you run out of lives.\n" +
                    "\n" +
                    "Have Fun!!");
            helpPage.getChildren().add(homeButton);
            helpPage.getChildren().add(helpLabel);
            helpLabel.getStyleClass().add("helpLabel");
        });

        homeButton.setOnAction(actionEvent -> {
            stage.setScene(landingScene);
        });
        secondHomeButton.setOnAction(actionEvent -> {
            stage.setScene(landingScene);
        });

        aboutButton.setOnAction(actionEvent -> {
            stage.setScene(aboutScene);
        });

        /* GAME PAGE */

//      SPRITE
        sprite = new ImageView("sp6.png");
        sprite.setFitWidth(120);
        sprite.setPreserveRatio(true);
        gamePage.getChildren().add(sprite);

        dashWord.getStyleClass().add("dashWord");
        landingPage.getChildren().add(dashWord);

        dashWord.setLineSpacing(20);
        gamePage.getChildren().add(dashWord);

        levelLabel.getStyleClass().add("levelLabel");
//        gamePage.getChildren().add(levelLabel);

        scoreLabel.getStyleClass().add("scoreLabel");
//        gamePage.getChildren().add(scoreLabel);

        gamePage.getChildren().add(scoreAndLevel);

        lifeLabel.getStyleClass().add("lifeLabel");
        gamePage.getChildren().add(lifeLabel);

        hintWord.getStyleClass().add("hintWord");
        landingPage.getChildren().add(hintWord);
        gamePage.getChildren().add(userInput);
        gamePage.getChildren().add(hintWord);

        userInput.setMaxWidth(160);
        userInput.setPromptText("Enter a Guess!");

        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        userInput.setTextFormatter(formatter);

        Button submitLetterBtn = new Button("Check Letter");
        submitLetterBtn.setLayoutX(280);
        submitLetterBtn.setLayoutY(310);
        submitLetterBtn.getStyleClass().add("submitLetterBtn");
        gamePage.getChildren().add(submitLetterBtn);
        gamePage.getChildren().add(homeButton);
        submitLetterBtn.setOnAction(actionEvent -> {
            if(!userInput.getText().trim().isEmpty()) {
                System.out.println(userInput.getText());
                try {
                    checkGuess(genWord, userInput.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInput.clear();
                if(isGameOver)
                {
                    System.out.println("Game Over");

                    if(scoreCounter < 9){
                        gameOverScoreLabel.setText("You Lose:  \n Your Score : "+scoreCounter);
                    } else if(scoreCounter == 9) {
                        gameOverScoreLabel.setText("Congratulations!!: \n Your Score : "+scoreCounter);
                    }


                    stage.setScene(gameOverScene);
                }
            }
            else
            {
                userInput.setPromptText("Enter a Guess!");
                System.out.println("No Input");
            }

        });
        userInput.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                System.out.println("Enter works");
                System.out.println(userInput.getText());
                try {
                    checkGuess(genWord,userInput.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInput.clear();
                if(isGameOver)
                {
                    System.out.println("Game Over");
                    if(scoreCounter < 9){
                        gameOverScoreLabel.setText("You Lose  Your Score : "+scoreCounter);
                    } else if(scoreCounter == 9) {
                        gameOverScoreLabel.setText("Congratulations!! Your Score : "+scoreCounter);
                    }
                    stage.setScene(gameOverScene);
                }
            }
        });

        startGameBtn.setOnAction(actionEvent ->{
            // Init Score Counter
            scoreCounter = 0;
            // Init Level Label
            levelCounter = 1;
            levelLabel.setText("Level: "+levelCounter);
            // Init Dashed Boolean to Create new star string string
            if ( initDashed == 0){
                initDashed = 1;
            }
            // Init the Sprite to Sp6;
            Image sp6 = new Image("sp6.png");
            sprite.setImage(sp6);
            // Init the Lives 6;
            livesRemaining = 6;
            lifeLabel.setText("Life Remaining: "+livesRemaining);
            // Init gameOver
            isGameOver = false;
            // Change Scene
            stage.setScene(gameScene);
            try {
                genWord = generateWord(choiceSelected);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        // Set up the Scene
        stage.setScene(landingScene);

        /* END OF THE LANDING PAGE */

        stage.show();
    }

    public String generateWord(String choiceSelected) throws IOException, InterruptedException {
        System.out.println(choiceSelected);
        switch (choiceSelected) {
            case "Python": {

                String[] tempData = getData("python");
                String genWord = tempData[0];
                String hint = tempData[1];

                System.out.println("Word" +genWord +", Hint"+ hint);

                if (initDashed == 1) {
                    initDashed--;
                    System.out.println("I run only once");
                    asterisk = new String(new char[genWord.length()]).replace("\0", "*");
                    dashWord.setText(asterisk.replace("", "  ").trim());
                }

                hintWord.setText(hint);
                break;
            }
            case "JavaScript": {

                String[] tempData = getData("javascript");
                String genWord = tempData[0];
                String hint = tempData[1];

                System.out.println("Word" +genWord +"Hint"+ hint);

                if (initDashed == 1) {
                    initDashed--;
                    System.out.println("I run only once");
                    asterisk = new String(new char[genWord.length()]).replace("\0", "*");
                    dashWord.setText(asterisk.replace("", "  ").trim());
                }

                hintWord.setText(hint);
                break;
            }
            case "Java": {

                String[] tempData = getData("java");
                String genWord = tempData[0];
                String hint = tempData[1];

                System.out.println("Word" +genWord +", Hint"+ hint);

                if (initDashed == 1) {
                    initDashed--;
                    System.out.println("I run only once");
                    asterisk = new String(new char[genWord.length()]).replace("\0", "*");
                    dashWord.setText(asterisk.replace("", "  ").trim());
                }

                hintWord.setText(hint);
                break;
            }
            case "CSS": {

                String[] tempData = getData("css");
                String genWord = tempData[0];
                String hint = tempData[1];

                System.out.println("Word" +genWord +", Hint"+ hint);

                if (initDashed == 1) {
                    initDashed--;
                    System.out.println("I run only once");
                    asterisk = new String(new char[genWord.length()]).replace("\0", "*");
                    dashWord.setText(asterisk.replace("", "  ").trim());
                }

                hintWord.setText(hint);
                break;
            }
            case "HTML": {
                String[] tempData = getData("html");
                String genWord = tempData[0];
                String hint = tempData[1];

                System.out.println("Word" +genWord +", Hint"+ hint);

                if (initDashed == 1) {
                    initDashed--;
                    System.out.println("I run only once");
                    asterisk = new String(new char[genWord.length()]).replace("\0", "*");
                    dashWord.setText(asterisk.replace("", "  ").trim());
                }

                hintWord.setText(hint);
                break;
            }
        }
        return randomGenWord;
    }

    public void checkGuess(String realWord , String userGuess ) throws IOException, InterruptedException {
        String newasterisk = "";
        for (int i = 0; i < realWord.length(); i++) {
            if (realWord.charAt(i) == userGuess.charAt(0)) {
                newasterisk += userGuess.charAt(0);
            } else if (asterisk.charAt(i) != '*') {
                newasterisk += realWord.charAt(i);
            } else {
                newasterisk += "*";
            }
        }
        if (asterisk.equals(newasterisk)) {
            livesRemaining--;
            if ( levelCounter == 1){
                if(livesRemaining == 5){
                    Image sp5 = new Image("sp5.png");
                    sprite.setImage(sp5);
                }
                if(livesRemaining == 4){
                    Image sp4 = new Image("sp4.png");
                    sprite.setImage(sp4);
                }
                if(livesRemaining == 3){
                    Image sp3 = new Image("sp3.png");
                    sprite.setImage(sp3);
                }
                if(livesRemaining == 2){
                    Image sp2 = new Image("sp2.png");
                    sprite.setImage(sp2);
                }
                if(livesRemaining == 1){
                    Image sp1 = new Image("sp1.png");
                    sprite.setImage(sp1);
                }
            } else if (levelCounter == 2){
                if(livesRemaining == 4){
                    Image sp6 = new Image("sp6.png");
                    sprite.setImage(sp6);
                }
                if(livesRemaining == 3){
                    Image sp4 = new Image("sp4.png");
                    sprite.setImage(sp4);
                }
                if(livesRemaining == 2){
                    Image sp2 = new Image("sp2.png");
                    sprite.setImage(sp2);
                }
                if(livesRemaining == 1){
                    Image sp1 = new Image("sp1.png");
                    sprite.setImage(sp1);
                }
            } else if (levelCounter == 3){
                if(livesRemaining == 2){
                    Image sp6 = new Image("sp6.png");
                    sprite.setImage(sp6);
                }
                if(livesRemaining == 1){
                    Image sp2 = new Image("sp2.png");
                    sprite.setImage(sp2);
                }
            }

            lifeLabel.setText("Lives Remaining: "+livesRemaining);
            System.out.println("Lives Remaining: "+livesRemaining);
            if(livesRemaining==0)
            {
                System.out.println("You Lose");
                isGameOver = true;
            }
        } else {
            asterisk = newasterisk;
        }
        if (asterisk.equals(realWord)) {
            System.out.println("Correct! You win! The word was " + realWord);
            scoreCounter++;
            scoreLabel.setText("Score: " +scoreCounter);
            questionCounter++;
            if(questionCounter == 2)
            {
                Image sp6 = new Image("sp6.png");
                sprite.setImage(sp6);
                livesRemaining = 6;
                lifeLabel.setText("Life Remaining: "+livesRemaining);
                levelLabel.setText("Level: "+levelCounter);
            }
            if(questionCounter == 3)
            {
                Image sp6 = new Image("sp6.png");
                sprite.setImage(sp6);
                livesRemaining = 6;
                lifeLabel.setText("Life Remaining: "+livesRemaining);
                levelLabel.setText("Level: "+levelCounter);
            }
            if(questionCounter == 4) {
                Image sp6 = new Image("sp6.png");
                sprite.setImage(sp6);
                levelCounter++;
                questionCounter = 1;
            }
            initDashed++;
            if ( levelCounter == 2){
                livesRemaining = 4;
                lifeLabel.setText("Life Remaining: "+livesRemaining);
                levelLabel.setText("Level: "+levelCounter);
            } else if (levelCounter == 3) {
                livesRemaining = 2;
                lifeLabel.setText("Life Remaining: "+livesRemaining);
                levelLabel.setText("Level: "+levelCounter);
            } else if (levelCounter == 4){
                isGameOver = true;
            }
            genWord = generateWord(choiceSelected);
            System.out.println("Level: " +levelCounter+"started");
        }
        System.out.println(asterisk);
        dashWord.setText(asterisk.replace("", "  ").trim());

    }

    public static void main(String[] args) {
        System.out.println("Init vGuess game");
        launch(args);
        System.out.println("Closing vGuess game");
    }
}