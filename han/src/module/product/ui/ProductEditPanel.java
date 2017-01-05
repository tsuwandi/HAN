package module.product.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.product.model.Grade;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.product.model.ProductPP;
import module.product.model.Uom;
import module.purchaseprodresult.model.PPRNote;
import module.sn.production.quality.model.ProductionQuality;
import module.sn.production.type.model.ProductionType;
import module.sn.woodtype.model.WoodType;
import module.util.Bridging;
import module.util.JTextFieldLimit;

import org.apache.log4j.Logger;

import main.component.AppConstants;
import controller.ServiceFactory;

public class ProductEditPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductEditPanel.class);

	JLabel breadcrumb;
	JButton backBtn;
	JLabel titleLbl;

	JLabel idLbl;
	JLabel nameLbl;
	JLabel catLbl;
	JLabel unitLbl;
	JLabel maintainLbl;
	JLabel attLbl;
	JLabel typeLbl;
	JLabel gradeLbl;
	JLabel thickLbl;
	JLabel longLbl;
	JLabel wideLbl;
	JLabel minQtyLbl;

	JTextField idField;
	JTextField nameField;
	ComboBox<ProductCategory> catField;
	ComboBox<Uom> uomField;
	ButtonGroup maintain;
	JRadioButton maintainYesField;
	JRadioButton maintainNoField;
	ComboBox<WoodType> typeField;
	ComboBox<Grade> gradeField;
	NumberField thickField;
	NumberField longField;
	NumberField wideField;
	NumberField minQtyField;

	JButton saveBtn;
	JScrollPane scrollPane;

	Product product;
	List<ProductCategory> categories = null;
	List<WoodType> woodTypes = null;
	List<Grade> grades = null;
	List<Uom> units = null;

	static int id;
	Date todayDate;
	JLabel lblProductionType;
	JLabel lblProductionQuality;
	ComboBox<ProductionType> cbProductionType;
	ComboBox<ProductionQuality> cbProductionQuality;
	List<ProductionType> listOfProductionType;
	List<ProductionQuality> listOfProductionQuality;
	JLabel productionQualityLblError;
	JLabel productionTypeLblError;
	
	List<ProductPP> listOfProductPP = null;
	JScrollPane scrollPaneProductPP;
	JTable tblProductPP;
	ProductPPTableModel productPPTableModel = null;
	JButton btnInsertProductPP;
	JButton btnDeleteProductPP;
	JPanel panel;
	List<ProductPP> listOfDeletedProductPP = new ArrayList<ProductPP>();
	JLabel lblPurchaserPrice;
	ProductEditPanel productEditPanel;

	public ProductEditPanel() {
		productEditPanel = this;
		setLayout(null);
		//setPreferredSize(new Dimension(1166, 620));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 850));
		panel.setLayout(null);
		
		DocumentFilter filter = new UppercaseDocumentFilter();

		todayDate = new Date();
		todayDate.getTime();

		breadcrumb = new JLabel("ERP > Pembelian > Produk");
		breadcrumb.setFont(new Font(null, Font.BOLD, 12));
		breadcrumb.setBounds(50, 10, 320, 25);

		backBtn = new JButton("Kembali");
		backBtn.setBounds(50, 780, 100, 25);
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int response = DialogBox.showCloseChoice();
				if (response == JOptionPane.YES_OPTION) {
					MainPanel.changePanel("module.product.ui.ProductViewPanel", product);
				}
			}
		});
		backBtn.setFocusable(false);
		backBtn.setHorizontalAlignment(SwingConstants.CENTER);

		titleLbl = new JLabel("Ubah");
		titleLbl.setBounds(50, 40, 300, 25);
		titleLbl.setFont(new Font(null, Font.BOLD, 12));

		idLbl = new JLabel("<html>Kode Produk <font color=\"red\">*</font></html>");
		idLbl.setBounds(50, 80, 100, 25);

		nameLbl = new JLabel("<html>Nama Produk <font color=\"red\">*</font></html>");
		nameLbl.setBounds(50, 110, 100, 25);

		catLbl = new JLabel("<html>Kategori Produk <font color=\"red\">*</font></html>");
		catLbl.setBounds(50, 140, 100, 25);

		unitLbl = new JLabel("<html>Satuan Produk <font color=\"red\">*</font></html>");
		unitLbl.setBounds(50, 170, 100, 25);

		maintainLbl = new JLabel("<html>Maintain Stock <font color=\"red\">*</font></html>");
		maintainLbl.setBounds(50, 200, 100, 25);

		attLbl = new JLabel("<html>Atribut Produk</html>");
		attLbl.setBounds(50, 245, 100, 25);
		attLbl.setFont(new Font(null, Font.BOLD, 12));
		
		typeLbl = new JLabel("Jenis Kayu");
		typeLbl.setBounds(50, 290, 100, 25);

		gradeLbl = new JLabel("Grade");
		gradeLbl.setBounds(50, 380, 100, 25);

		longLbl = new JLabel("Panjang");
		longLbl.setBounds(50, 410, 100, 25);

		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(50, 440, 100, 25);

		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(50, 470, 100, 25);

		minQtyLbl = new JLabel("<html>Minimum Qty <font color=\"red\">*</font></html>");
		minQtyLbl.setBounds(50, 500, 100, 25);

		idField = new JTextField();
		idField.setEnabled(false);
		idField.setBounds(220, 80, 150, 25);
		((AbstractDocument) idField.getDocument()).setDocumentFilter(filter);

		nameField = new JTextField();
		nameField.setDocument(new JTextFieldLimit(50));
		nameField.setBounds(220, 110, 150, 25);
		((AbstractDocument) nameField.getDocument()).setDocumentFilter(filter);

		///////////////////// Kategori Hasil Produksi
		///////////////////// ///////////////////////////////

		lblProductionType = new JLabel("Tipe Hasil Produksi");
		lblProductionType.setBounds(50, 320, 100, 25);
		lblProductionQuality = new JLabel("Kualitas Produksi");
		lblProductionQuality.setBounds(50, 350, 100, 25);

		try {
			listOfProductionType = new ArrayList<ProductionType>();
			listOfProductionType = ServiceFactory.getProductBL().getAllProductionType();
			listOfProductionType.add(0, new ProductionType("-- Pilih Tipe Hasil Produksi --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbProductionType = new ComboBox<ProductionType>();
		cbProductionType.setList(listOfProductionType);
		cbProductionType.setBounds(220, 320, 150, 25);

		try {
			listOfProductionQuality = new ArrayList<ProductionQuality>();
			listOfProductionQuality = ServiceFactory.getProductBL().getAllProductionQuality();
			listOfProductionQuality.add(0, new ProductionQuality("-- Pilih Kualitas Produksi --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
		}

		cbProductionQuality = new ComboBox<ProductionQuality>();
		cbProductionQuality.setList(listOfProductionQuality);
		cbProductionQuality.setBounds(220, 350, 150, 25);

		cbProductionQuality.setEnabled(false);
		cbProductionType.setEnabled(false);

		productionQualityLblError = new JLabel("");
		productionQualityLblError.setForeground(Color.RED);
		productionQualityLblError.setBounds(425, 320, 225, 25);

		productionTypeLblError = new JLabel("");
		productionTypeLblError.setForeground(Color.RED);
		productionTypeLblError.setBounds(425, 350, 225, 25);

		panel.add(lblProductionType);
		panel.add(lblProductionQuality);
		panel.add(cbProductionType);
		panel.add(cbProductionQuality);
		panel.add(productionQualityLblError);
		panel.add(productionTypeLblError);

		////////////////////////////////////////////////////////////////////////////////////
		grades = new ArrayList<Grade>();
		try {
			categories = ServiceFactory.getProductBL().getAllProductCategory();
			categories.add(0, new ProductCategory("-- Pilih Kategori Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		catField = new ComboBox<ProductCategory>();
		catField.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				switch (catField.getDataIndex().getId()) {
				case AppConstants.BALKEN_BASAH_ID:
					typeLbl.setText("<html>Jenis Kayu <font color=\"red\">*</font></html>");
					gradeLbl.setText("<html>Grade <font color=\"red\">*</font></html>");
					thickLbl.setText("<html>Tebal <font color=\"red\">*</font></html>");
					longLbl.setText("<html>Panjang <font color=\"red\">*</font></html>");
					wideLbl.setText("<html>Lebar <font color=\"red\">*</font></html>");
					lblProductionType.setText("Tipe Hasil Produksi");
					lblProductionQuality.setText("Kualitas Produksi");
					cbProductionQuality.setSelectedIndex(0);
					cbProductionType.setSelectedIndex(0);
					cbProductionQuality.setEnabled(false);
					cbProductionType.setEnabled(false);
					break;
				case AppConstants.BALKEN_KERING_ID:
					typeLbl.setText("<html>Jenis Kayu <font color=\"red\">*</font></html>");
					gradeLbl.setText("<html>Grade <font color=\"red\">*</font></html>");
					thickLbl.setText("<html>Tebal <font color=\"red\">*</font></html>");
					longLbl.setText("<html>Panjang <font color=\"red\">*</font></html>");
					wideLbl.setText("<html>Lebar <font color=\"red\">*</font></html>");
					lblProductionType.setText("Tipe Hasil Produksi");
					lblProductionQuality.setText("Kualitas Produksi");
					cbProductionQuality.setSelectedIndex(0);
					cbProductionType.setSelectedIndex(0);
					cbProductionQuality.setEnabled(false);
					cbProductionType.setEnabled(false);
					break;
				case AppConstants.HASIL_PRODUKSI_ID:
					typeLbl.setText("Jenis Kayu");
					gradeLbl.setText("Grade");
					thickLbl.setText("Tebal");
					longLbl.setText("Panjang");
					wideLbl.setText("Lebar");
					lblProductionType.setText("<html>Tipe Hasil Produksi<font color=\"red\">*</font></html>");
					lblProductionQuality.setText("<html>Kualitas Produksi<font color=\"red\">*</font></html>");
					cbProductionQuality.setEnabled(true);
					cbProductionType.setEnabled(true);
					break;
				case AppConstants.BARANG_PENDUKUNG_ID:
					typeLbl.setText("Jenis Kayu");
					gradeLbl.setText("Grade");
					thickLbl.setText("Tebal");
					longLbl.setText("Panjang");
					wideLbl.setText("Lebar");
					lblProductionType.setText("Tipe Hasil Produksi");
					lblProductionQuality.setText("Kualitas Produksi");
					cbProductionQuality.setSelectedIndex(0);
					cbProductionType.setSelectedIndex(0);
					cbProductionQuality.setEnabled(false);
					cbProductionType.setEnabled(false);
					break;
				default:
					break;
				}
			}
		});
		
		catField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (catField.getSelectedIndex() != 0) {
					int productCategoryId = catField.getDataIndex().getId();
					getAllGradeByProductCategoryId(productCategoryId);

					gradeField.removeAllItems();
					gradeField.setList(grades);
					gradeField.updateUI();
				} else {
					gradeField.removeAllItems();
					getAllGradeByProductCategoryId(0);
					gradeLblError.setText("");
				}
			}
		});
		catField.setList(categories);
		
		catField.setList(categories);
		catField.setBounds(220, 140, 150, 25);

		try {
			units = ServiceFactory.getProductBL().getAllUom();
			units.add(0, new Uom("-- Pilih Satuan Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		uomField = new ComboBox<Uom>();
		uomField.setList(units);
		uomField.setBounds(220, 170, 150, 25);

		maintain = new ButtonGroup();
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setSelected(true);
		maintainYesField.setBounds(220, 200, 50, 25);

		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setSelected(false);
		maintainNoField.setBounds(280, 200, 50, 25);

		maintain.add(maintainYesField);
		maintain.add(maintainNoField);

		try {
			woodTypes = ServiceFactory.getProductBL().getAllWoodType();
			woodTypes.add(0, new WoodType("-- Pilih Jenis Kayu --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
		typeField = new ComboBox<WoodType>();
		typeField.setList(woodTypes);
		typeField.setBounds(220, 290, 150, 25);

		grades.add(0, new Grade("-- Pilih Grade --"));
		
		gradeField = new ComboBox<Grade>();
		gradeField.setList(grades);
		gradeField.setBounds(220, 380, 150, 25);

		longField = new NumberField(10);
		longField.setBounds(220, 410, 150, 25);

		thickField = new NumberField(10);
		thickField.setBounds(220, 440, 150, 25);

		wideField = new NumberField(10);
		wideField.setBounds(220, 470, 150, 25);

		minQtyField = new NumberField(5);
		minQtyField.setText("0");
		minQtyField.setBounds(220, 500, 150, 25);

		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(925, 780, 75, 25);
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (doValidate() == false) {
					return;
				} else {
					int response = DialogBox.showInsertChoice();
					if (response == JOptionPane.YES_OPTION) {
						doEdit();
					}
				}

			}
		});

		panel.add(breadcrumb);
		panel.add(backBtn);
		panel.add(titleLbl);
		panel.add(idLbl);
		panel.add(nameLbl);
		panel.add(catLbl);
		panel.add(unitLbl);
		panel.add(maintainLbl);
		panel.add(typeLbl);
		panel.add(gradeLbl);
		panel.add(thickLbl);
		panel.add(longLbl);
		panel.add(wideLbl);
		panel.add(minQtyLbl);
		panel.add(idField);
		panel.add(nameField);
		panel.add(catField);
		panel.add(uomField);
		panel.add(maintainYesField);
		panel.add(maintainNoField);
		panel.add(attLbl);
		panel.add(typeField);
		panel.add(gradeField);
		panel.add(thickField);
		panel.add(longField);
		panel.add(wideField);
		panel.add(minQtyField);
		panel.add(saveBtn);

		idLblError = new JLabel("");
		idLblError.setForeground(Color.RED);
		idLblError.setBounds(425, 80, 225, 25);

		nameLblError = new JLabel("");
		nameLblError.setForeground(Color.RED);
		nameLblError.setBounds(425, 110, 225, 25);

		catLblError = new JLabel("");
		catLblError.setForeground(Color.RED);
		catLblError.setBounds(425, 140, 225, 25);

		unitLblError = new JLabel("");
		unitLblError.setForeground(Color.RED);
		unitLblError.setBounds(425, 170, 225, 25);

		maintainLblError = new JLabel("");
		maintainLblError.setForeground(Color.RED);
		maintainLblError.setBounds(425, 200, 225, 25);

		typeLblError = new JLabel("");
		typeLblError.setForeground(Color.RED);
		typeLblError.setBounds(425, 290, 225, 25);

		gradeLblError = new JLabel("");
		gradeLblError.setForeground(Color.RED);
		gradeLblError.setBounds(425, 380, 225, 25);

		thickLblError = new JLabel("");
		thickLblError.setForeground(Color.RED);
		thickLblError.setBounds(425, 440, 225, 25);

		longLblError = new JLabel("");
		longLblError.setForeground(Color.RED);
		longLblError.setBounds(425, 410, 225, 25);

		wideLblError = new JLabel("");
		wideLblError.setForeground(Color.RED);
		wideLblError.setBounds(425, 470, 225, 25);

		minQtyLblError = new JLabel("");
		minQtyLblError.setForeground(Color.RED);
		minQtyLblError.setBounds(425, 500, 225, 25);

		panel.add(idLblError);
		panel.add(nameLblError);
		panel.add(catLblError);
		panel.add(unitLblError);
		panel.add(maintainLblError);
		panel.add(typeLblError);
		panel.add(gradeLblError);
		panel.add(thickLblError);
		panel.add(longLblError);
		panel.add(wideLblError);
		panel.add(minQtyLblError);
		
		lblPurchaserPrice = new JLabel("Harga Beli");
		lblPurchaserPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPurchaserPrice.setBounds(50, 550, 150, 25);
		panel.add(lblPurchaserPrice);
		
		btnInsertProductPP = new JButton("Tambah");
		btnInsertProductPP.setBounds(825, 550, 100, 25);
		btnInsertProductPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAddProductPPDialog(productEditPanel);
			}
		});
		panel.add(btnInsertProductPP);
		
		btnDeleteProductPP = new JButton("Hapus");
		btnDeleteProductPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDeleteProductPP();
			}
		});
		btnDeleteProductPP.setBounds(925, 550, 100, 25);
		panel.add(btnDeleteProductPP);

		scrollPaneProductPP = new JScrollPane();
		scrollPaneProductPP.setBounds(50, 590, 975, 150);
		panel.add(scrollPaneProductPP);

		listOfProductPP = new ArrayList<ProductPP>();
		productPPTableModel = new ProductPPTableModel(listOfProductPP);
		tblProductPP = new JTable(productPPTableModel);
		tblProductPP.setBorder(new EmptyBorder(5, 5, 5, 5));
		tblProductPP.setFocusable(false);
		tblProductPP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblProductPP.getValueAt(tblProductPP.getSelectedRow(), 0).equals(true))
					listOfProductPP.get(tblProductPP.getSelectedRow()).setFlag(false);
				else
					listOfProductPP.get(tblProductPP.getSelectedRow()).setFlag(true);

				tblProductPP.updateUI();

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4) {
						showEditProductPPDialog(listOfProductPP.get(row), productEditPanel, row);
					}
				}
			}
		});
		scrollPaneProductPP.setViewportView(tblProductPP);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPane);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				nameField.requestFocusInWindow();
			}
		});
	}
	
	public void getAllGradeByProductCategoryId(int productCategoryId) {
		try {
			grades = ServiceFactory.getProductBL().getAllGradeByCategoryProductId(productCategoryId);
			grades.add(0, new Grade("-- Pilih Grade --"));
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}


	public void doEdit() {
		try {
			Product product = new Product();

			product.setProductId(id);
			product.setProductCode(idField.getText());
			product.setProductName(nameField.getText());
			product.setProductCat(catField.getSelectedIndex());
			product.setProductUom(uomField.getDataIndex().getId());
			product.setWoodType(typeField.getDataIndex().getId());
			product.setGrade(gradeField.getDataIndex().getId());
			product.setProductionTypeId(cbProductionType.getDataIndex().getId());
			product.setProductionQualityId(cbProductionQuality.getDataIndex().getId());

			if (maintainYesField.isSelected())
				product.setIsMaintain(0);
			else
				product.setIsMaintain(1);

			if (product.getProductCat() == AppConstants.BALKEN_BASAH_ID
					|| product.getProductCat() == AppConstants.BALKEN_KERING_ID) {
				product.setThickness(Double.parseDouble(thickField.getText()));
				product.setLength(Double.parseDouble(longField.getText()));
				product.setWidth(Double.parseDouble(wideField.getText()));
			} else {
				if (thickField.getText().length() > 0)
					product.setThickness(Double.parseDouble(thickField.getText()));
				if (longField.getText().length() > 0)
					product.setLength(Double.parseDouble(longField.getText()));
				if (wideField.getText().length() > 0)
					product.setWidth(Double.parseDouble(wideField.getText()));
			}
			
			if(product.getProductCat() == AppConstants.BALKEN_BASAH_ID) {
				product.setCondition(AppConstants.BALKEN_BASAH_ID);
			} else if(product.getProductCat() == AppConstants.BALKEN_KERING_ID) {
				product.setCondition(AppConstants.BALKEN_KERING_ID);
			}

		
			product.setMinQty(Integer.parseInt(minQtyField.getText()));
			
			if(!AppConstants.HASIL_PRODUKSI.equalsIgnoreCase(catField.getDataIndex().getProductCategory())) {
			Product checkProduct = ServiceFactory.getProductBL().isProductExists(Boolean.TRUE, product);
				if (checkProduct.getIsExists() > 0) {
					JOptionPane.showMessageDialog(null,
							"Produk sudah pernah diinput dengan kode " + checkProduct.getProductCode(), "Warning",
							JOptionPane.YES_NO_OPTION);
				} else {
					ServiceFactory.getProductBL().update(product, listOfProductPP, listOfDeletedProductPP);
					DialogBox.showEdit();
					MainPanel.changePanel("module.product.ui.ProductViewPanel", product);
				}
			} else {
				ServiceFactory.getProductBL().update(product, listOfProductPP, listOfDeletedProductPP);
				DialogBox.showEdit();
				MainPanel.changePanel("module.product.ui.ProductViewPanel", product);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}

	private JLabel idLblError;
	private JLabel nameLblError;
	private JLabel catLblError;
	private JLabel unitLblError;
	private JLabel maintainLblError;
	private JLabel typeLblError;
	private JLabel gradeLblError;
	private JLabel thickLblError;
	private JLabel longLblError;
	private JLabel wideLblError;
	private JLabel minQtyLblError;

	public boolean doValidate() {
		boolean isValid = true;

		idLblError.setText("");
		nameLblError.setText("");
		catLblError.setText("");
		unitLblError.setText("");
		maintainLblError.setText("");
		typeLblError.setText("");
		gradeLblError.setText("");
		thickLblError.setText("");
		longLblError.setText("");
		wideLblError.setText("");
		minQtyLblError.setText("");

		if (idField.getText() == null || idField.getText().length() == 0) {
			idLblError.setText("Kode Produk harus diisi");
			isValid = false;
		}
		if (nameField.getText() == null || nameField.getText().equals("")) {
			nameLblError.setText("Nama Produk harus diisi");
			isValid = true;
		}

		if (catField.getSelectedItem().toString() == "Pilih" || catField.getSelectedIndex() == 0) {
			catLblError.setText("Kategori Produk harus dipilih");
			isValid = true;
		}

		if (uomField.getSelectedItem().toString() == "Pilih" || uomField.getSelectedIndex() == 0) {
			unitLblError.setText("Satuan Produk harus dipilih");
			isValid = false;
		}

		if (catField.getDataIndex().getId() == AppConstants.BALKEN_BASAH_ID
				|| catField.getDataIndex().getId() == AppConstants.BALKEN_KERING_ID) {

			if (typeField.getSelectedIndex() == 0) {
				typeLblError.setText("Jenis Kayu harus dipilih");
				isValid = false;
			}

			if (gradeField.getSelectedIndex() == 0) {
				gradeLblError.setText("Grade Produk harus dipilih");
				isValid = false;
			}

			if (longField.getText() == null || longField.getText().equals("")) {
				longLblError.setText("Panjang Produk harus diisi");
				isValid = false;
			}
			if (wideField.getText() == null || wideField.getText().equals("")) {
				wideLblError.setText("Lebar Produk harus diisi");
				isValid = false;
			}

			if (thickField.getText() == null || thickField.getText().equals("")) {
				thickLblError.setText("Tebal Produk harus diisi");
				isValid = false;
			}
		} else if (catField.getDataIndex().getId() == AppConstants.HASIL_PRODUKSI_ID) {
			if (cbProductionQuality.getSelectedIndex() == 0) {
				productionQualityLblError.setText("Kualitas Produksi harus dipilih");
				isValid = false;
			}

			if (cbProductionType.getSelectedIndex() == 0) {
				productionTypeLblError.setText("Tipe Hasil Produksi harus dipilih");
				isValid = false;
			}
		}

		if (minQtyField.getText() == null || minQtyField.getText().equals("")) {
			minQtyLblError.setText("Minimal Qty Produk harus diisi");
			isValid = true;
		}

		return isValid;
	}

	protected void loadData(String productCode) {
		try {
			product = ServiceFactory.getProductBL().getProductByCode(productCode);
			listOfProductPP = ServiceFactory.getProductBL().getProductPPByProductCode(productCode);
			
			if (product != null) {
				id = product.getProductId();
				idField.setText(product.getProductCode());
				nameField.setText(product.getProductName());
				catField.setSelectedItem(product.getProductCatName());
				uomField.setSelectedItem(product.getUnitName());
				typeField.setSelectedItem(product.getWoodTypeName());
				if(!"".equals(product.getGradeName())) {
					gradeField.setSelectedItem(product.getGradeName());
				} else {
					gradeField.setSelectedIndex(0);
				}
				

				if (product.getThickness() != 0.00)
					thickField.setText(String.valueOf(product.getThickness()));

				if (product.getLength() != 0.00)
					longField.setText(String.valueOf(product.getLength()));

				if (product.getWidth() != 0.00)
					wideField.setText(String.valueOf(product.getWidth()));

				minQtyField.setText(String.valueOf(product.getMinQty()));
				if (product.getIsMaintain() == 0) {
					maintainYesField.setSelected(true);
				} else {
					maintainNoField.setSelected(true);
				}

				if (product.getProductCat() == AppConstants.HASIL_PRODUKSI_ID) {
					cbProductionQuality.setSelectedItem(product.getProductionQuality());
					cbProductionType.setSelectedItem(product.getProductionType());
				}
				
				refreshTableProductPP();

			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.product = (Product) objects[0];

		loadData(product.getProductCode());
	}
	
	/**
	 * Class as TableModel for ProductPP table
	 * 
	 * @author TSI
	 *
	 */
	class ProductPPTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		private List<ProductPP> listOfProductPP;

		public ProductPPTableModel(List<ProductPP> listOfProductPP) {
			this.listOfProductPP = listOfProductPP;
		}

		/**
		 * Method to get row count
		 * 
		 * @return int
		 */
		public int getRowCount() {
			return listOfProductPP.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 5;
		}

		/**
		 * Method to get selected value
		 * 
		 * @param rowIndex
		 *            rowIndex of selected table
		 * @param columnIndex
		 *            columnIndex of selected table
		 * @return ({@link PPRNote}) Object
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			ProductPP p = listOfProductPP.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.isFlag();
			case 1:
				return p.getPrice();
			case 2:
				return p.getEffectiveStartDate();
			case 3:
				return p.getEffectiveEndDate();
			case 4:
				return "<html><u>View</u></html>";
			default:
				return "";
			}
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			case 2:
				return Date.class;
			case 3:
				return Date.class;
			case 4:
				return String.class;
			default:
				return String.class;
			}
		}

		/**
		 * Method to getColumnName
		 * 
		 * @param column
		 *            columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";
			case 1:
				return "Price";
			case 2:
				return "Effective Start Date";
			case 3:
				return "Effective End Date";
			case 4:
				return "Tindakan";
			default:
				return "";
			}
		}
	}

	public void refreshTableProductPP() {
		try {
			tblProductPP.setModel(new ProductPPTableModel(listOfProductPP));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void showAddProductPPDialog(ProductEditPanel productEditPanel) {
		ProductPPDialog productPPDialog = new ProductPPDialog(false, new ProductPP(), productEditPanel, null);
		productPPDialog.setTitle("Harga Beli");
		productPPDialog.setLocationRelativeTo(null);
		productPPDialog.setVisible(true);
	}

	protected void showEditProductPPDialog(ProductPP productPP, ProductEditPanel productEditPanel, Integer index) {
		ProductPPDialog productPPDialog = new ProductPPDialog(true, productPP, productEditPanel, index);
		productPPDialog.setTitle("Harga Beli");
		productPPDialog.setLocationRelativeTo(null);
		productPPDialog.setVisible(true);
	}

	protected void doDeleteProductPP() {
		if (listOfProductPP.isEmpty())
			DialogBox.showDeleteEmptyChoice();
		else {
			int count = 0;

			List<ProductPP> temp = new ArrayList<ProductPP>();
			for (ProductPP s : listOfProductPP) {
				if (Boolean.TRUE.equals(s.isFlag())) {
					temp.add(s);
				} else {
					count += 1;
				}
			}

			if (count == listOfProductPP.size()) {
				DialogBox.showDeleteEmptyChoice();
				return;
			}

			if (Boolean.FALSE.equals(temp.isEmpty())) {
				for (ProductPP s : temp) {
					listOfDeletedProductPP.add(s);
					listOfProductPP.remove(s);
				}
				refreshTableProductPP();
				DialogBox.showDelete();
			}
		}
	}
}
