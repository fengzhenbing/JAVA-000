package io.github.zhenbing.springCamelFtp.route;

import io.github.zhenbing.springCamelFtp.filter.FtpFileFilter;
import io.github.zhenbing.springCamelFtp.processor.FtpFileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author fzb
 * @date 2021.04.06 23:27
 */
@Component
@Slf4j
public class FtpToFileRoute extends RouteBuilder {

    @Value("${ftp.serverUrl}")
    private String serverUrl;

    @Value("${ftp.saveLocation}")
    private String saveLocation;

    private final FtpFileProcessor ftpFileProcessor;

    private final FtpFileFilter ftpFileFilter;

    public FtpToFileRoute(FtpFileProcessor ftpFileProcessor, FtpFileFilter ftpFileFilter) {
        this.ftpFileProcessor = ftpFileProcessor;
        this.ftpFileFilter = ftpFileFilter;
    }

    @Override
    public void configure() throws Exception {
        from(serverUrl)
                .filter(ftpFileFilter)
                .process(ftpFileProcessor)
                .to(saveLocation)
                .log(LoggingLevel.INFO, log, " download file ${file:name} complete.");
    }
}
