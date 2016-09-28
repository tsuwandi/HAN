package module.sn.woodgenus.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.sn.woodgenus.model.WoodGenus;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class WoodGenusCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(WoodGenusCreatePanel.class);

	private WoodGenus woodGenus;

	JLabel lblWoodGenusCode;
	JLabel lblWoodGenus;

	JTextField txtWoodGenusCode;
	JTextField txtWoodGenus;

	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JButton btnCancel;
	JButton btnSave;

	JLabel lblErrorWoodGenusCode;
	JLabel lblErrorWoodGenus;

	JPanel panel;
	JScrollPane scrollPane;
	
	public WoodGenusCreatePanel() {
		woodGenus = new WoodGenus();

		DocumentFilter filter = new UppercaseDocumentFilter();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(getPreferredSize());
		panel.setLayout(null);

		lblWoodGenusCode = new JLabel("<html>Kode Nama Latin Kayu <font color=\"red\">*</font></html>");
		lblWoodGenusCode.setBounds(50, 80, 150, 25);
		panel.add(lblWoodGenusCode);

		txtWoodGenusCode = new JTextField();
		txtWoodGenusCode.setBounds(220, 80, 150, 25);
		txtWoodGenusCode.setDocument(new JTextFieldLimit(9));
		txtWoodGenusCode.setEnabled(false);
		((AbstractDocument) txtWoodGenusCode.getDocument()).setDocumentFilter(filter);
		panel.add(txtWoodGenusCode);

		lblErrorWoodGenusCode = new JLabel("");
		lblErrorWoodGenusCode.setForeground(Color.RED);
		lblErrorWoodGenusCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorWoodGenusCode);

		lblWoodGenus = new JLabel("<html>Nama Latin Kayu <font color=\"red\">*</font></html>");
		lblWoodGenus.setBounds(50, 110, 150, 25);
		panel.add(lblWoodGenus);

		txtWoodGenus = new JTextField();
		txtWoodGenus.setBounds(220, 110, 150, 25);
		txtWoodGenus.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtWoodGenus.getDocument()).setDocumentFilter(filter);
		panel.add(txtWoodGenus);

		lblErrorWoodGenus = new JLabel();
		lblErrorWoodGenus.setForeground(Color.RED);
		lblErrorWoodGenus.setBounds(425, 110, 225, 25);
		panel.add(lblErrorWoodGenus);

		lblBreadcrumb = new JLabel("ERP > Konfigurasi > Nama Latin Kayu");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (doValidate() == false) {
					return;
				}
				int response = DialogBox.showInsertChoice();
				if (response == JOptionPane.YES_OPTION) {
					doSave();
				}
			}
		});
		btnSave.setBounds(925, 570, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.sn.woodgenus.ui.WoodGenusListPanel");
				}
			}
		});
		btnCancel.setBounds(50, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtWoodGenus.requestFocusInWindow();
			}
		});
		
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(scrollPane);
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorWoodGenusCode.setText("");
		lblErrorWoodGenus.setText("");

		// if (txtWoodGenusCode.getText() == null ||
		// txtWoodGenusCode.getText().length() == 0) {
		// lblErrorWoodGenusCode.setText("Textbox Kode Nama Latin Kayu harus
		// diisi.");
		// isValid = false;
		// }

		if (txtWoodGenus.getText() == null || txtWoodGenus.getText().length() == 0) {
			lblErrorWoodGenus.setText("Textbox Nama Latin Kayu harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doSave() {
		woodGenus = new WoodGenus();
		// woodGenus.setWoodGenusCode(txtWoodGenusCode.getText());
		woodGenus.setWoodGenus(txtWoodGenus.getText());

		try {
			ServiceFactory.getWoodGenusBL().save(woodGenus);
			DialogBox.showInsert();
			MainPanel.changePanel("module.sn.woodgenus.ui.WoodGenusListPanel");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
	}
}
