package kamrul1157024.Adapters;

import kamrul1157024.youtube.VideoData;

import java.io.File;

public class FileToVideoDataAdapter {

    private static String generateName(String filePath)
    {
        return new File(filePath).getName();
    }
    public static VideoData convert(String filePath)
    {
        VideoData videoData=new VideoData()
                .setCatagoryId("01")
                .setDescription("No description")
                .setFilename(filePath)
                .setTitle(generateName(filePath))
                .setPrivacy("public");
        return videoData;
    }
}
