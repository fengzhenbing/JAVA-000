package io.github.zhenbing.springCamelFtp.processor;

import io.github.zhenbing.springCamelFtp.filter.FtpFileFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;

/**
 * @Description
 * @Author fzb
 * @date 2021.04.07 00:11
 */
@Component
@Slf4j
public class FtpFileProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        String path = inFileMessage.getGenericFile().getAbsoluteFilePath();
        log.info("{} is downloaded", path);
        FtpFileFilter.downloadedPaths.add(path);
    }
}
