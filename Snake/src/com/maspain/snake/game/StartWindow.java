package com.maspain.snake.game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JSlider;

public class StartWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final String title = "Snake";
	
	private JPanel contentPane;
	private JButton btnStartGame;
	private JSlider sliderSpeed;
	
	public StartWindow() {
		createWindow();
		setBehaviors();
	}
	
	private void setBehaviors() {
		btnStartGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(getSpeed());
			}
		});
		
		sliderSpeed.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
					new Game(getSpeed());
				}
			}
			
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	private Speed getSpeed() {
		Speed gameSpeed;
		switch (sliderSpeed.getValue()) {
			case 0:
				gameSpeed = Speed.NOVICE;
				break;
			case 1:
				gameSpeed = Speed.SLOW;
				break;
			case 2:
				gameSpeed = Speed.NORMAL;
				break;
			case 3:
				gameSpeed = Speed.FAST;
				break;
			case 4:
				gameSpeed = Speed.EXTREME;
				break;
			default:
				gameSpeed = Speed.NORMAL;
		}
		return gameSpeed;
	}
	
	private void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle(title);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSnake = new JLabel("Snake");
		lblSnake.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		lblSnake.setHorizontalAlignment(SwingConstants.CENTER);
		lblSnake.setBounds(6, 6, 438, 32);
		contentPane.add(lblSnake);
		
		JLabel lblByMarkSpain = new JLabel("By: Mark Spain (2013)");
		lblByMarkSpain.setHorizontalAlignment(SwingConstants.CENTER);
		lblByMarkSpain.setBounds(6, 50, 438, 16);
		contentPane.add(lblByMarkSpain);
		
		btnStartGame = new JButton("Start Game!");
		btnStartGame.setBounds(6, 201, 438, 71);
		contentPane.add(btnStartGame);
		
		Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();
		sliderLabels.put(new Integer(0), new JLabel("Novice"));
		sliderLabels.put(new Integer(1), new JLabel("Slow"));
		sliderLabels.put(new Integer(2), new JLabel("Normal"));
		sliderLabels.put(new Integer(3), new JLabel("Fast"));
		sliderLabels.put(new Integer(4), new JLabel("Extreme"));
		
		sliderSpeed = new JSlider();
		sliderSpeed.setMajorTickSpacing(1);
		sliderSpeed.setValue(2);
		sliderSpeed.setMaximum(4);
		sliderSpeed.setLabelTable(sliderLabels);
		sliderSpeed.setToolTipText("Choose a speed");
		sliderSpeed.setPaintLabels(true);
		sliderSpeed.setPaintTicks(true);
		sliderSpeed.setSnapToTicks(true);
		sliderSpeed.setBounds(6, 78, 438, 111);
		contentPane.add(sliderSpeed);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
