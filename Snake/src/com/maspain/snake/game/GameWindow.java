package com.maspain.snake.game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private String speed;
	private String highScore;
	public JButton btnPause;
	public JButton btnNewGame;
	public JLabel lblScore;
	public JLabel lblHigh;
	public JLabel lblTime;
	
	public GameWindow(String speed, String highScore) {
		this.speed = speed;
		this.highScore = highScore;
		createWindow();
	}
	
	private void createWindow() {
		Border line = new LineBorder(Color.black);
		Border padding = new EmptyBorder(5, 5, 5, 5);
		CompoundBorder border = new CompoundBorder(line, padding);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 100, 100, 100, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
		lblScore = new JLabel("Score: 0");
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.anchor = GridBagConstraints.WEST;
		gbc_lblScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblScore.gridx = 0;
		gbc_lblScore.gridy = 0;
		lblScore.setBorder(border);
		contentPane.add(lblScore, gbc_lblScore);
		
		lblHigh = new JLabel("High: " + highScore);
		GridBagConstraints gbc_lblHigh = new GridBagConstraints();
		gbc_lblHigh.anchor = GridBagConstraints.WEST;
		gbc_lblHigh.insets = new Insets(0, 0, 5, 5);
		gbc_lblHigh.gridx = 1;
		gbc_lblHigh.gridy = 0;
		lblHigh.setBorder(border);
		contentPane.add(lblHigh, gbc_lblHigh);
		
		lblTime = new JLabel("Time: 00:00");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.WEST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 2;
		gbc_lblTime.gridy = 0;
		lblTime.setBorder(border);
		contentPane.add(lblTime, gbc_lblTime);
		
		JLabel lblSpeed = new JLabel("Speed: " + speed);
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.anchor = GridBagConstraints.WEST;
		gbc_lblSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeed.gridx = 3;
		gbc_lblSpeed.gridy = 0;
		lblSpeed.setBorder(border);
		contentPane.add(lblSpeed, gbc_lblSpeed);
		
		btnPause = new JButton("PAUSE");
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.anchor = GridBagConstraints.EAST;
		gbc_btnPause.fill = GridBagConstraints.VERTICAL;
		gbc_btnPause.insets = new Insets(0, 0, 5, 5);
		gbc_btnPause.gridx = 4;
		gbc_btnPause.gridy = 0;
		contentPane.add(btnPause, gbc_btnPause);
		
		btnNewGame = new JButton("New Game");
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.anchor = GridBagConstraints.EAST;
		gbc_btnNewGame.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridx = 5;
		gbc_btnNewGame.gridy = 0;
		contentPane.add(btnNewGame, gbc_btnNewGame);
	}
}
