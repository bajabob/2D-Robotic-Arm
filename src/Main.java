import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Main
{

	/**
	 * All the buttons to interact with the robotic arm
	 */
	private static JButton incAx1, decAx1, incAx2, decAx2, incAx3, decAx3,
			paintCircle;

	/**
	 * Displays the current angles of the robotic arm
	 */
	private static JLabel degAx1, degAx2, degAx3;

	/**
	 * manages each of the three robotic arm segments
	 */
	private static RoboticArm roboticArm;

	/**
	 * takes care of drawing the robotic arm
	 */
	private static RobotDisplay displayPanel;

	private static class RobotDisplay extends JPanel
	{
		public void paintComponent( Graphics g )
		{
			super.paintComponent( g );
			g.setColor( Color.BLACK );
			g.fillRect( 0, 0, 600, 600 );
			roboticArm.onDraw( g );
		}
	}

	/**
	 * Called whenever a button is pressed
	 */
	private static class OnAxisButtonPress implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			JButton button = (JButton) e.getSource();
			if ( button.equals( incAx1 ) )
			{
				roboticArm.incrementLink1();
				degAx1.setText( "" + roboticArm.getLink1Angle() );
			}
			if ( button.equals( incAx2 ) )
			{
				roboticArm.incrementLink2();
				degAx2.setText( "" + roboticArm.getLink2Angle() );
			}
			if ( button.equals( incAx3 ) )
			{
				roboticArm.incrementLink3();
				degAx3.setText( "" + roboticArm.getLink3Angle() );
			}
			if ( button.equals( decAx1 ) )
			{
				roboticArm.decrementLink1();
				degAx1.setText( "" + roboticArm.getLink1Angle() );
			}
			if ( button.equals( decAx2 ) )
			{
				roboticArm.decrementLink2();
				degAx2.setText( "" + roboticArm.getLink2Angle() );
			}
			if ( button.equals( decAx3 ) )
			{
				roboticArm.decrementLink3();
				degAx3.setText( "" + roboticArm.getLink3Angle() );
			}
			if ( button.equals( paintCircle ) )
			{
				roboticArm.paintCircle();
			}
			roboticArm.onTranslate();
			displayPanel.repaint();
		}
	}

	public static void main( String[] args )
	{

		roboticArm = new RoboticArm( 300, 600 );

		displayPanel = new RobotDisplay();
		displayPanel.setBounds( 0, 0, 600, 600 );

		OnAxisButtonPress onAxisButtonPress = new OnAxisButtonPress();

		/**
		 * Create axis editing buttons
		 */
		incAx1 = new JButton( "+" );
		incAx1.addActionListener( onAxisButtonPress );
		decAx1 = new JButton( "-" );
		decAx1.addActionListener( onAxisButtonPress );

		incAx2 = new JButton( "+" );
		incAx2.addActionListener( onAxisButtonPress );
		decAx2 = new JButton( "-" );
		decAx2.addActionListener( onAxisButtonPress );

		incAx3 = new JButton( "+" );
		incAx3.addActionListener( onAxisButtonPress );
		decAx3 = new JButton( "-" );
		decAx3.addActionListener( onAxisButtonPress );

		paintCircle = new JButton( "Paint Circle" );
		paintCircle.addActionListener( onAxisButtonPress );

		/**
		 * Create axis degree labels
		 */
		degAx1 = new JLabel( "" );
		degAx2 = new JLabel( "" );
		degAx3 = new JLabel( "" );

		/**
		 * Create control panel, add buttons
		 */
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground( Color.WHITE );
		controlPanel.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		controlPanel.setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add( new JLabel( "Axis 1", SwingConstants.CENTER ), c );
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add( incAx1, c );
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add( degAx1, c );
		c.gridx = 3;
		c.gridy = 1;
		controlPanel.add( decAx1, c );

		c.gridx = 0;
		c.gridy = 2;
		controlPanel.add( new JLabel( "Axis 2", SwingConstants.CENTER ), c );
		c.gridx = 1;
		c.gridy = 2;
		controlPanel.add( incAx2, c );
		c.gridx = 2;
		c.gridy = 2;
		controlPanel.add( degAx2, c );
		c.gridx = 3;
		c.gridy = 2;
		controlPanel.add( decAx2, c );

		c.gridx = 0;
		c.gridy = 3;
		controlPanel.add( new JLabel( "Axis 2", SwingConstants.CENTER ), c );
		c.gridx = 1;
		c.gridy = 3;
		controlPanel.add( incAx3, c );
		c.gridx = 2;
		c.gridy = 3;
		controlPanel.add( degAx3, c );
		c.gridx = 3;
		c.gridy = 3;
		controlPanel.add( decAx3, c );

		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		controlPanel.add( new JLabel( "Control Panel", SwingConstants.CENTER ),
				c );
		c.gridy = 4;
		controlPanel.add( paintCircle, c );

		/**
		 * Create the overall panel, and incorporate
		 *   all the sub-panels for this project
		 */
		JPanel panes = new JPanel();
		panes.setLayout( new BorderLayout() );
		panes.add( displayPanel, BorderLayout.CENTER );
		panes.add( controlPanel, BorderLayout.EAST );

		/**
		 * Additional items needed to setup the window
		 */
		JFrame window = new JFrame( "Team Ares - Project 1" );
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setContentPane( panes );
		window.setSize( 830, 630 );
		window.setLocation( 100, 100 );
		window.setVisible( true );
		window.setResizable( false );

	}

}