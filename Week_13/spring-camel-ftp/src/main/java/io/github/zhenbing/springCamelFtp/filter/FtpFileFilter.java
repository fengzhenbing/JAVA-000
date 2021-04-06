package io.github.zhenbing.springCamelFtp.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author fzb
 * @date 2021.04.07 00:10
 */
@Component
@Slf4j
public class FtpFileFilter implements Predicate {
    public static List<String> downloadedPaths = Collections.synchronizedList(new ArrayList<>(128));

    @Override
    public boolean matches(Exchange exchange) {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        return !isDownloaded(inFileMessage);
    }

    private boolean isDownloaded(GenericFileMessage<RandomAccessFile> inFileMessage) {
        String path = inFileMessage.getGenericFile().getAbsoluteFilePath();
        boolean downloaded = downloadedPaths.contains(path);
        if (downloaded) {
            log.debug("{} has been downloaded already", path);
        }
        return downloaded;
    }
}
