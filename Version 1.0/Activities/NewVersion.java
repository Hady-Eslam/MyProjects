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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import my.projects.Consts;
import my.projects.Consts.BackAction;
import my.projects.Records.Project;
import my.projects.Records.Version;

public class NewVersion extends JFrame{
    
    private JPanel PTitle,PName,PVersion,PLanguage_Date,PDiscreption,PSave,PBack;
    private JLabel Title,Name,Version,Language,Date,Discreption,Purpose;
    private JComboBox JName;
    private JTextArea TDiscreption;
    private JButton Save,Back;
    private Box Small,box,Big;
    
    private Consts Const=new Consts();
    private ArrayList<Project> project=new ArrayList<Project>();
    private String Line ="NewVersion In Line ";
    private boolean Begin=false;
    
    public NewVersion(){
        
        Preparation();
        SetFarmeAndPanels();
        Setcomponents();
        AddComponentsToFarme();
    }
    
    private void Preparation(){
    
        try{
            File file=new File( Const.File_Path() );
            Scanner output=new Scanner( file );
            
            while(output.hasNext()){
                project.add( Const.GetProject( output.nextLine() ,Line+"56" ) );
            }
        }
        catch(Exception E){
            Const.GetOut(Line+"60");
        }
    }
    private void SetFarmeAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 500 , 500 );
        this.setResizable(false);
        this.setLocation( 410 , 170 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        
        PName=new JPanel();
        PName.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        PVersion=new JPanel();
        
        PLanguage_Date=new JPanel();
        PLanguage_Date.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        PDiscreption=new JPanel();
        
        Small=Box.createVerticalBox();
        Small.setBorder(new EmptyBorder(5,5,5,5));
        
        PSave=new JPanel();
        
        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(5,5,5,5));
        
        PBack=new JPanel();
        
        Big=Box.createVerticalBox();
        Big.setBorder(new EmptyBorder(5,5,5,5));
    }
    private void Setcomponents() {
    
        Title=new JLabel();
        Title.setText("New Version");
        Title.setFont( Const.TitleFont() );
        Title.setForeground(Color.BLUE);
        
        Name=new JLabel();
        Name.setText("Project Name");
        
        JName=new JComboBox();
        JName.setMaximumRowCount(10);
        for (int i=0;i<project.size();i++)
            JName.addItem( project.get(i) );
        JName.addItemListener(new ItemAction());
        JName.setSelectedIndex(0);
        
        Version=new JLabel();
        Version.setText("Version");
        
        Language=new JLabel();
        Language.setText("Language : ");
        
        Date=new JLabel();
        Date.setText("Date : ");
        
        Purpose=new JLabel();
        Purpose.setText("Purpose : ");
        
        Discreption=new JLabel();
        Discreption.setText("Version Discreption");
        
        TDiscreption=new JTextArea();
        TDiscreption.setColumns(31);
        TDiscreption.setRows(7);
        TDiscreption.setLineWrap(true);
        TDiscreption.setBorder(new EmptyBorder(3,3,3,3));
        TDiscreption.setForeground(Color.red);
        
        Save=new JButton();
        Save.setText("Save Version");
        Save.addActionListener(new SaveAction());
        
        Back=new JButton();
        Back.setText("Back");
        Back.addActionListener(new BackAction(NewVersion.this));
    }
    private void AddComponentsToFarme() {
    
        PTitle.add(Title);
        
        PName.add(Box.createHorizontalStrut(40));
        PName.add(Name);
        PName.add(Box.createHorizontalStrut(40));
        PName.add(JName);
        
        PVersion.add(Version);
        
        PLanguage_Date.add(Language);
        PLanguage_Date.add(Box.createHorizontalStrut(10));
        PLanguage_Date.add(Date);
        PLanguage_Date.add(Box.createHorizontalStrut(10));
        PLanguage_Date.add(Purpose);
        
        PDiscreption.add(Discreption);
        PDiscreption.add(TDiscreption);
        
        Small.add(PVersion);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PLanguage_Date);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PDiscreption);
        
        PSave.add(Save);
        
        box.add(PName);
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
        
            if (Begin){
                
                Project project=(Project)JName.getSelectedItem();
                Version version=new Version();

                version.SetDate( Const.Date() );
                version.SetNumber( project.Versions().size()+1 );
                version.SetDiscreption( TDiscreption.getText() );
                version.SetPath( Const.CreatePath(3,project.Namber(),version.Number()));

                project.SetVersions( version.Path() );

                Const.CreateFile( version.Path() , Line+"203" );
                Const.SaveVersion( version ,project.VersionsPathFile(), Line+"204" );

                JOptionPane.showMessageDialog(null,"Version Saved");
                Initializing(project);
            }
            else 
                JOptionPane.showMessageDialog(null,"Please Select Project");
        }
        private void Initializing(Project project) {
        
            NewVersion.this.project.clear();
            TDiscreption.setText("");
            JName.setSelectedItem(project);
            Version.setText("Version "+(project.Versions().size()+1));
        }
    }
    private class ItemAction implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
        
            Begin=true;
            Project project=(Project)JName.getSelectedItem();
            Version.setText("Version  "+(project.Versions().size()+1));
            Language.setText("Language : "+project.Language());
            Purpose.setText("Purpose : "+project.Purpose());
            Date.setText("Date : "+Const.Date() );
        }
    }
}