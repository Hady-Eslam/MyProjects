package my.projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import my.projects.Activities.Info;
import my.projects.Activities.NewProject;
import my.projects.Activities.NewVersion;
import my.projects.Activities.Show;

public class Main extends JFrame implements ActionListener{
    
    private JPanel PTitle,PNewVersion,PNewProject,PShow;
    private JLabel Title;
    private JButton NewProject,NewVersion,Show,Info;
    private Box box;
    
    public Main(){
        
        SetFrameAndPanels();
        SetComponents();
        AddComponentsToFrame();
    }

    private void SetFrameAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 300 , 300 );
        this.setResizable(false);
        this.setLocation( 500 , 250 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        PTitle.setLayout(new BorderLayout());
        PTitle.setBorder(new EmptyBorder(5,5,5,5));
        
        PNewVersion=new JPanel();
        
        PNewProject=new JPanel();
        
        PShow=new JPanel();
        
        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(15,15,15,15));
    }
    private void SetComponents() {
    
        Info=new JButton();
        Info.setText("Info");
        Info.addActionListener( this );
        
        Title=new JLabel();
        Title.setText( "Hello Hady" );
        Title.setForeground(Color.red);
        Title.setHorizontalAlignment(JLabel.CENTER);
        
        NewVersion=new JButton();
        NewVersion.setText("New Version");
        NewVersion.addActionListener( this );
        
        NewProject=new JButton();
        NewProject.setText("New Project");
        NewProject.addActionListener( this );
        
        Show=new JButton();
        Show.setText("Show");
        Show.addActionListener( this );
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Info,BorderLayout.WEST);
        PTitle.add(Title,BorderLayout.CENTER);
        
        PNewProject.add(NewProject);
        PNewVersion.add(NewVersion);
        PShow.add(Show);
        
        box.add(PNewVersion);
        box.add(PNewProject);
        box.add(PShow);
        
        this.add(PTitle,BorderLayout.NORTH);
        this.add(box,BorderLayout.CENTER);
    }
    
    // For Action Listeners
    @Override
    public void actionPerformed(ActionEvent e) {
    
        Main.this.dispose();
        switch (e.getActionCommand()) {
            
            case "New Version":
                new NewVersion();
                break;
            case "New Project":
                new NewProject();
                break;
            case "Info":
                new Info();
                break;
            default:
                new Show();
                break;
        }
    }
}