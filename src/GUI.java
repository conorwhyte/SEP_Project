import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JTable;


public class GUI {

	private JFrame frame;
	private JRadioButton rdbtnNewRadioButton = new JRadioButton("Simulated Annealing");
	private JRadioButton rdbtnGeneticAlgorithm = new JRadioButton("Genetic Algorithm");
	
	JButton btnRun = new JButton("Run");
	
	private JTextField textField;
	private JTable table;
	/**
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

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField();
		
		
		
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
						String[] columns = {"Name", "Project"};
						String[][] x = sa.getData();
						for(int i=0;x[i][0] != null;i++){
								System.out.println("Name:" + x[i][0] + " Allocation: " + x[i][1]);
						}
						JTable table = new JTable(sa.getData(), columns);
						table.setBounds(281, 106, 709, 454);
						frame.getContentPane().add(table);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			}
			
		});
		btnRun.setBackground(Color.WHITE);
		btnRun.setForeground(Color.BLACK);
		btnRun.setBounds(87, 531, 117, 29);
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
		
		JTextPane txtpnEnterFilenameHere = new JTextPane();
		txtpnEnterFilenameHere.setText("Enter filename here:");
		txtpnEnterFilenameHere.setBounds(90, 320, 174, 16);
		frame.getContentPane().add(txtpnEnterFilenameHere);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(275, 100, 715, 460);
		frame.getContentPane().add(scrollPane);
		
		
		
	}
}
