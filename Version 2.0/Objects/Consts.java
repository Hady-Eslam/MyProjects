package my.projects.Objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Consts{
    
    public String File_Path(){
        return "E:\\My Programs\\ProgramsFiles\\MyProjects\\File.txt";
    }
    public String FolderPath(){
        return "E:\\My Programs\\ProgramsFiles\\MyProjects\\";
    }
    public String Date(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
    public void GetOut( String Location){
        JOptionPane.showMessageDialog( null , "Wrong In Opening The File "+Location );
        System.exit(0);
    }
    public String SetColor( String Language, String Date, String Purpose){
        return ("<html><head><style type=\"text/css\">\n" +
                        "span{color: green;}</style></head><body>\n" +
                        "<p>Language : <span>"+Language+"</span> &nbsp;Date : <span>"+Date+"</span> "
                        +"&nbsp;Purpose : <span>"+Purpose+"</span></p>\n" +
                    "</body></html>");
    }
    
    public void CreateFile( String Path, String Location){
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
    public String CreatePath( String Path){
        return FolderPath()+Path;
    }
    
    public void SavePath( String Input, String Path, String Location){
        try{
            File file=new File(Path);
            PrintWriter input=new PrintWriter(new FileOutputStream(file,true));
            
            input.println(Input);
            input.close();
        }
        catch(Exception E){
            GetOut( Location);
        }
    }
    public void SaveVersion( Version version, String Location){
        try{
            File file=new File( version.Path );
            PrintWriter input=new PrintWriter(file);
            
            input.println( "Version_Number="+version.Version_Number );
            input.println( "Path="+version.Path );
            input.println( "Date="+version.Date );
            input.println( "Discreption="+version.Discreption );
            input.close();
        }
        catch(Exception E){
            GetOut( Location);
        }
    }
    public void SaveProject( Project project, String Location){
        try{
            File file=new File ( project.Path );
            PrintWriter input=new PrintWriter( file );
            file.delete();
            file.createNewFile();
            
            input.println( "Number="+project.Number );
            input.println( "Name="+project.Name );
            input.println( "Langauge="+project.Language );
            input.println( "Purpose="+project.Purpose );
            input.println( "Path="+project.Path );
            input.println( "Version_Number="+project.Version_Number );
            input.println( "VersionsPathFile="+project.VersionsPathFile );
            input.println( "Date="+project.Date );
            input.println( "Discreption="+project.Discreption );
            input.close();
        }
        catch( Exception E){
            GetOut( Location );
        }
    }
    
    public int GetNumber( String Number, String Location){
        int N=0;
        try{
            for (int i=0;i<Number.length();i++){
                N*=10;
                N+=(int)Number.charAt(i)-48;
            }
        }
        catch(Exception E){
            GetOut( Location );
        }
        return N;
    }
    
    public Project GetProject( String Path, String Location){
        
        Project project=new Project();
        try{
            File file=new File(Path);
            Scanner output=new Scanner(file);
            
            while(output.hasNext()){
                
                String Line=output.nextLine();
                if ( Line.charAt(0)=='N'&&Line.charAt(1)=='u' )
                    project.Number = Line.substring(7);
                else if ( Line.charAt(0)=='N' )
                    project.Name = Line.substring(5);
                else if ( Line.charAt(0)=='L' )
                    project.Language = Line.substring(9);
                else if ( Line.charAt(0)=='P'&&Line.charAt(1)=='u' )
                    project.Purpose = Line.substring(8);
                else if ( Line.charAt(0)=='P' )
                    project.Path = Line.substring(5);
                else if ( Line.charAt(0)=='V'&&Line.charAt(7)=='s' )
                    project.VersionsPathFile = Line.substring(17);
                else if ( Line.charAt(0)=='V' )
                    project.Version_Number = Line.substring(15);
                else if ( Line.charAt(0)=='D'&&Line.charAt(1)=='a' )
                    project.Date = Line.substring(5);
                else
                    project.Discreption = Line.substring(12) ;
            }
            output.close();
            GetVersions( project, Location );
        }
        catch(Exception E){
            GetOut(Location);
        }
        return project;
    }
    private void GetVersions( Project project, String Location){
            
            try{
                File file=new File( project.VersionsPathFile );
                Scanner output=new Scanner( file );
                
                while(output.hasNext())
                    project.Versions.add( output.nextLine() );
            }
            catch(Exception E){
                GetOut(Location);
            }
        }
}