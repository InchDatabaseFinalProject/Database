package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.beans.evaluate;
import com.beans.staff;
import com.dao.evaluateDao;
import com.dao.staffDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class evaluateAddInterfrm extends JFrame {
	private JTextField customer_nameTXT;
	private JTextField room_numberTXT;
	private JTextField staff_numberTXT;
	private JTextField timeTXT;
	private JTextArea evluateTXT;

	private jdbcUtil jbu=new jdbcUtil(); 
	private evaluateDao EvaluateDao=new evaluateDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					evaluateAddInterfrm frame = new evaluateAddInterfrm(null,0,null);
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
	public evaluateAddInterfrm(String customer_id,int room_number,Date time) {
//		setMaximizable(true);
//		setIconifiable(true);
//		setClosable(true);
		setTitle("\u8BC4\u4EF7\u8868\u6DFB\u52A0");
		setBounds(100, 100, 736, 516);
		
		JLabel lblNewLabel = new JLabel("\u987E\u5BA2\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel label = new JLabel("\u5BA2\u623F\u7F16\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("\u5458\u5DE5\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u65F6\u95F4\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("\u8BC4\u4EF7\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		customer_nameTXT = new JTextField();
		customer_nameTXT.setColumns(10);
		
		room_numberTXT = new JTextField();
		room_numberTXT.setColumns(10);
		
		staff_numberTXT = new JTextField();
		staff_numberTXT.setColumns(10);
		
		timeTXT = new JTextField();
		timeTXT.setColumns(10);
		
	    evluateTXT = new JTextArea();
	    
	    /**
	     * 这里判断传递参数，如果非空，则代表从退房记录进入，空则是点击功能栏进入
	     */
	    
	    Connection conn=null;
		try {
			conn = jbu.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    if(customer_id!=null&&room_number!=0&&time!=null) {
	    	try {
				evaluate eva =  EvaluateDao.query(conn, customer_id, room_number, time);
				customer_nameTXT.setText(eva.getCustomer_name());
				room_numberTXT.setText(Integer.toString(eva.getRoom_number()));
				staff_numberTXT.setText(Integer.toString(eva.getStaff_number()));
				timeTXT.setText(eva.getTime().toString());
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }else {
	    	
	    }
	    jbu.closeResource(conn, null);
	    
	    
	    
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evaluateAddActionPerformed(e);
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(customer_nameTXT, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(room_numberTXT, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(staff_numberTXT, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeTXT, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(evluateTXT, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(178)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addGap(102)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(customer_nameTXT, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(room_numberTXT, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(staff_numberTXT, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(timeTXT, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(evluateTXT, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGap(4)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
							.addGap(46)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
		);
		getContentPane().setLayout(groupLayout);

	}
	

	/** 
	 * 评价添加事件处理
	 * @param e
	 */
	private void evaluateAddActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String customer_name=this.customer_nameTXT.getText();
		int room_number =Integer.parseInt(this.room_numberTXT.getText());
		int staff_number = Integer.parseInt(this.staff_numberTXT.getText());
		String evluate =this.evluateTXT.getText();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String inDate=this.timeTXT.getText();
		java.util.Date dtemp1 = null;
		try {
			dtemp1 = formatter.parse(inDate);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(dtemp1==null) {
			JOptionPane.showMessageDialog(null, "输入不合法，请重新输入！");
			return ;
		}
		java.sql.Date time=new java.sql.Date(dtemp1.getTime());

		if(StringUtils.isEmpty(customer_name)) {
			JOptionPane.showMessageDialog(null, "顾客姓名不能为空！");
			return ;
		}
		String str1=this.room_numberTXT.getText();//！！！！！！！！！！！！
		if(StringUtils.isEmpty(str1)) {
			JOptionPane.showMessageDialog(null, "客房编号不能为空！");
			return ;
		}
		String str2=this.staff_numberTXT.getText();//！！！！！！！！！！！！！！
		if(StringUtils.isEmpty(str2)) {
			JOptionPane.showMessageDialog(null, "员工编号不能为空！");
			return ;
		}
		if(StringUtils.isEmpty(evluate)) {
			JOptionPane.showMessageDialog(null, "评价不能为空！");
			return ;
		}

		evaluate Evaluate =new evaluate(customer_name,room_number,staff_number,time, evluate);

		Connection con=null;
		     try {
		    	 con=jdbcUtil.getConnection();
				int num=EvaluateDao.add(con, Evaluate);
				if(num==1) {
					JOptionPane.showMessageDialog(null, "评价添加成功");	
					resetValue();
				}else {
					JOptionPane.showMessageDialog(null, "评价添加失败");						
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "评价添加异常，输入错误，不符合主键的独特性");			
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
		this.customer_nameTXT.setText("");
		this.room_numberTXT.setText("");
		this.staff_numberTXT.setText("");
		this.timeTXT.setText("");
		this.evluateTXT.setText("");
	}
}