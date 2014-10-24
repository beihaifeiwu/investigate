package com.freetmp.investigate.qrcode;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import com.google.zxing.EncodeHintType;

public class QrcdoeWindow {

	private JFrame frame;
	private JLabel qrcodeImg;
	private JTextArea content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QrcdoeWindow window = new QrcdoeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QrcdoeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("二维码生成");
		frame.setBounds(100, 100, 557, 283);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		qrcodeImg = new JLabel("");
		qrcodeImg.setBackground(SystemColor.activeCaption);
		qrcodeImg.setBounds(32, 24, 200, 200);
		qrcodeImg.setBorder(new LineBorder(new Color(50, 205, 50)));
		panel.add(qrcodeImg);
		
		JLabel lblNewLabel = new JLabel("内容：");
		lblNewLabel.setBounds(296, 24, 54, 15);
		panel.add(lblNewLabel);
		
		content = new JTextArea();
		lblNewLabel.setLabelFor(content);
		content.setLineWrap(true);
		content.setBounds(296, 49, 223, 122);
		panel.add(content);
		
		JButton make = new JButton("生成");
		make.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = content.getText();
				if(str != null && !str.trim().equals("")){
					File file = QRCode.from(str)
									  .withHint(EncodeHintType.CHARACTER_SET, "UTF-8")
									  .to(ImageType.JPG)
									  .withSize(qrcodeImg.getWidth(), qrcodeImg.getHeight())
									  .file();
					BufferedImage image = null;
					try {
						image = ImageIO.read(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(image );
					qrcodeImg.setIcon(icon);
				}
			}
		});
		make.setBounds(354, 201, 93, 23);
		panel.add(make);
	}
	protected JLabel getQrcodeImg() {
		return qrcodeImg;
	}
	protected JTextArea getContent() {
		return content;
	}
}
