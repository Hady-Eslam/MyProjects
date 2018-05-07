package my.projects.Activities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import my.projects.Main;
import my.projects.Objects.Consts;
import my.projects.Objects.Project;
import my.projects.Objects.Version;

public class NewVersion extends JFrame implements ActionListener,ItemListener{
    
    private JPanel PTitle,PName,PVersion,PInfo,PDiscreption,PSave;
    private JLabel Title,Number,Name,Info,Discreption;
    private JCheckBox VBig,VSmall;
    private JComboBox JName;
    private JTextArea TDiscreption;
    private JButton Save,Back;
    private Box Small,box;
    
    private final Consts Const=new Consts();
    
    public NewVersion(){
        
        SetFarmeAndPanels();
        Setcomponents();
        AddComponentsToFarme();
    }
    
    private void SetProjets(){
        try{
            File file=new File( Const.File_Path() );
            Scanner output=new Scanner( file );
            
            while(output.hasNext())
                JName.addItem( Const.GetProject( output.nextLine(), "From NewVersion In Line 52"));
        }
        catch(Exception E){
            Const.GetOut( "From NewVersion In Line 55" );
        }
    }
    private void SetColors(){
        if ( VBig.isSelected() )
            VBig.setForeground( Color.decode("#629B06") );
        else 
            VBig.setForeground( Color.red );
        
        if ( VSmall.isSelected() )
            VSmall.setForeground( Color.decode("#629B06") );
        else
            VSmall.setForeground( Color.red );
    }
    
    private void SetFarmeAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 430 , 400 );
        this.setResizable(false);
        this.setLocation( 410 , 170 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        PTitle.setLayout(new BorderLayout());
        PTitle.setBorder(new EmptyBorder(5,5,5,5));
        
        PName=new JPanel();
        
        PVersion=new JPanel();
        
        PInfo=new JPanel();
        PInfo.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        PDiscreption=new JPanel();
        PDiscreption.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        Small=Box.createVerticalBox();
        Small.setBorder(new EmptyBorder(5,5,5,5));
        
        PSave=new JPanel();
        
        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(5,5,5,5));
    }
    private void Setcomponents() {
    
        Back=new JButton();
        Back.setText("<<==");
        Back.addActionListener( this );
        
        Title=new JLabel();
        Title.setText("New Version");
        Title.setForeground(Color.BLUE);
        Title.setHorizontalAlignment(JLabel.CENTER);
        
        Number=new JLabel();
        Number.setText( "Project   0" );
        Number.setForeground(Color.red);
        
        Name=new JLabel();
        Name.setText("Project Name : ");
        
        JName=new JComboBox();
        JName.setPrototypeDisplayValue("Hello From World");
        JName.setMaximumRowCount(10);
        SetProjets();
        JName.setSelectedIndex(-1);
        JName.addItemListener( this );
        
        VBig=new JCheckBox();
        VBig.setText("0");
        VBig.addActionListener( this );
        
        VSmall=new JCheckBox();
        VSmall.setText("0");
        VSmall.addActionListener( this );
        
        Info=new JLabel();
        Info.setText( Const.SetColor("","","") );
        
        Discreption=new JLabel();
        Discreption.setText("Version Discreption :-");
        
        TDiscreption=new JTextArea();
        TDiscreption.setColumns(35);
        TDiscreption.setRows(7);
        TDiscreption.setLineWrap(true);
        TDiscreption.setBorder(new EmptyBorder(5,5,5,5));
        TDiscreption.setForeground(Color.red);
        
        Save=new JButton();
        Save.setText("Save");
        Save.addActionListener( this );
    }
    private void AddComponentsToFarme() {
    
        PTitle.add(Back,BorderLayout.WEST);
        PTitle.add(Title,BorderLayout.CENTER);
        PTitle.add(Number,BorderLayout.EAST);
        
        PName.add(Name);
        PName.add(JName);
        
        PVersion.add( VBig );
        PVersion.add( VSmall );
        
        PInfo.add(Info);
        
        PDiscreption.add(Discreption);
        PDiscreption.add(TDiscreption);
        
        Small.add(PVersion);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PInfo);
        Small.add(Box.createVerticalStrut(2));
        Small.add(PDiscreption);
        
        PSave.add(Save);
        
        box.add(PName);
        box.add(Small);
        box.add(PSave);
        
        this.add(PTitle,BorderLayout.NORTH);
        this.add(box,BorderLayout.CENTER);
    }
    
    //For Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "<<==":
                BackAction();
                break;
            case "Save":
                SaveAction();
                break;
            default:
                CheckAction( e );
                break;
        }
    }
    private void BackAction(){
        this.dispose();
        new Main();
    }
    private void CheckAction( ActionEvent e ){
        if ( JName.getSelectedIndex()==-1 )
                return ;
        
        JCheckBox check=(JCheckBox)e.getSource();
        if ( check.isSelected() )
            check.setText( String.valueOf( Const.GetNumber( check.getText() ,"From NewVersion In Line 210" )+1 ) );
        else
            check.setText( String.valueOf( Const.GetNumber( check.getText() ,"From NewVersion In Line 212" )-1 ) );
        SetColors();
    }
    private void SaveAction(){
        
        if ( JName.getSelectedIndex()==-1 ){
            JOptionPane.showMessageDialog(null,"Please Select Project");
            return ;
        }
        else if ( VBig.isSelected()&&VSmall.isSelected() || !VBig.isSelected()&&!VSmall.isSelected() ){
            JOptionPane.showMessageDialog(null,"Please Correct The Choise");
            return ;
        }
        
        Project project=(Project)JName.getSelectedItem();
        Version version=new Version();
        
        version.Version_Number = VBig.getText()+"."+VSmall.getText();
        version.Path = Const.CreatePath( project.Number+"\\"+version.Version_Number+".txt" );
        version.Date = Const.Date();
        if ( TDiscreption.getText().length()==0 )
            version.Discreption = "No Discreption";
        else
            version.Discreption = TDiscreption.getText().replace( '\n', '_' );
        
        project.Versions.add( version.Path );
        project.Version_Number = version.Version_Number;

        Const.CreateFile( version.Path , "From NewVersion In Line 240" );
        Const.SaveVersion( version, "From NewVersion In Line 241" );
        Const.SavePath( version.Path, project.VersionsPathFile, "From NewVersion In Line 242");
        Const.SaveProject( project, "From NewVersion In Line 243");
        
        JOptionPane.showMessageDialog(null,"Version Saved");
        Initializing();
    }
    private void Initializing() {
        
        TDiscreption.setText("");
        if ( VBig.isSelected() )
            VSmall.setText( "0" );
        
        VBig.setSelected( false );
        VSmall.setSelected( false );
        SetColors();
    }
    
    // For Displaying Action
    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if ( JName.getSelectedIndex()==-1 )
            return ;
        
        Project project=(Project)JName.getSelectedItem();
        
        Info.setText( Const.SetColor( project.Language , Const.Date() , project.Purpose ) );
        Number.setText( "Project   "+project.Number );
        VBig.setText( GetBig( project.Version_Number ) );
        VBig.setSelected( false );
        VSmall.setText( GetSmall( project.Version_Number ) );
        VSmall.setSelected( false );
        
        SetColors();
    }
    private String GetBig( String Get ){
        return Get.substring( 0, Get.indexOf('.') );
    }
    private String GetSmall( String Get ){
        return Get.substring( Get.indexOf('.')+1, Get.length() );
    }
}