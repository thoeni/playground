/*
package models;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;
import play.db.DB;

import javax.sql.DataSource;

*/
/**
 * Created by thoeni on 2/21/15 with Intellijnce
 *//*

public class PlayServerConfigStartup implements ServerConfigStartup{

    DataSource ds = DB.getDataSource();

    @Override
    public void onStart(ServerConfig serverConfig) {
        serverConfig.setName("play");
        serverConfig.setDatabaseSequenceBatchSize(1);
        serverConfig.setDataSource(ds);

        System.out.printf("PlayServerConfigStartup exit");

        // create the EbeanServer instance
        // EbeanServer server = EbeanServerFactory.create(serverConfig);
    }
}
*/
