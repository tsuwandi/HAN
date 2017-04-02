package module.stockopname.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.component.NumberField;
import main.component.TextField;

public class CreateNewScheduledSOPanel extends JPanel {
	private JLabel soNameLbl;
	private JLabel soReccurenceLbl;
	private JLabel soDayLbl;
	private JLabel soDateLbl;
	
	private TextField soNameField;
	private JComboBox<String> soReccurenceCmb;
	private JComboBox<String> soDayCmb;
	private NumberField soDateField;
	
	private JButton productBtn;
	private JButton saveBtn;
	private JButton backBtn;
	
	private JTable soProductTable;
	
}
