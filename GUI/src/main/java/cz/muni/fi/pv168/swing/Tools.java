package cz.muni.fi.pv168.swing;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Gajdos
 */
public class Tools
{
static String configFileName = "config.properties";
private final static Logger logger = Logger.getLogger(Tools.class);

public static DataSource getDS()
{

        BasicDataSource ds = new BasicDataSource();
        InputStream in = Tools.class.getClassLoader().getResourceAsStream(configFileName);
        if(in == null)
        {
                logger.error("Configuration file error");
        }

        Properties props = new Properties();
        try {
                props.load(in);
        } catch (IOException ex) {
                logger.error("Configuration file prperties error", ex);
        }
        ds.setUrl(props.getProperty("MYSQL_URL"));
        ds.setUsername(props.getProperty("MYSQL_USER"));
        ds.setPassword(props.getProperty("MYSQL_PASSWORD"));
        ds.setDriverClassName(props.getProperty("MYSQL_DRIVER_CLASS_NAME"));

        return ds;

}

}
