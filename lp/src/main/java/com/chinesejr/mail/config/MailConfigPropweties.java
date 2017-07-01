package com.chinesejr.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="mail")
public class MailConfigPropweties {
}
