package main.component;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class PagingPanel<T> extends JPanel{
	private TextField pageNumber;
	private JButton nextBtn;
	private JButton prevBtn;
	private int width;
	private int height;
	private List<T> data;
	private AbstractTableModel tableModel;
	private JTable table;
	private int page;
	private int maxDataPerPage;
	
	public PagingPanel(){
		
	}
	public PagingPanel(List<T> data,AbstractTableModel tableModel,JTable table,int page, int maxDataPerPage){
		this.data = data;
		this.tableModel = tableModel;
		this.table = table;
		this.maxDataPerPage = maxDataPerPage;
		this.page = page;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		createGUI();
	}
	private void createGUI(){
		width = getWidth()/3;
		height = getHeight();
	
		prevBtn = new JButton("<");
		prevBtn.setBounds(0,0,width,height);
		add(prevBtn);
		
		pageNumber = new TextField();
		pageNumber.setBounds(width,0,width,height);
		add(pageNumber);
		
		nextBtn = new JButton(">");
		nextBtn.setBounds(width*2,0,width,height);
		add(nextBtn);
	}
	
	private void calculatePage(){
		if(data.size()!=0){
			int maxPage = (int)Math.ceil((double)data.size()/(double)maxDataPerPage);
			int startIndex = (page-1)*maxDataPerPage;
			int toIndex = (page*maxDataPerPage)-1;
			int lastIndexPage = toIndex>data.size() ? data.size() : toIndex;
			data.subList(startIndex,lastIndexPage);
		}
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public AbstractTableModel getTableModel() {
		return tableModel;
	}
	public void setTableModel(AbstractTableModel tableModel) {
		this.tableModel = tableModel;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxDataPerPage() {
		return maxDataPerPage;
	}
	public void setMaxDataPerPage(int maxDataPerPage) {
		this.maxDataPerPage = maxDataPerPage;
	}
	
	
	
	
}
