package module.sn.woodtype.ui;

import java.awt.Color;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import main.component.UppercaseDocumentFilter;
import main.panel.MainPanel;
import module.sn.woodtype.model.WoodType;
import module.util.Bridging;
import module.util.JTextFieldLimit;
import module.sn.woodgenus.model.WoodGenus;

public class WoodTypeViewPanel extends JPanel implements Bridging {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(WoodTypeViewPanel.class);

	private WoodType woodType;

	JLabel lblWoodTypeCode;
	JLabel lblWoodType;
	JLabel lblWoodGenus;
	
	JTextField txtWoodTypeCode;
	JTextField txtWoodType;
	ComboBox<WoodGenus> cbWoodGenus;
	
	JLabel lblBreadcrumb;
	JLabel lblHeader;

	JButton btnCancel;
	JButton btnPrint;
	JButton btnDelete;
	JButton btnEdit;

	JLabel lblErrorWoodTypeCode;
	JLabel lblErrorWoodType;
	JLabel lblErrorWoodGenus;
	
	JPanel panel;
	JScrollPane scrollPane;
	
	List<WoodGenus> listOfWoodGenus;

	
	public WoodTypeViewPanel() {
		woodType = new WoodType();

		DocumentFilter filter = new UppercaseDocumentFilter();

		setLayout(null);
		panel = new JPanel();
		panel.setPreferredSize(getPreferredSize());
		panel.setLayout(null);

		lblWoodTypeCode = new JLabel("<html>Kode Jenis Kayu <font color=\"red\">*</font></html>");
		lblWoodTypeCode.setBounds(50, 80, 150, 25);
		panel.add(lblWoodTypeCode);

		txtWoodTypeCode = new JTextField();
		txtWoodTypeCode.setBounds(220, 80, 150, 25);
		txtWoodTypeCode.setDocument(new JTextFieldLimit(9));
		txtWoodTypeCode.setEnabled(false);
		((AbstractDocument) txtWoodTypeCode.getDocument()).setDocumentFilter(filter);
		panel.add(txtWoodTypeCode);

		lblErrorWoodTypeCode = new JLabel("");
		lblErrorWoodTypeCode.setForeground(Color.RED);
		lblErrorWoodTypeCode.setBounds(425, 80, 225, 25);
		panel.add(lblErrorWoodTypeCode);

		lblWoodType = new JLabel("<html>Jenis Kayu <font color=\"red\">*</font></html>");
		lblWoodType.setBounds(50, 110, 150, 25);
		panel.add(lblWoodType);

		txtWoodType = new JTextField();
		txtWoodType.setBounds(220, 110, 150, 25);
		txtWoodType.setDocument(new JTextFieldLimit(50));
		txtWoodType.setEnabled(false);
		((AbstractDocument) txtWoodType.getDocument()).setDocumentFilter(filter);
		panel.add(txtWoodType);

		lblErrorWoodType = new JLabel();
		lblErrorWoodType.setForeground(Color.RED);
		lblErrorWoodType.setBounds(425, 110, 225, 25);
		panel.add(lblErrorWoodType);
		
		lblWoodGenus = new JLabel("<html>Nama Latin Kayu <font color=\"red\">*</font></html>");
		lblWoodGenus.setBounds(50, 140, 150, 25);
		panel.add(lblWoodGenus);
		
		listOfWoodGenus = new ArrayList<WoodGenus>();
		listOfWoodGenus.add(0, new WoodGenus("-- Pilih Nama Latin Kayu --"));
	
		cbWoodGenus = new ComboBox<WoodGenus>();
		cbWoodGenus.setList(listOfWoodGenus);
		cbWoodGenus.setBounds(220, 140, 150, 25);
		cbWoodGenus.setEnabled(false);
		panel.add(cbWoodGenus);

		lblBreadcrumb = new JLabel("ERP > Konfigurasi > Jenis Kayu");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 25);
		panel.add(lblBreadcrumb);

		lblHeader = new JLabel("Buat Baru");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 25);
		panel.add(lblHeader);

		btnPrint = new JButton("Cetak");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPrint();
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

		btnEdit = new JButton("Ubah");
		btnEdit.setBounds(925, 570, 100, 25);
		panel.add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.changePanel("module.sn.woodtype.ui.WoodTypeEditPanel", woodType);
			}
		});

		btnCancel = new JButton("Kembali");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPanel.changePanel("module.sn.woodtype.ui.WoodTypeListPanel");
			}
		});
		btnCancel.setBounds(50, 570, 100, 25);
		btnCancel.setFocusable(false);
		panel.add(btnCancel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtWoodType.requestFocusInWindow();
			}
		});
		
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
	
	private void doPrint() {
		// TODO Auto-generated method stub
	}

	protected void doDelete() {
		try {
			ServiceFactory.getWoodTypeBL().delete(woodType);
			DialogBox.showDelete();
			MainPanel.changePanel("module.sn.woodtype.ui.WoodTypeListPanel");
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	protected void loadData(Integer woodTypeId) {
		try {
			woodType = ServiceFactory.getWoodTypeBL().getWoodTypeById(woodTypeId);
			
			if (woodType != null) {
				txtWoodTypeCode.setText(String.valueOf(woodType.getId()));
				txtWoodType.setText(woodType.getWoodType());
				cbWoodGenus.addItem(woodType.getWoodGenus().getWoodGenus());
				cbWoodGenus.setSelectedIndex(1);
			}
		} catch (SQLException e1) {
			LOGGER.error(e1.getMessage());
			DialogBox.showErrorException();
		}
	}

	@Override
	public void invokeObjects(Object... objects) {
		this.woodType = (WoodType) objects[0];

		loadData(woodType.getId());
	}
}
