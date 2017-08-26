package module.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.component.DialogBox;
import module.system.model.CommonConfig;

public class CommonConfigDAO {
	PreparedStatement getAllStatement;
	String getAllQuery = "SELECT * FROM common_config";
	Connection con;
	
	public CommonConfigDAO(Connection con){
		this.con = con;
	}
	
	public List<CommonConfig> getAll(){
		List<CommonConfig> commons = new ArrayList<>();
		try {
			getAllStatement = con.prepareStatement(getAllQuery);
			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				CommonConfig c = new CommonConfig();
				c.setId(rs.getInt("id"));
				c.setKeyConfig(rs.getString("key_config"));
				c.setValueConfig(rs.getString("value_config"));
				commons.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			DialogBox.showError(e.getMessage());
		}
		return commons;
	}
}
