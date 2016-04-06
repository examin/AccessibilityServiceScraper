package service.android.google.com.accessibility.scraper.scrapers;

import android.view.accessibility.AccessibilityNodeInfo;

import service.android.google.com.accessibility.model.Person;

public abstract class AbstractChatWindowScraper implements Scraper {

    private final Person you;
    private final String packageName;
    protected String hashCode;
    private Person contactPerson;

    public AbstractChatWindowScraper(final String packageName) {
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
        buildBaseHash();
    }

    private void buildBaseHash() {
        hashCode = String.format("%s_%s", packageName, this.contactPerson.fullName().replace(" ", "_"));
    }

    public String buildMessageHash(final String uniqueMessageIdentifier) {
        return String.format("%s_%s", hashCode, Integer.toString(uniqueMessageIdentifier.hashCode()));
    }

    @Override
    public boolean isForAccessibilityNodeInfo(AccessibilityNodeInfo nodeInfo) {
        return nodeInfo != null && nodeInfo.getPackageName().equals(packageName);
    }
}