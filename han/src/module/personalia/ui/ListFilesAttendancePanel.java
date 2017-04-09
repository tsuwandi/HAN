package module.personalia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.extractor.OldExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controller.ServiceFactory;
import main.component.DialogBox;
import main.component.PagingPanel;
import main.component.TextField;
import main.panel.MainPanel;
import model.User;
import module.personalia.model.ImportFingerprint;
import module.util.Pagination;

public class ListFilesAttendancePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Logger log = LogManager.getLogger(ListFilesAttendancePanel.class.getName());
	private JButton searchBtn;
	private TextField searchField;
	JTable importFingerPrintTable;
	private JScrollPane scrollPane;

	private JButton advancedSearchBtn;
	private JButton importBtn;
	private PagingPanel<ImportFingerprint> pagingPanel;


	ImportFingerPrintTableModel receivedTableModel;
	List<ImportFingerprint> importFingerPrints;
	ListFilesAttendancePanel listFilesAttendancePanel;
	public ListFilesAttendancePanel() {
		setLayout(null);
		listFilesAttendancePanel = this;

		JLabel lblBreadcrumb = new JLabel("ERP > Import Finger Print Data");
		lblBreadcrumb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBreadcrumb.setBounds(50, 10, 320, 30);
		add(lblBreadcrumb);

		JLabel lblHeader = new JLabel("IMPORT FINGER PRINT");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHeader.setBounds(50, 45, 320, 30);
		add(lblHeader);

		searchBtn = new JButton("Cari");
		searchBtn.setBounds(950,130,100,30);
		add(searchBtn);

		searchField = new TextField();
		searchField.setBounds(800, 131, 150, 28);
		add(searchField);

		importBtn = new JButton("Import");
		importBtn.setBounds(750,80,150,30);
		add(importBtn);

		advancedSearchBtn = new JButton("Pencarian Lanjut");
		advancedSearchBtn.setBounds(900,80,150,30);
		add(advancedSearchBtn);

		importFingerPrints = new ArrayList<>();
		receivedTableModel = new ImportFingerPrintTableModel(importFingerPrints);
		importFingerPrintTable = new JTable(receivedTableModel);
		importFingerPrintTable.setFocusable(false);

		scrollPane =  new JScrollPane(importFingerPrintTable);
		scrollPane.setBounds(50,200,1000,300);
		add(scrollPane);

		pagingPanel =new PagingPanel<>();
		add(pagingPanel);
		importFingerPrintTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(importFingerPrintTable.columnAtPoint(e.getPoint())==7)
					MainPanel.changePanel("module.pembelian.ui.ViewReceivedDetailPanel", pagingPanel.getSubListData().get(importFingerPrintTable.getSelectedRow()));
			}
		});

		try {
			importFingerPrints = ServiceFactory.getPersonaliaBL().getImportFingerprints("");
			importFingerPrintTable.setModel(new ImportFingerPrintTableModel(importFingerPrints));
			importFingerPrintTable.updateUI();

			pagingPanel.setPage(1);
			pagingPanel.setMaxDataPerPage(20);
			pagingPanel.setData(importFingerPrints);
			pagingPanel.setTable(importFingerPrintTable);
			pagingPanel.setTableModel(receivedTableModel);
			pagingPanel.setBounds(450,510,130,50);


//			setTableSize();
		} catch (Exception e1) {
			DialogBox.showError(e1.getMessage());
			log.error(e1.getMessage());
			e1.printStackTrace();
		}

		advancedSearchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//				PopUpAdvanceSearch pop = new PopUpAdvanceSearch(listFilesAttendancePanel);
				//				pop.show();
				//				pop.setLocationRelativeTo(null);
				//				pop.setModal(true);
			}
		});

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					importFingerPrints = ServiceFactory.getPersonaliaBL().getImportFingerprints("");
					importFingerPrintTable.setModel(new ImportFingerPrintTableModel(importFingerPrints));
					importFingerPrintTable.updateUI();
//					setTableSize();
				} catch (Exception e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}

			}
		});

		importBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("xls files (*.xls)", "xls");
				chooser.addChoosableFileFilter(xmlFilter);
				chooser.setFileFilter(xmlFilter);
				try {
					int returnVal = chooser.showOpenDialog(listFilesAttendancePanel);
//					OldExcelExtractor old = new OldExcelExtractor(input)
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						FileInputStream fileInputStream = new FileInputStream(chooser.getSelectedFile());
						Workbook workbook = getWorkbook(fileInputStream, chooser.getSelectedFile().getName());
						Sheet datatypeSheet = workbook.getSheetAt(0);
				        Iterator<Row> iterator = datatypeSheet.iterator();
				        while (iterator.hasNext()) {

			                Row currentRow = iterator.next();
			                Iterator<Cell> cellIterator = currentRow.iterator();

			                while (cellIterator.hasNext()) {

			                    Cell currentCell = cellIterator.next();
			                    System.out.println(getCellValues(currentCell));
			                }
			                System.out.println();

			            }
						log.info("Opening: " + chooser.getSelectedFile());
					} else {
						log.info("Open command cancelled by user.");
					}
				} catch (IOException e1) {
					DialogBox.showError(e1.getMessage());
					log.error(e1.getMessage());
					e1.printStackTrace();
				}

			}
		});

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				searchField.requestFocusInWindow();
			}
		});

	}

	private Workbook getWorkbook(FileInputStream inputStream,String excelFilePath)
			throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
	
	private Object getCellValues(Cell cell) {
	    switch (cell.getCellTypeEnum()) {
	    case STRING:
	        return cell.getStringCellValue();
	 
	    case BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case NUMERIC:
	        return cell.getNumericCellValue();
	        
	    case _NONE:
	    	return null;
	    case BLANK:
	    	return null;
	    case ERROR:
	    	return null;
	    case FORMULA:
	    	return null;
	    }
	 
	    return null;
	}

	public void setTableSize(){
		importFingerPrintTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		importFingerPrintTable.getTableHeader().setResizingAllowed(false);
		TableColumn column1 = importFingerPrintTable.getColumnModel().getColumn(0);
		TableColumn column2 = importFingerPrintTable.getColumnModel().getColumn(1);
		TableColumn column3 = importFingerPrintTable.getColumnModel().getColumn(2);
		TableColumn column4 = importFingerPrintTable.getColumnModel().getColumn(3);


		column1.setPreferredWidth(0);
		column1.setMinWidth(0);
		column1.setMaxWidth(0);

		column2.setPreferredWidth(200);
		column2.setMinWidth(200);
		column2.setMaxWidth(200);

		column3.setPreferredWidth(150);
		column3.setMinWidth(150);
		column3.setMaxWidth(150);

		column4.setPreferredWidth(150);
		column4.setMinWidth(150);
		column4.setMaxWidth(150);


	}

	public class ImportFingerPrintTableModel extends AbstractTableModel implements Pagination {
		private List<ImportFingerprint> importFingerPrints;

		public ImportFingerPrintTableModel(List<ImportFingerprint> importFingerPrint) {
			this.importFingerPrints = importFingerPrint;
		}

		/**
		 * Method to get row count
		 * @return int
		 */
		public int getRowCount() {
			return importFingerPrints.size();
		}

		/**
		 * Method to get Column Count
		 */
		public int getColumnCount() {
			return 3;
		}

		/**
		 * Method to get selected value
		 * @param rowIndex rowIndex of selected table
		 * @param columnIndex columnIndex of selected table 
		 * @return ({@link User}) Object 
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			ImportFingerprint p = importFingerPrints.get(rowIndex);
			switch(columnIndex){
			case 0 :
				return p.getId();
			case 1 : 
				return new SimpleDateFormat("dd-MM-yyyy").format(p.getDate());
			case 2 :
				return p.getFileName();
			default :
				return "";
			}
		}
		public boolean isCellEditable(int row, int column) {
			return false;
		}


		/**
		 * Method to getColumnName
		 * @param column columnIndex
		 * @return String column name
		 */
		public String getColumnName(int column) {
			switch(column){
			case 0 : 
				return "ID";
			case 1 :
				return "Tanggal Absensi";
			case 2 :
				return "Nama File";
			default :
				return "";
			}
		}

		@Override
		public <T> void setList(List<T> list) {
			importFingerPrints = (List<ImportFingerprint>) list;
		}

	}
}
