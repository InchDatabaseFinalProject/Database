package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class isFevaluateFrm extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					isFevaluateFrm frame = new isFevaluateFrm();
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
	public isFevaluateFrm() {
		
	}
	/**
	 * 传递相关参数
	 * @param customer_id
	 * @param room_number
	 * @param staff_number
	 * @param time
	 */
	public isFevaluateFrm(String customer_id,int room_number,Date time) {
		setTitle("\u8BC4\u4EF7");
		setBounds(100, 100, 386, 175);
		
		JLabel label = new JLabel("\u662F\u5426\u5BF9\u672C\u6B21\u7684\u5165\u4F4F\u611F\u5230\u6EE1\u610F\u5462\uFF1F\u6765\u8BC4\u4EF7\u4E00\u4E0B\u5427\uFF01");
		
		JButton btnYes = new JButton("yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//传递相关参数给评价表
				dispose();
				new evaluateAddInterfrm(customer_id,room_number,time).setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("no");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(btnYes)
							.addGap(85)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(label)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(label)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnYes))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
