package module.product.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.panel.MainPanel;
import module.product.model.Condition;
import module.sn.grade.model.Grade;
import module.product.model.Product;
import module.sn.productcategory.model.ProductCategory;
import module.product.model.ProductPP;
import module.sn.uom.model.Uom;
import module.sn.production.quality.model.ProductionQuality;
import module.sn.production.type.model.ProductionType;
import module.sn.woodtype.model.WoodType;
import module.util.Bridging;

import org.apache.log4j.Logger;

import controller.ServiceFactory;

public class ProductViewPanel extends JPanel implements Bridging {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductViewPanel.class);

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
	JTextField thickField;
	JTextField longField;
	JTextField wideField;
	JTextField minQtyField;

	JButton printBtn;
	JButton deleteBtn;
	JButton editBtn;

	JScrollPane scrollPane;

	String filename;

	Product product;
	List<ProductCategory> categories = null;
	List<WoodType> woodTypes = null;
	List<Grade> grades = null;
	List<Uom> units = null;
	List<Condition> conditions = null;

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
	ProductViewPanel productViewPanel;

	@Override
	public void invokeObjects(Object... objects) {
		this.product = (Product) objects[0];

		loadData(product.getProductCode());
	}

	public ProductViewPanel() {
		productViewPanel = this;
		setLayout(null);
		//setPreferredSize(new Dimension(1080, 675));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 850));
		panel.setLayout(null);

		todayDate = new Date();
		todayDate.getTime();

		breadcrumb = new JLabel("ERP > Pembelian > Produk");
		breadcrumb.setFont(new Font(null, Font.BOLD, 12));
		breadcrumb.setBounds(50, 10, 320, 25);

		backBtn = new JButton("Kembali");
		backBtn.setBounds(50, 780, 75, 25);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.product.ui.ProductListPanel");
			}
		});
		backBtn.setFocusable(false);
		backBtn.setHorizontalAlignment(SwingConstants.CENTER);

		titleLbl = new JLabel("VIEW DETAIL");
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

		longLbl = new JLabel("Panjang ");
		longLbl.setBounds(50, 410, 100, 25);

		thickLbl = new JLabel("Tebal");
		thickLbl.setBounds(50, 470, 100, 25);

		wideLbl = new JLabel("Lebar");
		wideLbl.setBounds(50, 440, 100, 25);

		minQtyLbl = new JLabel("<html>Minimum Qty <font color=\"red\">*</font></html>");
		minQtyLbl.setBounds(50, 500, 100, 25);

		idField = new JTextField();
		idField.setEnabled(false);
		idField.setBounds(220, 80, 200, 25);

		nameField = new JTextField();
		nameField.setEnabled(false);
		nameField.setBounds(220, 110, 200, 25);

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
		cbProductionType.setBounds(220, 320, 200, 25);

		try {
			listOfProductionQuality = new ArrayList<ProductionQuality>();
			listOfProductionQuality = ServiceFactory.getProductBL().getAllProductionQuality();
			listOfProductionQuality.add(0, new ProductionQuality("-- Pilih Kualitas Produksi --"));
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbProductionQuality = new ComboBox<ProductionQuality>();
		cbProductionQuality.setList(listOfProductionQuality);
		cbProductionQuality.setBounds(220, 350, 200, 25);

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

		try {
			categories = ServiceFactory.getProductBL().getAllProductCategory();
			categories.add(0, new ProductCategory("-- Pilih Kategori Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		catField = new ComboBox<ProductCategory>();
		catField.setList(categories);
		catField.setEnabled(false);
		catField.setBounds(220, 140, 200, 25);

		try {
			units = ServiceFactory.getProductBL().getAllUom();
			units.add(0, new Uom("-- Pilih Satuan Produk --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		uomField = new ComboBox<Uom>();
		uomField.setList(units);
		uomField.setEnabled(false);
		uomField.setBounds(220, 170, 200, 25);

		maintain = new ButtonGroup();
		maintainYesField = new JRadioButton("Ya");
		maintainYesField.setEnabled(false);
		maintainYesField.setBounds(220, 200, 50, 25);

		maintainNoField = new JRadioButton("Tidak");
		maintainNoField.setEnabled(false);
		maintainNoField.setBounds(280, 200, 50, 25);

		maintain.add(maintainYesField);
		maintain.add(maintainNoField);

		try {
			woodTypes = ServiceFactory.getProductBL().getAllWoodType();
			woodTypes.add(0, new WoodType("-- Pilih Jenis Kayu --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		typeField = new ComboBox<WoodType>();
		typeField.setList(woodTypes);
		typeField.setEnabled(false);
		typeField.setBounds(220, 290, 200, 25);

		try {
			grades = ServiceFactory.getProductBL().getAllGrade();
			grades.add(0, new Grade("-- Pilih Grade --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		gradeField = new ComboBox<Grade>();
		gradeField.setList(grades);
		gradeField.setEnabled(false);
		gradeField.setBounds(220, 380, 200, 25);

		longField = new JTextField();
		longField.setEnabled(false);
		longField.setBounds(220, 410, 200, 25);

		thickField = new JTextField();
		thickField.setEnabled(false);
		thickField.setBounds(220, 470, 200, 25);

		wideField = new JTextField();
		wideField.setEnabled(false);
		wideField.setBounds(220, 440, 200, 25);

		minQtyField = new JTextField();
		minQtyField.setEnabled(false);
		minQtyField.setBounds(220, 500, 200, 25);

		printBtn = new JButton("Cetak");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		printBtn.setBounds(725, 780, 100, 25);

		deleteBtn = new JButton("Hapus");
		deleteBtn.setBounds(825, 780, 100, 25);
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogResult = DialogBox.showDeleteChoice();
				if (dialogResult == JOptionPane.YES_OPTION) {
					doDelete();
				}
			}
		});

		editBtn = new JButton("Ubah");
		editBtn.setBounds(925, 780, 100, 25);
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				product = new Product();

				product.setProductCode(idField.getText());
				MainPanel.changePanel("module.product.ui.ProductEditPanel", product);
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
		panel.add(printBtn);
		panel.add(deleteBtn);
		panel.add(editBtn);

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
		gradeLblError.setBounds(425, 320, 225, 25);

		thickLblError = new JLabel("");
		thickLblError.setForeground(Color.RED);
		thickLblError.setBounds(425, 380, 225, 25);

		longLblError = new JLabel("");
		longLblError.setForeground(Color.RED);
		longLblError.setBounds(425, 350, 225, 25);

		wideLblError = new JLabel("");
		wideLblError.setForeground(Color.RED);
		wideLblError.setBounds(425, 410, 225, 25);

		minQtyLblError = new JLabel("");
		minQtyLblError.setForeground(Color.RED);
		minQtyLblError.setBounds(425, 440, 225, 25);

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
		lblPurchaserPrice.setBounds(50, 550, 200, 25);
		panel.add(lblPurchaserPrice);
		
		btnInsertProductPP = new JButton("Tambah");
		btnInsertProductPP.setBounds(825, 550, 100, 25);
		btnInsertProductPP.setEnabled(false);
		panel.add(btnInsertProductPP);
		
		btnDeleteProductPP = new JButton("Hapus");
		btnDeleteProductPP.setEnabled(false);
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
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();

					if (column == 4) {
						showViewPPRProductDialog(listOfProductPP.get(row), productViewPanel, row);
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

	protected void doDelete() {
		try {
			Product deleteProduct = new Product();
			deleteProduct.setDeletedDate(todayDate);
			deleteProduct.setDeletedBy("Timotius");
			deleteProduct.setProductCode(idField.getText());

			ServiceFactory.getProductBL().delete(deleteProduct);
			DialogBox.showDelete();
			MainPanel.changePanel("module.product.ui.ProductListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void loadData(String productCode) {
		try {
			product = ServiceFactory.getProductBL().getProductByCode(productCode);
			listOfProductPP = ServiceFactory.getProductBL().getProductPPByProductCode(productCode);
			
			if (product != null) {
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
				
				switch (catField.getDataIndex().getId()) {
				case AppConstants.BALKEN_BASAH_ID:
					typeLbl.setText("<html>Jenis Kayu <font color=\"red\">*</font></html>");
					gradeLbl.setText("<html>Grade <font color=\"red\">*</font></html>");
					thickLbl.setText("<html>Tebal <font color=\"red\">*</font></html>");
					longLbl.setText("<html>Panjang <font color=\"red\">*</font></html>");
					wideLbl.setText("<html>Lebar <font color=\"red\">*</font></html>");
					lblProductionType.setText("Tipe Hasil Produksi");
					lblProductionQuality.setText("Kualitas Produksi");
					break;
				case AppConstants.BALKEN_KERING_ID:
					typeLbl.setText("<html>Jenis Kayu <font color=\"red\">*</font></html>");
					gradeLbl.setText("<html>Grade <font color=\"red\">*</font></html>");
					thickLbl.setText("<html>Tebal <font color=\"red\">*</font></html>");
					longLbl.setText("<html>Panjang <font color=\"red\">*</font></html>");
					wideLbl.setText("<html>Lebar <font color=\"red\">*</font></html>");
					lblProductionType.setText("Tipe Hasil Produksi");
					lblProductionQuality.setText("Kualitas Produksi");
					break;
				case AppConstants.HASIL_PRODUKSI_ID:
					typeLbl.setText("Jenis Kayu");
					gradeLbl.setText("Grade");
					thickLbl.setText("Tebal");
					longLbl.setText("Panjang");
					wideLbl.setText("Lebar");
					lblProductionType.setText("<html>Tipe Hasil Produksi<font color=\"red\">*</font></html>");
					lblProductionQuality.setText("<html>Kualitas Produksi<font color=\"red\">*</font></html>");
					
					cbProductionQuality.setSelectedItem(product.getProductionQuality());
					cbProductionType.setSelectedItem(product.getProductionType());
					break;
				case AppConstants.BARANG_PENDUKUNG_ID:
					typeLbl.setText("Jenis Kayu");
					gradeLbl.setText("Grade");
					thickLbl.setText("Tebal");
					longLbl.setText("Panjang");
					wideLbl.setText("Lebar");
					lblProductionType.setText("Tipe Hasil Produksi");
					lblProductionQuality.setText("Kualitas Produksi");
					break;
				default:
					break;
				}
				
				refreshTableProductPP();
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
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
		 * @return ({@link RPRNote}) Object
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
	
	protected void showViewPPRProductDialog(ProductPP productPP,
			ProductViewPanel pprViewPanel, Integer index) {
		ProductPPDialog productPPDialog = new ProductPPDialog(true, productPP, pprViewPanel, index);
		productPPDialog.setTitle("Harga Beli");
		productPPDialog.setLocationRelativeTo(null);
		productPPDialog.setVisible(true);
	}
}
