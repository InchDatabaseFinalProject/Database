package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.beans.staff;
import com.dao.staffDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class staffAddInterfrm extends JInternalFrame {
	private JTextField staff_numberTXT;
	private JTextField staff_nameTXT;
	
	private jdbcUtil jbu=new jdbcUtil(); 
	private staffDao StaffDao=new staffDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffAddInterfrm frame = new staffAddInterfrm();
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
	public staffAddInterfrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5458\u5DE5\u6DFB\u52A0");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("\u5458\u5DE5\u7F16\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("\u5458\u5DE5\u59D3\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		staff_numberTXT = new JTextField();
		staff_numberTXT.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		staff_nameTXT = new JTextField();
		staff_nameTXT.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(staff_nameTXT, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addComponent(staff_numberTXT, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
					.addGap(46))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(101)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(68))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(staff_numberTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(staff_nameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(44))
		);
		getContentPane().setLayout(groupLayout);

	}
	/**
	 * 员工类别添加事件处理
	 * @param e
	 */
	private void staffAddActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int  staff_number = Integer.parseInt(this.staff_numberTXT.getText());
		String staff_name=this.staff_nameTXT.getText();
		if(StringUtils.isEmpty(this.staff_numberTXT.getText())) {
			JOptionPane.showMessageDialog(null, "员工编号不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(staff_name)) {
			JOptionPane.showMessageDialog(null, "员工姓名不能为空！");
			return ;
		}
		staff Staff=new staff(staff_number,staff_name);
		Connection con=null;
		     try {
		    	 con=jdbcUtil.getConnection();
				int n=StaffDao.add(con, Staff);
				if(n==1) {
					JOptionPane.showMessageDialog(null, "员工添加成功");	
					resetValue();
				}else {
					JOptionPane.showMessageDialog(null, "员工添加失败");	
					resetValue();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
			try {
				jdbcUtil.closeResource(con,null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	/**
	 * 重置处理
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.resetValue();
	}

	/**
	 *重置表量
	 *
	 */
	private void resetValue() {
		this.staff_numberTXT.setText("");
		this.staff_nameTXT.setText("");
	}
	
}
