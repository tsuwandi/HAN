package module.purchaseprodresult.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.product.model.Product;
import module.purchaseprodresult.model.PPRNote;

public class BigPPRNoteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(BigPPRNoteDialog.class);

	JPanel panel;

	JLabel lblNote;

	JTextField txtNote;

	JLabel lblErrorNote;
	JLabel lblErrorQty;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private PPRNote pprNote;
	private BigPurchaseProdResultCreatePanel pprCreatePanel;
	private BigPurchaseProdResultEditPanel pprEditPanel;
	private BigPurchaseProdResultViewPanel pprViewPanel;
	List<Product> listOfProduct = null;

	private Integer index;

	public BigPPRNoteDialog(boolean edit, PPRNote pprNote, BigPurchaseProdResultCreatePanel pprCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.pprNote = pprNote;
		this.pprCreatePanel = pprCreatePanel;
		this.index = index;
		init();
	}

	public BigPPRNoteDialog(boolean edit, PPRNote pprNote, BigPurchaseProdResultEditPanel pprEditPanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.pprNote = pprNote;
		this.pprEditPanel = pprEditPanel;
		this.index = index;
		init();
	}
	
	public BigPPRNoteDialog(boolean view, PPRNote pprNote, BigPurchaseProdResultViewPanel pprViewPanel,
			Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.pprNote = pprNote;
		this.pprViewPanel = pprViewPanel;
		this.index = index;
		init();
	}

	public void init() {

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 145);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblNote = new JLabel("<html>No Nota <font color=\"red\">*</font></html>");
		lblNote.setBounds(25, 15, 150, 25);
		getContentPane().add(lblNote);

		lblErrorNote = new JLabel();
		lblErrorNote.setForeground(Color.RED);
		lblErrorNote.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorNote);
		
		txtNote = new JTextField(20);
		txtNote.setBounds(150, 15, 150, 25);
		getContentPane().add(txtNote);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}

				doInsert();
			}
		});
		btnInsert.setBounds(460, 45, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtNote.setText(String.valueOf(pprNote.getNote()));
		}
		
		if(isView == true) {
			txtNote.setEnabled(false);
			btnInsert.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorNote.setText("");
		
		if (txtNote.getText() == null || txtNote.getText().length() == 0) {
			lblErrorNote.setText("Textbox No Nota harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		pprNote.setNote(txtNote.getText());
		
		try {
			if (isEdit == false) {
				if (pprCreatePanel != null) {
					pprCreatePanel.listOfPPRNote.add(pprNote);
				} 
				else if (pprEditPanel != null) {
					pprEditPanel.listOfPPRNote.add(pprNote);
				}

				DialogBox.showInsert();
			} else {
				if (pprCreatePanel != null) {
					pprCreatePanel.listOfPPRNote.set(index, pprNote);
				} 
				else if (pprEditPanel != null) {
					pprEditPanel.listOfPPRNote.set(index, pprNote);
				}

				DialogBox.showInsert();
			}

			closeDialog();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (pprCreatePanel != null)
			pprCreatePanel.refreshTablePPRNote();
		 else if (pprEditPanel != null)
			 pprEditPanel.refreshTablePPRNote();

		dispose();
	}
}
