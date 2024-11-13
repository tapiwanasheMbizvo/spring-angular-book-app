package com.tapiwa.book.social.messenger;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    ACTIVATE_ACCOUNT("activate-account");

    private final String templateName;

    EmailTemplate(String templateName) {
        this.templateName = templateName;
    }
}
