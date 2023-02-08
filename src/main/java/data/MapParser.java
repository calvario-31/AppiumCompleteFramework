package data;

import models.CredentialErrorMessageModel;
import models.CredentialModel;
import utilities.Logs;

import java.util.HashMap;
import java.util.Map;

public class MapParser {

    public static Map<String, CredentialModel> getCredentialMap() {
        Logs.debug("Creating credential map");

        final var map = new HashMap<String, CredentialModel>();
        final var credentialList = ExcelReader.getCredentialList();

        for (var element : credentialList) {
            map.put(element.getKey(), element);
        }

        Logs.debug(map.toString());

        return map;
    }

    public static Map<String, CredentialErrorMessageModel> getCredentialErrorMessageMap() {
        Logs.debug("Creating error message map");

        final var map = new HashMap<String, CredentialErrorMessageModel>();
        final var credentialErrorMessageList = ExcelReader.getCredentialErrorMessageList();

        for (var element : credentialErrorMessageList) {
            map.put(element.getKey(), element);
        }

        Logs.debug(map.toString());

        return map;
    }
}
