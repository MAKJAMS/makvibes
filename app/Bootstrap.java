import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

    @OnApplicationStart
    public class Bootstrap extends Job {

        public void doJob() {
            Fixtures.deleteAllModels();
            Fixtures.loadModels("data.yml");
        }

    }