// Sample Java code for user authorization
package kamrul1157024.youtube;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import kamrul1157024.static_saver.YoutubeAuthorization;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;


public class YoutubeVideoUploader {

    YouTube youtube;
    public static YouTube getYouTubeService() throws IOException {
        Credential credential = YoutubeAuthorization.authorization.authorize();
        return new YouTube.Builder(
                YoutubeAuthorization.authorization.HTTP_TRANSPORT, YoutubeAuthorization.authorization.JSON_FACTORY, credential)
                .setApplicationName(YoutubeAuthorization.authorization.APPLICATION_NAME)
                .build();
    }
    public YoutubeVideoUploader()
    {
        try {
            youtube=getYouTubeService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean uploadIt(VideoData vdt)throws IOException {



        try {
            String mime_type = "video/*";
            File media_filename = new File(vdt.getFilename());
            HashMap<String, String> parameters = new HashMap<String,String>();
            parameters.put("part", "snippet,status");


            Video video = new Video();
            VideoSnippet snippet = new VideoSnippet();
            snippet.set("categoryId",vdt.getCatagoryId());
            snippet.set("description",vdt.getDescription());
            snippet.set("title", vdt.getTitle());
            VideoStatus status = new VideoStatus();
            status.set("privacyStatus",vdt.getPrivacy());

            video.setSnippet(snippet);
            video.setStatus(status);

            InputStreamContent mediaContent = new InputStreamContent(mime_type,new FileInputStream(media_filename));
            YouTube.Videos.Insert videosInsertRequest = youtube.videos().insert(parameters.get("part").toString(), video, mediaContent);
            MediaHttpUploader uploader = videosInsertRequest.getMediaHttpUploader();


            uploader.setDirectUploadEnabled(false);
            MediaHttpUploaderProgressListener progressListener = uploader1 -> {
                switch (uploader1.getUploadState()) {
                    case INITIATION_STARTED:
                        System.out.println("Initiation Started");
                        break;
                    case INITIATION_COMPLETE:
                        System.out.println("Initiation Completed");
                        break;
                    case MEDIA_IN_PROGRESS:
                        System.out.println("Upload in progress");
                        System.out.println("Upload percentage: " + uploader1.getNumBytesUploaded());
                        break;
                    case MEDIA_COMPLETE:
                        System.out.println("Upload Completed!");
                        break;
                    case NOT_STARTED:
                        System.out.println("Upload Not Started!");
                        break;
                }
            };
            uploader.setProgressListener(progressListener);
            Video response = videosInsertRequest.execute();
            System.out.println(response);
            return true;
        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }
}