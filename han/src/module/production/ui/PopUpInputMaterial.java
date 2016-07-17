package module.production.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PopUpInputMaterial extends JDialog{
	
	private JLabel titleLbl;
	private JLabel palletCardCodeLbl;
	private JLabel lengthLbl;
	private JLabel widthLbl;
	private JLabel thickLbl;
	private JLabel logLbl;
	private JLabel volumeLbl;
	private JLabel materialLbl;
	private JLabel totalPalletCardLbl;
	private JLabel totalLogLbl;
	private JLabel totalVolumeLbl;
	
	private JLabel uomLengthLbl;
	private JLabel uomWidthLbl;
	private JLabel uomThickLbl;
	private JLabel uomLogLbl;
	private JLabel uomVolumeLbl;
	private JLabel uomPalletCardLbl;
	private JLabel uomTotalLogLbl;
	private JLabel uomTotalVolumeLbl;
	private JLabel firstSeparatorLbl;
	private JLabel secondSeparatorLbl;
	private JLabel thirdSeparator;
	
	private JTextField ritNoField;
	private JTextField dateField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField sequenceField;
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField thickField;
	private JTextField logField;
	private JTextField volumeField;
	private JTextField palletCardField;
	private JTextField totalLogField;
	private JTextField totalVolumeield;
	
	private JButton searchPalletCardBtn;
	private JButton addBtn;
	private JButton saveBtn;
	
	private JTable materialTable;
	
	
	public PopUpInputMaterial(){
		setLayout(null);
		setTitle("Input Bahan Baku");
		setSize(800, 750);
	}
}
