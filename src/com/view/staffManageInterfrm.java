package com.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.beans.staff;
import com.dao.staffDao;
import com.jdbc.util.StringUtils;
import com.jdbc.util.jdbcUtil;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class staffManageInterfrm extends JInternalFrame {
	private JTable staffTable;
	
	private jdbcUtil jbu=new jdbcUtil(); 
	private staffDao StaffDao=new staffDao();
	private JTextField staff_numberTXT;
	private JTextField staff_nameTXT;
	private JTextField staff_numberoutTXT;
	private JTextField staff_nameoutTXT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffManageInterfrm frame = new staffManageInterfrm();
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
	public staffManageInterfrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5458\u5DE5\u7BA1\u7406");
		setBounds(100, 100, 658, 527);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u5458\u5DE5\u7F16\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		
		staff_numberTXT = new JTextField();
		staff_numberTXT.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffSearchActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\u5458\u5DE5\u59D3\u540D");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		staff_nameTXT = new JTextField();
		staff_nameTXT.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(staff_nameTXT, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addGap(66)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(115))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(staff_numberTXT, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 277, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(staff_numberTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(31))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(staff_nameTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("\u5458\u5DE5\u7F16\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		
		staff_numberoutTXT = new JTextField();
		staff_numberoutTXT.setEditable(false);
		staff_numberoutTXT.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5458\u5DE5\u59D3\u540D\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		
		staff_nameoutTXT = new JTextField();
		staff_nameoutTXT.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffUpdateActionEvent(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffDeleteActionEvent(e);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(staff_numberoutTXT, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(staff_nameoutTXT, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(58)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(staff_numberoutTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(staff_nameoutTXT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		panel.setLayout(gl_panel);
		
		staffTable = new JTable();
		staffTable.setCellSelectionEnabled(true);
		staffTable.setColumnSelectionAllowed(true);
		staffTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				staffTableMousePressed(e);
			}
		});
		staffTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5458\u5DE5\u7F16\u53F7", "\u5458\u5DE5\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(staffTable);
		getContentPane().setLayout(groupLayout);

		
		this.fillTable(new staff());//staff_number会默认为零；即编号不可为零；
		
	}
	/**
	 * 员工删除操作
	 * @param e
	 */
	protected void staffDeleteActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		int staff_number = 0;
		try {
		     staff_number = Integer.parseInt(this.staff_numberoutTXT.getText());
		} catch (NumberFormatException ex) {
		    ex.printStackTrace();
		}
		if(StringUtils.isEmpty(this.staff_numberoutTXT.getText())) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return ;
		}
		int x=JOptionPane.showConfirmDialog(null, "确定要删除吗？");
		if(x==0) {
			Connection con=null;
		     try {
		    	 con=jdbcUtil.getConnection();
				int n=StaffDao.delete(con, staff_number);
				if(n==1) {
					JOptionPane.showMessageDialog(null, "删除成功");	
					this.resetValue();
					this.fillTable(new staff());
				}else {
					JOptionPane.showMessageDialog(null, "删除失败");	
					this.resetValue();
					this.fillTable(new staff());
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
	}

	/**
	 * 修改处理
	 * @param e
	 */
	protected void staffUpdateActionEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		int staff_number = 0;
		try {
		     staff_number = Integer.parseInt(this.staff_numberoutTXT.getText());
		} catch (NumberFormatException ex) {
		    ex.printStackTrace();
		}
		String staff_name=this.staff_nameoutTXT.getText();
		if(StringUtils.isEmpty(this.staff_numberoutTXT.getText())) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return ;
		}
		if(StringUtils.isEmpty(this.staff_nameoutTXT.getText())) {
			JOptionPane.showMessageDialog(null, "员工姓名不能为空");
			return ;
		}
		staff Staff=new staff(staff_number,staff_name);
		Connection con=null;
	     try {
	    	 con=jdbcUtil.getConnection();
			int n=StaffDao.update(con, Staff);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "修改成功");	
				this.resetValue();
				this.fillTable(new staff());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败");	
				this.resetValue();
				this.fillTable(new staff());
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
	 * 表格行点击事件
	 * @param e staff_numberoutTXT;
	private JTextField staff_nameoutTXT;
	 */
	protected void staffTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=staffTable.getSelectedRow();
		staff_numberoutTXT.setText((String) staffTable.getValueAt(row, 0));
		staff_nameoutTXT.setText((String) staffTable.getValueAt(row, 1));
		
	}

	/**
	 * 员工查询处理
	 * @param e
	 */
	protected void staffSearchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int staff_number =Integer.parseInt(this.staff_numberTXT.getText());
		String staff_name=this.staff_nameTXT.getText();
		staff Staff=new staff(staff_number,staff_name);
		this.fillTable(Staff);
	}

	/**
	 * 初始化表格
	 * @param Staff
	 */
	@SuppressWarnings("unchecked")
	private void fillTable(staff Staff) {
		
		DefaultTableModel dtm=(DefaultTableModel) staffTable.getModel();
		dtm.setRowCount(0);//设置为0行；
		Connection con=null;
	     try {
	    	 con=jdbcUtil.getConnection();
			ResultSet rs= StaffDao.select(con, Staff);
			
			while(rs.next()) {
				@SuppressWarnings("rawtypes")
				Vector v=new Vector();
				v.add(rs.getString("staff_number"));
				v.add(rs.getString("staff_name"));
				
				dtm.addRow(v);
				
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
	 *重置表量
	 *
	 */
	private void resetValue() {
		this.staff_numberoutTXT.setText("");
		this.staff_nameoutTXT.setText("");
	}
}
