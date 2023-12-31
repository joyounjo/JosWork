package com.tsrs.webedi.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 多数据源的配置
 *
 * @author tsrs
 * @Date 2017/6/23 23:05
 */
@Component
@ConfigurationProperties(prefix = "biz.datasource")
public class MutiDataSourceProperties {

//    private String url = "jdbc:mysql://127.0.0.1:3306/db_movin?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
    private String url = "jdbc:mysql://127.0.0.1:3306/jobnavi?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

    private String username = "root";

//    private String password = "zaq12wsx";
    private String password = "123";

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
