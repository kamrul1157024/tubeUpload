package kamrul1157024.root;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kamrul1157024.scene_management.SceneController;
import kamrul1157024.static_saver.Saver;
import kamrul1157024.files.FileProcess;
import kamrul1157024.static_saver.SceneControllerSaver;
import kamrul1157024.static_saver.YoutubeAuthorization;
import kamrul1157024.youtube.Authorization;
import kamrul1157024.youtube.YoutubeVideoUploader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/Loading.fxml"));
        SceneControllerSaver.primaryStage=primaryStage;
        primaryStage.setTitle("TubeUpload");
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Task<Void> task=new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                SceneControllerSaver.sceneController=new SceneController();
               YoutubeAuthorization.authorization=new Authorization();


                SceneControllerSaver.sceneController.addScreen("Loading", FXMLLoader.load(getClass().getResource("/Loading.fxml")));
                SceneControllerSaver.sceneController.addScreen("Video", FXMLLoader.load(getClass().getResource("/Video.fxml")));
                SceneControllerSaver.sceneController.addScreen("Main", FXMLLoader.load(getClass().getResource("/Main.fxml")));
                SceneControllerSaver.sceneController.addScreen("Progress", FXMLLoader.load(getClass().getResource("/Progress.fxml")));
                Platform.runLater(()-> {
//                    if (!YoutubeAuthorization.authorization.isLoggedin())
//                        primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Main"));
//                    else
//                        primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Video"));
                    SceneControllerSaver.primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Main"));

                });
                System.out.println("OK3");
                Saver.fileProcess=new FileProcess();
                return null;
            }
        };
        Thread thread=new Thread(task);
        thread.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
