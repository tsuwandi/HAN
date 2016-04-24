package main.component;

import javax.swing.JOptionPane;

public class DialogBox extends JOptionPane{
	
	public static void showDelete(){
		JOptionPane.showMessageDialog(null,"Berhasil Menghapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showInsert(){
		JOptionPane.showMessageDialog(null,"Berhasil Menyimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showEdit(){
		JOptionPane.showMessageDialog(null,"Berhasil Menyimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int showDeleteChoice(){
		return JOptionPane.showConfirmDialog(null,"Apakah anda ingin menghapus data ?","Peringatan",JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showInsertChoice(){
		JOptionPane.showConfirmDialog(null,"Apakah anda ingin menyimpan data ?","Peringatan",JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showEditChoice(){
		JOptionPane.showConfirmDialog(null,"Apakah anda ingin mengubah data ?","Peringatan",JOptionPane.WARNING_MESSAGE);
	}
}
