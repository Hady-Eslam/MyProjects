package my.projects.Objects;

import java.util.ArrayList;

public class Project {
    
    public String Number = "",Name = "",Purpose = "",Date = "",Discreption = "",
                    Language = "",Path = "",VersionsPathFile = "",Version_Number="";
    public ArrayList<String> Versions=new ArrayList<>();
    
    @Override
    public String toString(){
        return Name;
    }
}