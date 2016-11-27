package module.productionwaste.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
import main.component.NumberField;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.production.model.GroupShift;
import module.production.model.Line;
import module.production.model.ProductionType;
import module.production.model.Shift;
import module.productionwaste.model.PWProduct;
import module.productionwaste.model.ProductionWaste;
import module.util.Bridging;
import module.util.JTextFieldLimit;

public class ProductionWasteViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	@Override
	public void invokeObjects(Object... objects) {
		this.productionWaste = (ProductionWaste) objects[0];

		loadData(productionWaste.getId());
	}

	protected void loadData(Integer pwId) {
		try {
			productionWaste = ServiceFactory.getProductionWasteBL().getProductionWasteById(pwId);
			listOfPWProduct = ServiceFactory.getProductionWasteBL().getPWProductByPwCode(productionWaste.getPwCode());

			if (productionWaste != null) {
				txtProductionCode.setText(productionWaste.getPwCode());
				dcProductionDate.setDate(productionWaste.getProductionDate());
				cbGroupShift.setSelectedItem(productionWaste.getGroupShift().getDescription());
				cbShift.setSelectedItem(productionWaste.getShift().getShiftName());
				cbLine.setSelectedItem(productionWaste.getLine().getDescription());
				cbProductionType.setSelectedItem(productionWaste.getProductionType().getProductionType());

				for (PWProduct pwProduct : listOfPWProduct) {
					if (PRODUCT_CODE_KLEM_A.equals(pwProduct.getProductCode()))
						txtRepairKlemTotalGradeA.setText(String.valueOf(pwProduct.getQty()));
					else if (PRODUCT_CODE_KLEM_B.equals(pwProduct.getProductCode()))
						txtRepairKlemTotalGradeB.setText(String.valueOf(pwProduct.getQty()));
					else if (PRODUCT_CODE_PROTOL_A.equals(pwProduct.getProductCode()))
						txtRepairProtolTotalGradeA.setText(String.valueOf(pwProduct.getQty()));
					else if (PRODUCT_CODE_PROTOL_B.equals(pwProduct.getProductCode()))
						txtRepairProtolTotalGradeB.setText(String.valueOf(pwProduct.getQty()));
					else if (PRODUCT_CODE_NORMAL_A.equals(pwProduct.getProductCode()))
						txtRepairNormalTotalGradeA.setText(String.valueOf(pwProduct.getQty()));
					else if (PRODUCT_CODE_NORMAL_B.equals(pwProduct.getProductCode()))
						txtRepairNormalTotalGradeB.setText(String.valueOf(pwProduct.getQty()));
				}
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	private static final Logger LOGGER = Logger.getLogger(ProductionWasteViewPanel.class);

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
	JButton btnPrint;
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

	public ProductionWasteViewPanel() {
		productionWaste = new ProductionWaste();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		lblBreadcrumb = new JLabel("ERP > Pembelian > Input Hasil Produksi > Sisa Produksi");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 414, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("VIEW DETAIL");
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

		dcProductionDate = new JDateChooser();
		dcProductionDate.setBounds(220, 110, 150, 25);
		dcProductionDate.setDateFormatString("dd-MM-yyyy");
		dcProductionDate.setEnabled(false);
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
		cbGroupShift.setEnabled(false);
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
		cbShift.setEnabled(false);
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
		cbLine.setEnabled(false);
		panel.add(cbLine);

		lblErrorLine = new JLabel();
		lblErrorLine.setForeground(Color.RED);
		lblErrorLine.setBounds(425, 200, 225, 25);
		panel.add(lblErrorLine);

		lblProductionType = new JLabel("<html>Tipe Produksi <font color=\"red\">*</font></html>");
		lblProductionType.setBounds(50, 230, 150, 25);
		panel.add(lblProductionType);

		listOfProductionType = new ArrayList<ProductionType>();
		try {
			listOfProductionType = ServiceFactory.getProductionWasteBL().getAllProductionType();
			listOfProductionType.add(0, new ProductionType("-- Pilih Tipe Produksi --"));
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}

		cbProductionType = new ComboBox<ProductionType>();
		cbProductionType.setList(listOfProductionType);
		cbProductionType.setBounds(220, 230, 150, 25);
		cbProductionType.setEnabled(false);
		panel.add(cbProductionType);

		lblErrorProductionType = new JLabel();
		lblErrorProductionType.setForeground(Color.RED);
		lblErrorProductionType.setBounds(425, 230, 225, 25);
		panel.add(lblErrorProductionType);

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
		txtRepairKlemTotalGradeA.setEnabled(false);
		panel.add(txtRepairKlemTotalGradeA);

		txtRepairKlemTotalGradeB = new NumberField(3);
		txtRepairKlemTotalGradeB.setBounds(220, 320, 150, 25);
		txtRepairKlemTotalGradeB.setEnabled(false);
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
		txtRepairProtolTotalGradeA.setEnabled(false);
		panel.add(txtRepairProtolTotalGradeA);

		txtRepairProtolTotalGradeB = new NumberField(3);
		txtRepairProtolTotalGradeB.setBounds(220, 410, 150, 25);
		txtRepairProtolTotalGradeB.setEnabled(false);
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
		txtRepairNormalTotalGradeA.setEnabled(false);
		panel.add(txtRepairNormalTotalGradeA);

		txtRepairNormalTotalGradeB = new NumberField(3);
		txtRepairNormalTotalGradeB.setBounds(220, 500, 150, 25);
		txtRepairNormalTotalGradeB.setEnabled(false);
		panel.add(txtRepairNormalTotalGradeB);

		btnSave = new JButton("Ubah");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.productionwaste.ui.ProductionWasteEditPanel", productionWaste);
			}
		});
		btnSave.setBounds(925, 570, 100, 25);
		panel.add(btnSave);
		
		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//doPrint();
			}
		});
		btnPrint.setBounds(715, 570, 100, 25);
		panel.add(btnPrint);
		
		btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = DialogBox.showDeleteChoice();
				if (response == JOptionPane.YES_OPTION) {
					doDelete();
				}
			}
		});
		btnDelete.setBounds(820, 570, 100, 25);
		panel.add(btnDelete);

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

	}
	
	protected void doDelete() {
		try {
			ServiceFactory.getProductionWasteBL().deleteAll(productionWaste);
			DialogBox.showDelete();
			MainPanel.changePanel("module.productionwaste.ui.ProductionWasteListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	private static final String PRODUCT_CODE_KLEM_A = "PDC009-3";
	private static final String PRODUCT_CODE_KLEM_B = "PDC009-4";
	private static final String PRODUCT_CODE_PROTOL_A = "PDC009-5";
	private static final String PRODUCT_CODE_PROTOL_B = "PDC009-6";
	private static final String PRODUCT_CODE_NORMAL_A = "PDC009";
	private static final String PRODUCT_CODE_NORMAL_B = "PDC009-2";

	
}
