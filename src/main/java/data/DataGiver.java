package data;

import models.CredentialModel;
import utilities.Logs;

import java.util.ArrayList;
import java.util.List;

public class DataGiver {

    public static CredentialModel getValidCredential() {
        Logs.debug("Getting valid credential");
        return MapParser.getCredentialMap().get("valid");
    }

    public static CredentialModel getInvalidCredential() {
        Logs.debug("Getting invalid credential");
        return MapParser.getCredentialMap().get("invalid");
    }

    public static CredentialModel getLockedCredential() {
        Logs.debug("Getting locked credential");
        return MapParser.getCredentialMap().get("locked");
    }

    public static String getInvalidErrorMessage() {
        Logs.debug("Getting invalid error message");
        return MapParser.getCredentialErrorMessageMap().get("invalid").getMessage();
    }

    public static String getLockedErrorMessage() {
        Logs.debug("Getting locked error message");
        return MapParser.getCredentialErrorMessageMap().get("locked").getMessage();
    }

    public static List<String> getItemTitleList() {
        Logs.debug("Getting item title list");
        final var titleList = new ArrayList<String>();
        final var listItem = ExcelReader.getItemList();

        for (var element : listItem) {
            titleList.add(element.getItemName());
        }

        return titleList;
    }
}
