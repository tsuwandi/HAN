package module.productsupportinggood.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.dailyclosing.model.Inventory;
import module.prodpk.model.ProdPK;
import module.prodpk.model.ProdPKMaterial;
import module.prodpk.model.ProdPKResultProduct;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.model.ProductionWaste;
import module.productsupportinggood.model.ProductSupp;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class ProductSupportingGoodCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductSupportingGoodCreatePanel.class);

	//section_header_start
	JLabel lblProductCode;
	JLabel lblProductName;
	JLabel lblProductCategory;
	JLabel lblProductUom;
	JLabel lblMaintainStock;
	JLabel lblImagePath;
	JTextField txtProductCode;
	JTextField txtProductName;
	ComboBox<ProductCategory> cbProductCategory;
	ComboBox<Uom> cbProductUom;
	ButtonGroup maintainStock;
	JRadioButton maintainYesField;
	JRadioButton maintainNoField;
	JLabel lblErrorProductCode;
	JLabel lblErrorProductName;
	JLabel lblErrorProductCategory;
	JLabel lblErrorProductUom;
	//section_header_end
	
	//section_description_start
	JLabel lblBarcode;
	JLabel lblDescription;
	JTextField txtBarcode;
	JTextArea txtDescription;
	//section_description_end
	
	JButton btnInsert;
	JButton btnDelete;
	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	
	ProductSupp productSupp;
	DocumentFilter filter = new UppercaseDocumentFilter();

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	JLabel lblHeaderDescription;
	JLabel lblHeaderAtributeProduct;
	
	public ProductSupportingGoodCreatePanel() {
		productSupp = new ProductSupp();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > General > Produk Barang Pendukung");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		//timotius_section_1_start
		lblProductCode = new JLabel("<html>Kode Produk <font color=\"red\">*</font></html>");
		lblProductCode.setBounds(50, 80, 150, 25);
		panel.add(lblProductCode);

		txtProductCode = new JTextField();
		txtProductCode.setBounds(220, 80, 150, 25);
		txtProductCode.setDocument(new JTextFieldLimit(20));
		((AbstractDocument) txtProductCode.getDocument()).setDocumentFilter(filter);
		txtProductCode.setEnabled(false);
		panel.add(txtProductCode);
		
		lblErrorProductCode = new JLabel();
		lblErrorProductCode.setForeground(Color.RED);
		lblErrorProductCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorProductCode);
		
		lblProductName = new JLabel("<html>Nama Produk<font color=\"red\">*</font></html>");
		lblProductName.setBounds(50, 110, 150, 25);
		panel.add(lblProductName);

		txtProductName = new JTextField();
		txtProductName.setBounds(220, 110, 150, 25);
		txtProductName.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtProductName.getDocument()).setDocumentFilter(filter);
		txtProductName.setEnabled(true);
		panel.add(txtProductName);
		
		lblErrorProductName = new JLabel();
		lblErrorProductName.setForeground(Color.RED);
		lblErrorProductName.setBounds(425, 110, 225, 25);
		panel.add(lblErrorProductName);
		
		lblProductCategory = new JLabel("<html>Kategori Produk <font color=\"red\">*</font></html>");
		lblProductCategory.setBounds(50, 140, 150, 25);
		panel.add(lblProductCategory);
		
		cbProductCategory = new ComboBox<ProductCategory>();
		cbProductCategory.setList(getAllProductCategory());
		cbProductCategory.setBounds(220, 140, 150, 25);
		cbProductCategory.setEnabled(true);
		panel.add(cbProductCategory);

		lblErrorProductCategory = new JLabel();
		lblErrorProductCategory.setForeground(Color.RED);
		lblErrorProductCategory.setBounds(425, 140, 225, 25);
		panel.add(lblErrorProductCategory);
		
		lblProductUom= new JLabel("<html>Satuan Produk <font color=\"red\">*</font></html>");
		lblProductUom.setBounds(50, 170, 150, 25);
		panel.add(lblProductUom);
		
		cbProductUom = new ComboBox<Uom>();
		cbProductUom.setList(getAllProductUom());
		cbProductUom.setBounds(220, 170, 150, 25);
		cbProductUom.setEnabled(true);
		panel.add(cbProductUom);

		lblErrorProductUom = new JLabel();
		lblErrorProductUom.setForeground(Color.RED);
		lblErrorProductUom.setBounds(425, 170, 225, 25);
		panel.add(lblErrorProductUom);
		
		lblMaintainStock= new JLabel("<html>Maintain Produk <font color=\"red\">*</font></html>");
		lblMaintainStock.setBounds(50, 200, 150, 25);
		panel.add(lblMaintainStock);
		
		maintainStock = new ButtonGroup();
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setSelected(true);
		maintainYesField.setBounds(220, 200, 50, 25);

		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setSelected(false);
		maintainNoField.setBounds(290, 200, 50, 25);

		maintainStock.add(maintainYesField);
		maintainStock.add(maintainNoField);
		
		panel.add(maintainYesField);
		panel.add(maintainNoField);
		
		lblImagePath= new JLabel("<html>Gambar</html>");
		lblImagePath.setBounds(50, 230, 150, 25);
		panel.add(lblImagePath);
		//timotius_section_1_end
		
		//timotius_section_description_start
		lblHeaderDescription = new JLabel("Deskripsi");
		lblHeaderDescription.setBounds(50, 265, 200, 25);
		lblHeaderDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderDescription);
		
		lblBarcode = new JLabel("<html>Barcode</html>");
		lblBarcode.setBounds(50, 295, 150, 25);
		panel.add(lblBarcode);

		txtBarcode = new JTextField();
		txtBarcode.setBounds(220, 295, 150, 25);
		txtBarcode.setDocument(new JTextFieldLimit(50));
		((AbstractDocument) txtBarcode.getDocument()).setDocumentFilter(filter);
		txtBarcode.setEnabled(true);
		panel.add(txtBarcode);
		
		lblDescription = new JLabel("<html>Deskripsi</html>");
		lblDescription.setBounds(50, 325, 150, 25);
		panel.add(lblDescription);

		txtDescription = new JTextArea();
		txtDescription.setBounds(220, 325, 200, 75);
		txtDescription.setDocument(new JTextFieldLimit(200));
		((AbstractDocument) txtDescription.getDocument()).setDocumentFilter(filter);
		txtDescription.setEnabled(true);
		panel.add(txtDescription);
		//timotius_section_description_end
		
		//timotius_section_attribute_product_start
		lblHeaderAtributeProduct = new JLabel("Atribut Produk");
		lblHeaderAtributeProduct.setBounds(50, 350, 200, 25);
		lblHeaderAtributeProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblHeaderAtributeProduct);
		//timotius_section_attribute_product_end
		
		btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				if (doValidate() == false) {
//					return;
//				}
//				int response = DialogBox.showInsertChoice();
//				if (response == JOptionPane.YES_OPTION) {
//					doSave();
//				}
			}
		});
		btnSave.setBounds(925, 570, 100, 25);
		panel.add(btnSave);

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int response = DialogBox.showCloseChoice();
				 if (response == JOptionPane.YES_OPTION) {
					 MainPanel.changePanel("module.prodpk.ui.ProdPKListPanel");
				 }
			}
		});
		btnCancel.setBounds(50, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
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
	
	public List<ProductCategory> getAllProductCategory() {
		List<ProductCategory> listOfProductCategory = null;
		try {
			listOfProductCategory = new ArrayList<ProductCategory>();
			//listOfProductCategory = ServiceFactory.getProductSuppGoodBL().getAllProductCategory();
			listOfProductCategory.add(0, new ProductCategory("-- Pilih Kategori Produk --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		
		return listOfProductCategory;
	}
	
	public List<Uom> getAllProductUom() {
		List<Uom> listOfProductUom = null;
		try {
			listOfProductUom = new ArrayList<Uom>();
			//listOfProductUom = ServiceFactory.getProductSuppGoodBL().getAllProductUom();
			listOfProductUom.add(0, new Uom("-- Pilih Satuan Produk --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		
		return listOfProductUom;
	}
	
	
	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
