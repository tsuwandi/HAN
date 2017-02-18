package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ServiceFactory;
import main.panel.MainPanel;
import module.personalia.model.Division;

public class PopUpSearchDivisionPanel extends JDialog {

	private static final long serialVersionUID = 4895635283165466566L;
	private JTextField divisionNameField;
	private JTextField divisionIdField;
	private JPanel parent;
	
	public JPanel getParent() {
		return parent;
	}

	public void setParent(JPanel parent) {
		this.parent = parent;
	}

	public PopUpSearchDivisionPanel(JPanel parent) {
		super((JFrame) parent.getTopLevelAncestor());
		
		setSize(1024, 630);
		getContentPane().setLayout(null);
		getContentPane().setSize(1024, 630);
		setLayout(null);
		
		JLabel lblHeader = new JLabel("SEARCH DIVISI");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(10, 10, 150, 25);
		add(lblHeader);

		JLabel label = new JLabel("ID Divisi");
		label.setBounds(10, 40, 100, 30);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(110, 40, 10, 30);
		add(label_1);
		
		JLabel label_2 = new JLabel("Nama Divisi");
		label_2.setBounds(10, 80, 100, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(110, 80, 10, 30);
		add(label_3);
		
		divisionNameField = new JTextField();
		divisionNameField.setBounds(120, 80, 200, 30);
		add(divisionNameField);
		
		divisionIdField = new JTextField();
		divisionIdField.setBounds(120, 40, 200, 30);
		add(divisionIdField);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(230, 120, 90, 30);
		add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		JButton searchBtn = new JButton("Cari");
		searchBtn.setBounds(130, 120, 90, 30);
		add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
	}

	protected void search() {
		StringBuilder buffer = new StringBuilder();
		if(!"".equals(divisionIdField.getText())) {
			buffer.append(" and id like '%");
			buffer.append(divisionIdField.getText());
			buffer.append("%' ");
		}
		if(!"".equals(divisionNameField.getText())) {
			buffer.append(" and name like '%");
			buffer.append(divisionNameField.getText());
			buffer.append("%' ");
		}
		List<Division> divisions = ServiceFactory.getPersonaliaBL().getDivisions(buffer.toString());
		MainPanel.changePanel("module.personalia.ui.DivisionConfigPanel",divisions);
	}

	protected void reset() {
		divisionIdField.setText("");
		divisionNameField.setText("");
	}
}