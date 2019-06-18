package com.swpu.funchat.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PhoneContactEntity
 * @since 2019-06-18
 */
public class PhoneContactEntity {
    private String phoneContactName;
    private String phoneContactNumber;

    public String getPhoneContactName() {
        return phoneContactName;
    }

    public void setPhoneContactName(String phoneContactName) {
        this.phoneContactName = phoneContactName;
    }

    public String getPhoneContactNumber() {
        return phoneContactNumber;
    }

    public void setPhoneContactNumber(String phoneContactNumber) {
        this.phoneContactNumber = phoneContactNumber;
    }
}
