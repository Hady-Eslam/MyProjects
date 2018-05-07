package my.projects.Activities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import my.projects.Main;
import my.projects.Objects.Consts;
import my.projects.Objects.Project;
import my.projects.Objects.Version;

public class NewProject extends JFrame implements ActionListener{
    
    private JPanel PTitle,PName,PLanguage_Purpose,PDate,PProject,PDiscreption,PSave;
    private JLabel Title,Project,Name,Language,Purpose,Date,Discreption;
    private JTextField TName;
    private JTextArea TDiscreption;
    private JComboBox JLanguage,JPurpose;
    private JButton Save,Back;
    private Box Small,box;
    
    private final Consts Const=new Consts();
    private Project project = new Project();
    private Version version = new Version();
    
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
            Const.GetOut( "From NewProject In Line 60" );   
        }
        return Number;
    }
    private void Preparation(){
        
        project.Number = String.valueOf( GetNumber()+1 ) ;
        project.Path =  Const.CreatePath( project.Number+"\\File.txt" ) ;
        project.Version_Number = "1.0";
        project.VersionsPathFile =  Const.CreatePath( project.Number+"\\Version.txt" );
        project.Date =  Const.Date() ;
        
        version.Version_Number ="1.0";
        version.Path = Const.CreatePath( project.Number+"\\1.txt" );
        version.Date = Const.Date();
        version.Discreption = "NO Discreption";
    }
    
    private void SetFrameAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 400 , 400 );
        this.setResizable(false);
        this.setLocation( 410 , 170 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        PTitle.setLayout(new BorderLayout());
        PTitle.setBorder(new EmptyBorder(5,5,5,5));
        
        PProject=new JPanel();
        
        PName=new JPanel();
        PName.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PLanguage_Purpose=new JPanel();
        PLanguage_Purpose.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PDate=new JPanel();
        PDate.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PDiscreption=new JPanel();
        PDiscreption.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        Small=Box.createVerticalBox();
        Small.setBorder(new EmptyBorder(3,3,3,3));
        
        PSave=new JPanel();
        
        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(3,3,3,3));
    }
    private void SetComponents() {
    
        Back=new JButton();
        Back.setText("<<==");
        Back.addActionListener( this );
        
        Title=new JLabel();
        Title.setText("New Project");
        Title.setForeground(Color.BLUE);
        Title.setHorizontalAlignment(JLabel.CENTER);
        
        Project=new JLabel();
        Project.setForeground(Color.red);
        Project.setText( "Project  "+project.Number );
        
        Name=new JLabel();
        Name.setText("Name : ");
        
        TName=new JTextField();
        TName.setBorder(new EmptyBorder(3,3,3,3));
        TName.setColumns(16);
        TName.setForeground(Color.red);
        
        Language=new JLabel();
        Language.setText("Language : ");
        
        JLanguage=new JComboBox();
        JLanguage.setMaximumRowCount(3);
        JLanguage.setPrototypeDisplayValue("Android");
        JLanguage.addItem("C++");
        JLanguage.addItem("Java");
        JLanguage.addItem("C#");
        JLanguage.addItem("Android");
        JLanguage.addItem("Web");
        JLanguage.setSelectedIndex(-1);
        
        Purpose=new JLabel();
        Purpose.setText("Purpose : ");
        
        JPurpose=new JComboBox();
        JPurpose.setMaximumRowCount(3);
        JPurpose.addItem("For Faculty");
        JPurpose.addItem("For Fun");
        JPurpose.addItem("For Work");
        JPurpose.addItem("For Personal Purpose");
        JPurpose.setPrototypeDisplayValue("For Personal Purp");
        JPurpose.setSelectedIndex(-1);
        
        Date=new JLabel();
        Date.setText("Date Of Release   ==>>     "+Const.Date() );
        
        Discreption=new JLabel();
        Discreption.setText("Project Discreption :-");
        
        TDiscreption=new JTextArea();
        TDiscreption.setColumns(33);
        TDiscreption.setRows(8);
        TDiscreption.setBorder(new EmptyBorder(5,5,5,5));
        TDiscreption.setLineWrap(true);
        TDiscreption.setForeground(Color.red);
        
        Save=new JButton();
        Save.setText("Save");
        Save.addActionListener( this );
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Title,BorderLayout.CENTER);
        PTitle.add(Back,BorderLayout.WEST);
        PTitle.add(Project,BorderLayout.EAST);
        
        PName.add(Name);
        PName.add(Box.createHorizontalStrut(10));
        PName.add(TName);
        
        PLanguage_Purpose.add(Language);
        PLanguage_Purpose.add(JLanguage);
        PLanguage_Purpose.add(Box.createHorizontalStrut(10));
        PLanguage_Purpose.add(Purpose);
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
        
        this.add(PTitle,BorderLayout.NORTH);
        this.add(box,BorderLayout.CENTER);
    }
    
    // For Saving Action
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if ( e.getActionCommand().equals("<<==")){
            this.dispose();
            new Main();
            return ;
        }
        
        if ( TName.getText().length()==0||JLanguage.getSelectedIndex()==-1||JPurpose.getSelectedIndex()==-1 ){
            JOptionPane.showMessageDialog( null , "Please Enter The Full Info" );
            return ;
        }
        
        project.Versions.add( version.Path );
        
        CreateFile();
        SaveProject();
        
        Const.CreateFile( version.Path , "From NewProject In Line 239" );
        Const.SaveVersion( version , "From NewProject In Line 240" );
        Const.SavePath( version.Path, project.VersionsPathFile, "From NewProject In Line 241");
        
        JOptionPane.showMessageDialog( null, "Project Saved" );
        Intializing();
        
    }
    private void CreateFile(){
        try{
            File file=new File( Const.FolderPath()+project.Number );
            file.mkdir();
            Const.CreateFile( project.Path, "From NewProject In Line 251" );
        }
        catch (Exception E){
            Const.GetOut( "From NewProject In Line 254" );
        }
    }
    private void SaveProject(){

        project.Name = TName.getText();
        project.Language = JLanguage.getSelectedItem().toString();
        project.Purpose = JPurpose.getSelectedItem().toString();
        if ( TDiscreption.getText().length()==0 )
            project.Discreption = "No Discreption";
        else
            project.Discreption = TDiscreption.getText().replace( '\n', '_' );
        
        Const.SaveProject( project, "From NewProject In Line 267" );
        Const.SavePath( project.Path,Const.File_Path(), "From NewProject In Line 268" );
    }
    private void Intializing() {

        Preparation();
        JLanguage.setSelectedIndex(-1);
        JPurpose.setSelectedIndex(-1);
        Project.setText( "Project  "+project.Number );
        TName.setText("");
        TDiscreption.setText("");
    }
}