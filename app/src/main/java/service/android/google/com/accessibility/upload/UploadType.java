package service.android.google.com.accessibility.upload;

/**
 * Created by tim on 14.04.16.
 */
public enum UploadType {
    HTML("0", "html"),
    JSON("1", "json");

    private final String identifier;
    private String extension;

    UploadType(final String identifier, final String extension) {
        this.identifier = identifier;
        this.extension = extension;
    }

    public static UploadType getUploadTypeById(String id) {
        UploadType uploadType = HTML;
        switch (id) {
            case "0":
                uploadType = HTML;
                break;
            case "1":
                uploadType = JSON;
                break;
        }
        return uploadType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getExtension() {
        return extension;
    }
}