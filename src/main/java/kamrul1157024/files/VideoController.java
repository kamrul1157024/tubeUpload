package kamrul1157024.files;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kamrul1157024.root.Main;
import kamrul1157024.scene_management.SceneController;
import kamrul1157024.static_saver.Saver;
import kamrul1157024.static_saver.SceneControllerSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideoController {
    public boolean isAnyFileChoosen=false;
    @FXML private Button file;
    @FXML private CheckBox isSubfolderIncluded;
    @FXML
    void selectFolder()
    {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Folder to Upload Video");
        Stage stage=new Stage();
        File selectedDirectory = chooser.showDialog(stage);
        if(selectedDirectory==null)
        {
            return;
        }
        isAnyFileChoosen=true;
        String path=selectedDirectory.getAbsolutePath();
        int len=path.length();
        String pathLast;
        if(len>25)
        pathLast=path.substring(len-25,len);
        else
            pathLast=path;
        file.setText(pathLast);
        Saver.foldername=path;

    }





    @FXML
    void choosenFilesList() {
        Saver.isSubFolderIncluded = isSubfolderIncluded.isSelected();
        if (isAnyFileChoosen) {
            SceneControllerSaver.primaryStage.setScene(SceneControllerSaver.sceneController.getScene("Loading"));
          Thread loadFiles=new Thread(()->{
              loadingFiles();
          });
          loadFiles.start();
          Task<Void> viewFiles=new Task<Void>() {
              @Override
              protected Void call() throws Exception {
                  loadFiles.join();
                  Platform.runLater(()->{
                      viewFiles();
                  });
                  return null;
              }
          };

          Thread viewFileThread=new Thread(viewFiles);
          viewFileThread.start();
        }
        else
        {
            folderNotSelected();
        }
    }



    void  loadingFiles()
    {
        if(!Saver.isSubFolderIncluded)
            Saver.videos=Saver.fileProcess.getVideos(Saver.foldername);
        else {
            Saver.videos = Saver.fileProcess.getVideosIncludeSubFolder(Saver.foldername);
        }
        Saver.fileDatalists.clear();
        for(String name:Saver.videos) {
            double bytes=new File(name).length();
            double mb=(bytes/1024)/1024;
            String size=Integer.toString((int) Math.round(mb));
            Saver.fileDatalists.add(new FileData(name,size,true));
        }
    }

    void viewFiles()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/File.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root1);
            scene.getStylesheets().add(getClass().getResource("/bootstrap3.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
            Saver.stage = stage;





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void folderNotSelected()
    {

        Stage stage=new Stage();
        Pane pane=new Pane();
        Text text=new Text("Please Select A folder");
        text.relocate(0,15);
        pane.getChildren().add(text);
        Scene scene=new Scene(pane,150,50);
        stage.setScene(scene);
        stage.show();

    }
}
