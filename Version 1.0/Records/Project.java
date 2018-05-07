package my.projects.Records;

import java.util.ArrayList;

public class Project {
    
    private String Number = "",Name = "",Purpose = "",Date = "",Discreption = "",
                    Language = "",Path = "",VersionsPathFile = "";
    private ArrayList<String> Versions=new ArrayList<String>();
    
    public void SetNumber(int Number){
        this.Number=Integer.toString(   Number  );
    }
    public void SetPath(String Path){
        this.Path=Path;
    }
    public void SetVersionPathFile(String Path){
        this.VersionsPathFile=Path;
    }
    public void SetName(String Name){
        this.Name=Name;
    }
    public void SetPurpose(String Purpose){
        this.Purpose=Purpose;
    }
    public void SetDiscreption(String Discreption){
        this.Discreption=Discreption;
    }
    public void SetLanguage(String Language){
        this.Language=Language;
    }
    public void SetVersions(String Version){
        this.Versions.add(Version);
    }
    public void SetDate(String Date){
        this.Date=Date;
    }
    
    public String Namber(){
        return Number;
    }
    public String Path(){
        return Path;
    }
    public String VersionsPathFile(){
        return VersionsPathFile;
    }
    public String Name(){
        return Name;
    }
    public String Purpose(){
        return Purpose;
    }
    public String Date(){
        return Date;
    }
    public String Discreption(){
        return Discreption;
    }
    public String Language(){
        return Language;
    }
    public ArrayList<String> Versions(){
        return Versions;
    }
    
    public String toString(){
        return Name;
    }
}