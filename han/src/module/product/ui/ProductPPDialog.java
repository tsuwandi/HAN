package module.product.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.product.model.Product;
import module.product.model.ProductPP;

public class ProductPPDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductPPDialog.class);

	JPanel panel;

	JLabel lblPrice;
	JLabel lblEffectiveStartDate;
	JLabel lblEffectiveEndDate;

	JTextField txtPrice;
	JDateChooser dcEffectiveStartDate;
	JDateChooser dcEffectiveEndDate;

	JLabel lblErrorPrice;
	JLabel lblErrorEffectiveStartDate;
	JLabel lblErrorEffectiveEndDate;

	JButton btnInsert;

	private boolean isEdit;
	private boolean isView;
	private ProductPP productPP;
	private ProductCreatePanel productCreatePanel;
	private ProductEditPanel productEditPanel;
	private ProductViewPanel productViewPanel;
	List<Product> listOfProduct = null;

	private Integer index;

	public ProductPPDialog(boolean edit, ProductPP productPP, ProductCreatePanel productCreatePanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.productPP = productPP;
		this.productCreatePanel = productCreatePanel;
		this.index = index;
		init();
	}

	public ProductPPDialog(boolean edit, ProductPP productPP, ProductEditPanel productEditPanel,
			Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.productPP = productPP;
		this.productEditPanel = productEditPanel;
		this.index = index;
		init();
	}
	
	public ProductPPDialog(boolean view, ProductPP productPP, ProductViewPanel productViewPanel,
			Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.productPP = productPP;
		this.productViewPanel = productViewPanel;
		this.index = index;
		init();
	}

	public void init() {

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 190);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblPrice = new JLabel("<html>Harga Beli<font color=\"red\">*</font></html>");
		lblPrice.setBounds(25, 15, 150, 25);
		getContentPane().add(lblPrice);

		lblErrorPrice = new JLabel();
		lblErrorPrice.setForeground(Color.RED);
		lblErrorPrice.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorPrice);
		
		txtPrice = new NumberField(10);
		txtPrice.setBounds(150, 15, 150, 25);
		getContentPane().add(txtPrice);
		
		lblEffectiveStartDate = new JLabel("<html>Tanggal Mulai Efektif <font color=\"red\">*</font></html>");
		lblEffectiveStartDate.setBounds(25, 45, 150, 25);
		getContentPane().add(lblEffectiveStartDate);
		
		lblErrorEffectiveStartDate = new JLabel();
		lblErrorEffectiveStartDate.setForeground(Color.RED);
		lblErrorEffectiveStartDate.setBounds(335, 45, 225, 25);
		getContentPane().add(lblErrorEffectiveStartDate);

		dcEffectiveStartDate = new JDateChooser(new Date());
		dcEffectiveStartDate.setBounds(150, 45, 150, 25);
		dcEffectiveStartDate.setDateFormatString("dd-MM-yyyy");
		getContentPane().add(dcEffectiveStartDate);
		
		lblEffectiveEndDate = new JLabel("<html>Tanggal Akhir Efektif <font color=\"red\">*</font></html>");
		lblEffectiveEndDate.setBounds(25, 75, 150, 25);
		getContentPane().add(lblEffectiveEndDate);
		
		lblErrorEffectiveEndDate = new JLabel();
		lblErrorEffectiveEndDate.setForeground(Color.RED);
		lblErrorEffectiveEndDate.setBounds(335, 75, 225, 25);
		getContentPane().add(lblErrorEffectiveEndDate);

		dcEffectiveEndDate = new JDateChooser(new Date());
		dcEffectiveEndDate.setBounds(150, 75, 150, 25);
		dcEffectiveEndDate.setDateFormatString("dd-MM-yyyy");
		getContentPane().add(dcEffectiveEndDate);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}

				doInsert();
			}
		});
		btnInsert.setBounds(460, 105, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtPrice.setText(String.valueOf(productPP.getPrice()));
		}
		
		if(isView == true) {
			txtPrice.setEnabled(false);
			dcEffectiveStartDate.setEnabled(false);
			dcEffectiveEndDate.setEnabled(false);
			btnInsert.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorPrice.setText("");
		lblErrorEffectiveStartDate.setText("");
		if (txtPrice.getText() == null || txtPrice.getText().length() == 0) {
			lblErrorPrice.setText("Textbox Harga beli harus diisi.");
			isValid = false;
		}
		
		if (dcEffectiveStartDate.getDate() == null) {
			lblErrorEffectiveStartDate.setText("Tanggal mulai efektif harus dipilih.");
			isValid = false;
		}
		
		if (dcEffectiveEndDate.getDate() == null) {
			lblErrorEffectiveEndDate.setText("Tanggal akhir efektif harus dipilih.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		productPP.setPrice(Double.valueOf(txtPrice.getText()));
		productPP.setEffectiveStartDate(dcEffectiveStartDate.getDate());
		productPP.setEffectiveEndDate(dcEffectiveEndDate.getDate());
		try {
			if (isEdit == false) {
				if (productCreatePanel != null) {
					productCreatePanel.listOfProductPP.add(productPP);
				} 
				else if (productEditPanel != null) {
					productEditPanel.listOfProductPP.add(productPP);
				}

				DialogBox.showInsert();
			} else {
				if (productCreatePanel != null) {
					productCreatePanel.listOfProductPP.set(index, productPP);
				} 
				else if (productEditPanel != null) {
					productEditPanel.listOfProductPP.set(index, productPP);
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
		if (productCreatePanel != null)
			productCreatePanel.refreshTableProductPP();
		 else if (productEditPanel != null)
			 productEditPanel.refreshTableProductPP();

		dispose();
	}
}
