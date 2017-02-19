package module.productionwaste.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.model.ProductionWaste;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class BigProductionWasteViewPanel extends JPanel implements Bridging {

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
			dcProductionDate.setEnabled(false);
			cbGroupShift.setEnabled(false);
			cbShift.setEnabled(false);
			cbLine.setEnabled(false);
			cbProductionType.setEnabled(false);
		}
		
		System.out.println("productionWaste.getProductionType()"+productionWaste.getProductionType());
	}

	private static final Logger LOGGER = Logger.getLogger(BigProductionWasteViewPanel.class);

	private JLabel lblProductionCode;
	private JLabel lblProductionDate;
	private JLabel lblGroupShift;
	private JLabel lblShift;
	private JLabel lblLine;
	private JLabel lblProductionType;
	
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
	

	final int SUPP_TYPE_ID_HASIL_PRODUKSI = 3;
	final String PRODUCTION_TYPE_BARECORE = "Barecore";
	
	private BigProductionWasteViewPanel parent;

	public BigProductionWasteViewPanel() {
		parent = this;
		productionWaste = new ProductionWaste();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > View Hasil Produksi > Sisa Produksi 13");
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

		btnSave = new JButton("Edit");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.productionwaste.ui.BigProductionWasteCreatePanel",productionWaste);
			}
		});
		btnSave.setBounds(925, 570, 100, 25);
		panel.add(btnSave);
		
		btnInsertProdResult = new JButton("View Hasil Produksi");
		btnInsertProdResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopUpViewBigProductionResult pop = new PopUpViewBigProductionResult(parent);
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
			}
		});
		btnInsertProdResult.setBounds(765, 570, 150, 25);
		panel.add(btnInsertProdResult);
		
		btnDelete = new JButton("Hapus");
		btnDelete.setBounds(605,570,150,25);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = DialogBox.showDeleteChoice();
				if (response == JOptionPane.YES_OPTION){
					try {
						ServiceFactory.getProductionWasteBL().deleteAll(productionWaste);
						LOGGER.info("Success Deleting Production Waste "+productionWaste.getPwCode());
						MainPanel.changePanel("module.productionwaste.ui.BigProductionWasteListPanel");
					} catch (SQLException e1) {
						LOGGER.error(e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnDelete);
		
		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 MainPanel.changePanel("module.productionwaste.ui.BigProductionWasteListPanel");
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
	
	


	public ProductionWaste getProductionWaste() {
		return productionWaste;
	}


	public void setProductionWaste(ProductionWaste productionWaste) {
		this.productionWaste = productionWaste;
	}
	
	
}
