package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.beans.customer;
import com.dao.customerDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;

public class customerFrm extends JFrame {
	private JTextField idText;
	private JTextField nameText;
	private JTextField phoneText;
	//
	private customerDao cD=new customerDao();
	//为了使用事务而引用该连接
	private Connection conn=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerFrm frame = new customerFrm();
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
	public customerFrm() {
		
	}
	public customerFrm(String adminNumber) {
		//数据库事务连接
		try {
			conn=jdbcUtil.getConnection();
			//下面操作可保证事务，不自动提交
			conn.setAutoCommit(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setTitle("\u5BA2\u4EBA\u5165\u4F4F\u767B\u8BB0");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		
		idText = new JTextField();
		idText.setColumns(10);
		
		JLabel label_1 = new JLabel("\u540D    \u79F0\uFF1A");
		
		nameText = new JTextField();
		nameText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		
		phoneText = new JTextField();
		phoneText.setColumns(10);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//填写相关信息后，注册用户
				registerActionPerformed(e,adminNumber,conn);
			}
		});
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重置已填入的信息
				resetValueActionPerformed(e);
			}
		});
		
		JButton button_2 = new JButton("\u767B\u5F55");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e,adminNumber,conn);
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(77)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(lblNewLabel))
								.addComponent(label_1))))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(nameText)
							.addComponent(phoneText)
							.addComponent(idText, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button)
							.addGap(41)
							.addComponent(button_2)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(80)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(phoneText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button)
						.addComponent(button_2))
					.addGap(23))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	/**
	 * 对于用户进行判断是否已注册，如果注册信息不符合，则提示错误，否则进入下一页面
	 * @param e
	 * @param adminNumber
	 * @param conn
	 */
	private void loginActionPerformed(ActionEvent e,String adminNumber,Connection conn) {
		//下面三条语句分别获取框中内容
		String idText=this.idText.getText();
		String nameText=this.nameText.getText();
		String phoneText=this.phoneText.getText();
		//身份证号码不能为空
		if(StringUtils.isEmpty(idText)) {
			JOptionPane.showMessageDialog(null, "身份证号码不能为空！");
			return;
		}
		//密码不能为空
		if(StringUtils.isEmpty(nameText)) {
			JOptionPane.showMessageDialog(null, "名字不能为空！");
			return;
		}
		//电话号码不能为空
		if(StringUtils.isEmpty(phoneText)) {
			JOptionPane.showMessageDialog(null, "电话号码不能为空！");
			return;
		}
		customer cus=new customer(idText,nameText,phoneText);
		try {
			//检查用户是否注册过
			int change=cD.query(conn, cus);
			if(change==2) {
				JOptionPane.showMessageDialog(null, "登陆成功，请选择房间！");
				dispose();
				//转换窗口，进行下一步的房间选择
				new inFrm(adminNumber,idText,conn).setVisible(true);			
			}else if(change==1){
				JOptionPane.showMessageDialog(null, "输入信息有误，请重试！");
			}else {
				JOptionPane.showMessageDialog(null, "该用户尚未注册，请点击注册按钮！");
			}
		} catch (Exception e1) {
			try {
				//如果出现错误直接回滚
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e1.printStackTrace();
		}
		
		
	}

	/**
	 * 对于个人信息进行注册，如注册不成功提示该用户已注册
	 * @param e
	 * @param adminNumber
	 * @param conn
	 */
	private void registerActionPerformed(ActionEvent e,String adminNumber,Connection conn) {
		//下面三条语句分别获取框中内容
		String idText=this.idText.getText();
		String nameText=this.nameText.getText();
		String phoneText=this.phoneText.getText();
		//身份证号码不能为空
		if(StringUtils.isEmpty(idText)) {
			JOptionPane.showMessageDialog(null, "身份证号码不能为空！");
			return;
		}
		//密码不能为空
		if(StringUtils.isEmpty(nameText)) {
			JOptionPane.showMessageDialog(null, "名字不能为空！");
			return;
		}
		//电话号码不能为空
		if(StringUtils.isEmpty(phoneText)) {
			JOptionPane.showMessageDialog(null, "电话号码不能为空！");
			return;
		}
		customer cus=new customer(idText,nameText,phoneText);
		try {
			//检查用户是否注册过
			int change=cD.query(conn, cus);
			if(change==0) {
				cD.add(conn, cus);
				JOptionPane.showMessageDialog(null, "注册成功，请选择房间！");
				dispose();
				//转换窗口，进行下一步的房间选择
				new inFrm(adminNumber,idText,conn).setVisible(true);			
			}else {
				JOptionPane.showMessageDialog(null, "该用户已注册，如果疑问请询问前台工作人员！");
			}
		} catch (Exception e1) {
			try {
				//如果出现错误直接回滚
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e1.printStackTrace();
		}
}
	/**
	 * 重置信息框中的内容
	 * @param e
 	*/
	private void resetValueActionPerformed(ActionEvent e) {
		this.idText.setText("");
		this.nameText.setText("");
		this.phoneText.setText("");
	}
}
