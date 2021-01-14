package com.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.beans.room;
import com.dao.roomDao;
import com.jdbc.util.jdbcUtil;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
public class roomManagermentFrm2 extends JFrame {
	private JTable roomTypeTable;
	private jdbcUtil dbUtil=new jdbcUtil();
	private roomDao roomTypeDao=new roomDao();
	//private JTextField s_roomNameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					roomManagermentFrm2 frame = new roomManagermentFrm2();
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
	public roomManagermentFrm2() {
		//setClosable(true);
		setTitle("\u5BA2\u623F\u7BA1\u7406");
		setBounds(100, 100, 720, 442);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("\u5BA2\u623F\u7C7B\u578B");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new roomManagermentFrm().setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		
		roomTypeTable = new JTable();
		roomTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u623F\u95F4\u53F7", "\u623F\u95F4\u7C7B\u578B", "\u5355\u4EF7", "\u670D\u52A1\u5458"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(roomTypeTable);
		getContentPane().setLayout(groupLayout);
		this.fillTable();
	}
	/*private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s_roomName=this.s_roomNameTxt.getText();
		room room=new room();
		room.setStaff_name(s_roomName);
		this.fillTable(room);
	}*/
	private void fillTable() {
		DefaultTableModel dtm= (DefaultTableModel)(roomTypeTable.getModel());
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getConnection();
			ResultSet rs=roomTypeDao.list(con);
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getInt("room_number"));
				v.add(rs.getString("room_type"));
				v.add(rs.getFloat("day_price"));
				v.add(rs.getString("staff_name"));
				dtm.addRow(v);
				
			}
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				jdbcUtil.closeResource(con,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	}
}
