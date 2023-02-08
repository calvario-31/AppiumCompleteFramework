package models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("credentialsErrorMessages")
public class CredentialErrorMessageModel {
    @ExcelCellName("key")
    private String key;
    @ExcelCellName("message")
    private String message;

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
