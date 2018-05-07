 package my.projects.Activities;

import my.projects.Activities.Fragements.Discreption;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import my.projects.Main;
import my.projects.Objects.Consts;
import my.projects.Objects.Project;
import my.projects.Objects.Version;

public class Show extends JFrame implements ItemListener,ActionListener{
    
    private JPanel PTitle,PProject,PInfo,PProjectDiscreption,PVersion,PVersionDate,PVersionDiscreption,PPre_Next;
    private JLabel Number,Title,Project,Info,Version,VersionDate;
    private JComboBox JProject;
    private JButton ProjectDiscreption,VersionDiscreption,Next,Pre,Back;
    private Box Small,box;
    
    private ArrayList<Version>version=new ArrayList<>();
    private final Consts Const=new Consts();
    private int Order=0;
    
    public Show(){
        
        SetFrameAndPanels();
        SetComponents();
        AddComponentsToFrame();
    }
    
    private void SetProjects(){
        try{
            File file=new File( Const.File_Path() );
            Scanner output=new Scanner( file );
            
            while(output.hasNext())
                JProject.addItem( Const.GetProject( output.nextLine() , "From Show In Line 50" ) );
        }
        catch(Exception E){
            Const.GetOut( "From Show In Line 53" );
        }
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

        PInfo=new JPanel();
        
        PProjectDiscreption=new JPanel();

        PVersion=new JPanel();

        PVersionDate=new JPanel();

        PVersionDiscreption=new JPanel();

        PPre_Next=new JPanel();

        Small=Box.createVerticalBox();
        Small.setBorder(new EmptyBorder(5,5,5,5));

        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(5,5,5,5));
    }
    private void SetComponents() {
    
        Title=new JLabel();
        Title.setText("Show My Projects");
        Title.setForeground(Color.BLUE);
        Title.setHorizontalAlignment(JLabel.CENTER);
        
        Number=new JLabel();
        Number.setText( "Project   0" );
        Number.setForeground(Color.red);
        
        Project=new JLabel();
        Project.setText("Project Name : ");
        
        JProject=new JComboBox();
        JProject.setMaximumRowCount(5);
        SetProjects();
        JProject.setSelectedIndex(-1);
        JProject.addItemListener( this );
        
        Info=new JLabel();
        Info.setText( Const.SetColor("","","") );
        
        ProjectDiscreption=new JButton();
        ProjectDiscreption.setText("Project Discreption");
        ProjectDiscreption.addActionListener( this );       
        Version=new JLabel();
        Version.setText("Version  ");
        
        VersionDate=new JLabel();
        VersionDate.setText("Version Date : ");
        
        VersionDiscreption=new JButton();
        VersionDiscreption.setText("Version Discreption");
        VersionDiscreption.addActionListener( this );
        
        Pre=new JButton();
        Pre.setText("<=");
        Pre.addActionListener( this );
        
        Next=new JButton();
        Next.setText("=>");
        Next.addActionListener( this );
        
        Back=new JButton();
        Back.setText("<<==");
        Back.addActionListener( this );
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Back,BorderLayout.WEST);
        PTitle.add(Title,BorderLayout.CENTER);
        PTitle.add(Number,BorderLayout.EAST);
        
        PProject.add(Project);
        PProject.add(JProject);
        
        PInfo.add(Info);
        
        PProjectDiscreption.add(ProjectDiscreption);
        
        PVersion.add(Version);
        
        PVersionDate.add(VersionDate);
        
        PVersionDiscreption.add(VersionDiscreption);
        
        PPre_Next.add(Pre);
        PPre_Next.add(Box.createHorizontalStrut(50));
        PPre_Next.add(Next);
        
        Small.add(PVersion);
        Small.add(Box.createVerticalStrut(3));
        Small.add(PVersionDate);
        Small.add(Box.createVerticalStrut(3));
        Small.add(PVersionDiscreption);
        Small.add(Box.createVerticalStrut(3));
        Small.add(PPre_Next);
        
        box.add(PProject);
        box.add(Box.createVerticalStrut(3));
        box.add(PInfo);
        box.add(Box.createVerticalStrut(3));
        box.add(PProjectDiscreption);
        box.add(Small);
        
        this.add(PTitle,BorderLayout.NORTH);
        this.add(box,BorderLayout.CENTER);
    }
    
    private void SetVersion(){
        
        if ( Order+1==version.size() )
            Version.setText("Last Version  "+version.get(Order).Version_Number);
        else
            Version.setText("Version  "+version.get(Order).Version_Number);
        VersionDate.setText("Version Date : "+version.get(Order).Date );
    }
    
    // For Items Displaying
    @Override
    public void itemStateChanged(ItemEvent e) {

        if ( JProject.getSelectedIndex()==-1 )
            return ;
        
        Order=0;
        version.clear();

        Project project=(Project)JProject.getSelectedItem();
        Info.setText( Const.SetColor( project.Language , project.Date , project.Purpose ) );
        Number.setText( "Project   "+project.Number );
        
        for (int i=0;i<project.Versions.size();i++)
            version.add( GetVersion( project.Versions.get(i) ) );
        SetVersion();
    }
    private Version GetVersion(String Path){

        Version version=new Version();
        try{
            File file=new File(Path);
            Scanner output=new Scanner(file);

            while(output.hasNext()){
                String Line=output.nextLine();
                if ( Line.charAt(0)=='V' )
                    version.Version_Number = Line.substring(15);
                else if ( Line.charAt(0)=='P' )
                    version.Path = Line.substring(5);
                else if ( Line.charAt(0)=='D'&&Line.charAt(1)=='a' )
                    version.Date = Line.substring(5);
                else
                    version.Discreption = Line.substring(12) ;
            }
        }
        catch(Exception E){
            Const.GetOut( "From Show In Line 228" );
        }
        return version;
    }
    
    //For Actions
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if ( e.getActionCommand().equals("<<==") ){
            this.dispose();
            new Main();
            return ;
        }
        if ( JProject.getSelectedIndex()==-1 )
            return ;
        
        if ( e.getActionCommand().equals("Project Discreption") ){
            Project project=(Project)JProject.getSelectedItem();
            new Discreption("Project   "+project.Name+"   Discreption",project.Discreption);
        }
        
        else if ( e.getActionCommand().equals("Version Discreption") )
            new Discreption("Version   "+version.get(Order).Version_Number+"   Discreption",version.get(Order).Discreption);
        
        else if ( e.getActionCommand().equals("<=")&&Order-1>-1 ){
            Order--;
            SetVersion();
        }
        else if ( e.getActionCommand().equals("=>")&&Order+1<version.size() ){
            Order++;
            SetVersion();
        }
    }
}