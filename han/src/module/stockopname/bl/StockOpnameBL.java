package module.stockopname.bl;

import javax.sql.DataSource;

import com.mysql.jdbc.Connection;

import main.component.DialogBox;

public class StockOpnameBL {
	private DataSource dataSource;
	
	public StockOpnameBL(DataSource dataSource){
		this.dataSource = dataSource;
		Connection con;
		try {
			
		} catch (Exception e) {
			e.getMessage();
			DialogBox.showError("Koneksi Error Stock Opname BL");
		}
	}
	
}
