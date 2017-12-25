package module.productsupportinggood.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import module.personalia.model.Tax;
import module.sn.productcategory.model.ProductCategory;
import module.sn.uom.model.Uom;
import module.util.ComboBoxProperties;

public class ProductSupp implements Serializable, ComboBoxProperties {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String productCode;
	private String productName;
	private Integer productCategoryId;
	private Integer productUomId;
	private int isMaintainStock;
	private String imagePath;
	private String brand;
	private String barcode;
	private String description;
	private int isFixedAsset;
	private Integer warranty;
	private BigDecimal weightNet;
	private BigDecimal weightGross;
	private Integer weightUomId;
	private int isPurchaseItem;
	private Integer minor;
	private Integer minorUomId;
	private Integer leadTime;
	private Integer buyCostCenterId;
	private Integer expenseAccId;
	private String mainSuppCode;
	private String manufacturer;
	private int isSalesItem;
	private int isServiceItem;
	private Integer sellCostCenterId;
	private Integer incomeAccId;
	private Integer assetId;
	private BigDecimal maxDisc;
	private Date inputDate;
	private String inputBy;
	private Date editDate;
	private String editedBy;
	private Date deletedDate;
	private String deletedBy;
	private Integer minqty;
	private BigDecimal thickness;
	private BigDecimal length;
	private BigDecimal width;
	private Integer volumeUomId;
	private Integer taxId;

	private Tax tax;
	private Uom weightUom;
	private Uom minorUom;
	private Uom volumeUom;
	private Uom productUom;
	private ProductCategory productCategory;
	
	private BigDecimal unitPrice;
	private BigDecimal qty;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetId == null) ? 0 : assetId.hashCode());
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((buyCostCenterId == null) ? 0 : buyCostCenterId.hashCode());
		result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
		result = prime * result + ((deletedDate == null) ? 0 : deletedDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + ((editedBy == null) ? 0 : editedBy.hashCode());
		result = prime * result + ((expenseAccId == null) ? 0 : expenseAccId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((incomeAccId == null) ? 0 : incomeAccId.hashCode());
		result = prime * result + ((inputBy == null) ? 0 : inputBy.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
		result = prime * result + isFixedAsset;
		result = prime * result + isMaintainStock;
		result = prime * result + isPurchaseItem;
		result = prime * result + isSalesItem;
		result = prime * result + isServiceItem;
		result = prime * result + ((leadTime == null) ? 0 : leadTime.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((mainSuppCode == null) ? 0 : mainSuppCode.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((maxDisc == null) ? 0 : maxDisc.hashCode());
		result = prime * result + ((minor == null) ? 0 : minor.hashCode());
		result = prime * result + ((minorUom == null) ? 0 : minorUom.hashCode());
		result = prime * result + ((minorUomId == null) ? 0 : minorUomId.hashCode());
		result = prime * result + ((minqty == null) ? 0 : minqty.hashCode());
		result = prime * result + ((productCategoryId == null) ? 0 : productCategoryId.hashCode());
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productUomId == null) ? 0 : productUomId.hashCode());
		result = prime * result + ((sellCostCenterId == null) ? 0 : sellCostCenterId.hashCode());
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
		result = prime * result + ((thickness == null) ? 0 : thickness.hashCode());
		result = prime * result + ((volumeUomId == null) ? 0 : volumeUomId.hashCode());
		result = prime * result + ((warranty == null) ? 0 : warranty.hashCode());
		result = prime * result + ((weightGross == null) ? 0 : weightGross.hashCode());
		result = prime * result + ((weightNet == null) ? 0 : weightNet.hashCode());
		result = prime * result + ((weightUomId == null) ? 0 : weightUomId.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSupp other = (ProductSupp) obj;
		if (assetId == null) {
			if (other.assetId != null)
				return false;
		} else if (!assetId.equals(other.assetId))
			return false;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (buyCostCenterId == null) {
			if (other.buyCostCenterId != null)
				return false;
		} else if (!buyCostCenterId.equals(other.buyCostCenterId))
			return false;
		if (deletedBy == null) {
			if (other.deletedBy != null)
				return false;
		} else if (!deletedBy.equals(other.deletedBy))
			return false;
		if (deletedDate == null) {
			if (other.deletedDate != null)
				return false;
		} else if (!deletedDate.equals(other.deletedDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (editDate == null) {
			if (other.editDate != null)
				return false;
		} else if (!editDate.equals(other.editDate))
			return false;
		if (editedBy == null) {
			if (other.editedBy != null)
				return false;
		} else if (!editedBy.equals(other.editedBy))
			return false;
		if (expenseAccId == null) {
			if (other.expenseAccId != null)
				return false;
		} else if (!expenseAccId.equals(other.expenseAccId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (incomeAccId == null) {
			if (other.incomeAccId != null)
				return false;
		} else if (!incomeAccId.equals(other.incomeAccId))
			return false;
		if (inputBy == null) {
			if (other.inputBy != null)
				return false;
		} else if (!inputBy.equals(other.inputBy))
			return false;
		if (inputDate == null) {
			if (other.inputDate != null)
				return false;
		} else if (!inputDate.equals(other.inputDate))
			return false;
		if (isFixedAsset != other.isFixedAsset)
			return false;
		if (isMaintainStock != other.isMaintainStock)
			return false;
		if (isPurchaseItem != other.isPurchaseItem)
			return false;
		if (isSalesItem != other.isSalesItem)
			return false;
		if (isServiceItem != other.isServiceItem)
			return false;
		if (leadTime == null) {
			if (other.leadTime != null)
				return false;
		} else if (!leadTime.equals(other.leadTime))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (mainSuppCode == null) {
			if (other.mainSuppCode != null)
				return false;
		} else if (!mainSuppCode.equals(other.mainSuppCode))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (maxDisc == null) {
			if (other.maxDisc != null)
				return false;
		} else if (!maxDisc.equals(other.maxDisc))
			return false;
		if (minor == null) {
			if (other.minor != null)
				return false;
		} else if (!minor.equals(other.minor))
			return false;
		if (minorUom == null) {
			if (other.minorUom != null)
				return false;
		} else if (!minorUom.equals(other.minorUom))
			return false;
		if (minorUomId == null) {
			if (other.minorUomId != null)
				return false;
		} else if (!minorUomId.equals(other.minorUomId))
			return false;
		if (minqty == null) {
			if (other.minqty != null)
				return false;
		} else if (!minqty.equals(other.minqty))
			return false;
		if (productCategoryId == null) {
			if (other.productCategoryId != null)
				return false;
		} else if (!productCategoryId.equals(other.productCategoryId))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productUomId == null) {
			if (other.productUomId != null)
				return false;
		} else if (!productUomId.equals(other.productUomId))
			return false;
		if (sellCostCenterId == null) {
			if (other.sellCostCenterId != null)
				return false;
		} else if (!sellCostCenterId.equals(other.sellCostCenterId))
			return false;
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
			return false;
		if (thickness == null) {
			if (other.thickness != null)
				return false;
		} else if (!thickness.equals(other.thickness))
			return false;
		if (volumeUomId == null) {
			if (other.volumeUomId != null)
				return false;
		} else if (!volumeUomId.equals(other.volumeUomId))
			return false;
		if (warranty == null) {
			if (other.warranty != null)
				return false;
		} else if (!warranty.equals(other.warranty))
			return false;
		if (weightGross == null) {
			if (other.weightGross != null)
				return false;
		} else if (!weightGross.equals(other.weightGross))
			return false;
		if (weightNet == null) {
			if (other.weightNet != null)
				return false;
		} else if (!weightNet.equals(other.weightNet))
			return false;
		if (weightUomId == null) {
			if (other.weightUomId != null)
				return false;
		} else if (!weightUomId.equals(other.weightUomId))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductSupp [id=" + id + ", productCode=" + productCode + ", productName=" + productName
				+ ", productCategoryId=" + productCategoryId + ", productUomId=" + productUomId + ", isMaintainStock="
				+ isMaintainStock + ", imagePath=" + imagePath + ", brand=" + brand + ", barcode=" + barcode
				+ ", description=" + description + ", isFixedAsset=" + isFixedAsset + ", warranty=" + warranty
				+ ", weightNet=" + weightNet + ", weightGross=" + weightGross + ", weightUomId=" + weightUomId
				+ ", isPurchaseItem=" + isPurchaseItem + ", minor=" + minor + ", minorUomId=" + minorUomId
				+ ", leadTime=" + leadTime + ", buyCostCenterId=" + buyCostCenterId + ", expenseAccId=" + expenseAccId
				+ ", mainSuppCode=" + mainSuppCode + ", manufacturer=" + manufacturer + ", isSalesItem=" + isSalesItem
				+ ", isServiceItem=" + isServiceItem + ", sellCostCenterId=" + sellCostCenterId + ", incomeAccId="
				+ incomeAccId + ", assetId=" + assetId + ", maxDisc=" + maxDisc + ", inputDate=" + inputDate
				+ ", inputBy=" + inputBy + ", editDate=" + editDate + ", editedBy=" + editedBy + ", deletedDate="
				+ deletedDate + ", deletedBy=" + deletedBy + ", minqty=" + minqty + ", thickness=" + thickness
				+ ", length=" + length + ", width=" + width + ", volumeUomId=" + volumeUomId + ", taxId=" + taxId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Integer getProductUomId() {
		return productUomId;
	}

	public void setProductUomId(Integer productUomId) {
		this.productUomId = productUomId;
	}

	public int getIsMaintainStock() {
		return isMaintainStock;
	}

	public void setIsMaintainStock(int isMaintainStock) {
		this.isMaintainStock = isMaintainStock;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsFixedAsset() {
		return isFixedAsset;
	}

	public void setIsFixedAsset(int isFixedAsset) {
		this.isFixedAsset = isFixedAsset;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public BigDecimal getWeightNet() {
		return weightNet;
	}

	public void setWeightNet(BigDecimal weightNet) {
		this.weightNet = weightNet;
	}

	public BigDecimal getWeightGross() {
		return weightGross;
	}

	public void setWeightGross(BigDecimal weightGross) {
		this.weightGross = weightGross;
	}

	public Integer getWeightUomId() {
		return weightUomId;
	}

	public void setWeightUomId(Integer weightUomId) {
		this.weightUomId = weightUomId;
	}

	public int getIsPurchaseItem() {
		return isPurchaseItem;
	}

	public void setIsPurchaseItem(int isPurchaseItem) {
		this.isPurchaseItem = isPurchaseItem;
	}

	public Integer getMinor() {
		return minor;
	}

	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	public Integer getMinorUomId() {
		return minorUomId;
	}

	public void setMinorUomId(Integer minorUomId) {
		this.minorUomId = minorUomId;
	}

	public Integer getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	public Integer getBuyCostCenterId() {
		return buyCostCenterId;
	}

	public void setBuyCostCenterId(Integer buyCostCenterId) {
		this.buyCostCenterId = buyCostCenterId;
	}

	public Integer getExpenseAccId() {
		return expenseAccId;
	}

	public void setExpenseAccId(Integer expenseAccId) {
		this.expenseAccId = expenseAccId;
	}

	public String getMainSuppCode() {
		return mainSuppCode;
	}

	public void setMainSuppCode(String mainSuppCode) {
		this.mainSuppCode = mainSuppCode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getIsSalesItem() {
		return isSalesItem;
	}

	public void setIsSalesItem(int isSalesItem) {
		this.isSalesItem = isSalesItem;
	}

	public int getIsServiceItem() {
		return isServiceItem;
	}

	public void setIsServiceItem(int isServiceItem) {
		this.isServiceItem = isServiceItem;
	}

	public Integer getSellCostCenterId() {
		return sellCostCenterId;
	}

	public void setSellCostCenterId(Integer sellCostCenterId) {
		this.sellCostCenterId = sellCostCenterId;
	}

	public Integer getIncomeAccId() {
		return incomeAccId;
	}

	public void setIncomeAccId(Integer incomeAccId) {
		this.incomeAccId = incomeAccId;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public BigDecimal getMaxDisc() {
		return maxDisc;
	}

	public void setMaxDisc(BigDecimal maxDisc) {
		this.maxDisc = maxDisc;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Integer getMinqty() {
		return minqty;
	}

	public void setMinqty(Integer minqty) {
		this.minqty = minqty;
	}

	public BigDecimal getThickness() {
		return thickness;
	}

	public void setThickness(BigDecimal thickness) {
		this.thickness = thickness;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public Integer getVolumeUomId() {
		return volumeUomId;
	}

	public void setVolumeUomId(Integer volumeUomId) {
		this.volumeUomId = volumeUomId;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public Tax getTax() {
		if (tax == null)
			tax = new Tax();
		return tax;
	}

	public void setTax(Tax tax) {
		if (tax == null)
			tax = new Tax();
		this.tax = tax;
	}

	public Uom getWeightUom() {
		if (weightUom == null)
			weightUom = new Uom();
		return weightUom;
	}

	public void setWeightUom(Uom weightUom) {
		if (weightUom == null)
			weightUom = new Uom();
		this.weightUom = weightUom;
	}

	public Uom getMinorUom() {
		if (minorUom == null)
			minorUom = new Uom();
		return minorUom;
	}

	public void setMinorUom(Uom minorUom) {
		if (minorUom == null)
			minorUom = new Uom();
		this.minorUom = minorUom;
	}

	public Uom getVolumeUom() {
		if (volumeUom == null)
			volumeUom = new Uom();
		return volumeUom;
	}

	public void setVolumeUom(Uom volumeUom) {
		if (volumeUom == null)
			volumeUom = new Uom();
		this.volumeUom = volumeUom;
	}

	public ProductCategory getProductCategory() {
		if (productCategory == null)
			productCategory = new ProductCategory();
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		if (productCategory == null)
			productCategory = new ProductCategory();
		this.productCategory = productCategory;
	}

	public Uom getProductUom() {
		if (productUom == null)
			productUom = new Uom();
		return productUom;
	}

	public void setProductUom(Uom productUom) {
		if (productUom == null)
			productUom = new Uom();
		this.productUom = productUom;
	}
	
	public ProductSupp() {
		
	}
	
	@Override
	public Object getField() {
		return productName ;
	}
	
	public ProductSupp(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

}
