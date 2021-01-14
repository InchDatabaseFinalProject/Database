package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.beans.in_record;
import com.beans.in_record_add;
import com.dao.in_recordDao;
import com.dao.in_record_addDao;
import com.dao.roomDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class inFrm extends JFrame {
	//下面为定义的全局变量
	private JPanel contentPane;
	private JTextField inDate;//输入框，入住时间
	private JTextField lastDate;//输入框，预计退房时间
	private JTable roomDetails;//表单

	private int room_num;//房间号
	//下面为相应的dao层使用
	private roomDao rD = new roomDao();
	private in_recordDao irD = new in_recordDao();
	private in_record_addDao iraD = new in_record_addDao();
	//连接
	private Connection conn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inFrm frame = new inFrm();
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
	public inFrm() {

	}
	
	public inFrm(String adminNumber,String idText,Connection con) {
		setTitle("\u5BA2\u623F\u4FE1\u606F");
		setBounds(100, 100, 450, 449);
		//使用来自customerFrm的连接，事务操作
		conn=con;
		JLabel label = new JLabel("\u5165\u4F4F\u65F6\u95F4\uFF1A");
		
		inDate = new JTextField();
		inDate.setColumns(10);
		
		JLabel label_1 = new JLabel("\u9000\u623F\u65F6\u95F4\uFF1A");
		
		lastDate = new JTextField();
		lastDate.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u4E0B\u5355");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//提交订房信息
				sendActionPerformed(e,adminNumber,idText);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lastDate))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(inDate, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
					.addGap(55)
					.addComponent(button)
					.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(inDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(lastDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(58))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(button)
							.addContainerGap())))
		);
		
		roomDetails = new JTable();
		roomDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//检测当前鼠标的相应信息
				mousePressedActionPerformed(e);
			}
		});
		roomDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u623F\u95F4\u53F7", "\u623F\u95F4\u7C7B\u578B", "\u4EF7\u683C", "\u670D\u52A1\u5458\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		roomDetails.getColumnModel().getColumn(0).setResizable(false);
		roomDetails.getColumnModel().getColumn(1).setResizable(false);
		roomDetails.getColumnModel().getColumn(2).setResizable(false);
		roomDetails.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(roomDetails);
		getContentPane().setLayout(groupLayout);
		this.fillTable();

	}
	/**
	 * 检测鼠标当前的选择
	 * @param e
	 */
	private void mousePressedActionPerformed(MouseEvent e) {
		int row=roomDetails.getSelectedRow();
		room_num=(Integer.parseInt((String)(roomDetails.getValueAt(row, 0))));
	}
	/**
	 * 根据选择的房间，添加入房记录
	 * @param e
	 */
	private void sendActionPerformed(ActionEvent e,String adminNumber,String idText) {
		//日期格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//入住时间
		String inDate=this.inDate.getText();
		//退房时间
		String outDate=this.lastDate.getText();
		//不能不选择房间
		if(room_num==0) {
			JOptionPane.showMessageDialog(null, "请选择房间！");
			return;
		}
		//入住日期不能为空
		if(StringUtils.isEmpty(inDate)){
			JOptionPane.showMessageDialog(null, "入住日期不能为空！");
			return;
		}
		//最晚退房时间不能为空
		if(StringUtils.isEmpty(outDate)){
			JOptionPane.showMessageDialog(null, "最晚退房时间日期不能为空！");
			return;
		}
		//用来转换时间的中间变量
		java.util.Date dtemp1 = null;
		java.util.Date dtemp2 = null;
		//判断入住时间晚于最晚退房时间
		try {
			dtemp1 = formatter.parse(inDate);
			dtemp2 = formatter.parse(outDate);
			if(dtemp1.getTime()>dtemp2.getTime()) {
				JOptionPane.showMessageDialog(null, "输入时间错误，入住时间不能晚于最迟退房时间！");
					return;
				}
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "请输入正确的日期格式，如1999-01-01！");
			return;
		}
		try {
			//转换时间
			java.sql.Date dateIn=new java.sql.Date(dtemp1.getTime());
			java.sql.Date dateOut=new java.sql.Date(dtemp2.getTime());
			//判断该时段该房间是否可用
			boolean isF=irD.query(conn, dateIn, dateOut, room_num);
			//可用则执行插入入住记录，并给出需要缴纳的金额
			if(isF) {
				//需要缴纳的金额
				float sum=(rD.price(conn, room_num)*(int)((dateOut.getTime()-dateIn.getTime())/(24*60*60*1000)));
				in_record ird=new in_record(room_num,idText,dateIn,dateOut,adminNumber);
				in_record_add ira=new in_record_add(room_num,dateIn,dateOut,sum);
				JOptionPane.showMessageDialog(null, "订房成功！一共需要"+sum+"元！");
				dispose();
				//增加记录
				irD.add(conn,ird);
				iraD.add(conn, ira);
				//提交操作
				conn.commit();
				//关闭连接
				jdbcUtil.closeResource(conn, null);
			}else {
				//房间不可用
				JOptionPane.showMessageDialog(null, "该时间段本房间不空闲，试试别的房间吧！");
			}
		} catch (Exception e1) {
			try {
				//发生错误，回滚
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e1.printStackTrace();
		}
	}
/**
 * 填充表格
 */
	private void fillTable() {
		DefaultTableModel dtm=(DefaultTableModel) roomDetails.getModel();
		dtm.setRowCount(0);
		try {
			ResultSet rs = rD.list(conn);
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getString("room_number"));
				v.add(rs.getString("room_type"));
				v.add(rs.getString("day_price"));
				v.add(rs.getString("staff_name"));
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
