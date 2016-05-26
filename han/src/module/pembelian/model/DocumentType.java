package module.pembelian.model;

import module.util.ComboBoxProperties;

public class DocumentType implements ComboBoxProperties{
	int id;
	String documentType;
	
	public DocumentType(){
		
	}
	public DocumentType(String documentType){
		this.documentType = documentType;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDocumentType() {
		return documentType;
	}



	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}



	@Override
	public Object getField() {
		return documentType;
	}
	
}