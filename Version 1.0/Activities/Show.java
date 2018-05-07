package my.projects.Activities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import my.projects.Consts;
import my.projects.Consts.BackAction;
import my.projects.Records.Project;
import my.projects.Records.Version;

public class Show extends JFrame{
    
    private JPanel PTitle,PProject,PName,PLanguage_Date_Purpose,PProjectDiscreption
                    ,PVersion,PVersionDate,PVersionDiscreption,PPre_Next,PBack;
    private JLabel Title,Project,Name,Language,Date,Purpose,Version,VersionDate;
    private JComboBox JProject;
    private JButton ProjectDiscreption,VersionDiscreption,Next,Pre,Back;
    private Box Small,box,Big;
    
    private ArrayList<Project>project=new ArrayList<Project>();
    private ArrayList<Version>version=new ArrayList<Version>();
    private Consts Const=new Consts();
    private int it=0;
    private String Line = "Show In Line ";
    
    public Show(){
        
        Preparation();
        SetFrameAndPanels();
        SetComponents();
        AddComponentsToFrame();
    }
    
    private void Preparation(){
        try{
            File file=new File( Const.File_Path() );
            Scanner output=new Scanner( file );
            
            while(output.hasNext()){
                project.add( Const.GetProject( output.nextLine() , Line+"54") );
            }
        }
        catch(Exception E){
            Const.GetOut(Line+"58");
        }
    }
    
    private void SetFrameAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 470 , 470 );
        this.setResizable(false);
        this.setLocation( 460 , 180 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();

        PProject=new JPanel();
        PProject.setLayout(new FlowLayout(FlowLayout.LEFT));

        PName=new JPanel();
        PName.setLayout(new FlowLayout(FlowLayout.LEFT));

        PLanguage_Date_Purpose=new JPanel();
        PLanguage_Date_Purpose.setLayout(new FlowLayout(FlowLayout.CENTER));

        PProjectDiscreption=new JPanel();

        PVersion=new JPanel();

        PVersionDate=new JPanel();

        PVersionDiscreption=new JPanel();

        PPre_Next=new JPanel();

        Small=Box.createVerticalBox();
        Small.setBorder(new EmptyBorder(5,5,5,5));

        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(5,5,5,5));

        PBack=new JPanel();
        
        Big=Box.createVerticalBox();
        Big.setBorder(new EmptyBorder(5,5,5,5));
    }
    private void SetComponents() {
    
        Title=new JLabel();
        Title.setText("Show My Projects");
        Title.setFont( Const.TitleFont() );
        Title.setForeground(Color.BLUE);
        
        Project=new JLabel();
        Project.setText("Project Name");
        
        JProject=new JComboBox();
        JProject.setMaximumRowCount(5);
        for (int i=0;i<project.size();i++)
            JProject.addItem( project.get(i) );
        JProject.addItemListener(new ItemAction());
        JProject.setSelectedIndex(0);
        
        Name=new JLabel();
        Name.setText("Name");
        
        Language=new JLabel();
        Language.setText("Langauge");
        
        Date=new JLabel();
        Date.setText("Date");
        
        Purpose=new JLabel();
        Purpose.setText("Purpose");
        
        ProjectDiscreption=new JButton();
        ProjectDiscreption.setText("Project Discreption");
        ProjectDiscreption.addActionListener(new PDIS());
        
        Version=new JLabel();
        Version.setText("Version  ");
        
        VersionDate=new JLabel();
        VersionDate.setText("Version Date : ");
        
        VersionDiscreption=new JButton();
        VersionDiscreption.setText("Version Discreption");
        VersionDiscreption.addActionListener(new VDIS());
        
        Pre=new JButton();
        Pre.setText("Previes Version");
        Pre.addActionListener(new PreAction());
        
        Next=new JButton();
        Next.setText("Next Version");
        Next.addActionListener(new NextAction());
        
        Back=new JButton();
        Back.setText("Back");
        Back.addActionListener( new BackAction(Show.this) );
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Title);
        
        PProject.add(Project);
        PProject.add(Box.createHorizontalStrut(50));
        PProject.add(JProject);
        
        PName.add(Name);
        
        PLanguage_Date_Purpose.add(Language);
        PLanguage_Date_Purpose.add(Box.createHorizontalStrut(10));
        PLanguage_Date_Purpose.add(Date);
        PLanguage_Date_Purpose.add(Box.createHorizontalStrut(10));
        PLanguage_Date_Purpose.add(Purpose);
        
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
        box.add(PName);
        box.add(Box.createVerticalStrut(3));
        box.add(PLanguage_Date_Purpose);
        box.add(Box.createVerticalStrut(3));
        box.add(PProjectDiscreption);
        box.add(Small);
        
        PBack.add(Back);
        
        Big.add(PTitle);
        Big.add(box);
        Big.add(PBack);
        
        this.add(Big,BorderLayout.CENTER);
    }
    
    private void SetVersion(){
        
        if (it+1==version.size())
            Version.setText("Last Version  "+version.get(it).Number());
        else
            Version.setText("Version  "+version.get(it).Number());
        VersionDate.setText("Version Date : "+version.get(it).Date());
    }
    
    private class PDIS implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
        
            if (version.size()!=0){
                Project project=(Project)JProject.getSelectedItem();
                new Discreption("Project "+project.Name()+" Discreption",project.Discreption());
            }
        }
    }
    private class VDIS implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            if (version.size()!=0){
                new Discreption("Version "+version.get(it).Number()+" Discreption",version.get(it).Discreption());
            }
        }
    }
    private class PreAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            if (it-1>-1&&!version.isEmpty()){  
                it--;
                SetVersion();
            }
        }
    }
    private class NextAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            if (it+1<version.size()){
                it++;
                SetVersion();
            }
            
        }
    }
    private class ItemAction implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
        
            if (!Show.this.project.isEmpty()){
                
                it=0;
                version.clear();

                Project project=(Project)JProject.getSelectedItem();
                Name.setText("Name ==>> "+project.Name());
                Language.setText("Language : "+project.Language());
                Date.setText("Date : "+project.Date());
                Purpose.setText("Purpose : "+project.Purpose());

                for (int i=0;i<project.Versions().size();i++)
                    version.add( GetVersion( project.Versions().get(i) ) );

                SetVersion();
            }
        }
        public Version GetVersion(String Path){
        
            Version version=new Version();
            try{
                File file=new File(Path);
                Scanner output=new Scanner(file);
                int n=1;

                while(output.hasNext()){
                    String X=output.nextLine();
                    if (n==1)
                        version.SetNumber( Const.GetNumber(X) );
                    else if (n==2)
                        version.SetDate( X );
                    else if (n==3)
                        version.SetPath( X );
                    else
                        version.SetDiscreption( version.Discreption()+X );
                    n++;
                }
            }
            catch(Exception E){
                Const.GetOut(Line+"309");
            }
            return version;
        }
    }
}