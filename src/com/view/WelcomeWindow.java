package com.view;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;



class WelcomeWindow extends JWindow{//welcome window.
    public WelcomeWindow(String filename,Frame f,int waitTime){
        super(f);
        JLabel l = new JLabel(new ImageIcon(filename));//image add to label l
        getContentPane().add(l, BorderLayout.CENTER);
        pack();
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2),screenSize.height/2 - (labelSize.height/2));
        setBounds(700, 300, 474, 391);
//        JButton btnNewButton = new JButton("\u767B\u5F55");
//        btnNewButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });

//press the welcome window,it will be closed.
//   addMouseListener(new MouseAdapter(){
//   public void mousePressed(MouseEvent e){
//    setVisible(false);
//    dispose();
//   }
//  });


        final int pause = waitTime;
        final Runnable closerRunner = new Runnable(){
            public void run(){
                setVisible(false);
                dispose();
            }
        };


        Runnable waitRunner = new Runnable(){
            public void run(){
                try{
                    Thread.sleep(pause);
                    //invoke closerRunner and wait for waitRunner run.
                    SwingUtilities.invokeAndWait(closerRunner);
                }catch(Exception e){
                    e.printStackTrace();
                    // Catch InvocationTargetException
                    // Catch InterruptedException
                }
            }
        };


        setVisible(true);
        Thread waitThread = new Thread(waitRunner, "SplashThread");
        waitThread.start();
    }


    public static void main(String []args) throws InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
        //com.jtattoo.plaf.mint.MintLookAndFeel
        //com.jtattoo.plaf.mcwin.McWinLookAndFeel
        Frame f=new Frame();
        f.setVisible(true);
        System.out.println(1);
        new WelcomeWindow("OIP.jpg",f,5000);
        System.out.println(2);
        //new loginFrm().setVisible(true);
        Runnable log=new Runnable() {
            @Override
            public void run() {
                new loginFrm().setVisible(true);
            }
        };
        Thread login=new Thread(log);
        //set login
        Thread.sleep(5000);
        //wait wel close, then login start
        login.start();
    }

}