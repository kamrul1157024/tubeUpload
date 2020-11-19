package kamrul1157024.root;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import kamrul1157024.scene_management.SceneController;
import kamrul1157024.static_saver.SceneControllerSaver;
import kamrul1157024.static_saver.YoutubeAuthorization;

public class MainController {
    private volatile boolean isDone=false;
    @FXML
    public void login()
    {
        SceneControllerSaver.primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Loading"));
        Thread thread=new Thread(() -> {
            YoutubeAuthorization.authorization.login();
            isDone=true;
            System.out.println("ok");
        });
        thread.start();
        Task<Void> task=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!isDone);
                Platform.runLater(()->{
                    SceneControllerSaver.primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Video"));
                });
                return null;
            }

        };
        Thread taskThread=new Thread(task);
        taskThread.start();


    }
}
