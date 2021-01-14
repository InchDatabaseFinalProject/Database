package com.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm("1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrm() {
		
	}
	/**
	 * 由于需要传递管理员的number，所以采用有参的构造
	 * @param adminNumber
	 */
	public MainFrm(String adminNumber) {
		setTitle("\u9152\u5E97\u5165\u4F4F\u7BA1\u7406\u7CFB\u7EDF\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 388);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("\u57FA\u7840\u529F\u80FD\u5B9E\u73B0");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem = new JMenuItem("\u5165\u4F4F\u8BB0\u5F55");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开注册、登录用户的窗口
				new customerFrm(adminNumber).setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u9000\u623F\u8BB0\u5F55");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开退房服务的窗口
				new outFrm(adminNumber).setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u5BA2\u623F\u72B6\u51B5");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//客房状况
				roomManagermentFrm2 roomManagermentFrm2 =new roomManagermentFrm2();
				roomManagermentFrm2.setVisible(true);
				desktopPane.add(roomManagermentFrm2);
			}
			
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenu menu_1 = new JMenu("\u8BC4\u4EF7\u72B6\u51B5");//评价状况
		mnNewMenu_1.add(menu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u6DFB\u52A0\u8BC4\u4EF7");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加评价
//				evaluateAddInterfrm EvaluateAddInterfrm=new evaluateAddInterfrm();
//				EvaluateAddInterfrm.setVisible(true);
//				desktopPane.add(EvaluateAddInterfrm);
				new evaluateAddInterfrm(null,0,null).setVisible(true);
			}
		});
		menu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u8BC4\u4EF7\u6570\u636E\u7EF4\u62A4");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//评价数据维护
				evaluateManageInterfrm EvaluateManageInterfrm=new evaluateManageInterfrm();
				EvaluateManageInterfrm.setVisible(true);
				desktopPane.add(EvaluateManageInterfrm);
			}
		});
		menu_1.add(mntmNewMenuItem_4);
		
		JMenu menu = new JMenu("\u5458\u5DE5\u72B6\u51B5");//员工状况
		mnNewMenu_1.add(menu);
		
		JMenuItem menuItem_1 = new JMenuItem("\u6DFB\u52A0\u5458\u5DE5");
		menuItem_1.addActionListener(new ActionListener() {
			//添加员工
			public void actionPerformed(ActionEvent e) {
				staffAddInterfrm StaffAddInterfrm=new staffAddInterfrm();
				StaffAddInterfrm.setVisible(true);
				desktopPane.add(StaffAddInterfrm);
			}
		});
		menu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5458\u5DE5\u6570\u636E\u7EF4\u62A4");
		menuItem_2.addActionListener(new ActionListener() {
			//员工数据维护
			public void actionPerformed(ActionEvent e) {
				staffManageInterfrm StaffManageInterfrm=new staffManageInterfrm();
				StaffManageInterfrm.setVisible(true);
				desktopPane.add(StaffManageInterfrm);
			}
		});
		menu.add(menuItem_2);
		JMenuItem menuItem_4 = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//安全退出
				int result=JOptionPane.showConfirmDialog(null, "是否退出系统？");
				if(result==0) {
					dispose();
					System.exit(0);
				}
			}
		});
		mnNewMenu_1.add(menuItem_4);
		
		JMenu mnNewMenu = new JMenu("HELP");
		menuBar.add(mnNewMenu);
		/**
		 * 关于我们
		 */
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u8FD9\u662F\u4E2A\u6570\u636E\u5E93\u5B9E\u9A8C");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutInnerFrm about=new aboutInnerFrm();
				about.setVisible(true);
				desktopPane.add(about);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		//使界面最大化
  		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
