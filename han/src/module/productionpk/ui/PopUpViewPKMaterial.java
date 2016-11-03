package module.productionpk.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.component.DialogBox;
import main.component.NumberField;
import main.component.TextField;
import module.productionpk.model.ProdPKMaterial;

public class PopUpViewPKMaterial extends JDialog{
	Logger log = LogManager.getLogger(PopUpProdPKMaterial.class.getName());
	private static final long serialVersionUID = 1L;
	private JLabel titleLbl;
	private JLabel klemLbl;
	private JLabel klemGradeALbl;
	private JLabel klemGradeBLbl;
	private JLabel protolLbl;
	private JLabel protolGradeALbl;
	private JLabel protolGradeBLbl;
	private JLabel totalOutputLbl;
	private JLabel totalKlemLbl;
	private JLabel totalProtolLbl;
	private JLabel totalLbl;

	private JLabel errorKlemALbl;
	private JLabel errorKlemBLbl;
	private JLabel errorProtolALbl;
	private JLabel errorProtolBLbl;	

	private NumberField klemGradeAField;
	private NumberField klemGradeBField;
	private NumberField protolGradeAField;
	private NumberField protolGradeBField;
	private TextField totalOutputField;
	private TextField totalKlemField;
	private TextField totalProtolField;

	private JButton saveBtn;
	
	private ViewProductionPKPanel createProductionPKPanel;
	private List<ProdPKMaterial> listProdPK;

	
	static final String KA = "PDC009-3";
	static final String KB = "PDC009-4";
	static final String PA = "PDC009-5";
	static final String PB = "PDC009-6";
	static final String NA = "PDC009";
	static final String NB = "PDC009-2";
	
	public PopUpViewPKMaterial(JPanel parent){
		super((JFrame)parent.getTopLevelAncestor());
		createGUI();
		initData(parent);
		listener();
	}
	private void initData(JPanel parent){
		totalOutputField.setEnabled(false);
		totalKlemField.setEnabled(false);
		totalProtolField.setEnabled(false);
		klemGradeAField.setEnabled(false);
		klemGradeBField.setEnabled(false);
		protolGradeAField.setEnabled(false);
		protolGradeBField.setEnabled(false);
		
		listProdPK = new ArrayList<>();
		
		createProductionPKPanel = (ViewProductionPKPanel) parent;
		if(createProductionPKPanel.getProduction().getListPKMaterial()!=null){
			listProdPK = createProductionPKPanel.getProduction().getListPKMaterial();
			for (ProdPKMaterial prodPKMaterial : listProdPK) {
				if(prodPKMaterial.getProductCode().equals(KA)){
					klemGradeAField.setText(prodPKMaterial.getQty()+"");
				}
				if(prodPKMaterial.getProductCode().equals(KB)){
					klemGradeBField.setText(prodPKMaterial.getQty()+"");
				}
				if(prodPKMaterial.getProductCode().equals(PA)){
					protolGradeAField.setText(prodPKMaterial.getQty()+"");
				}
				if(prodPKMaterial.getProductCode().equals(PB)){
					protolGradeBField.setText(prodPKMaterial.getQty()+"");
				}
				calculateTotal();
			}
		}
	}
	private void createGUI(){
		setLayout(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Input Produksi Bahan Baku Protol Klem");
		setSize(800, 600);

		
		titleLbl = new JLabel("View Material Produksi Protol Klem");
		titleLbl.setBounds(50,10,350,30);
		titleLbl.setFont(new Font("Arial", 1, 18));
		add(titleLbl);
	
		//TODO klem Area
		klemLbl = new JLabel("Repair (Klem)");
		klemLbl.setBounds(40,90,150,20);
		add(klemLbl);
		
		klemGradeALbl = new JLabel("Jumlah Grade A");
		klemGradeALbl.setBounds(40,130,150,20);
		add(klemGradeALbl);
		
		klemGradeAField = new NumberField(7);
		klemGradeAField.setBounds(230,130,150,20);
		add(klemGradeAField);
		
		errorKlemALbl = new JLabel();
		errorKlemALbl.setBounds(385,130,150,20);
		add(errorKlemALbl);
		
		klemGradeBLbl = new JLabel("Jumlah Grade B");
		klemGradeBLbl.setBounds(40,170,150,20);
		add(klemGradeBLbl);
		
		klemGradeBField = new NumberField(7);
		klemGradeBField.setBounds(230,170,150,20);
		add(klemGradeBField);
		
		errorKlemBLbl = new JLabel();
		errorKlemBLbl.setBounds(385,170,150,20);
		add(errorKlemBLbl);
		
		//TODO protol Area
		protolLbl = new JLabel("Repair (protol)");
		protolLbl.setBounds(40,210,150,20);
		add(protolLbl);
		
		protolGradeALbl = new JLabel("Jumlah Grade A");
		protolGradeALbl.setBounds(40,250,150,20);
		add(protolGradeALbl);
		
		protolGradeAField = new NumberField(7);
		protolGradeAField.setBounds(230,250,150,20);
		add(protolGradeAField);
		
		errorProtolALbl = new JLabel();
		errorProtolALbl.setBounds(385,250,150,20);
		add(errorProtolALbl);
		
		protolGradeBLbl = new JLabel("Jumlah Grade B");
		protolGradeBLbl.setBounds(40,290,150,20);
		add(protolGradeBLbl);
		
		protolGradeBField = new NumberField(7);
		protolGradeBField.setBounds(230,290,150,20);
		add(protolGradeBField);
		
		errorProtolBLbl = new JLabel();
		errorProtolBLbl.setBounds(385,290,150,20);
		add(errorProtolBLbl);
		
		//TODO label total
		totalLbl = new JLabel("Total Label");
		totalLbl.setBounds(40,330,150,20);
		add(totalLbl);
		
		
		//TODO totalKlem Area
		totalKlemLbl = new JLabel("Total Repair(Klem)");
		totalKlemLbl.setBounds(40,370,150,20);
		add(totalKlemLbl);
		
		totalKlemField = new TextField();
		totalKlemField.setBounds(230,370,150,20);
		add(totalKlemField);
		
		//TODO totalProtol Area
		totalProtolLbl = new JLabel("Total Repair(Protol)");
		totalProtolLbl.setBounds(40,410,150,20);
		add(totalProtolLbl);
		
		totalProtolField = new TextField();
		totalProtolField.setBounds(230,410,150,20);
		add(totalProtolField);
		
		//TODO totalOutput Area
		totalOutputLbl = new JLabel("Total Output");
		totalOutputLbl.setBounds(40,450,150,20);
		add(totalOutputLbl);
		
		totalOutputField = new TextField();
		totalOutputField.setBounds(230,450,150,20);
		add(totalOutputField);
		
		saveBtn = new JButton("Simpan");
		saveBtn.setBounds(550,490,150,30);
		saveBtn.setFocusable(false);
		saveBtn.setVisible(false);
		add(saveBtn);
		
		
	}
	
	
	private void listener(){
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/*if(DialogBox.showCloseChoice()==JOptionPane.YES_OPTION)*/ dispose();
			}
		});
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveProductMaterial();
			}
		});
		
		klemGradeAField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calculateTotal();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calculateTotal();	
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				calculateTotal();
			}
		});
		klemGradeBField.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						calculateTotal();
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						calculateTotal();	
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						calculateTotal();
					}
		});
		protolGradeAField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calculateTotal();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calculateTotal();	
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				calculateTotal();
			}
		});
		protolGradeBField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				calculateTotal();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				calculateTotal();	
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				calculateTotal();
			}
		});
	}

	private void calculateTotal(){
		double totalOutput=0;
		double totalKlem=0;
		double totalProtol=0;
		double klemA = klemGradeAField.getText().equals("")?0:Double.valueOf(klemGradeAField.getText());
		double klemB = klemGradeBField.getText().equals("")?0:Double.valueOf(klemGradeBField.getText());
		double protolA = protolGradeAField.getText().equals("")?0:Double.valueOf(protolGradeAField.getText());
		double protolB = protolGradeBField.getText().equals("")?0:Double.valueOf(protolGradeBField.getText());
		
		totalKlem+=klemA+klemB;
		totalProtol+=protolA+protolB;
		totalOutput+=totalKlem+totalProtol;

		totalOutputField.setText(totalOutput+"");
		totalKlemField.setText(totalKlem+"");
		totalProtolField.setText(totalProtol+"");

	}
	
	private void saveProductMaterial(){
		if(DialogBox.showInsertChoice()==JOptionPane.YES_OPTION){
			double klemA = klemGradeAField.getText().equals("")?0:Double.valueOf(klemGradeAField.getText());
			double klemB = klemGradeBField.getText().equals("")?0:Double.valueOf(klemGradeBField.getText());
			double protolA = protolGradeAField.getText().equals("")?0:Double.valueOf(protolGradeAField.getText());
			double protolB = protolGradeBField.getText().equals("")?0:Double.valueOf(protolGradeBField.getText());
			if(listProdPK.size()>0){
				for (ProdPKMaterial prodPKMaterial : listProdPK) {
					if(prodPKMaterial.getProductCode().equals(KA)){
						prodPKMaterial.setQty(klemA);
					}
					if(prodPKMaterial.getProductCode().equals(KB)){
						prodPKMaterial.setQty(klemB);
					}
					if(prodPKMaterial.getProductCode().equals(PA)){
						prodPKMaterial.setQty(protolA);
					}
					if(prodPKMaterial.getProductCode().equals(PB)){
						prodPKMaterial.setQty(protolB);
					}
				}
			}else{
				for(int i=0;i<4;i++){
					ProdPKMaterial prodPK = new ProdPKMaterial();
					prodPK.setProdPKCode(createProductionPKPanel.getProduction().getProdPKCode());
					if(i==0){
						prodPK.setProductCode(KA);
						prodPK.setQty(Double.valueOf(klemA));
					}
					if(i==1){
						prodPK.setProductCode(KB);
						prodPK.setQty(Double.valueOf(klemB));
					}
					if(i==2){
						prodPK.setProductCode(PA);
						prodPK.setQty(Double.valueOf(protolA));
					}
					if(i==3){
						prodPK.setProductCode(PB);
						prodPK.setQty(Double.valueOf(protolB));
					}
					listProdPK.add(prodPK);
				}
			}
			DialogBox.showInsert();
			dispose();
		}
	}
}
