package com.swpu.funchat.datasource.storage.preference.dto;

import com.swpu.funchat.datasource.storage.preference.Preference;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AccountPreference
 * @since 2019-06-16
 */
@Preference(name = "account")
public class AccountPreference {

    private String username;

    private String password;

    public AccountPreference() {
    }

    public AccountPreference(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountPreference{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
