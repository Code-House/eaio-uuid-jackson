package org.code_house.eaio.uuid.jackson.itests;

import org.apache.karaf.features.FeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import javax.inject.Inject;
import java.util.EnumSet;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFileExtend;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class FeaturesIntegrationTest {

    private final static EnumSet<FeaturesService.Option> INSTALL_OPTIONS = EnumSet.of(FeaturesService.Option.Verbose);

    @Inject
    private FeaturesService features;

    @Configuration
    public Option[] config() {
        String featuresUrl = maven("org.code-house.eaio-uuid.jackson", "features").classifier("features").type("xml").versionAsInProject().getURL();
        String karafVersion = MavenUtils.getArtifactVersion("org.apache.karaf", "apache-karaf");
        MavenArtifactUrlReference frameworkURL = maven("org.apache.karaf", "apache-karaf").type("zip").version(karafVersion);

        return new Option[]{
            karafDistributionConfiguration().karafVersion(karafVersion).frameworkUrl(frameworkURL),
            editConfigurationFileExtend("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + featuresUrl)
        };
    }

    @Test
    public void testInstallBvalValidationProvider() throws Exception {
        features.installFeature("eaio-uuid-jackson", INSTALL_OPTIONS);

        assertFeatureInstalled("eaio-uuid-jackson");
    }

    private void assertFeatureInstalled(String name) throws Exception {
        assertTrue("Feature " + name + " should be installed", features.isInstalled(features.getFeature(name)));
    }

}
