package main.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import module.util.Pagination;

public class PagingPanel<T> extends JPanel{
	private TextField pageNumber;
	private JButton nextBtn;
	private JButton prevBtn;
	private int width;
	private int height;
	private List<T> data;
	private List<T> subListData;
	private AbstractTableModel tableModel;
	private JTable table;
	private int page;
	private int maxDataPerPage;
	private int maxPage;
	
	
	public PagingPanel(){
		setLayout(null);
	}
	public PagingPanel(List<T> data,AbstractTableModel tableModel,JTable table,int page, int maxDataPerPage){
		this.data = data;
		this.tableModel = tableModel;
		this.table = table;
		this.maxDataPerPage = maxDataPerPage;
		this.page = page;
		maxPage = (int)Math.ceil((double)data.size()/(double)maxDataPerPage);
		setLayout(null);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		createGUI();
		listener();
		calculatePage();
	}
	private void createGUI(){
		width = getWidth()/3;
		height = getHeight();
	
		prevBtn = new JButton("¢¸");
		prevBtn.setBounds(0,0,width,height);
		add(prevBtn);
		
		pageNumber = new TextField();
		pageNumber.setBounds(width,0,width,height);
		pageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		add(pageNumber);
		
		nextBtn = new JButton("¢º");
		nextBtn.setBounds(width*2,0,width,height);
		add(nextBtn);
	}
	
	private void calculatePage(){
		subListData = new ArrayList<>();
		if(data.size()!=0){
			int startIndex = (page-1)*maxDataPerPage;
			int toIndex = (page*maxDataPerPage);
			int lastIndexPage = toIndex>data.size() ? data.size() : toIndex;
			pageNumber.setText(page+"/"+maxPage);
			subListData =  data.subList(startIndex,lastIndexPage);
			if(tableModel instanceof Pagination)((Pagination) tableModel).setList(subListData);
			table.setModel(tableModel);
			table.updateUI();
		}
	}
	
	private void listener(){
		prevBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(page!=1){
					page-=1;
					calculatePage();
				}
			}
		});
		
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(page<maxPage){
					page += 1;
					calculatePage();
				}
			}
		});
	}
	
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		maxPage = (int)Math.ceil((double)data.size()/(double)maxDataPerPage);
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
	public List<T> getSubListData() {
		return subListData;
	}
	public void setSubListData(List<T> subListData) {
		this.subListData = subListData;
	}
	
}