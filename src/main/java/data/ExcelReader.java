package data;

import com.poiji.bind.Poiji;
import models.CredentialErrorMessageModel;
import models.CredentialModel;
import models.ItemModel;
import utilities.Logs;

import java.io.File;
import java.util.List;

public class ExcelReader {
    private static final String excelPath = "src/test/resources/data/testData.xlsx";

    public static List<CredentialModel> getCredentialList() {
        Logs.debug("Reading credential list from excel");
        return Poiji.fromExcel(new File(excelPath), CredentialModel.class);
    }

    public static List<CredentialErrorMessageModel> getCredentialErrorMessageList() {
        Logs.debug("Reading error message list from excel");
        return Poiji.fromExcel(new File(excelPath), CredentialErrorMessageModel.class);
    }

    public static List<ItemModel> getItemList() {
        Logs.debug("Reading item list from excel");
        return Poiji.fromExcel(new File(excelPath), ItemModel.class);
    }
}
