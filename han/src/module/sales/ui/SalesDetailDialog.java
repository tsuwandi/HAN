package module.sales.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import module.pembelian.model.Product;
import module.product.model.Uom;
import module.sales.model.SalesDetail;

public class SalesDetailDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SalesDetailDialog.class);

	JPanel panel;

	JLabel lblProductCode;
	JLabel lblProductName;
	JLabel lblQuantity;
	JLabel lblUom;
	JLabel lblNettPrice;

	JTextField txtProductCode;
	JTextArea txtProductName;
	NumberField txtOrderQty;
	JTextField txtUom;
	NumberField txtNettPrice;

	JButton btnInsert;

	JLabel lblErrorProductCode;
	JLabel lblErrorOrderQty;
	JLabel lblErrorNettPrice;

	private boolean isEdit;
	private boolean isView;
	private SalesDetail salesDetail;
	private SalesCreatePanel salesCreate;
	private SalesEditPanel salesEdit;
	private SalesViewPanel salesView;

	private Integer index;

	public SalesDetailDialog(boolean edit, SalesDetail salesDetail, SalesCreatePanel salesCreate, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.salesDetail = salesDetail;
		this.salesCreate = salesCreate;
		this.index = index;
		init();
	}

	public SalesDetailDialog(boolean edit, SalesDetail salesDetail, SalesEditPanel salesEdit, Integer index) {
		this.isEdit = edit;
		this.isView = false;
		this.salesDetail = salesDetail;
		this.salesEdit = salesEdit;
		this.index = index;
		init();
	}

	public SalesDetailDialog(boolean view, SalesDetail salesDetail, SalesViewPanel salesView, Integer index) {
		this.isEdit = true;
		this.isView = view;
		this.salesDetail = salesDetail;
		this.salesView = salesView;
		this.index = index;
		init();
	}

	public void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 310);
		getContentPane().setLayout(null);

		DocumentFilter filter = new UppercaseDocumentFilter();

		lblProductCode = new JLabel("<html>Kode Product <font color=\"red\">*</font></html>");
		lblProductCode.setBounds(25, 15, 150, 25);
		getContentPane().add(lblProductCode);

		txtProductCode = new JTextField();
		txtProductCode.setBounds(150, 15, 150, 25);

		Action productCodeAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Product product = new Product();
					Uom uom = new Uom();

					product = ServiceFactory.getSalesBL().getProductByCode(txtProductCode.getText());
					if (product == null) {
						txtProductName.setText("");
						txtUom.setText("");
						
						salesDetail = new SalesDetail();
					} else {
						txtProductName.setText(product.getProductName());
						salesDetail.setProductId(product.getId());
						salesDetail.getProduct().setLength(product.getLength());
						salesDetail.getProduct().setWidth(product.getWidth());
						salesDetail.getProduct().setThickness(product.getThickness());
						
						uom = ServiceFactory.getSalesBL().getUomByProductUomId(product.getProductUomId());
						txtUom.setText(uom.getUom());
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
		txtProductCode.addActionListener(productCodeAction);

		((AbstractDocument) txtProductCode.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtProductCode);

		lblErrorProductCode = new JLabel();
		lblErrorProductCode.setForeground(Color.RED);
		lblErrorProductCode.setBounds(335, 15, 225, 25);
		getContentPane().add(lblErrorProductCode);

		lblProductName = new JLabel("Nama Product");
		lblProductName.setBounds(25, 45, 150, 25);
		getContentPane().add(lblProductName);

		txtProductName = new JTextArea();
		txtProductName.setBounds(150, 45, 150, 75);
		txtProductName.setEnabled(false);
		txtProductName.setBackground(Color.LIGHT_GRAY);
		((AbstractDocument) txtProductName.getDocument()).setDocumentFilter(filter);
		Border border = BorderFactory.createLineBorder(Color.gray);
		txtProductName.setBorder(border);
		txtProductName.setLineWrap(true);
		getContentPane().add(txtProductName);

		lblUom = new JLabel("UOM");
		lblUom.setBounds(25, 125, 150, 25);
		getContentPane().add(lblUom);

		txtUom = new JTextField();
		txtUom.setBounds(150, 125, 150, 25);
		txtUom.setEnabled(false);
		((AbstractDocument) txtUom.getDocument()).setDocumentFilter(filter);
		getContentPane().add(txtUom);

		lblQuantity = new JLabel("<html>Quantity <font color=\"red\">*</font></html>");
		lblQuantity.setBounds(25, 155, 150, 25);
		getContentPane().add(lblQuantity);

		txtOrderQty = new NumberField(6);
		txtOrderQty.setBounds(150, 155, 150, 25);
		getContentPane().add(txtOrderQty);

		lblErrorOrderQty = new JLabel();
		lblErrorOrderQty.setForeground(Color.RED);
		lblErrorOrderQty.setBounds(335, 155, 200, 25);
		getContentPane().add(lblErrorOrderQty);

		lblNettPrice = new JLabel("<html>Nett Price <font color=\"red\">*</font></html>");
		lblNettPrice.setBounds(25, 185, 150, 25);
		getContentPane().add(lblNettPrice);

		txtNettPrice = new NumberField(12);
		txtNettPrice.setBounds(150, 185, 150, 25);
		getContentPane().add(txtNettPrice);

		lblErrorNettPrice = new JLabel();
		lblErrorNettPrice.setForeground(Color.RED);
		lblErrorNettPrice.setBounds(335, 185, 200, 25);
		getContentPane().add(lblErrorNettPrice);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doValidate() == false) {
					return;
				}
				doInsert();
			}
		});
		btnInsert.setBounds(390, 225, 100, 25);
		getContentPane().add(btnInsert);

		if (isEdit == true) {
			txtProductCode.setText(salesDetail.getProduct().getProductCode());
			txtProductName.setText(salesDetail.getProduct().getProductName());
			txtUom.setText(salesDetail.getUom().getUom());
			txtOrderQty.setText(Integer.toString(salesDetail.getQuantity()));
			txtNettPrice.setText(Double.toString(salesDetail.getNettPrice()));
		}
		if (isView == true) {
			txtProductCode.setEnabled(false);
			txtProductName.setEnabled(false);
			txtUom.setEnabled(false);
			txtOrderQty.setEnabled(false);
			txtNettPrice.setEnabled(false);
		}
	}

	protected boolean doValidate() {
		boolean isValid = true;
		lblErrorProductCode.setText("");
		lblErrorOrderQty.setText("");
		lblErrorNettPrice.setText("");

		if (txtProductCode.getText() == null || txtProductCode.getText().length() == 0) {
			lblErrorProductCode.setText("Textarea Kode Produk harus diisi.");
			isValid = false;
		}

		if (txtOrderQty.getText() == null || txtOrderQty.getText().length() == 0) {
			lblErrorOrderQty.setText("Textbox Kuantitas harus diisi.");
			isValid = false;
		}

		if (txtNettPrice.getText() == null || txtNettPrice.getText().length() == 0) {
			lblErrorNettPrice.setText("Textbox Harga Netto harus diisi.");
			isValid = false;
		}

		return isValid;
	}

	protected void doInsert() {
		salesDetail.getProduct().setProductCode(txtProductCode.getText());
		salesDetail.getProduct().setProductName(txtProductName.getText());
		salesDetail.getUom().setUom(txtUom.getText());
		salesDetail.setQuantity(Integer.parseInt(txtOrderQty.getText()));
		salesDetail.setNettPrice(Double.parseDouble(txtNettPrice.getText()));
		salesDetail.setTotalPrice(salesDetail.getQuantity() * salesDetail.getNettPrice());
		salesDetail.setTotalVolume(salesDetail.getQuantity() * salesDetail.getProduct().getLength() * salesDetail.getProduct().getWidth()
				* salesDetail.getProduct().getThickness());

		try {
			if (isEdit == false) {
				if (salesCreate != null) {
					salesCreate.listOfSalesDetail.add(salesDetail);
				} else if (salesEdit != null) {
					salesEdit.listOfSalesDetail.add(salesDetail);
				}

				DialogBox.showInsert();
			} else {
				if (salesCreate != null) {
					salesCreate.listOfSalesDetail.set(index, salesDetail);
				} else if (salesEdit != null) {
					salesEdit.listOfSalesDetail.set(index, salesDetail);
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
		if (salesCreate != null) {
			salesCreate.refreshTableSalesDetail();
		} else if (salesEdit != null) {
			salesEdit.refreshTableSalesDetail();
		}

		dispose();
	}
}
