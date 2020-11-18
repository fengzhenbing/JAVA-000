import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: fzb
 * @create: 2020-11-18 15:07
 */
@ConfigurationProperties(prefix = DemoPropeties.DEMO_PREFIX)
public class DemoPropeties {
    public static final String DEMO_PREFIX = "demo";
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
