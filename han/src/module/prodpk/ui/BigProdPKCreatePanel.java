package module.prodpk.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.AppConstants;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.dailyclosing.model.Inventory;
import module.prodpk.model.ProdPK;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.util.Bridging;
import module.util.JTextFieldLimit;
import module.prodpk.model.ProdPKMaterial;
import module.prodpk.model.ProdPKResultProduct;

public class BigProdPKCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
	}

	private static final Logger LOGGER = Logger.getLogger(BigProdPKCreatePanel.class);

	JLabel lblProductionCode;
	JLabel lblProductionDate;
	JLabel lblGroupShift;
	JLabel lblShift;
	JLabel lblLine;
	
	JLabel lblRepairKlem;
	JLabel lblRepairKlemTotalGradeA;
	JLabel lblRepairKlemTotalGradeB;
	
	JLabel lblProductionResult;
	JLabel lblProductionResultTotalGradeA;
	JLabel lblProductionResultTotalGradeB;

	JButton btnInsert;
	JButton btnDelete;
	JButton btnCancel;
	JButton btnSave;

	JPanel panel;
	JScrollPane scrollPane;

	JTextField txtProductionCode;
	JDateChooser dcProductionDate;
	ComboBox<GroupShift> cbGroupShift;
	ComboBox<Shift> cbShift;
	ComboBox<Line> cbLine;
	
	JTextField txtRepairKlemTotalGradeA;
	JTextField txtRepairKlemTotalGradeB;
	NumberField txtProductionResultTotalGradeA;
	NumberField txtProductionResultTotalGradeB;
	
	JLabel lblErrorProductionCode;
	JLabel lblErrorProductionDate;
	JLabel lblErrorGroupShift;
	JLabel lblErrorShift;
	JLabel lblErrorLine;
	JLabel lblErrorTxtRepairKlemTotalGradeA;
	JLabel lblErrorTxtRepairKlemTotalGradeB;
	JLabel lblErrorTxtProductionResultTotalGradeA;
	JLabel lblErrorTxtProductionResultTotalGradeB;

	ProdPK prodPK;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<GroupShift> listOfGroupShift = null;
	List<Shift> listOfShift = null;
	List<Line> listOfLine = null;
	List<ProductionType> listOfProductionType = null;

	List<ProdPKMaterial> listOfProdPKMaterial = null;
	List<ProdPKResultProduct> listOfProdPKResultProduct = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;
	
	private JLabel lblProductionType;
	private ComboBox<ProductionType> cbProductionType;

	public BigProdPKCreatePanel() {
		prodPK = new ProdPK();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi > Produksi PK (Hasil Klem) 12");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 500, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		lblProductionCode = new JLabel("<html>Kode Produksi <font color=\"red\">*</font></html>");
		lblProductionCode.setBounds(50, 80, 150, 25);
		panel.add(lblProductionCode);

		txtProductionCode = new JTextField();
		txtProductionCode.setBounds(220, 80, 150, 25);
		txtProductionCode.setDocument(new JTextFieldLimit(15));
		((AbstractDocument) txtProductionCode.getDocument()).setDocumentFilter(filter);
		txtProductionCode.setEnabled(false);
		panel.add(txtProductionCode);
		
		lblErrorProductionCode = new JLabel();
		lblErrorProductionCode.setForeground(Color.RED);
		lblErrorProductionCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorProductionCode);

		lblProductionDate = new JLabel("<html>Tanggal Produksi <font color=\"red\">*</font></html>");
		lblProductionDate.setBounds(50, 110, 150, 25);
		panel.add(lblProductionDate);
		
		dcProductionDate = new JDateChooser(new Date());
		dcProductionDate.setBounds(220, 110, 150, 25);
		dcProductionDate.setDateFormatString("dd-MM-yyyy");
		dcProductionDate.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			               makeCodeNumber(dcProductionDate.getDate());
			            }
			        }
			    });
		makeCodeNumber(dcProductionDate.getDate());
		panel.add(dcProductionDate);

		lblErrorProductionDate = new JLabel();
		lblErrorProductionDate.setForeground(Color.RED);
		lblErrorProductionDate.setBounds(425, 110, 225, 25);
		panel.add(lblErrorProductionDate);

		lblGroupShift = new JLabel("<html>Group Shift <font color=\"red\">*</font></html>");
		lblGroupShift.setBounds(50, 140, 150, 25);
		panel.add(lblGroupShift);
		
		listOfGroupShift = new ArrayList<GroupShift>();
		try {
			listOfGroupShift = ServiceFactory.getProductionPKBL().getAllGroupShift();
			listOfGroupShift.add(0, new GroupShift("-- Pilih Group Shift --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		
		cbLine = new ComboBox<Line>();
		listOfLine = new ArrayList<Line>();
		listOfLine.add(0, new Line("-- Pilih Line --"));
		cbLine.setList(listOfLine);
		cbLine.setBounds(220, 200, 150, 25);
		panel.add(cbLine);

		cbGroupShift = new ComboBox<GroupShift>();
		cbGroupShift.setList(listOfGroupShift);
		cbGroupShift.setBounds(220, 140, 150, 25);
		cbGroupShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					listOfLine = new ArrayList<Line>();
					Line line = new Line();
					line.setLineCode(cbGroupShift.getDataIndex().getLineCode());
					line.setDescription(cbGroupShift.getDataIndex().getLineDescription());
					listOfLine.add(0, line);
					cbLine.setList(listOfLine);
				} catch (Exception e1) {
					LOGGER.error(e1.getMessage());
					DialogBox.showErrorException();
				}
			}
		});
		panel.add(cbGroupShift);

		lblErrorGroupShift = new JLabel();
		lblErrorGroupShift.setForeground(Color.RED);
		lblErrorGroupShift.setBounds(425, 140, 225, 25);
		panel.add(lblErrorGroupShift);
		
		lblShift = new JLabel("<html>Shift <font color=\"red\">*</font></html>");
		lblShift.setBounds(50, 170, 150, 25);
		panel.add(lblShift);
		
		listOfShift = new ArrayList<Shift>();
		try {
			listOfShift = ServiceFactory.getProductionPKBL().getAllShift();
			listOfShift.add(0, new Shift("-- Pilih Shift --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbShift = new ComboBox<Shift>();
		cbShift.setList(listOfShift);
		cbShift.setBounds(220, 170, 150, 25);
		panel.add(cbShift);

		lblErrorShift = new JLabel();
		lblErrorShift.setForeground(Color.RED);
		lblErrorShift.setBounds(425, 170, 225, 25);
		panel.add(lblErrorShift);
		
		lblLine = new JLabel("<html>Line <font color=\"red\">*</font></html>");
		lblLine.setBounds(50, 200, 150, 25);
		panel.add(lblLine);
		
	
		lblErrorLine = new JLabel();
		lblErrorLine.setForeground(Color.RED);
		lblErrorLine.setBounds(425, 200, 225, 25);
		panel.add(lblErrorLine);
		
		listOfProductionType = new ArrayList<ProductionType>();
		try {
			listOfProductionType = ServiceFactory.getProductionPKBL().getAllProductionType();
			listOfProductionType.add(0, new ProductionType("-- Pilih Tipe Produksi --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		cbProductionType = new ComboBox<ProductionType>();
		cbProductionType.setList(listOfProductionType);
		cbProductionType.setBounds(220, 230, 150, 25);
		for(int i = 0; i < listOfProductionType.size(); i++) {
			if(AppConstants.BC_TYPE_9.equalsIgnoreCase(listOfProductionType.get(i).getProductionTypeCode())) {
				cbProductionType.setSelectedIndex(i);
				break;
			}
		}
		cbProductionType.setEnabled(false);
		
		panel.add(cbProductionType);
		
		lblErrorTxtRepairKlemTotalGradeA = new JLabel();
		lblErrorTxtRepairKlemTotalGradeA.setForeground(Color.RED);
		lblErrorTxtRepairKlemTotalGradeA.setBounds(425, 290, 225, 25);
		panel.add(lblErrorTxtRepairKlemTotalGradeA);
		
		lblErrorTxtRepairKlemTotalGradeB = new JLabel();
		lblErrorTxtRepairKlemTotalGradeB.setForeground(Color.RED);
		lblErrorTxtRepairKlemTotalGradeB.setBounds(425, 320, 225, 25);
		panel.add(lblErrorTxtRepairKlemTotalGradeB);
		
		lblErrorTxtProductionResultTotalGradeA = new JLabel();
		lblErrorTxtProductionResultTotalGradeA.setForeground(Color.RED);
		lblErrorTxtProductionResultTotalGradeA.setBounds(425, 380, 225, 25);
		panel.add(lblErrorTxtProductionResultTotalGradeA);
		
		lblErrorTxtProductionResultTotalGradeB = new JLabel(); 
		lblErrorTxtProductionResultTotalGradeB.setForeground(Color.RED);
		lblErrorTxtProductionResultTotalGradeB.setBounds(425, 410, 225, 25);
		panel.add(lblErrorTxtProductionResultTotalGradeB);
		
		lblRepairKlem = new JLabel("Bahan Baku (Repair - Klem)");
		lblRepairKlem.setBounds(50, 260, 200, 25);
		lblRepairKlem.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblRepairKlem);
		
		lblRepairKlemTotalGradeA = new JLabel("Jumlah Grade A");
		lblRepairKlemTotalGradeA.setBounds(120, 290, 150, 25);
		panel.add(lblRepairKlemTotalGradeA);
		
		lblRepairKlemTotalGradeB = new JLabel("Jumlah Grade B");
		lblRepairKlemTotalGradeB.setBounds(120, 320, 150, 25);
		panel.add(lblRepairKlemTotalGradeB);
		
		try {
			Inventory inventoryKlemGradeA = ServiceFactory.getProductionPKBL().getInventoryByProductCode(AppConstants.PRODUCT_CODE_KLEM_A_TYPE_12);
			Inventory inventoryKlemGradeB = ServiceFactory.getProductionPKBL().getInventoryByProductCode(AppConstants.PRODUCT_CODE_KLEM_B_TYPE_12);
		
			txtRepairKlemTotalGradeA = new JTextField();
			txtRepairKlemTotalGradeA.setEnabled(false);
			txtRepairKlemTotalGradeA.setText(inventoryKlemGradeA != null ? inventoryKlemGradeA.getQty().toString() : "0");
			txtRepairKlemTotalGradeA.setBounds(220, 290, 150, 25);
			panel.add(txtRepairKlemTotalGradeA);
			
			txtRepairKlemTotalGradeB = new JTextField();
			txtRepairKlemTotalGradeB.setEnabled(false);
			txtRepairKlemTotalGradeB.setText(inventoryKlemGradeB != null ? inventoryKlemGradeB.getQty().toString() : "0");
			txtRepairKlemTotalGradeB.setBounds(220, 320, 150, 25);
			panel.add(txtRepairKlemTotalGradeB);
		
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
		
//		txtRepairKlemTotalGradeA = new NumberField(3);
//		txtRepairKlemTotalGradeA.setEnabled(false);
//		txtRepairKlemTotalGradeA.setText("0");
//		txtRepairKlemTotalGradeA.setBounds(220, 290, 150, 25);
//		panel.add(txtRepairKlemTotalGradeA);
//		
//		txtRepairKlemTotalGradeB = new NumberField(3);
//		txtRepairKlemTotalGradeB.setEnabled(false);
//		txtRepairKlemTotalGradeB.setText("0");
//		txtRepairKlemTotalGradeB.setBounds(220, 320, 150, 25);
//		panel.add(txtRepairKlemTotalGradeB);
		
		lblProductionResult = new JLabel("Hasil Produksi (Hasil Klem)");
		lblProductionResult.setBounds(50, 350, 200, 25);
		lblProductionResult.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblProductionResult);
		
		lblProductionResultTotalGradeA = new JLabel("Jumlah Grade A");
		lblProductionResultTotalGradeA.setBounds(120, 380, 150, 25);
		panel.add(lblProductionResultTotalGradeA);
	
		lblProductionResultTotalGradeB = new JLabel("Jumlah Grade B");
		lblProductionResultTotalGradeB.setBounds(120, 410, 150, 25);
		panel.add(lblProductionResultTotalGradeB);
		
		txtProductionResultTotalGradeA = new NumberField(3);
		txtProductionResultTotalGradeA.setBounds(220, 380, 150, 25);
		panel.add(txtProductionResultTotalGradeA);
		
		txtProductionResultTotalGradeB = new NumberField(3);
		txtProductionResultTotalGradeB.setBounds(220, 410, 150, 25);
		panel.add(txtProductionResultTotalGradeB);
	
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
					 MainPanel.changePanel("module.prodpk.ui.ProdPKListPanel");
				 }
			}
		});
		btnCancel.setBounds(50, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
		txtProductionResultTotalGradeA.setText("0");
		txtProductionResultTotalGradeB.setText("0");

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
	
	protected void doSave() {
		prodPK = new ProdPK();
		prodPK.setProdPKCode(txtProductionCode.getText());
		prodPK.setProductionDate(dcProductionDate.getDate());
		prodPK.setGroupShiftCode(cbGroupShift.getDataIndex().getGroupShiftCode());
		prodPK.setShiftCode(cbShift.getDataIndex().getShiftCode());
		prodPK.setLineCode(cbLine.getDataIndex().getLineCode());
		prodPK.setProductionTypeCode(AppConstants.BC_TYPE_12);
		
		listOfProdPKMaterial = prodPKMaterialDetail();
		listOfProdPKResultProduct = prodPKResultProductDetail();
		
		try {
			ServiceFactory.getProductionPKBL().save(prodPK, listOfProdPKMaterial, listOfProdPKResultProduct);
			DialogBox.showInsert();
			MainPanel.changePanel("module.prodpk.ui.BigProdPKListPanel");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	private List<ProdPKMaterial> prodPKMaterialDetail() {
		List<ProdPKMaterial> listOfProdPKMaterialTemp = new ArrayList<ProdPKMaterial>();
		
		ProdPKMaterial pwProductKlemGradeA = new ProdPKMaterial();
		pwProductKlemGradeA.setProductCode(AppConstants.PRODUCT_CODE_KLEM_A_TYPE_12);
		pwProductKlemGradeA.setQty(new BigDecimal(txtRepairKlemTotalGradeA.getText()));
		listOfProdPKMaterialTemp.add(pwProductKlemGradeA);
		
		ProdPKMaterial pwProductKlemGradeB = new ProdPKMaterial();
		pwProductKlemGradeB.setProductCode(AppConstants.PRODUCT_CODE_KLEM_B_TYPE_12);
		pwProductKlemGradeB.setQty(new BigDecimal(txtRepairKlemTotalGradeB.getText()));
		listOfProdPKMaterialTemp.add(pwProductKlemGradeB);
		
		return listOfProdPKMaterialTemp;
	}
	
	private List<ProdPKResultProduct> prodPKResultProductDetail () {
		List<ProdPKResultProduct> listOfProdPKResultProductTemp = new ArrayList<ProdPKResultProduct>();
		
		ProdPKResultProduct prodPKResultProductKlemGradeA = new ProdPKResultProduct();
		prodPKResultProductKlemGradeA.setProductCode(AppConstants.PRODUCT_CODE_PROD_RESULT_A_TYPE_12);
		prodPKResultProductKlemGradeA.setQty(new BigDecimal(txtProductionResultTotalGradeA.getText()));
		listOfProdPKResultProductTemp.add(prodPKResultProductKlemGradeA);
		
		ProdPKResultProduct prodPKResultProductKlemGradeB = new ProdPKResultProduct();
		prodPKResultProductKlemGradeB.setProductCode(AppConstants.PRODUCT_CODE_PROD_RESULT_B_TYPE_12);
		prodPKResultProductKlemGradeB.setQty(new BigDecimal(txtProductionResultTotalGradeB.getText()));
		listOfProdPKResultProductTemp.add(prodPKResultProductKlemGradeB);
		
		return listOfProdPKResultProductTemp;
	}
	
	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorProductionCode.setText("");
		lblErrorGroupShift.setText("");
		lblErrorShift.setText("");
		lblErrorLine.setText("");
		lblErrorTxtRepairKlemTotalGradeA.setText("");
		lblErrorTxtRepairKlemTotalGradeB.setText("");
		lblErrorTxtProductionResultTotalGradeA.setText("");
		lblErrorTxtProductionResultTotalGradeB.setText("");
		
		if (txtProductionCode.getText() == null || txtProductionCode.getText().length() == 0) {
			lblErrorProductionCode.setText("Textbox Kode Produksi harus diisi.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getProductionPKBL().isProdPKCodeExists(txtProductionCode.getText()) > 0) {
					lblErrorProductionCode.setText("Kode Produksi sudah pernah diinput.");
					isValid = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DialogBox.showErrorException();
				isValid = false;
			}
		}

		if (cbGroupShift.getSelectedItem() == null || cbGroupShift.getSelectedIndex() == 0) {
			lblErrorGroupShift.setText("Combobox Group Shift harus dipilih.");
			isValid = false;
		} 

		if (dcProductionDate.getDate() == null) {
			lblErrorProductionDate.setText("Tanggal Produksi harus dipilih.");
			isValid = false;
		}
		
		if (cbShift.getSelectedItem() == null || cbShift.getSelectedIndex() == 0) {
			lblErrorShift.setText("Combobox Shift harus dipilih.");
			isValid = false;
		}
		
		if (cbLine.getSelectedItem() == null) {
			lblErrorLine.setText("Combobox Line harus dipilih.");
			isValid = false;
		}
		
		if (txtRepairKlemTotalGradeA.getText() == null || txtRepairKlemTotalGradeA.getText().length() == 0) {
			lblErrorTxtRepairKlemTotalGradeA.setText("Textbox Jumlah Grade A harus diisi.");
			isValid = false;
		}
		
		if (txtRepairKlemTotalGradeB.getText() == null || txtRepairKlemTotalGradeB.getText().length() == 0) {
			lblErrorTxtRepairKlemTotalGradeB.setText("Textbox Jumlah Grade B harus diisi.");
			isValid = false;
		}
		
		if (txtProductionResultTotalGradeA.getText() == null || txtProductionResultTotalGradeA.getText().length() == 0) {
			lblErrorTxtProductionResultTotalGradeA.setText("Textbox Jumlah Grade A harus diisi.");
			isValid = false;
		}
		
		if (txtProductionResultTotalGradeB.getText() == null || txtProductionResultTotalGradeB.getText().length() == 0) {
			lblErrorTxtProductionResultTotalGradeB.setText("Textbox Jumlah Grade B harus diisi.");
			isValid = false;
		}

		return isValid;
	}
	
	public void makeCodeNumber(Date producationDate) {
		final String constant = "PK12";

		Calendar cal = Calendar.getInstance();
		cal.setTime(producationDate);
		
		String date = String.valueOf(cal.get(Calendar.DATE));
		String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);

		String ordinal = null;
		try {
			ordinal = ServiceFactory.getProductionPKBL().getOrdinalOfCodeNumber(Integer.valueOf(year), AppConstants.BC_TYPE_12);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
		txtProductionCode.setText(new StringBuilder().append(ordinal).append("/").append(constant)
				.append("/").append(date).append("/").append(month)
				.append("/").append(year).toString());
	}
	
}
