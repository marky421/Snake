package com.maspain.snake.game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Result extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblScore;
	private JLabel lblHighScore;
	private JButton btnNewGame;
	private JButton btnResetHighScore;
	
	private int score;
	private String speed;
	private String time;
	private Record record;
	
	public Result(int score, Record record, String speed, String time) {
		this.score = score;
		this.speed = speed;
		this.time = time;
		this.record = record;
		
		createWindow();
		setBehaviors();
	}
	
	public void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSnake = new JLabel("Snake");
		lblSnake.setBounds(5, 6, 289, 16);
		lblSnake.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSnake);
		
		JLabel lblAuthor = new JLabel("Mark Spain (2013)");
		lblAuthor.setBounds(5, 24, 289, 16);
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAuthor);
		
		String scoreText = (record.newHighScore()) ? "New High Score: " + score + "!" : "Score: " + score;
		lblScore = new JLabel(scoreText);
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblScore.setBounds(5, 52, 289, 44);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScore);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(5, 284, 289, 44);
		contentPane.add(btnNewGame);
		
		JLabel lblTime = new JLabel("Time: " + time);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblTime.setBounds(5, 228, 289, 44);
		contentPane.add(lblTime);
		
		JLabel lblSpeed = new JLabel("Speed: " + speed);
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblSpeed.setBounds(5, 164, 289, 52);
		contentPane.add(lblSpeed);
		
		btnResetHighScore = new JButton("Reset High Score");
		btnResetHighScore.setBounds(5, 342, 289, 44);
		contentPane.add(btnResetHighScore);
		
		lblHighScore = new JLabel("High Score: " + record.getHighScore());
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblHighScore.setBounds(5, 108, 289, 44);
		contentPane.add(lblHighScore);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setBehaviors() {
		btnNewGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StartWindow();
			}
		});
		
		btnNewGame.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
					new StartWindow();
				}
			}
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
		});
		
		btnResetHighScore.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				record.resetHighScore();
				lblHighScore.setText("High Score: 0");
			}
		});
	}
}
