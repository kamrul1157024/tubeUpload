package kamrul1157024.progress;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import kamrul1157024.Adapters.FileToVideoDataAdapter;
import kamrul1157024.static_saver.Saver;
import kamrul1157024.youtube.YoutubeVideoUploader;

import java.io.IOException;

public class ProgressController {
    @FXML private ProgressBar progressBar;
    private void uploadToYoutube(String filePath)
    {
        Task<Void> youtubeUploadingTask=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                YoutubeVideoUploader youtubeVideoUploader=new YoutubeVideoUploader();
                try {
                    youtubeVideoUploader.uploadIt(FileToVideoDataAdapter.convert(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        Thread youtubeUploadingthread=new Thread(youtubeUploadingTask);
        youtubeUploadingthread.start();

    }


@FXML
public void initialize() {
      //  uploadToYoutube(Saver.fileToUpload);
}


}
