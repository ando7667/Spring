package org.ignatov;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.reader")
public class IssueProperty {

    private int maxAllowedBook = 10;
}
