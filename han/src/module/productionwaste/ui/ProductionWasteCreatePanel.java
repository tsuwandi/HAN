package module.productionwaste.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.model.PWProduct;
import module.productionwaste.model.ProductionWaste;
import module.purchaseprodresult.model.PPRProduct;
import module.purchaseprodresult.model.PurchaseProdResult;
import module.sn.currency.model.Currency;
import module.supplier.model.Supplier;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class ProductionWasteCreatePanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		// TODO Auto-generated method stub
	}

	private static final Logger LOGGER = Logger.getLogger(ProductionWasteCreatePanel.class);

	JLabel lblProductionCode;
	JLabel lblProductionDate;
	JLabel lblGroupShift;
	JLabel lblShift;
	JLabel lblLine;
	JLabel lblProductionType;
	
	JLabel lblRepairKlem;
	JLabel lblRepairKlemTotalGradeA;
	JLabel lblRepairKlemTotalGradeB;
	
	JLabel lblRepairProtol;
	JLabel lblRepairProtolTotalGradeA;
	JLabel lblRepairProtolTotalGradeB;
	
	JLabel lblRepairNormal;
	JLabel lblRepairNormalTotalGradeA;
	JLabel lblRepairNormalTotalGradeB;

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
	ComboBox<ProductionType> cbProductionType;
	
	NumberField txtRepairKlemTotalGradeA;
	NumberField txtRepairKlemTotalGradeB;
	NumberField txtRepairProtolTotalGradeA;
	NumberField txtRepairProtolTotalGradeB;
	NumberField txtRepairNormalTotalGradeA;
	NumberField txtRepairNormalTotalGradeB;

	JLabel lblErrorProductionCode;
	JLabel lblErrorProductionDate;
	JLabel lblErrorGroupShift;
	JLabel lblErrorShift;
	JLabel lblErrorLine;
	JLabel lblErrorProductionType;
	JLabel lblErrorTxtRepairKlemTotalGradeA;
	JLabel lblErrorTxtRepairKlemTotalGradeB;
	JLabel lblErrorTxtRepairProtolTotalGradeA;
	JLabel lblErrorTxtRepairProtolTotalGradeB;
	JLabel lblErrorTxtRepairNormalTotalGradeA;
	JLabel lblErrorTxtRepairNormalTotalGradeB;

	ProductionWaste productionWaste;
	DocumentFilter filter = new UppercaseDocumentFilter();

	List<GroupShift> listOfGroupShift = null;
	List<Shift> listOfShift = null;
	List<Line> listOfLine = null;
	List<ProductionType> listOfProductionType = null;

	List<PWProduct> listOfPWProduct = null;

	JLabel lblBreadcrumb;
	JLabel lblHeader;


	final int SUPP_TYPE_ID_HASIL_PRODUKSI = 3;
	final String PRODUCTION_TYPE_BARECORE = "Barecore";

	public ProductionWasteCreatePanel() {
		productionWaste = new ProductionWaste();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi > Sisa Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 414, 25);
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
			listOfGroupShift = ServiceFactory.getProductionWasteBL().getAllGroupShift();
			listOfGroupShift.add(0, new GroupShift("-- Pilih Group Shift --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbGroupShift = new ComboBox<GroupShift>();
		cbGroupShift.setList(listOfGroupShift);
		cbGroupShift.setBounds(220, 140, 150, 25);
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
			listOfShift = ServiceFactory.getProductionWasteBL().getAllShift();
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
		
		listOfLine = new ArrayList<Line>();
		try {
			listOfLine = ServiceFactory.getProductionWasteBL().getAllLine();
			listOfLine.add(0, new Line("-- Pilih Line --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbLine = new ComboBox<Line>();
		cbLine.setList(listOfLine);
		cbLine.setBounds(220, 200, 150, 25);
		panel.add(cbLine);

		lblErrorLine = new JLabel();
		lblErrorLine.setForeground(Color.RED);
		lblErrorLine.setBounds(425, 200, 225, 25);
		panel.add(lblErrorLine);
		
		lblProductionType = new JLabel("<html>Tipe Produksi <font color=\"red\">*</font></html>");
		lblProductionType.setBounds(50, 230, 150, 25);
		panel.add(lblProductionType);
		
		cbProductionType = new ComboBox<ProductionType>();
		
		
		listOfProductionType = new ArrayList<ProductionType>();
		try {
			listOfProductionType = ServiceFactory.getProductionWasteBL().getAllProductionType();
			listOfProductionType.add(0, new ProductionType("-- Pilih Tipe Produksi --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbProductionType.setList(listOfProductionType);
		for(int i = 0; i < listOfProductionType.size(); i++) {
			if(PRODUCTION_TYPE_BARECORE.equalsIgnoreCase(listOfProductionType.get(i).getProductionType())) {
				cbProductionType.setSelectedIndex(i);
				break;
			}
		}
		cbProductionType.setBounds(220, 230, 150, 25);
		panel.add(cbProductionType);

		lblErrorProductionType = new JLabel();
		lblErrorProductionType.setForeground(Color.RED);
		lblErrorProductionType.setBounds(425, 230, 225, 25);
		panel.add(lblErrorProductionType);
		
		lblErrorTxtRepairKlemTotalGradeA = new JLabel();
		lblErrorTxtRepairKlemTotalGradeA.setForeground(Color.RED);
		lblErrorTxtRepairKlemTotalGradeA.setBounds(425, 290, 225, 25);
		panel.add(lblErrorTxtRepairKlemTotalGradeA);
		
		lblErrorTxtRepairKlemTotalGradeB = new JLabel();
		lblErrorTxtRepairKlemTotalGradeB.setForeground(Color.RED);
		lblErrorTxtRepairKlemTotalGradeB.setBounds(425, 320, 225, 25);
		panel.add(lblErrorTxtRepairKlemTotalGradeB);
		
		lblErrorTxtRepairProtolTotalGradeA = new JLabel();
		lblErrorTxtRepairProtolTotalGradeA.setForeground(Color.RED);
		lblErrorTxtRepairProtolTotalGradeA.setBounds(425, 380, 225, 25);
		panel.add(lblErrorTxtRepairProtolTotalGradeA);
		
		lblErrorTxtRepairProtolTotalGradeB = new JLabel(); 
		lblErrorTxtRepairProtolTotalGradeB.setForeground(Color.RED);
		lblErrorTxtRepairProtolTotalGradeB.setBounds(425, 410, 225, 25);
		panel.add(lblErrorTxtRepairProtolTotalGradeB);
		
		lblErrorTxtRepairNormalTotalGradeA = new JLabel();
		lblErrorTxtRepairNormalTotalGradeA.setForeground(Color.RED);
		lblErrorTxtRepairNormalTotalGradeA.setBounds(425, 470, 225, 25);
		panel.add(lblErrorTxtRepairNormalTotalGradeA);
		
		lblErrorTxtRepairNormalTotalGradeB = new JLabel();
		lblErrorTxtRepairNormalTotalGradeB.setForeground(Color.RED);
		lblErrorTxtRepairNormalTotalGradeB.setBounds(425, 500, 225, 25);
		panel.add(lblErrorTxtRepairNormalTotalGradeB);
		
		lblRepairKlem = new JLabel("Repair (Klem)");
		lblRepairKlem.setBounds(50, 260, 150, 25);
		lblRepairKlem.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblRepairKlem);
		
		lblRepairKlemTotalGradeA = new JLabel("Jumlah Grade A");
		lblRepairKlemTotalGradeA.setBounds(120, 290, 150, 25);
		panel.add(lblRepairKlemTotalGradeA);
		
		lblRepairKlemTotalGradeB = new JLabel("Jumlah Grade B");
		lblRepairKlemTotalGradeB.setBounds(120, 320, 150, 25);
		panel.add(lblRepairKlemTotalGradeB);
		
		txtRepairKlemTotalGradeA = new NumberField(3);
		txtRepairKlemTotalGradeA.setBounds(220, 290, 150, 25);
		panel.add(txtRepairKlemTotalGradeA);
		
		txtRepairKlemTotalGradeB = new NumberField(3);
		txtRepairKlemTotalGradeB.setBounds(220, 320, 150, 25);
		panel.add(txtRepairKlemTotalGradeB);
		
		lblRepairProtol = new JLabel("Repair (Protol)");
		lblRepairProtol.setBounds(50, 350, 150, 25);
		lblRepairProtol.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblRepairProtol);
		
		lblRepairProtolTotalGradeA = new JLabel("Jumlah Grade A");
		lblRepairProtolTotalGradeA.setBounds(120, 380, 150, 25);
		panel.add(lblRepairProtolTotalGradeA);
	
		lblRepairProtolTotalGradeB = new JLabel("Jumlah Grade B");
		lblRepairProtolTotalGradeB.setBounds(120, 410, 150, 25);
		panel.add(lblRepairProtolTotalGradeB);
		
		txtRepairProtolTotalGradeA = new NumberField(3);
		txtRepairProtolTotalGradeA.setBounds(220, 380, 150, 25);
		panel.add(txtRepairProtolTotalGradeA);
		
		txtRepairProtolTotalGradeB = new NumberField(3);
		txtRepairProtolTotalGradeB.setBounds(220, 410, 150, 25);
		panel.add(txtRepairProtolTotalGradeB);
		
		lblRepairNormal = new JLabel("Hasil Baik");
		lblRepairNormal.setBounds(50, 440, 150, 25);
		lblRepairNormal.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblRepairNormal);
		
		lblRepairNormalTotalGradeA = new JLabel("Jumlah Grade A");
		lblRepairNormalTotalGradeA.setBounds(120, 470, 150, 25);
		panel.add(lblRepairNormalTotalGradeA);
	
		lblRepairNormalTotalGradeB = new JLabel("Jumlah Grade B");
		lblRepairNormalTotalGradeB.setBounds(120, 500, 150, 25);
		panel.add(lblRepairNormalTotalGradeB);
		
		txtRepairNormalTotalGradeA = new NumberField(3);
		txtRepairNormalTotalGradeA.setBounds(220, 470, 150, 25);
		panel.add(txtRepairNormalTotalGradeA);
		
		txtRepairNormalTotalGradeB = new NumberField(3);
		txtRepairNormalTotalGradeB.setBounds(220, 500, 150, 25);
		panel.add(txtRepairNormalTotalGradeB);

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
					 MainPanel.changePanel("module.productionwaste.ui.ProductionWasteListPanel");
				 }
			}
		});
		btnCancel.setBounds(50, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);
		
		txtRepairKlemTotalGradeA.setText("0");
		txtRepairKlemTotalGradeB.setText("0");
		txtRepairProtolTotalGradeA.setText("0");
		txtRepairProtolTotalGradeB.setText("0");
		txtRepairNormalTotalGradeA.setText("0");
		txtRepairNormalTotalGradeB.setText("0");

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
	
	private static final String PRODUCT_CODE_KLEM_A = "PDC009-3";
	private static final String PRODUCT_CODE_KLEM_B = "PDC009-4";
	private static final String PRODUCT_CODE_PROTOL_A = "PDC009-5";
	private static final String PRODUCT_CODE_PROTOL_B = "PDC009-6";
	private static final String PRODUCT_CODE_NORMAL_A = "PDC009";
	private static final String PRODUCT_CODE_NORMAL_B = "PDC009-2";
	
	protected void doSave() {
		productionWaste = new ProductionWaste();
		productionWaste.setPwCode(txtProductionCode.getText());
		productionWaste.setProductionDate(dcProductionDate.getDate());
		productionWaste.setGroupShiftCode(cbGroupShift.getDataIndex().getGroupShiftCode());
		productionWaste.setShiftCode(cbShift.getDataIndex().getShiftCode());
		productionWaste.setLineCode(cbLine.getDataIndex().getLineCode());
		productionWaste.setProductionTypeCode(cbProductionType.getDataIndex().getProductionTypeCode());
		
		listOfPWProduct = pwProductDetail();
		
		try {
			ServiceFactory.getProductionWasteBL().save(productionWaste, listOfPWProduct);
			DialogBox.showInsert();
			MainPanel.changePanel("module.productionwaste.ui.ProductionWasteListPanel");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	private List<PWProduct> pwProductDetail () {
		List<PWProduct> listOfPWProductTemp = new ArrayList<PWProduct>();
		
		PWProduct pwProductKlemGradeA = new PWProduct();
		pwProductKlemGradeA.setProductCode(PRODUCT_CODE_KLEM_A);
		pwProductKlemGradeA.setQty(Integer.valueOf(txtRepairKlemTotalGradeA.getText()));
		listOfPWProductTemp.add(pwProductKlemGradeA);
		
		PWProduct pwProductKlemGradeB = new PWProduct();
		pwProductKlemGradeB.setProductCode(PRODUCT_CODE_KLEM_B);
		pwProductKlemGradeB.setQty(Integer.valueOf(txtRepairKlemTotalGradeB.getText()));
		listOfPWProductTemp.add(pwProductKlemGradeB);
		
		PWProduct pwProductProtolGradeA = new PWProduct();
		pwProductProtolGradeA.setProductCode(PRODUCT_CODE_PROTOL_A);
		pwProductProtolGradeA.setQty(Integer.valueOf(txtRepairProtolTotalGradeA.getText()));
		listOfPWProductTemp.add(pwProductProtolGradeA);
		
		PWProduct pwProductProtolGradeB = new PWProduct();
		pwProductProtolGradeB.setProductCode(PRODUCT_CODE_PROTOL_B);
		pwProductProtolGradeB.setQty(Integer.valueOf(txtRepairProtolTotalGradeB.getText()));
		listOfPWProductTemp.add(pwProductProtolGradeB);
		
		PWProduct pwProductNormalGradeA = new PWProduct();
		pwProductNormalGradeA.setProductCode(PRODUCT_CODE_NORMAL_A);
		pwProductNormalGradeA.setQty(Integer.valueOf(txtRepairNormalTotalGradeA.getText()));
		listOfPWProductTemp.add(pwProductNormalGradeA);
		
		PWProduct pwProductNormalGradeB = new PWProduct();
		pwProductNormalGradeB.setProductCode(PRODUCT_CODE_NORMAL_B);
		pwProductNormalGradeB.setQty(Integer.valueOf(txtRepairNormalTotalGradeB.getText()));
		listOfPWProductTemp.add(pwProductNormalGradeB);
		
		return listOfPWProductTemp;
	}
	
	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorProductionCode.setText("");
		lblErrorGroupShift.setText("");
		lblErrorShift.setText("");
		lblErrorLine.setText("");
		lblErrorProductionType.setText("");
		lblErrorTxtRepairKlemTotalGradeA.setText("");
		lblErrorTxtRepairKlemTotalGradeB.setText("");
		lblErrorTxtRepairProtolTotalGradeA.setText("");
		lblErrorTxtRepairProtolTotalGradeB.setText("");
		lblErrorTxtRepairNormalTotalGradeA.setText("");
		lblErrorTxtRepairNormalTotalGradeB.setText("");
		
		if (txtProductionCode.getText() == null || txtProductionCode.getText().length() == 0) {
			lblErrorProductionCode.setText("Textbox Kode Produksi harus diisi.");
			isValid = false;
		} else {
			try {
				if (ServiceFactory.getProductionWasteBL().isPWCodeExists(txtProductionCode.getText()) > 0) {
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
		
		if (cbLine.getSelectedItem() == null || cbLine.getSelectedIndex() == 0) {
			lblErrorLine.setText("Combobox Line harus dipilih.");
			isValid = false;
		}
		
		if (cbProductionType.getSelectedItem() == null || cbProductionType.getSelectedIndex() == 0) {
			lblErrorProductionType.setText("Combobox Tipe Produksi harus dipilih.");
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
		
		if (txtRepairProtolTotalGradeA.getText() == null || txtRepairProtolTotalGradeA.getText().length() == 0) {
			lblErrorTxtRepairProtolTotalGradeA.setText("Textbox Jumlah Grade A harus diisi.");
			isValid = false;
		}
		
		if (txtRepairProtolTotalGradeB.getText() == null || txtRepairProtolTotalGradeB.getText().length() == 0) {
			lblErrorTxtRepairProtolTotalGradeB.setText("Textbox Jumlah Grade B harus diisi.");
			isValid = false;
		}
		
		if (txtRepairNormalTotalGradeA.getText() == null || txtRepairNormalTotalGradeA.getText().length() == 0) {
			lblErrorTxtRepairNormalTotalGradeA.setText("Textbox Jumlah Grade A harus diisi.");
			isValid = false;
		}
		
		if (txtRepairNormalTotalGradeB.getText() == null || txtRepairNormalTotalGradeB.getText().length() == 0) {
			lblErrorTxtRepairNormalTotalGradeB.setText("Textbox Jumlah Grade B harus diisi.");
			isValid = false;
		}

		
		return isValid;
	}
	
	public void makeCodeNumber(Date producationDate) {
		final String constant = "PW";

		Calendar cal = Calendar.getInstance();
		cal.setTime(producationDate);
		
		String date = String.valueOf(cal.get(Calendar.DATE));
		String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);

		String ordinal = null;
		try {
			ordinal = ServiceFactory.getProductionWasteBL().getOrdinalOfCodeNumber(Integer.valueOf(year));
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
		txtProductionCode.setText(new StringBuilder().append(ordinal).append("/").append(constant)
				.append("/").append(date).append("/").append(month)
				.append("/").append(year).toString());
	}
	
}
