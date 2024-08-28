package com.ericsson.oss.mediation.integration;

import java.io.File;
import java.util.*;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.*;

import com.ericsson.nms.mediation.core.channels.EventBasedMediationClientChannel;
import com.ericsson.nms.mediation.core.events.SupervisionTaskRequest;
import com.ericsson.oss.data.model.supervision.SupervisionObject;
import com.ericsson.oss.data.model.supervision.SupervisionObjectImpl;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObject;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObjectImpl;
import com.ericsson.oss.mediation.supervision.SupervisionService;
import com.ericsson.oss.mediation.supervision.integration.DPSFacade;

/**
 * ARQ Helper class used to create EAR/JAR/EJB artifact and to download all required dependencies from nexus
 * 
 * @author emaomic
 * 
 */
public class CreateArtifact {

    public static final String DEPLOYMENT_NAME = "SupervisionClient";

    /**
     * Create Supervision Client API JAR
     * 
     * @return
     */

    public static JavaArchive createAPI() {
        final JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class, CreateArtifact.DEPLOYMENT_NAME + "-api.jar");
        javaArchive.addClasses(SupervisionTaskRequest.class);
        javaArchive.addClasses(EventBasedMediationClientChannel.class);
        javaArchive.addClasses(SupervisionObject.class, SupervisionObjectImpl.class, StreamingSupervisionObject.class, StreamingSupervisionObjectImpl.class);
        javaArchive.addClasses(SupervisionObjectImpl.class);
        javaArchive.addAsResource("META-INF/beans.xml", "META-INF/beans.xml");
        return javaArchive;
    }

    /**
     * Create Supervision Client JAR JAR
     * 
     * @return
     */

    public static JavaArchive createJAR() {
        final JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class, CreateArtifact.DEPLOYMENT_NAME + "-jar.jar");
        javaArchive.addAsResource("META-INF/beans.xml", "META-INF/beans.xml");
        return javaArchive;
    }

    /**
     * Create Supervision Client EJB JAR
     * 
     * @return
     */

    public static JavaArchive createEJB() {
        final JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class, CreateArtifact.DEPLOYMENT_NAME + "-ejb.jar");
        javaArchive.addAsResource("META-INF/beans.xml", "META-INF/beans.xml");
        javaArchive.addPackage(SupervisionService.class.getPackage().getName());
        javaArchive.addPackage(DPSFacade.class.getPackage().getName());

        return javaArchive;
    }

    /**
     * Create Supervision Client EAR JAR
     * 
     * @return
     */
    public static EnterpriseArchive createEAR(final Class<?>... classes) {
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class);

        ear.addAsLibraries(CreateArtifact.getRequiredLibs());
        ear.addAsLibraries(CreateArtifact.createAPI());
        //ear.addAsLibraries(CreateArtifact.createJAR());
        if (classes.length > 0) {
            ear.addAsLibrary(createTestJar(classes));
        }
        ear.addAsModule(CreateArtifact.createEJB());
        return ear;
    }

    /**
     * Get all required libraries from nexus server the list of required artifact must be in synch with jee/pom.xml, if you need new library put it there first
     * 
     * @return
     */

    public static File[] getRequiredLibs() {
        final List<File> libs = new ArrayList<>();

        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___CONFIG_API_JAR).resolveAsFiles()));
        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___CONFIG_CORE_JAR).resolveAsFiles()));
        // TODO: demian to remove next line 
        //libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___CONFIG_IMPL_DEV_JAR).resolveAsFiles()));
        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___MODELLED_EVENTBUS_JAR).resolveAsFiles()));

        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_DATA_DATA_PERSISTENCE_JAR).resolveAsFiles()));
        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_DATA_DATA_PERSISTENCE_API).resolveAsFiles()));

        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.COM_ERICSSON_OSS_MEDIATION_CORE_MEDIATION_MODELS_JAR).resolveAsFiles()));

        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.DE_ODYSSEUS___JUEL_IMPL).resolveAsFiles()));
        libs.addAll(Arrays.asList(Artifact.getMavenResolver().artifact(Artifact.DE_ODYSSEUS___JUEL_SPI).resolveAsFiles()));

        return libs.toArray(new File[1]);

    }

    /**
     * get actual Supervision Client EAR file from nexus
     * 
     * @param classes
     * @return
     */

    public static EnterpriseArchive getEAR(final Class<?>... classes) {
        final File archiveFile = getEARFileFromMaven(Artifact.COM_ERICSSON_OSS_SUPERVISION_CLIENT_EAR_JAR);

        final EnterpriseArchive ear = ShrinkWrap.createFromZipFile(EnterpriseArchive.class, archiveFile);

        if (classes.length > 0) {
            ear.addAsLibrary(createTestJar(classes));
        }

        return ear;

    }

    /**
     * get actual MediationCore EAR file from nexus
     * 
     * @param classes
     * @return
     */

    public static EnterpriseArchive getMediationCoreEAR(final Class<?>... classes) {
        final File archiveFile = getEARFileFromMaven(Artifact.COM_ERICSSON_NMS_MEDIATION_CORE_EAR_JAR);

        final EnterpriseArchive ear = ShrinkWrap.createFromZipFile(EnterpriseArchive.class, archiveFile);

        if (classes.length > 0) {
            ear.addAsLibrary(createTestJar(classes));
        }

        return ear;

    }

    /**
     * get actual MediationService EAR file from nexus
     * 
     * @param classes
     * @return
     */

    public static EnterpriseArchive getMediationServiceEAR(final Class<?>... classes) {
        final File archiveFile = getEARFileFromMaven(Artifact.COM_ERICSSON_NMS_MEDIATION_SERVICE_EAR_JAR);

        final EnterpriseArchive ear = ShrinkWrap.createFromZipFile(EnterpriseArchive.class, archiveFile);

        if (classes.length > 0) {
            ear.addAsLibrary(createTestJar(classes));
        }

        return ear;

    }

    /**
     * 
     * @return
     */

    private static File getEARFileFromMaven(final String artifact) {

        final File[] resolved = Artifact.getMavenResolver().artifact(artifact).exclusion("*").resolveAsFiles();

        if (resolved.length != 1) {
            throw new RuntimeException("Can't resolve single EAR file; actual resolved file(s): " + resolved.length);
        }

        final File archiveFile = resolved[0];
        return archiveFile;
    }

    /**
     * 
     * @param classes
     * @return
     */

    public static WebArchive createTestWar(final Class<?>... classes) {
        return createTestWar("test", classes);
    }

    public static WebArchive createTestWar(final String name, final Class<?>... classes) {

        final WebArchive war = ShrinkWrap.create(WebArchive.class, name + ".war").addAsWebInfResource("META-INF/beans.xml", "beans.xml").addAsManifestResource("MANIFEST.MF", "MANIFEST.MF")
                .addClasses(classes);
        return war;
    }

    /**
     * 
     * @return
     */

    public static JavaArchive createTestJar(final Class<?>... classes) {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, CreateArtifact.DEPLOYMENT_NAME + "-test.jar").addAsManifestResource("META-INF/beans.xml", "beans.xml").addClasses(classes);
        return jar;
    }
}
