package main.component;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import module.util.ComboBoxProperties;

public class ComboBox<E> extends JComboBox<Object> {
	
	private static final long serialVersionUID = 1L;
	
	private List<E> list;
	
	public List<E> getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List<E> list) {
		this.list = list;
		setModel(makeModel());
	}

	public E getDataIndex(){
		return list.get(getSelectedIndex());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DefaultComboBoxModel makeModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (E data : list) {
			if(data instanceof ComboBoxProperties) model.addElement(((ComboBoxProperties) data).getField());
			else model.addElement(data);
		}
        return model;
    }
}
