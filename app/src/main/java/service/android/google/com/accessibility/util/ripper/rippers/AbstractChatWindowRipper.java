package service.android.google.com.accessibility.util.ripper.rippers;

import android.view.accessibility.AccessibilityNodeInfo;

import service.android.google.com.accessibility.model.Person;

public abstract class AbstractChatWindowRipper implements Ripper {

    private final Person you;
    private final String packageName;
    private Person contactPerson;

    public AbstractChatWindowRipper(final String packageName) {
        this.packageName = packageName;
        this.you = Person.builder()
                .fullName("You")
                .build();
    }

    public Person getYou() {
        return you;
    }

    public String getFQResID(final String id) {
        return String.format("%s:id/%s", this.packageName, id);
    }

    public Person getContactPerson() {
        return contactPerson;
    }

    protected void setContactPersonFromName(final String fullContactName) {
        this.contactPerson = Person.builder()
                .fullName(fullContactName)
                .build();
    }

    @Override
    public boolean isForAccessibilityNodeInfo(AccessibilityNodeInfo nodeInfo) {
        return nodeInfo != null && nodeInfo.getPackageName().equals(packageName);
    }
}