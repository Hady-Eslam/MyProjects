package my.projects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import my.projects.Records.Project;
import my.projects.Records.Version;

public class Consts {
    
    private final String File_Path = "E:\\My Programs\\ProgramsFiles\\MyProjects\\File.txt",
                    FolderPath = "E:\\My Programs\\ProgramsFiles\\MyProjects\\",
                    Wrong = "Wrong In Opening The File ",
                    Greeting = "Hello Hady";
    
    public String File_Path(){
        return File_Path;
    }
    public String FolderPath(){
        return FolderPath;
    }
    public String Wrong(){
        return Wrong;
    }
    public String Greeting(){
        return Greeting;
    }
    public Font TitleFont(){
        return new Font("",Font.BOLD,15);
    }
    public String Date(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
    public void GetOut(String Location){
        JOptionPane.showMessageDialog( null , Wrong()+Location );
        System.exit(0);
    }
    
    public void CreateFile(String Path,String Location){
        try{
            File file=new File( Path );
            if (!file.exists()){
                file.createNewFile();
            }
        }
        catch (Exception E){
            GetOut(Location);
        }
    }
    public String CreatePath(int For,String Project,String Version){
        if (For==1)
            return FolderPath+Project+"\\File.txt";
        else if (For==2)
            return FolderPath+Project+"\\Version.txt";
        else
            return FolderPath+Project+"\\"+Version+".txt";
    }
    
    public void SavePath(String Input,String Path,String Location){
        try{
            File file=new File(Path);
            PrintWriter input=new PrintWriter(new FileOutputStream(file,true));
            
            input.println(Input);
            input.close();
        }
        catch(Exception E){
            GetOut(Location);
        }
    }
    public void SaveVersion(Version version,String Path,String Location){
        
        try{
            File file=new File( version.Path() );
            PrintWriter input=new PrintWriter(file);
            
            input.println( version.Number() );
            input.println( version.Date() );
            input.println( version.Path() );
            input.println( version.Discreption() );
            
            input.close();
            SavePath( version.Path() , Path , Location );
        }
        catch(Exception E){
            GetOut(Location);
        }
    }
    
    public int GetNumber(String Number){
        
        int N=0;
        for (int i=0;i<Number.length();i++){
            N*=10;
            N+=(int)Number.charAt(i)-48;
        }
        return N;
    }
    public Project GetProject(String Path ,String Location){
        
        Project project=new Project();
        try{
            File file=new File(Path);
            Scanner output=new Scanner(file);
            int n=1;
            
            while(output.hasNext()){
                String Line=output.nextLine();
                if (n==1)
                    project.SetNumber( GetNumber(Line) );
                else if (n==2)
                    project.SetName(Line);
                else if (n==3)
                    project.SetLanguage(Line);
                else if (n==4)
                    project.SetPurpose(Line);
                else if (n==5)
                    project.SetDate(Line);
                else if (n==6)
                    project.SetPath(Line);
                else if (n==7)
                    project.SetVersionPathFile(Line);
                else
                    project.SetDiscreption( project.Discreption()+Line );
                n++;
            }
            for (int i=0;i<GetVersios(project.VersionsPathFile(),Location).size();i++)
                project.SetVersions( GetVersios( project.VersionsPathFile(),Location ).get(i) );
            
            output.close();
        }
        catch(Exception E){
            GetOut(Location);
        }
        return project;
    }
    private ArrayList<String> GetVersios(String Path,String Location){
            
            ArrayList<String> Paths=new ArrayList<String>();
            try{
                File file=new File(Path);
                Scanner output=new Scanner(file);
                
                while(output.hasNext())
                    Paths.add( output.nextLine() );
            }
            catch(Exception E){
                GetOut(Location);
            }
            return Paths;
        }
    
    public static class BackAction implements ActionListener{
        
        private JFrame Frame;
        public BackAction(JFrame Frame){
            this.Frame=Frame;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
        
            Frame.dispose();
            new Main();
        }
    }
}