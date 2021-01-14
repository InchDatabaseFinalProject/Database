package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

public class aboutInnerFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aboutInnerFrm frame = new aboutInnerFrm();
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
	public aboutInnerFrm() {
		getContentPane().setBackground(Color.WHITE);
		setIconifiable(true);
		setBackground(Color.RED);
		setClosable(true);
		setTitle("\u8FD9\u662F\u4E2A\u6570\u636E\u5E93\u5B9E\u9A8C");
		setBounds(100, 100, 450, 300);
		
		JLabel label = new JLabel("\u5173\u4E8E\u9152\u5E97\u5165\u4F4F\u7BA1\u7406\u7CFB\u7EDF\u7684\u8BFE\u7A0B\u8BBE\u8BA1");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
