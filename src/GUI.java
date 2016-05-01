import java.awt.EventQueue;



import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.ScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;


public class GUI {

	private JFrame frame;
	private JRadioButton rdbtnNewRadioButton = new JRadioButton("Simulated Annealing");
	private JRadioButton rdbtnGeneticAlgorithm = new JRadioButton("Genetic Algorithm");
	private JTextArea textArea = new JTextArea();
	JButton btnRun = new JButton("Run");
	private JTextField textField;
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GUI() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField();
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
		//textArea.setBorder(border);
		
		final JTextPane textPane = new JTextPane();
		textArea.insert("\tName\t\t\tAllocation\n\n", 0);
		btnRun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!rdbtnGeneticAlgorithm.isSelected() && !rdbtnNewRadioButton.isSelected()){
					JOptionPane.showMessageDialog(null, "Please select an algorithm to run");
				}else{
				if(rdbtnGeneticAlgorithm.isSelected()){
					try {
						GeneticAlgorithm ga = new GeneticAlgorithm(textField.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else if(rdbtnNewRadioButton.isSelected()){
					try {
						SimulatedAnnealling sa = new SimulatedAnnealling(textField.getText());
						String[][] x = sa.getData();
						textPane.setText("Initial Energy: " + sa.getInitialEnergy() + "\nEnergy: " + sa.getEnergy());
						for (int i = 1; x[i][0] != null; i++) {
							String curr = "\n\t" + x[i][0] + "\t\t\t" + x[i][1] + "\n\t";
					        textArea.append(curr);
					        for(int j =0;j<100;j++){
					        	textArea.append("-");
					        }
					 }
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			}
		});
		btnRun.setBackground(Color.WHITE);
		btnRun.setForeground(Color.BLACK);
		btnRun.setBounds(87, 407, 117, 29);
		frame.getContentPane().add(btnRun);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnGeneticAlgorithm.isSelected()){
					rdbtnGeneticAlgorithm.setSelected(false);
				}
			}
		});
		rdbtnNewRadioButton.setBounds(87, 129, 216, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnGeneticAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(rdbtnNewRadioButton.isSelected()){
					rdbtnNewRadioButton.setSelected(false);
				}
			}
		});
		rdbtnGeneticAlgorithm.setBounds(87, 187, 182, 23);
		frame.getContentPane().add(rdbtnGeneticAlgorithm);
		
		textField.setBounds(87, 348, 182, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(345, 50, 621, 471);
		JTextPane txtpnEnterFilenameHere = new JTextPane();
		txtpnEnterFilenameHere.setText("Enter filename here:");
		txtpnEnterFilenameHere.setBounds(90, 320, 174, 16);
		frame.getContentPane().add(txtpnEnterFilenameHere);
		
		
		textArea.setBounds(345, 50, 621, 471);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		scrollPane.setPreferredSize(new Dimension(667,537));
		scrollPane.setBackground(Color.WHITE);
		panel.add(scrollPane);
		frame.getContentPane().add(panel);
		
		JTextPane txtpnHintsAndJives = new JTextPane();
		txtpnHintsAndJives.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		txtpnHintsAndJives.setText("Hints And Jives FYP\n Allocation Software");
		txtpnHintsAndJives.setBounds(20, 23, 300, 80);
		frame.getContentPane().add(txtpnHintsAndJives);
		
		
		textPane.setBounds(6, 448, 307, 124);
		frame.getContentPane().add(textPane);
		
	}
}
