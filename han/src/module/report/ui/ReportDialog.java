package module.report.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.DataSourceFactory;
import controller.ServiceFactory;
import main.component.ComboBox;
import main.component.DialogBox;
import module.pembelian.model.Grade;
import module.pembelian.model.Thickness;
import module.pembelian.model.WoodType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JPanel panel;

	JLabel lblWoodType;
	JLabel lblGrade;
	JLabel lblThickness;

	ComboBox<WoodType> cbWoodType;
	ComboBox<Grade> cbGrade;
	ComboBox<Thickness> cbThickness;

	List<WoodType> listOfWoodType;
	List<Grade> listOfGrade;
	List<Thickness> listOfThickness;

	JButton btnView;

	public ReportDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 203);
		getContentPane().setLayout(null);

		lblWoodType = new JLabel("Jenis Kayu");
		lblWoodType.setBounds(25, 15, 150, 30);
		getContentPane().add(lblWoodType);

		listOfWoodType = new ArrayList<WoodType>();
		try {
			//listOfWoodType = ServiceFactory.getReportBL().getAllWoodType();
			listOfWoodType.add(0, new WoodType("-- Pilih Tipe Kayu --"));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		cbWoodType = new ComboBox<WoodType>();
		cbWoodType.setList(listOfWoodType);
		cbWoodType.setBounds(150, 15, 150, 30);
		getContentPane().add(cbWoodType);

		lblGrade = new JLabel("Grade");
		lblGrade.setBounds(25, 50, 150, 30);
		getContentPane().add(lblGrade);

		listOfGrade = new ArrayList<Grade>();
		try {
			//listOfGrade = ServiceFactory.getReportBL().getAllGrade();
			listOfGrade.add(0, new Grade("-- Pilih Grade --"));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		cbGrade = new ComboBox<Grade>();
		cbGrade.setList(listOfGrade);
		cbGrade.setBounds(150, 50, 150, 30);
		getContentPane().add(cbGrade);

		lblThickness = new JLabel("Tebal");
		lblThickness.setBounds(25, 85, 150, 30);
		getContentPane().add(lblThickness);

		listOfThickness = new ArrayList<Thickness>();
		try {
			//listOfThickness = ServiceFactory.getReportBL().getAllThickness();
			listOfThickness.add(0, new Thickness("-- Pilih Tebal --"));
		} catch (Exception e1) {
			e1.printStackTrace();
			DialogBox.showErrorException();
		}

		cbThickness = new ComboBox<Thickness>();
		cbThickness.setList(listOfThickness);
		cbThickness.setBounds(150, 85, 150, 30);
		getContentPane().add(cbThickness);

		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.sql.Connection conn = DataSourceFactory.getDataSource().getConnection();
					JasperDesign jDesign = JRXmlLoader.load("src/module/report/StockBalkenBasahReport.jrxml");
					String sql = "SELECT * FROM received order by id desc";
					JRDesignQuery jDesignQuery = new JRDesignQuery();
					jDesignQuery.setText(sql);
					jDesign.setQuery(jDesignQuery);
					JasperReport jreprt = JasperCompileManager.compileReport(jDesign);
					JasperPrint jprintt = JasperFillManager.fillReport(jreprt, null, conn);
					JasperViewer.viewReport(jprintt, false);
					
					setVisible(false);

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Gagal Membuka Laporan", "Cetak Laporan",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnView.setBounds(200, 123, 100, 30);
		getContentPane().add(btnView);

	}

}
