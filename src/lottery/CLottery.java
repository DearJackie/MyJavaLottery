package lottery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

import java.awt.CardLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.Component;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import java.awt.Font;

import lottery.CInputs;

public class CLottery extends JFrame {
	private boolean m_debug = true; 
	private String m_btnStateString = "stop"; 
	CInputs m_InputRef = null;
	private JLabel m_lblNamelistRef = null;
	private JLabel m_lblPrizeLevelRef = null;
	private JLabel m_lblPrizeTypeRef = null;
	private String m_currentResult = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CLottery frame = new CLottery();
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
	public CLottery() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CLottery.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/capslock-icon.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("抽奖");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{959, 0};
		gridBagLayout.rowHeights = new int[]{372, 372, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panelDisplay = new JPanel();
		panelDisplay.setBackground(Color.BLUE);
		GridBagConstraints gbc_panelDisplay = new GridBagConstraints();
		gbc_panelDisplay.gridheight = 2;
		gbc_panelDisplay.gridwidth = 4;
		gbc_panelDisplay.fill = GridBagConstraints.BOTH;
		gbc_panelDisplay.insets = new Insets(0, 0, 5, 0);
		gbc_panelDisplay.gridx = 0;
		gbc_panelDisplay.gridy = 0;
		getContentPane().add(panelDisplay, gbc_panelDisplay);
		GridBagLayout gbl_panelDisplay = new GridBagLayout();
		gbl_panelDisplay.columnWidths = new int[]{959, 0};
		gbl_panelDisplay.rowHeights = new int[]{122, 122, 122, 0};
		gbl_panelDisplay.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelDisplay.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDisplay.setLayout(gbl_panelDisplay);
		
		JLabel lblPrizeLevel = new JLabel("奖项");
		m_lblPrizeLevelRef = lblPrizeLevel;
		lblPrizeLevel.setFont(new Font("Dialog", Font.BOLD, 60));
		lblPrizeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPrizeLevel = new GridBagConstraints();
		gbc_lblPrizeLevel.fill = GridBagConstraints.BOTH;
		gbc_lblPrizeLevel.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrizeLevel.gridx = 0;
		gbc_lblPrizeLevel.gridy = 0;
		panelDisplay.add(lblPrizeLevel, gbc_lblPrizeLevel);
		
		JLabel lblPrizeType = new JLabel("奖品");
		m_lblPrizeTypeRef = lblPrizeType;
		lblPrizeType.setFont(new Font("Dialog", Font.BOLD, 40));
		lblPrizeType.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPrizeType = new GridBagConstraints();
		gbc_lblPrizeType.fill = GridBagConstraints.BOTH;
		gbc_lblPrizeType.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrizeType.gridx = 0;
		gbc_lblPrizeType.gridy = 1;
		panelDisplay.add(lblPrizeType, gbc_lblPrizeType);
		
		JLabel lblDisplay = new JLabel("名单");
		GridBagConstraints gbc_lblDisplay = new GridBagConstraints();
		gbc_lblDisplay.fill = GridBagConstraints.BOTH;
		gbc_lblDisplay.gridx = 0;
		gbc_lblDisplay.gridy = 2;
		panelDisplay.add(lblDisplay, gbc_lblDisplay);
		lblDisplay.setFont(new Font("Dialog", Font.BOLD, 80));
		m_lblNamelistRef = lblDisplay;
		lblDisplay.setEnabled(false);
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panelOperation = new JPanel();
		GridBagConstraints gbc_panelOperation = new GridBagConstraints();
		gbc_panelOperation.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelOperation.gridx = 0;
		gbc_panelOperation.gridy = 1;
		getContentPane().add(panelOperation, gbc_panelOperation);
		panelOperation.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnStartStopButton = new JButton("开始/停止");
		panelOperation.add(btnStartStopButton);
		btnStartStopButton.setFont(new Font("Dialog", Font.BOLD, 30));
		btnStartStopButton.setIcon(new ImageIcon(CLottery.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnStartStopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( m_btnStateString == "stop" )
				{
					m_btnStateString = "start";
					new Thread() {
						public void run() {
							while (m_btnStateString == "start") {
								DisplayResult("start");
								try {
									Thread.sleep(50); // in ms
								} catch (Exception e) {
									System.err.println("fatal error!");
								}
							}
						}
					}.start();					
				}
				else
				{
					m_btnStateString = "stop";
					DisplayResult("stop");
					//String strs[] = m_InputRef.StartStopRound("stop");
					
					//String result = m_currentResult;//new StringBuffer("恭喜").append(m_currentResult)
                                    //                       .append("中奖啦！")
                                    //                       .toString(); 
					//m_lblNamelistRef.setText(result);	
			        //if (m_debug) { System.out.println("prize name list: " + result); }						
				}
		}});
		
		/**
		 * Load the input file
		 * */
		m_InputRef = new CInputs();
	}
	
	private void DisplayResult( String operation ) {
		String strs[] = m_InputRef.StartStopRound(operation);
		if ( m_debug ) { System.out.println("Returned string length:" + strs.length); }

		int i = 0;
		int numNames = 0;
		String delimeter = "";
		m_currentResult = "";
		while(i < strs.length-1)
		{
			if ( strs[i] != "" )
			{
				if ( i == 0) 
				{ 
					m_lblPrizeLevelRef.setText(strs[i]); 
				}
				else if (i == 1) 
				{
					m_lblPrizeTypeRef.setText(strs[i]);											
				}
				else 
				{
					if ( m_currentResult == "" )
					{
						numNames ++;
						m_currentResult = m_currentResult + strs[i];
					}
					else
					{
						if ( (numNames % 2) == 0)
						{
							delimeter = "<br>";
						}
						else
						{
							delimeter = ",";
						}
						numNames ++;						
						m_currentResult = m_currentResult + delimeter + strs[i];
					}
				}
			}
			i++;
		}
		m_currentResult = "<html>"+m_currentResult + "</html>";
		m_lblNamelistRef.setText(m_currentResult);
		
		if ( operation == "stop")
		{
			if (m_debug) { System.out.println("prize name list: " + m_currentResult); }
		}		
	}
}
