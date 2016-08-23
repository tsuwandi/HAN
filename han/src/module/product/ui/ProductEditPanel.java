package module.product.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.panel.MainPanel;
import module.pembelian.model.WoodType;
import module.product.ProductCategoryType;
import module.product.model.Grade;
import module.product.model.Product;
import module.product.model.ProductCategory;
import module.product.model.Uom;
import module.sn.production.quality.model.ProductionQuality;
import module.sn.production.type.model.ProductionType;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class ProductEditPanel extends JPanel implements Bridging {

	/**
	 * 
	 */
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

	public ProductEditPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(1166, 620));

		todayDate = new Date();
		todayDate.getTime();

		breadcrumb = new JLabel("ERP > Pembelian > Produk");
		breadcrumb.setFont(new Font(null, Font.BOLD, 12));
		breadcrumb.setBounds(50, 10, 320, 25);

		backBtn = new JButton("Kembali");
		backBtn.setBounds(50, 550, 75, 25);
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

		nameField = new JTextField();
		nameField.setDocument(new JTextFieldLimit(50));
		nameField.setBounds(220, 110, 150, 25);

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

		add(lblProductionType);
		add(lblProductionQuality);
		add(cbProductionType);
		add(cbProductionQuality);
		add(productionQualityLblError);
		add(productionTypeLblError);

		////////////////////////////////////////////////////////////////////////////////////

		try {
			categories = ServiceFactory.getProductBL().getAllProductCategory();
			categories.add(0, new ProductCategory("-- Pilih Kategori Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
		catField = new ComboBox<ProductCategory>();
		catField.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				switch (catField.getDataIndex().getId()) {
				case ProductCategoryType.BALKEN_BASAH:
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
				case ProductCategoryType.BALKEN_KERING:
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
				case ProductCategoryType.HASIL_PRODUKSI:
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
				case ProductCategoryType.BARANG_PENDUKUNG:
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
		catField.setList(categories);
		catField.setBounds(220, 140, 150, 25);

		try {
			units = ServiceFactory.getProductBL().getAllUom();
			units.add(0, new Uom("-- Pilih Satuan Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
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

		try {
			grades = ServiceFactory.getProductBL().getAllGrade();
			grades.add(0, new Grade("-- Pilih Grade --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
		}
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
		saveBtn.setBounds(925, 550, 75, 25);
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

		add(breadcrumb);
		add(backBtn);
		add(titleLbl);
		add(idLbl);
		add(nameLbl);
		add(catLbl);
		add(unitLbl);
		add(maintainLbl);
		add(typeLbl);
		add(gradeLbl);
		add(thickLbl);
		add(longLbl);
		add(wideLbl);
		add(minQtyLbl);
		add(idField);
		add(nameField);
		add(catField);
		add(uomField);
		add(maintainYesField);
		add(maintainNoField);
		add(typeField);
		add(gradeField);
		add(thickField);
		add(longField);
		add(wideField);
		add(minQtyField);
		add(saveBtn);

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

		add(idLblError);
		add(nameLblError);
		add(catLblError);
		add(unitLblError);
		add(maintainLblError);
		add(typeLblError);
		add(gradeLblError);
		add(thickLblError);
		add(longLblError);
		add(wideLblError);
		add(minQtyLblError);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				nameField.requestFocusInWindow();
			}
		});
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

			if (maintainYesField.isSelected())
				product.setIsMaintain(0);
			else
				product.setIsMaintain(1);

			if (product.getProductCat() == ProductCategoryType.BALKEN_BASAH
					|| product.getProductCat() == ProductCategoryType.BALKEN_KERING) {
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

			product.setProductionTypeId(cbProductionType.getDataIndex().getId());
			product.setProductionQualityId(cbProductionQuality.getDataIndex().getId());

			product.setMinQty(Integer.parseInt(minQtyField.getText()));

			ServiceFactory.getProductBL().update(product);
			DialogBox.showEdit();
			MainPanel.changePanel("module.product.ui.ProductViewPanel", product);
		} catch (Exception e) {
			DialogBox.showErrorException();
			e.printStackTrace();
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

		if (catField.getDataIndex().getId() == ProductCategoryType.BALKEN_BASAH
				|| catField.getDataIndex().getId() == ProductCategoryType.BALKEN_KERING) {

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
		} else if (catField.getDataIndex().getId() == ProductCategoryType.HASIL_PRODUKSI) {
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

			if (product != null) {
				id = product.getProductId();
				idField.setText(product.getProductCode());
				nameField.setText(product.getProductName());
				catField.setSelectedIndex(product.getProductCat());
				uomField.setSelectedIndex(product.getProductUom());
				typeField.setSelectedIndex(product.getWoodType());
				gradeField.setSelectedIndex(product.getGrade());

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

				if (product.getProductCat() == ProductCategoryType.HASIL_PRODUKSI) {
					cbProductionQuality.setSelectedIndex(product.getProductionQualityId());
					cbProductionType.setSelectedIndex(product.getProductionTypeId());
				}

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.product = (Product) objects[0];

		loadData(product.getProductCode());
	}
}
