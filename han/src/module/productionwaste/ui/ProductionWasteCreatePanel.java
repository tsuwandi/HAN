package module.productionwaste.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import main.component.AppConstants;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.production.ui.PopUpProductionResult;
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
		if(objects.length>0){
			productionWaste = (ProductionWaste) objects[0];
			txtProductionCode.setText(productionWaste.getPwCode());
			dcProductionDate.setDate(productionWaste.getProductionDate());
			cbGroupShift.setSelectedItem(productionWaste.getGroupShift().getDescription());
			cbShift.setSelectedItem(productionWaste.getShift().getShiftName());
			cbLine.setSelectedItem(productionWaste.getLine().getDescription());
			cbProductionType.setSelectedItem(productionWaste.getProductionType().getProductionType());
			editMode = true;
			dcProductionDate.setEnabled(false);
			lblBreadcrumb.setText("ERP > Pembelian > Edit Hasil Produksi > Sisa Produksi 9");
		}
	}

	private static final Logger LOGGER = Logger.getLogger(ProductionWasteCreatePanel.class);

	private JLabel lblProductionCode;
	private JLabel lblProductionDate;
	private JLabel lblGroupShift;
	private JLabel lblShift;
	private JLabel lblLine;
	private JLabel lblProductionType;
	
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnCancel;
	private JButton btnSave;
	private JButton btnInsertProdResult;

	private JPanel panel;
	private JScrollPane scrollPane;

	private JTextField txtProductionCode;
	private JDateChooser dcProductionDate;
	private ComboBox<GroupShift> cbGroupShift;
	private ComboBox<Shift> cbShift;
	private ComboBox<Line> cbLine;
	private ComboBox<ProductionType> cbProductionType;

	private JLabel lblErrorProductionCode;
	private JLabel lblErrorProductionDate;
	private JLabel lblErrorGroupShift;
	private JLabel lblErrorShift;
	private JLabel lblErrorLine;
	private JLabel lblErrorProductionType;

	private ProductionWaste productionWaste;
	private DocumentFilter filter = new UppercaseDocumentFilter();

	private List<GroupShift> listOfGroupShift = null;
	private List<Shift> listOfShift = null;
	private List<Line> listOfLine = null;
	private List<ProductionType> listOfProductionType = null;

	private JLabel lblBreadcrumb;
	private JLabel lblHeader;
	private boolean editMode;

	final int SUPP_TYPE_ID_HASIL_PRODUKSI = 3;
	final String PRODUCTION_TYPE_BARECORE = "Barecore";
	String title = "";
	
	private ProductionWasteCreatePanel parent;

	public ProductionWasteCreatePanel() {

		parent = this;
		productionWaste = new ProductionWaste();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);
		
		
		
		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi > Sisa Produksi 9");
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
			            if(!editMode){
			            	if ("date".equals(e.getPropertyName())) {
			            		 makeCodeNumber(dcProductionDate.getDate());
				            }	
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
		cbLine.setEnabled(false);
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
		productionTypeLoop:
		for(int i = 0; i < listOfProductionType.size(); i++) {
			if(AppConstants.BC_TYPE_9.equalsIgnoreCase(listOfProductionType.get(i).getProductionTypeCode())) {
				cbProductionType.setSelectedIndex(i);
				break productionTypeLoop;
			}
		}
		cbProductionType.setBounds(220, 230, 150, 25);
		panel.add(cbProductionType);

		lblErrorProductionType = new JLabel();
		lblErrorProductionType.setForeground(Color.RED);
		lblErrorProductionType.setBounds(425, 230, 225, 25);
		panel.add(lblErrorProductionType);

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
		
		btnInsertProdResult = new JButton("Input Hasil Produksi");
		btnInsertProdResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopUpInputProductionResult pop = new PopUpInputProductionResult(parent);
				pop.show();
				pop.setLocationRelativeTo(null);
			}
		});
		btnInsertProdResult.setBounds(765, 570, 150, 25);
		panel.add(btnInsertProdResult);

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
		

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(MainPanel.bodyPanel.getSize());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(32);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(32);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		add(scrollPane);

		cbGroupShift.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cbLine.setSelectedItem(cbGroupShift.getDataIndex().getLineDescription());
			}
		});
	}
	
	
	protected void doSave() {
//		productionWaste = new ProductionWaste();
		productionWaste.setPwCode(txtProductionCode.getText());
		productionWaste.setProductionDate(dcProductionDate.getDate());
		productionWaste.setGroupShiftCode(cbGroupShift.getDataIndex().getGroupShiftCode());
		productionWaste.setShiftCode(cbShift.getDataIndex().getShiftCode());
		productionWaste.setLineCode(cbLine.getDataIndex().getLineCode());
		productionWaste.setProductionTypeCode(cbProductionType.getDataIndex().getProductionTypeCode());
		
		try {
			if(editMode){
				ServiceFactory.getProductionWasteBL().update(productionWaste);
				DialogBox.showEdit();
			}
			else {
				productionWaste.setType("9");
				ServiceFactory.getProductionWasteBL().save(productionWaste);
				DialogBox.showInsert();
			}
			
			MainPanel.changePanel("module.productionwaste.ui.ProductionWasteListPanel");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			DialogBox.showErrorException();
		}
	}
	
	
	protected boolean doValidate() {
		boolean isValid = true;

		lblErrorProductionCode.setText("");
		lblErrorGroupShift.setText("");
		lblErrorShift.setText("");
		lblErrorLine.setText("");
		lblErrorProductionType.setText("");
		
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


	public ProductionWaste getProductionWaste() {
		return productionWaste;
	}


	public void setProductionWaste(ProductionWaste productionWaste) {
		this.productionWaste = productionWaste;
	}
	
	
}
