package my.projects.Activities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import my.projects.Consts;
import my.projects.Consts.BackAction;
import my.projects.Records.Project;
import my.projects.Records.Version;

public class NewProject extends JFrame{
    
    private JPanel PTitle,PName,PLanguage_Purpose,PDate,PProject,PDiscreption,PSave,PBack;
    private JLabel Title,Project,Name,Language,Purpose,Date,Discreption;
    private JTextField TName;
    private JTextArea TDiscreption;
    private JComboBox JLanguage,JPurpose;
    private JButton Save,Back;
    private Box Small,box,Big;
    
    private Consts Const=new Consts();
    private Project project;
    private Version version;
    private String Line="NewProject In Line ";
    
    public NewProject(){
        
        Preparation();
        SetFrameAndPanels();
        SetComponents();
        AddComponentsToFrame();
    }
    
    private int GetNumber(){
        int Number=0;
        try{
            File file=new File( Const.File_Path() );
            Scanner output=new Scanner(file);
            
            while(output.hasNext()){
                output.nextLine();
                Number++;
            }
            output.close();
        }
        catch(Exception E){
            Const.GetOut(Line+"62");   
        }
        return Number;
    }
    private void Preparation(){
        
        project=new Project();
        project.SetNumber( GetNumber()+1 );
        project.SetPath( Const.CreatePath(1,project.Namber(),"") );
        project.SetVersionPathFile( Const.CreatePath(2,project.Namber(),"" ));
        project.SetDate( Const.Date() );
        
        version=new Version();
        version.SetNumber(1);
        version.SetPath( Const.CreatePath(3,project.Namber(),"1" ));
        version.SetDate( Const.Date() );
    }
    
    private void SetFrameAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 500 , 500 );
        this.setResizable(false);
        this.setLocation( 410 , 170 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        
        PProject=new JPanel();
        
        PName=new JPanel();
        PName.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PLanguage_Purpose=new JPanel();
        PLanguage_Purpose.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PDate=new JPanel();
        PDate.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PDiscreption=new JPanel();
        
        Small=Box.createVerticalBox();
        Small.setBorder(new EmptyBorder(3,3,3,3));
        
        PSave=new JPanel();
        
        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(3,3,3,3));
        
        PBack=new JPanel();
        
        Big=Box.createVerticalBox();
        Big.setBorder(new EmptyBorder(5,5,5,5));
    }
    private void SetComponents() {
    
        Title=new JLabel();
        Title.setText("New Project");
        Title.setFont( Const.TitleFont() );
        Title.setForeground(Color.BLUE);
        
        Project=new JLabel();
        Project.setText("Project  "+project.Namber());
        
        Name=new JLabel();
        Name.setText("Project Name");
        
        TName=new JTextField();
        TName.setBorder(new EmptyBorder(3,3,3,3));
        TName.setColumns(16);
        TName.setForeground(Color.red);
        
        Language=new JLabel();
        Language.setText("Project Language");
        
        JLanguage=new JComboBox();
        JLanguage.setMaximumRowCount(6);
        JLanguage.addItem("C++");
        JLanguage.addItem("Java");
        JLanguage.addItem("C#");
        JLanguage.addItem("Android");
        
        Purpose=new JLabel();
        Purpose.setText("Purpose");
        
        JPurpose=new JComboBox();
        JPurpose.setMaximumRowCount(6);
        JPurpose.addItem("For Faculty");
        JPurpose.addItem("For Fun");
        JPurpose.addItem("For Work");
        JPurpose.addItem("For Personal Purpose");
        
        Date=new JLabel();
        Date.setText("Date Of Release ==>>     "+Const.Date());
        
        Discreption=new JLabel();
        Discreption.setText("Project Discreption");
        
        TDiscreption=new JTextArea();
        TDiscreption.setColumns(40);
        TDiscreption.setRows(8);
        TDiscreption.setBorder(new EmptyBorder(3,3,3,3));
        TDiscreption.setLineWrap(true);
        TDiscreption.setForeground(Color.red);
        
        Save=new JButton();
        Save.setText("Save Project");
        Save.addActionListener(new SaveAction());
        
        Back=new JButton();
        Back.setText("Back");
        Back.addActionListener(new BackAction(NewProject.this));
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Title);
        
        PProject.add(Project);
        
        PName.add(Name);
        PName.add(Box.createHorizontalStrut(50));
        PName.add(TName);
        
        PLanguage_Purpose.add(Language);
        PLanguage_Purpose.add(Box.createHorizontalStrut(10));
        PLanguage_Purpose.add(JLanguage);
        PLanguage_Purpose.add(Box.createHorizontalStrut(30));
        PLanguage_Purpose.add(Purpose);
        PLanguage_Purpose.add(Box.createHorizontalStrut(10));
        PLanguage_Purpose.add(JPurpose);
        
        
        PDate.add(Date);
        
        PDiscreption.add(Discreption);
        PDiscreption.add(TDiscreption);
        
        Small.add(PProject);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PName);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PLanguage_Purpose);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PDate);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PDiscreption);
        
        PSave.add(Save);
        
        box.add(Small);
        box.add(PSave);
        
        PBack.add(Back);
        
        Big.add(PTitle);
        Big.add(box);
        Big.add(PBack);
        
        this.add(Big,BorderLayout.CENTER);
    }
    
    private class SaveAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            if (TName.getText().length()!=0){
                
                project.SetName( TName.getText() );
                project.SetDiscreption( TDiscreption.getText() );
                project.SetLanguage( JLanguage.getSelectedItem().toString() );
                project.SetPurpose( JPurpose.getSelectedItem().toString() );
                project.SetVersions( version.Path() );
                
                version.SetDiscreption("NO Discreption");
                
                CreateFolder();
                Const.CreateFile( project.Path() , Line+"241" );
                SaveProject();
                
                Const.CreateFile( version.Path() , Line+"244" );
                Const.SaveVersion( version , project.VersionsPathFile() ,Line+"245" );
                
                JOptionPane.showMessageDialog( null, "Project Saved" );
                Intializing();
            }
            else 
                JOptionPane.showMessageDialog( null , "Enter The Full Info" );
        }
        
        private void CreateFolder(){
            try{
                File file=new File( Const.FolderPath()+project.Namber() );
                file.mkdir();
            }
            catch (Exception E){
                Const.GetOut(Line+"260");
            }
        }
        private void SaveProject(){
        
            try{
                File file=new File( project.Path() );
                PrintWriter input=new PrintWriter(file);

                input.println( project.Namber() );
                input.println( project.Name() );
                input.println( project.Language() );
                input.println( project.Purpose() );
                input.println( project.Date() );
                input.println( project.Path() );
                input.println( project.VersionsPathFile() );
                input.println( project.Discreption() );

                input.close();

                Const.SavePath( project.Path(),Const.File_Path(),Line+"280" );
            }
            catch (Exception E){
                Const.GetOut(Line+"283");
            }
        }
        private void Intializing() {
        
            Preparation();
            Project.setText("Project  "+project.Namber());
            TName.setText("");
            TDiscreption.setText("");
        }
    }
}