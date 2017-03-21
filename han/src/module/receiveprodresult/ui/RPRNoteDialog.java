package module.receiveprodresult.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;

import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import module.product.model.Product;
import module.receiveprodresult.model.RPRNote;

import org.apache.log4j.Logger;

public class RPRNoteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(RPRNoteDialog.class);

	JPanel panel;

	JLabel lblNote;

	JTextField txtNote;

	JLabel lblErrorNote;
	JLabel lblErrorQty;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private RPRNote rprNote;
	private ReceiveProdResultCreatePanel rprCreatePanel;
	//private ReceiveProdResultEditPanel rprEditPanel;
	private ReceiveProdResultViewPanel pprViewPanel;
	List<Product> listOfProduct = null;

	private Integer index;

	public RPRNoteDialog(boolean edit, RPRNote rprNote, ReceiveProdResultCreatePanel rprCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.rprNote = rprNote;
		this.rprCreatePanel = rprCreatePanel;
		this.index = index;
		init();
	}

//	public RPRNoteDialog(boolean edit, RPRNote rprNote, ReceiveProdResultEditPanel rprEditPanel,
//			Integer index) {
//		this.isEdit = edit;
//		this.isView = false;
//		this.rprNote = rprNote;
//		this.rprEditPanel = rprEditPanel;
//		this.index = index;
//		init();
//	}
	
	public RPRNoteDialog(boolean view, RPRNote rprNote, ReceiveProdResultViewPanel pprViewPanel,
			Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.rprNote = rprNote;
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
			txtNote.setText(String.valueOf(rprNote.getNote()));
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
		rprNote.setNote(txtNote.getText());
		
		try {
			if (isEdit == false) {
				if (rprCreatePanel != null) {
					rprCreatePanel.listOfRPRNote.add(rprNote);
				} 
//				else if (rprEditPanel != null) {
//					rprEditPanel.listOfRPRNote.add(rprNote);
//				}

				DialogBox.showInsert();
			} else {
				if (rprCreatePanel != null) {
					rprCreatePanel.listOfRPRNote.set(index, rprNote);
				} 
//				else if (rprEditPanel != null) {
//					rprEditPanel.listOfRPRNote.set(index, rprNote);
//				}

				DialogBox.showInsert();
			}

			closeDialog();
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void closeDialog() {
		if (rprCreatePanel != null)
			rprCreatePanel.refreshTableRPRNote();
//		 else if (rprEditPanel != null)
//			 rprEditPanel.refreshTableRPRNote();

		dispose();
	}
}
