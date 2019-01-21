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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Font;

public class CLottery extends JFrame {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String m_btnStateString = "stop"; 
	private List<String> m_LotteryList = new ArrayList<String>();
	private int m_LotteryItemsNum = 0;
	private JLabel m_lblDisplayRef = null;
	private String m_currentResult = null;
	private JTextField textField4thPrize;
	private JTextField textField3rdPrize;
	private JTextField textField2ndPrize;
	private JTextField textField1stPrize;
	private JTextField textFieldTopPrize;
	private JTextField textFieldCashPrize;
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
		
		JPanel panelDisplay = new JPanel();
		
		JPanel panelOperation = new JPanel();
		
		JPanel panelPrizeType = new JPanel();
		panelPrizeType.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panelPrizeType.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton rdbtnTopPrize = new JRadioButton("特等奖");
		panel_5.add(rdbtnTopPrize);
		buttonGroup.add(rdbtnTopPrize);
		
		textFieldTopPrize = new JTextField();
		panel_5.add(textFieldTopPrize);
		textFieldTopPrize.setText("1");
		textFieldTopPrize.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panelPrizeType.add(panel_4);
		
		JRadioButton rdbtnFirstPrize = new JRadioButton("一等奖");
		panel_4.add(rdbtnFirstPrize);
		buttonGroup.add(rdbtnFirstPrize);
		
		textField1stPrize = new JTextField();
		panel_4.add(textField1stPrize);
		textField1stPrize.setText("2");
		textField1stPrize.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panelPrizeType.add(panel_3);
		
		JRadioButton rdbtnSecondPrize = new JRadioButton("二等奖");
		panel_3.add(rdbtnSecondPrize);
		buttonGroup.add(rdbtnSecondPrize);
		
		textField2ndPrize = new JTextField();
		panel_3.add(textField2ndPrize);
		textField2ndPrize.setText("4");
		textField2ndPrize.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panelPrizeType.add(panel_2);
		
		JRadioButton rdbtnThirdPrize = new JRadioButton("三等奖");
		panel_2.add(rdbtnThirdPrize);
		buttonGroup.add(rdbtnThirdPrize);
		
		textField3rdPrize = new JTextField();
		panel_2.add(textField3rdPrize);
		textField3rdPrize.setText("18");
		textField3rdPrize.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panelPrizeType.add(panel_1);
		
		JRadioButton rdbtnFourthPrize = new JRadioButton("四等奖");
		panel_1.add(rdbtnFourthPrize);
		buttonGroup.add(rdbtnFourthPrize);
		
		textField4thPrize = new JTextField();
		panel_1.add(textField4thPrize);
		textField4thPrize.setText("28");
		textField4thPrize.setColumns(10);
		
		JPanel panelStartStop = new JPanel();
		GroupLayout gl_panelOperation = new GroupLayout(panelOperation);
		gl_panelOperation.setHorizontalGroup(
			gl_panelOperation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOperation.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelStartStop, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelOperation.createSequentialGroup()
					.addComponent(panelPrizeType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(24))
		);
		gl_panelOperation.setVerticalGroup(
			gl_panelOperation.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelOperation.createSequentialGroup()
					.addGap(18)
					.addComponent(panelStartStop, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelPrizeType, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		panelPrizeType.add(panel);
		
		JRadioButton rdbtnCash = new JRadioButton("现金奖");
		panel.add(rdbtnCash);
		buttonGroup.add(rdbtnCash);
		
		textFieldCashPrize = new JTextField();
		panel.add(textFieldCashPrize);
		textFieldCashPrize.setText("10");
		textFieldCashPrize.setColumns(10);
		
		JButton btnStartStopButton = new JButton("开始/停止");
		btnStartStopButton.setIcon(new ImageIcon(CLottery.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		panelStartStop.add(btnStartStopButton);
		btnStartStopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( m_btnStateString == "stop" )
				{
					m_btnStateString = "start";
					new Thread() {
						public void run() {
							while (m_btnStateString == "start") {
								int index = random(m_LotteryItemsNum);
								try {
									String itemName = m_LotteryList.get(index);
									m_currentResult = itemName;
									m_lblDisplayRef.setText(itemName);
								} catch (Exception e) {
									e.printStackTrace();
									System.err.println(new StringBuffer("error happens").append(e));
									System.exit(1);
								}
								
								try {
									Thread.sleep(50); // in ms
								} catch (Exception e) {
									//log.fatal(e);
									System.err.println("fatal error!");
								}
							}
						}
					}.start();					
				}
				else
				{
					m_btnStateString = "stop";
					
					String result = new StringBuffer("恭喜").append(m_currentResult)
                                                           .append("中奖啦！")
                                                           .toString(); 
					m_lblDisplayRef.setText(result);	
			        System.out.println(result);					
					String currentName = m_currentResult;
					int k = 0;
					for (k = 0; k < m_LotteryList.size(); k++)
					{
					    if( currentName == m_LotteryList.get(k))
					    {
						    break;
					    }
					}
					m_LotteryList.remove(k);
					m_LotteryItemsNum = m_LotteryList.size();					
				}
		}});
		
		panelOperation.setLayout(gl_panelOperation);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(panelDisplay);
		panelDisplay.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelMain = new JPanel();
		panelDisplay.add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDisplay = new JLabel("");
		lblDisplay.setFont(new Font("Dialog", Font.BOLD, 50));
		m_lblDisplayRef = lblDisplay;
		lblDisplay.setEnabled(false);
		panelMain.add(lblDisplay);
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(panelOperation);
		
		/**
		 * Load the input file
		 * */
		ReadTextFileToArray();
	}
	
	/**
	 * A simple example program that reads a into a String using StringBuilder.
	 */
	private void ReadTextFileToArray() {
		String separator = System.getProperty("file.separator");		
		String input_file_name = new StringBuffer("res").append(separator)
				                                        .append("inputfile.txt")
				                                        .toString(); 
		
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(input_file_name ))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        System.out.println(line);
		        m_LotteryList.add(line);
		    }
		    m_LotteryItemsNum = m_LotteryList.size();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 产生随机ID
	 * 
	 * @return
	 */
	private int random(int max) {
		//log.debug("生成随机数 - start & end");
		return (int) (Math.random() * m_LotteryItemsNum);
	}	
}
