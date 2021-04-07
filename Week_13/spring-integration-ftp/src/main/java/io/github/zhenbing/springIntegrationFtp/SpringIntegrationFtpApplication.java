package io.github.zhenbing.springIntegrationFtp;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.File;

/**
 * https://docs.spring.io/spring-integration/docs/current/reference/html/sftp.html#configuring-with-java-configuration
 */
@SpringBootApplication
public class SpringIntegrationFtpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationFtpApplication.class, args);
    }


    @Bean
    public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost("xxx");
        factory.setPort(22);
        factory.setUser("xxx");
        factory.setPassword("xxx");
        factory.setAllowUnknownKeys(true);
        return new CachingSessionFactory<ChannelSftp.LsEntry>(factory);
    }

    @Bean
    public SftpInboundFileSynchronizer sftpInboundFileSynchronizer() {
        SftpInboundFileSynchronizer fileSynchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
        fileSynchronizer.setDeleteRemoteFiles(false);
        fileSynchronizer.setRemoteDirectory("/home/data");
        fileSynchronizer.setFilter(new SftpSimplePatternFileListFilter("*.png"));
        return fileSynchronizer;
    }

    @Bean
    @InboundChannelAdapter(channel = "sftpChannel", poller = @Poller(fixedDelay = "5000"))
    public MessageSource<File> sftpMessageSource() {
        SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(
                sftpInboundFileSynchronizer());
        source.setLocalDirectory(new File("sftp-inbound"));
        source.setAutoCreateLocalDirectory(true);
        source.setLocalFilter(new AcceptOnceFileListFilter<File>());
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "sftpChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getPayload());
            }
        };
    }

    // 通过 Java DSL 配置
   /* @Bean
    public IntegrationFlow sftpInboundFlow() {
        return IntegrationFlows
                .from(Sftp.inboundAdapter(this.sftpSessionFactory)
                                .preserveTimestamp(true)
                                .remoteDirectory("foo")
                                .regexFilter(".*\\.txt$")
                                .localFilenameExpression("#this.toUpperCase() + '.a'")
                                .localDirectory(new File("sftp-inbound")),
                        e -> e.id("sftpInboundAdapter")
                                .autoStartup(true)
                                .poller(Pollers.fixedDelay(5000)))
                .handle(m -> System.out.println(m.getPayload()))
                .get();
    }*/
}
