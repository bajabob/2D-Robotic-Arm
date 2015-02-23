import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Main {
	
	private static JButton inc_ax1, dec_ax1, inc_ax2, dec_ax2, inc_ax3, dec_ax3;
	private static JLabel deg_ax1, deg_ax2, deg_ax3;
	
	private static class RobotDisplay extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.drawString("Robot Area", 20, 10);
			//g.drawString("Control Panel", 700, 10);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 600, 600);
		}
	}

	private static class OnAxisButtonPress implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	public static void main(String[] args) {

		RobotDisplay displayPanel = new RobotDisplay();
		displayPanel.setBounds(0, 0, 600, 600);
		
		OnAxisButtonPress onAxisButtonPress = new OnAxisButtonPress();
		
		/**
		 * Create axis editing buttons
		 */
		inc_ax1 = new JButton("+");
		inc_ax1.addActionListener(onAxisButtonPress);
		dec_ax1 = new JButton("-");
		dec_ax1.addActionListener(onAxisButtonPress);
		
		inc_ax2 = new JButton("+");
		inc_ax2.addActionListener(onAxisButtonPress);
		dec_ax2 = new JButton("-");
		dec_ax2.addActionListener(onAxisButtonPress);
		
		inc_ax3 = new JButton("+");
		inc_ax3.addActionListener(onAxisButtonPress);
		dec_ax3 = new JButton("-");
		dec_ax3.addActionListener(onAxisButtonPress);
		
		/**
		 * Create axis degree labels
		 */
		deg_ax1 = new JLabel("90");
		deg_ax2 = new JLabel("0");
		deg_ax3 = new JLabel("0");
		
		/**
		 * Create control panel, add buttons
		 */
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		controlPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(new JLabel("Axis 1", SwingConstants.CENTER), c);
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add(inc_ax1, c);
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add(deg_ax1, c);
		c.gridx = 3;
		c.gridy = 1;
		controlPanel.add(dec_ax1, c);
		
		c.gridx = 0;
		c.gridy = 2;
		controlPanel.add(new JLabel("Axis 2", SwingConstants.CENTER), c);
		c.gridx = 1;
		c.gridy = 2;
		controlPanel.add(inc_ax2, c);
		c.gridx = 2;
		c.gridy = 2;
		controlPanel.add(deg_ax2, c);
		c.gridx = 3;
		c.gridy = 2;
		controlPanel.add(dec_ax2, c);
		
		c.gridx = 0;
		c.gridy = 3;
		controlPanel.add(new JLabel("Axis 2", SwingConstants.CENTER), c);
		c.gridx = 1;
		c.gridy = 3;
		controlPanel.add(inc_ax3, c);
		c.gridx = 2;
		c.gridy = 3;
		controlPanel.add(deg_ax3, c);
		c.gridx = 3;
		c.gridy = 3;
		controlPanel.add(dec_ax3, c);
		
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		controlPanel.add(new JLabel("Control Panel", SwingConstants.CENTER), c);
		
		
		
		JPanel panes = new JPanel();
		panes.setLayout(new BorderLayout());
		panes.add(displayPanel, BorderLayout.CENTER);
		panes.add(controlPanel, BorderLayout.EAST);
		
		
		JFrame window = new JFrame("Team Ares - Project 1");
		window.setContentPane(panes);
		window.setSize(800, 600);
		window.setLocation(100, 100);
		window.setVisible(true);

	}

}