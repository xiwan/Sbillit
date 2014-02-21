package entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class SbillitCombo implements Serializable {
	
	private Long id;
	private String itemName1;
	private Long itemNumber1;
	private Double itemPrice1;
	private String itemName2;
	private Long itemNumber2;
	private Double itemPrice2;
	private String itemName3;
	private Long itemNumber3;
	private Double itemPrice3;
	private String itemName4;
	private Long itemNumber4;
	private Double itemPrice4;
	private String itemName5;
	private Long itemNumber5;
	private Double itemPrice5;
	private Long sellerId;
	private Integer valid;

	private class ComboArray {
		public String itemName;
		public Long itemNumber;
		public Double itemPrice;		
	};
	
	public List<ComboArray> genComboArray (){
		List<ComboArray> comboList = new ArrayList<ComboArray> ();
		if (this.itemName1!=null && this.itemName1.length()>0) {
			ComboArray combo = new ComboArray();
			combo.itemName = this.itemName1;
			combo.itemNumber = this.itemNumber1;
			combo.itemPrice = this.itemPrice1;
			comboList.add(combo);
		}
		if (this.itemName2!=null &&  this.itemName2.length()>0) {
			ComboArray combo = new ComboArray();
			combo.itemName = this.itemName2;
			combo.itemNumber = this.itemNumber2;
			combo.itemPrice = this.itemPrice2;
			comboList.add(combo);
		}
		if (this.itemName3!=null &&  this.itemName3.length()>0) {
			ComboArray combo = new ComboArray();
			combo.itemName = this.itemName3;
			combo.itemNumber = this.itemNumber3;
			combo.itemPrice = this.itemPrice3;
			comboList.add(combo);
		}
		if (this.itemName4!=null &&  this.itemName4.length()>0) {
			ComboArray combo = new ComboArray();
			combo.itemName = this.itemName4;
			combo.itemNumber = this.itemNumber4;
			combo.itemPrice = this.itemPrice4;
			comboList.add(combo);
		}
		if (this.itemName5!=null && this.itemName5.length()>0) {
			ComboArray combo = new ComboArray();
			combo.itemName = this.itemName5;
			combo.itemNumber = this.itemNumber5;
			combo.itemPrice = this.itemPrice5;
			comboList.add(combo);
		}
		return comboList;
	};
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName1() {
		return itemName1;
	}
	public void setItemName1(String itemName1) {
		this.itemName1 = itemName1;
	}
	public Long getItemNumber1() {
		return itemNumber1;
	}
	public void setItemNumber1(Long itemNumber1) {
		this.itemNumber1 = itemNumber1;
	}
	public Double getItemPrice1() {
		return itemPrice1;
	}
	public void setItemPrice1(Double itemPrice1) {
		this.itemPrice1 = itemPrice1;
	}
	public String getItemName2() {
		return itemName2;
	}
	public void setItemName2(String itemName2) {
		this.itemName2 = itemName2;
	}
	public Long getItemNumber2() {
		return itemNumber2;
	}
	public void setItemNumber2(Long itemNumber2) {
		this.itemNumber2 = itemNumber2;
	}
	public Double getItemPrice2() {
		return itemPrice2;
	}
	public void setItemPrice2(Double itemPrice2) {
		this.itemPrice2 = itemPrice2;
	}
	public String getItemName3() {
		return itemName3;
	}
	public void setItemName3(String itemName3) {
		this.itemName3 = itemName3;
	}
	public Long getItemNumber3() {
		return itemNumber3;
	}
	public void setItemNumber3(Long itemNumber3) {
		this.itemNumber3 = itemNumber3;
	}
	public Double getItemPrice3() {
		return itemPrice3;
	}
	public void setItemPrice3(Double itemPrice3) {
		this.itemPrice3 = itemPrice3;
	}
	public String getItemName4() {
		return itemName4;
	}
	public void setItemName4(String itemName4) {
		this.itemName4 = itemName4;
	}
	public Long getItemNumber4() {
		return itemNumber4;
	}
	public void setItemNumber4(Long itemNumber4) {
		this.itemNumber4 = itemNumber4;
	}
	public Double getItemPrice4() {
		return itemPrice4;
	}
	public void setItemPrice4(Double itemPrice4) {
		this.itemPrice4 = itemPrice4;
	}
	public String getItemName5() {
		return itemName5;
	}
	public void setItemName5(String itemName5) {
		this.itemName5 = itemName5;
	}
	public Long getItemNumber5() {
		return itemNumber5;
	}
	public void setItemNumber5(Long itemNumber5) {
		this.itemNumber5 = itemNumber5;
	}
	public Double getItemPrice5() {
		return itemPrice5;
	}
	public void setItemPrice5(Double itemPrice5) {
		this.itemPrice5 = itemPrice5;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}

}
