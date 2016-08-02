package main.component;

import javax.swing.JOptionPane;

public class DialogBox extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public static void showDelete() {
		JOptionPane.showMessageDialog(null, "Berhasil Menghapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showInsert() {
		JOptionPane.showMessageDialog(null, "Berhasil Menyimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showEdit() {
		JOptionPane.showMessageDialog(null, "Berhasil Mengubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
	}

	public static int showDeleteChoice() {
		return JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus data ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);
	}

	public static int showInsertChoice() {
		return JOptionPane.showConfirmDialog(null, "Apakah anda ingin menyimpan data ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);
	}

	public static int showEditChoice() {
		return JOptionPane.showConfirmDialog(null, "Apakah anda ingin mengubah data ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);
	}

	public static void showErrorIsExists() {
		JOptionPane.showMessageDialog(null, "Data sudah ada.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void showErrorException() {
		JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada sistem", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void showError(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static int showCloseChoice() {
		return JOptionPane.showConfirmDialog(null,
				"Data yang belum disimpan akan dihapus, Apakah anda yakin menutup layar ?", "Peringatan",
				JOptionPane.WARNING_MESSAGE);
	}

	public static void showDeleteEmptyChoice() {
		JOptionPane.showMessageDialog(null, "Tidak ada data yang terhapus, silahkan pilih data.", "Peringatan",
				JOptionPane.WARNING_MESSAGE);
	}
}
