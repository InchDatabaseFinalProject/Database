package com.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.beans.admin;
import com.dao.adminDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;

import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

public class loginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField adminNumber;
	private JPasswordField adminPassword;
	private adminDao aD=new adminDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					loginFrm frame = new loginFrm();
					frame.setVisible(true);
				}catch(Exception e){e.printStackTrace();}
			}});
	}

	/**
	 * Create the frame.
	 */
	public loginFrm() {

		setResizable(false);
		setTitle("\u7BA1\u7406\u5458\u767B\u9646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u9152\u5E97\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\u7BA1\u7406\u5458\u7F16\u53F7\uFF1A");
		
		JLabel lblNewLabel_2 = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801\uFF1A");
		
		adminNumber = new JTextField();
		adminNumber.setColumns(10);
		
		adminPassword = new JPasswordField();
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(100)
								.addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(76)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addGap(18)
										.addComponent(adminPassword))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addGap(18)
										.addComponent(adminNumber, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(140, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(lblNewLabel)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(adminNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(adminPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
	}
	/**
	 * 重置输入框中的内容
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e) {
		this.adminNumber.setText("");
		this.adminPassword.setText("");
	}
	/**
	 * 检查输入的用户名或者密码是否正确，正确则跳转主页面，不正确则提示错误
	 * @param evt
	 */
	private void loginActionPerformed(ActionEvent evt) {
		//获取用户名
		String adminNumber=this.adminNumber.getText();
		//获取密码
		String adminPassword=new String(this.adminPassword.getPassword());
		//用户名不能为空
		if(StringUtils.isEmpty(adminNumber)){
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		//密码不能为空
		if(StringUtils.isEmpty(adminPassword)){
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		admin ad=new admin(adminNumber,adminPassword);
		Connection con=null;
		try {
			con=jdbcUtil.getConnection();
			admin currentAdmin=aD.login(con, ad);
			if(currentAdmin!=null){
				dispose();
				//显示主界面
				new MainFrm(adminNumber).setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//关闭连接
				jdbcUtil.closeResource(con,null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
