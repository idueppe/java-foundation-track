package managers;

import com.lsy.vehicle.controller.spi.DBFixture;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationManager {

    private static final Logger LOG = Logger.getLogger(ApplicationManager.class.getName());

    @ManagedProperty("#{DBFixture}")
    private DBFixture dbFixture;

    @PostConstruct
    public void initDatabase() {
        LOG.info("Initializing Database");
        dbFixture.createDefaultDataInDatabase();
    }

    public void setDbFixture(DBFixture dbFixture) {
        this.dbFixture = dbFixture;
    }
}
