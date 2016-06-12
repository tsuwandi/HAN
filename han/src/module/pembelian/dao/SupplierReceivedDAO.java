package module.pembelian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import module.sn.bank.model.Bank;
import module.sn.currency.model.Currency;
import module.sn.supptype.model.SuppType;
import module.supplier.model.Supplier;

public class SupplierReceivedDAO {
	private PreparedStatement getAllStatement;
	private DataSource dataSource;
	
	private String getAllQuery = "select s.id, s.supp_code, s.supp_name, s.pt, s.npwp, "
			+ "s.supp_type_id, s.supp_status, s.default_tax, s.account_no,"
			+ "s.bank_id, s.account_name, s.currency_id, s.top, "
			+ "st.supp_type, b.bank, b.bank_abbr, c.currency, c.currency_abbr from supplier s "
			+ "inner join supp_type st on s.supp_type_id = st.id left join bank b on s.bank_id = b.id "
			+ "left join currency c on s.currency_id = c.id "
			+ "where s.deleted_date is null and st.deleted_date is null "
			+ "and b.deleted_date is null and c.deleted_date is null ";
	
	
	public SupplierReceivedDAO(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
	}

	
	public List<Supplier> getAll() throws SQLException {
		Connection con = null;
		List<Supplier> suppliers = new ArrayList<Supplier>();

		try {
			con = dataSource.getConnection();
			getAllStatement = con.prepareStatement(getAllQuery);

			ResultSet rs = getAllStatement.executeQuery();
			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("id"));
				supplier.setSuppCode(rs.getString("supp_code"));
				supplier.setSuppName(rs.getString("supp_name"));
				supplier.setPt(rs.getString("pt"));
				supplier.setNpwp(rs.getString("npwp"));
				supplier.setSuppTypeId(rs.getInt("supp_type_id"));
				supplier.setSuppStatus(rs.getString("supp_status"));
				supplier.setDefaultTax(rs.getInt("default_tax"));
				supplier.setAccountNo(rs.getString("account_no"));
				supplier.setBankId(rs.getInt("bank_id"));
				supplier.setAccountName(rs.getString("account_name"));
				supplier.setCurrencyId(rs.getInt("currency_id"));
				supplier.setTop(rs.getInt("top"));

				SuppType suppType = new SuppType();
				suppType.setId(rs.getInt("supp_type_id"));
				suppType.setSuppType(rs.getString("supp_type"));

				Bank bank = new Bank();
				bank.setId(rs.getInt("bank_id"));
				bank.setBankAbbr(rs.getString("bank_abbr"));
				bank.setBank(rs.getString("bank"));

				Currency currency = new Currency();
				currency.setId(rs.getInt("currency_id"));
				currency.setCurrencyAbbr(rs.getString("currency_abbr"));
				currency.setCurrency(rs.getString("currency"));

				supplier.setSuppType(suppType);
				supplier.setBank(bank);
				supplier.setCurrency(currency);

				suppliers.add(supplier);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}

		return suppliers;
	}
}
