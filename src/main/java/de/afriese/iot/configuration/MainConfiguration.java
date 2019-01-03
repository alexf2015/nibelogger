package de.afriese.iot.configuration;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Configuration
@PropertySource(value = {
        "classpath:/conf/application.properties", "classpath:/conf/application.build.properties",
        "file:${configPath}/application.properties" }, ignoreResourceNotFound = true)
public class MainConfiguration {

    @Value("${app.configName}")
    private String configName;

    // @Value("${app.title}")
    // private String appTitle;
    //
    // @Value("${app.subTitle}")
    // private String appSubTitle;
    //
    // @Value("${app.footer.showVersions}")
    // private boolean footerShowVersions;
    //
    // @Value("${app.footer.showBuildInfo}")
    // private boolean footerShowBuildInfo;

    @Value("${project.name}")
    private String appName;

    @Value("${project.version}")
    private String appVersion;

    @Value("${configPath:N/A}")
    private String configPath;

    @Value("${project.buildtime}")
    private String appBuildtime;

    @Value("${project.revision}")
    private String appRevision;

    @Value("${project.branch}")
    private String appBranch;

    // Nibe Connector configuration   

    @Value("${nibe.user}")
    private String nibeUser;

    @Value("${nibe.password}")
    private String nibePassword;

    @Value("${nibe.id}")
    private String nibeId;

    // Other Config

    @Value("${excel.file}")
    private String excelFile;

    @PostConstruct
    private void logConfig() {
        appName = WordUtils.capitalize(appName);
        log.info("constructed config: " + configName);
        log.info(this.toString());
    }

}
