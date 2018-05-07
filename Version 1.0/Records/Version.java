package my.projects.Records;

public class Version {
    
    private String Number = "",Date = "" ,Discreption = "",Path = "";
    
    public void SetNumber(int Number){
        this.Number=Integer.toString(Number);
    }
    public void SetDiscreption(String Discreption){
        this.Discreption=Discreption;
    }
    public void SetPath(String Path){
        this.Path=Path;
    }
    public void SetDate(String Date){
        this.Date=Date;
    }
    
    public String Number(){
        return Number;
    }
    public String Date(){
        return Date;
    }
    public String Discreption(){
        return Discreption;
    }
    public String Path(){
        return Path;
    }
}