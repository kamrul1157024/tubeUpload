package kamrul1157024.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileProcess {
    ArrayList<String> fileListIncludedsubfolder;
    boolean isVideo(String ext)
    {
        if(ext.equals("mp4")||ext.equals("flv")||ext.equals("mkv")||ext.equals("3gp"))
            return true;
        return false;
    }

    public ArrayList<String> getVideos(String name)
    {
        File pathfile=new File(name);
        String[] names=pathfile.list();
        ArrayList<String> list=new ArrayList();
        String last;
        for(String nam:names)
        {
            int len=nam.length();
            File file=new File(nam);
            boolean isfolder=file.isDirectory();
            file=null;
            if(isfolder||nam.length()<3) {
                continue;
            }
            last=nam.substring(len-3,len);
            if(isVideo(last)) {
                String temp=pathfile.getAbsolutePath() + '/' + nam;
                temp=temp.replace('\\','/');
                list.add(temp);
            }
        }
        return list;
    }
    void getAll(File f)
    {
        if(f.isDirectory())
        {
            String []list=f.list();
            for(String s:list)
            {
                s=f.getAbsolutePath()+'/'+s;
                s=s.replace('\\', '/');
                File now=new File(s);
                if(!now.equals(null)){
                    if(now.isDirectory())
                    {
                        getAll(now);
                    }
                    else
                    {
                        fileListIncludedsubfolder.add(now.getAbsolutePath());
                    }

                }
            }
        }
        return;
    }


    public ArrayList<String> getVideosIncludeSubFolder(String name)
    {
        fileListIncludedsubfolder=new ArrayList<>();
        getAll(new File(name));
        ArrayList<String> list=new ArrayList<>();
        for(String nam:fileListIncludedsubfolder)
        {
            int len=nam.length();
            File file=new File(nam);
            boolean isfolder=file.isDirectory();
            file=null;
            if(isfolder||nam.length()<3) {
                continue;
            }
            String last=nam.substring(len-3,len);
            if(isVideo(last))
                list.add(nam);
        }
        return list;
    }
}
