package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.beans.out_record;
import com.beans.out_record_add;
import com.dao.customerDao;
import com.dao.in_recordDao;
import com.dao.out_recordDao;
import com.dao.out_record_addDao;
import com.dao.roomDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class outFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//文本框输入退房时间时间
	private JTextField textField;
	//下面为dao层的调用
	private out_record_addDao oraD=new out_record_addDao();
	private in_recordDao irD=new in_recordDao();
	private out_recordDao orD=new out_recordDao();
	private roomDao rD = new roomDao();
	//连接的声明
	private Connection conn;
	//下面对用一些数据全局定义
	private int in_number;//入住编号
	private int room_number;//房间编号
	private String customer_id;//顾客id
	private String dateTemp;//用来暂存时间，判断是否为空值较为方便
	private Date last_date;//用来存放实际上的退房时间

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					outFrm frame = new outFrm();
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
	public outFrm() {

	}
	
	public outFrm(String adminNumber) {
		//建立数据库的连接，并且设置不自动提交，作为事务处理
		try {
			conn=jdbcUtil.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setTitle("\u9000\u623F\u670D\u52A1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u5728\u4F4F\u8BB0\u5F55");
		
		JLabel label_1 = new JLabel("\u9000\u623F\u65F6\u95F4\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u9000\u623F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加退房记录
				exitActionPerformed(e,adminNumber);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(190, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(186))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(label_1)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(34))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"\u5165\u4F4F\u7F16\u53F7","\u623F\u95F4\u53F7" , "\u5BA2\u6237\u8EAB\u4EFD\u8BC1\u53F7", "\u6700\u665A\u9000\u623F\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//鼠标单击的事件（用来获取选中行的相关数据）
				mousePressedActionPerformed(e);
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		this.fillTable();
	}
	private void exitActionPerformed(ActionEvent event,String adminNumber) {
		//设置日期的格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//获取日期
		String date=this.textField.getText();
		//退房日期不能为空
		if(StringUtils.isEmpty(date)){
			JOptionPane.showMessageDialog(null, "退房日期不能为空！");
			return;
		}
		//中间变量，获取日期
		java.util.Date dtemp1 = null;
		java.util.Date dtemp2 = null;
		try {
			dtemp1 = formatter.parse(date);
			dtemp2 = formatter.parse(dateTemp);
		} catch (Exception e) {
			e.printStackTrace();
			//提示日期格式出错
			JOptionPane.showMessageDialog(null, "请输入正确的日期格式，如1999-01-01！");
			return;
		}
		try {
			//实际的退房时间
			last_date=new java.sql.Date(dtemp1.getTime());
			//预计的退房时间
			Date out_time=new java.sql.Date(dtemp2.getTime());
			//如果实际的退房时间晚于实际的退房时间，那么需要缴纳罚款
			float sum=(int)(out_time.getTime()<last_date.getTime()?(rD.price(conn, room_number)*(int)(last_date.getTime()-out_time.getTime())/(24*60*60*1000)):0);
			out_record or=new out_record(room_number,customer_id,out_time,sum,adminNumber);
			orD.add(conn, or);
			out_record_add ora=new out_record_add(in_number,orD.query(conn, or));
			oraD.add(conn, ora);
			//判断是否需要缴纳罚款
			if(sum==0) {
				JOptionPane.showMessageDialog(null, "无需缴纳罚款，欢迎下次光临！");
			}else {
				JOptionPane.showMessageDialog(null, "需要缴纳"+sum+"元，欢迎下次光临！");
			}
			//跳转是否评价,顺带传递参数,非事务，不强制用户进行评价
			new isFevaluateFrm(customer_id,room_number,last_date).setVisible(true);
			//界面消除
			dispose();
			conn.commit();
			jdbcUtil.closeResource(conn, null);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 用来获取当前选中行的相关数据
	 * @param e
	 */
	private void mousePressedActionPerformed(MouseEvent e) {
		int row=table.getSelectedRow();
		in_number=(Integer.parseInt((String)(table.getValueAt(row, 0))));
		room_number=(Integer.parseInt((String)(table.getValueAt(row, 1))));
		customer_id=(String)(table.getValueAt(row, 2));
		dateTemp=(String)(table.getValueAt(row, 3));		
	}
	/**
	 * fillTable用来显示当前的入住记录
	 */
	private void fillTable() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		try {
			ResultSet rs = irD.query(conn);
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getString("in_number"));
				v.add(rs.getString("room_number"));
				v.add(rs.getString("customer_id"));
				v.add(rs.getString("last_date"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
