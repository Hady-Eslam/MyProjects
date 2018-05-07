package my.projects;

import java.awt.EventQueue;
import java.io.File;

public class MyProjects {

    private static Consts Const=new Consts();
    
    public static void main(String[] args) {
    
        EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run() {
            
                Preparation();
                new Main();
            }
        });
    }
    private static void Preparation(){
        
        File file=new File( Const.File_Path() );
        if (!file.exists())
            Const.GetOut("MyProjects in Line 26");
    }
}