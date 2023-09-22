package com.teclan.lottery.db;

import com.teclan.lottery.config.CommonConfig;
import com.typesafe.config.Config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class DBFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBFactory.class);

    private static   String DRIVER_CLASS = "";
    private static   String URL = "";
    private static   String USER = "";
    private static   String PASSWORD = "";
    private static String dbName = "default";

    private static DB db;

    static {
        Config config = CommonConfig.getConfig();
        String database = config.getString("database");
        Config databaseConfig = config.getConfig(database);
        DRIVER_CLASS = databaseConfig.getString("driver");
        URL = databaseConfig.getString("url");
        USER = databaseConfig.getString("username");
        PASSWORD = databaseConfig.getString("password");

        LOGGER.info(String.format("当前数据库信息：类型：%s，驱动:%s，连接符：%s，用户:%s，密码：%s",database,DRIVER_CLASS,URL,USER,PASSWORD));

        initDb();
    }

    private static HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DRIVER_CLASS);
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        return new HikariDataSource(config);
    }

    public static DB getDb() {
        return getDb(dbName);
    }

    public static DB getDb(String dbName) {

        if (db == null) {
            db = new DB(dbName);
        }
        return db;
    }

    public static void initDb(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.migrate();
    }

    public static void open(String dbName) {
        new DB(dbName).open(DRIVER_CLASS,URL,USER,PASSWORD);
        LOGGER.info("\n打开数据库成功...");
    }

    public static void close(String dbName){
        getDb(dbName).close();
        LOGGER.info("\n关闭数据库成功...");
    }


    public static void open() {
       open(dbName);
    }

    public static void close(){
       close(dbName);
    }
    
    public static Connection getConnection() {
    	return getDb().getConnection();
    }

}
