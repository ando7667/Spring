package org.ignatov;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("logging.time")
public class LoggingExecutionTimeProperties {

    private Level logLevel = Level.INFO;
}