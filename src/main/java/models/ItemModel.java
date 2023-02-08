package models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("itemData")
public class ItemModel {
    @ExcelCellName("key")
    private String key;
    @ExcelCellName("itemName")
    private String itemName;
    @ExcelCellName("itemId")
    private String itemId;
    @ExcelCellName("price")
    private double price;
    @ExcelCellName("quantity")
    private int quantity;

    public String getKey() {
        return key;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
