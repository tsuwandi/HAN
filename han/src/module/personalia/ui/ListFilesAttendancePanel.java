package module.personalia.ui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
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
import module.personalia.model.Attendance;
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
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();
						FileInputStream fileInputStream = new FileInputStream(file);
						Workbook workbook = getWorkbook(fileInputStream, file.getName());
						Sheet dataTypeSheet = workbook.getSheetAt(0);
						new TaskProcessFile(file.getName(),dataTypeSheet,dataTypeSheet.getPhysicalNumberOfRows()).execute();
						workbook.close();
						fileInputStream.close();
					} else {
						log.info("Open command cancelled by user.");
					}
				} catch (IOException e1) {
					DialogBox.showError("Proses File Gagal : "+e1.getMessage());
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

		private static final long serialVersionUID = 1L;
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
	
	class TaskProcessFile extends SwingWorker<Void, Integer>{
		String fileName;
		private JProgressBar progressBar;
		private JDialog dialog;
		private int maxSize;
		private Sheet dataTypeSheet;
		public TaskProcessFile(String fileName, Sheet dataTypeSheet, int maxSize) {
			this.fileName=fileName;
			this.maxSize= maxSize;
			this.dataTypeSheet = dataTypeSheet;
			progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setIndeterminate(false);
			progressBar.setBounds(0,0,300,60);
			
			dialog = new JDialog();
			dialog.setSize(300,100);
			dialog.setTitle("Proses File");
			dialog.setLayout(null);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			dialog.add(progressBar);
		}
		@Override
		protected void process(List<Integer> chunks) {
			progressBar.setValue((chunks.get(chunks.size()-1)*100/maxSize));
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			Iterator<Row> iterator = dataTypeSheet.iterator();
			log.info("Opening: " + fileName);
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				publish(currentRow.getRowNum());
				Iterator<Cell> cellIterator = currentRow.iterator();
				Attendance attendance = new Attendance();
				
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if(currentRow.getRowNum()!=0){
						switch (currentCell.getColumnIndex()) {
						case 0:
							attendance.setPin(Integer.valueOf(getCellValues(currentCell).toString().trim()));
							break;
						case 1:
							attendance.setNik(Integer.valueOf(getCellValues(currentCell).toString().trim()));
							break;
						case 2:
							attendance.setEmployeeName(getCellValues(currentCell).toString());
							break;
						case 3:
							attendance.setAttendanceDate(sdf.parse(getCellValues(currentCell).toString()));
							break;
						case 4:
							attendance.setAttendanceTime(getCellValues(currentCell).toString().trim());
							break;
						case 5:
							attendance.setMachineSerialNumber(getCellValues(currentCell).toString().trim());
							break;
						case 6:
							attendance.setMachineName(getCellValues(currentCell).toString().trim());
							break;
						case 7:
							attendance.setVerificationType(getCellValues(currentCell).toString().trim());
							break;
						case 8:
							attendance.setMode(getCellValues(currentCell).toString().trim());
							break;
						case 9:
							attendance.setUpdateMode(getCellValues(currentCell).toString().trim());
							break;
						case 10:
							attendance.setBranchOffice(getCellValues(currentCell).toString().trim());
							break;
						case 11:
							attendance.setDepartment(getCellValues(currentCell).toString().trim());
							break;
						case 12:
							attendance.setEmployeeRole(getCellValues(currentCell).toString().trim());
							break;
						default:
							break;
						}
					}
				}
				if(currentRow.getRowNum()!=0)System.out.println(attendance.toString());
				if(currentRow.getRowNum()!=0)ServiceFactory.getPersonaliaBL().saveAttendance(attendance);
	
			}
			log.info("Finished Reading File");
			return null;
		}
		
		@Override
		protected void done() {
			try {
				get();
				dialog.dispose();
				ImportFingerprint o = new ImportFingerprint();
				o.setFileName(fileName);
				o.setDate(new Date());
				ServiceFactory.getPersonaliaBL().saveImportFingerprint(o);
				importFingerPrints = ServiceFactory.getPersonaliaBL().getImportFingerprints("");
				importFingerPrintTable.setModel(new ImportFingerPrintTableModel(importFingerPrints));
				importFingerPrintTable.updateUI();
				DialogBox.showInfo("Berhasil Memproses File : "+fileName);
			} catch (InterruptedException e) {
				DialogBox.showError("Proses File Gagal : "+e.getMessage());
				log.error(e.getMessage());
				e.printStackTrace();
			} catch (ExecutionException e) {
				DialogBox.showError("Proses File Gagal : "+e.getMessage());
				log.error(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				DialogBox.showError("Proses File Gagal : "+e.getMessage());
				log.error(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}
}
