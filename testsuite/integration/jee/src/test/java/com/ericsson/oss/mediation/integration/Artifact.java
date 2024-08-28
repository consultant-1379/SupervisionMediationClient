package com.ericsson.oss.mediation.integration;

import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

public class Artifact {

    private Artifact() {
    }

    //
    // Constants that come as part of Service Framework
    //

    public static final String COM_ERICSSON_OSS_ITPF_SDK___SERVICES_CORE_JAR = "com.ericsson.oss.itpf.sdk:sdk-services-core:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CONFIG_API_JAR = "com.ericsson.oss.itpf.sdk:sdk-config-api:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CONFIG_CORE_JAR = "com.ericsson.oss.itpf.sdk:sdk-config-core:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CONFIG_IMPL_DEV_JAR = "com.ericsson.oss.itpf.sdk:sdk-config-impl-dev:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___MODELLED_EVENTBUS_API_JAR = "com.ericsson.oss.itpf.sdk:sdk-modeled-eventbus-api:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___MODELLED_EVENTBUS_JAR = "com.ericsson.oss.itpf.sdk:sdk-modeled-eventbus:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___EVENTBUS_API_JAR = "com.ericsson.oss.itpf.sdk:sdk-eventbus-api:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___EVENTBUS_CORE_JAR = "com.ericsson.oss.itpf.sdk:sdk-eventbus-core:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___EVENTBUS_JMS_JAR = "com.ericsson.oss.itpf.sdk:sdk-eventbus-jms:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___MODEL_SERVICE_MOCK_JAR = "com.ericsson.oss.itpf.sdk:model-service-mock:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___EVENTBUS_JMS_HORNETQ_JAR = "com.ericsson.oss.itpf.sdk:sdk-eventbus-jms-hornetq:jar";

    //
    // Cache constants
    //
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CACHE_INFINISPAN_JAR = "com.ericsson.oss.itpf.sdk:sdk-cache-infinispan:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CACHE_API_JAR = "com.ericsson.oss.itpf.sdk:sdk-cache-api:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CACHE_CORE_JAR = "com.ericsson.oss.itpf.sdk:sdk-cache-core:jar";
    public static final String ORG_JBOSS___INFINISPAN_CORE_JAR = "org.infinispan:infinispan-core:jar";
    public static final String ORG_JBOSS___INFINISPAN_CDI_JAR = "org.infinispan:infinispan-cdi:jar";
    public static final String COM_ERICSSON_OSS_ITPF_SDK___CLUSTER_INFINISPAN_JAR = "com.ericsson.oss.itpf.sdk:sdk-cluster-infinispan:jar";
    public static final String DE_ODYSSEUS___JUEL_SPI = "de.odysseus.juel:juel-spi:jar";
    public static final String DE_ODYSSEUS___JUEL_IMPL = "de.odysseus.juel:juel-impl:jar";

    //
    // 3PP
    //

    public static final String ORG_SLF4J___SLF4J_API_JAR = "org.slf4j:slf4j-api:jar";
    public static final String ORG_JBOSS___SEAM_SOLDER_JAR = "org.jboss.solder:solder-impl:jar";

    //
    // Constants associated with Mediation Core
    //
    public static final String COM_ERICSSON_NMS_MEDIATION_CORE_EAR_JAR = "com.ericsson.nms.mediation:MediationCore-ear:ear";
    public static final String COM_ERICSSON_NMS_MEDIATION_SERVICE_EAR_JAR = "com.ericsson.nms.mediation:mediation-service-ear:ear";

    //
    // Constants associated with the Mediation Client 
    //

    public static final String COM_ERICSSON_OSS_SUPERVISION_CLIENT_API_JAR = "com.ericsson.oss.mediation:supervision-client-api:jar";
    public static final String COM_ERICSSON_OSS_SUPERVISION_CLIENT_EJB_JAR = "com.ericsson.oss.mediation:supervision-client-ejb:jar";
    public static final String COM_ERICSSON_OSS_SUPERVISION_CLIENT_JAR_JAR = "com.ericsson.oss.mediation:supervision-client-jar:jar";
    public static final String COM_ERICSSON_OSS_SUPERVISION_CLIENT_EAR_JAR = "com.ericsson.oss.mediation:supervision-client-ear:ear";

    //
    // Constants associated with the DPS
    //

    public static final String COM_ERICSSON_OSS_DATA_DATA_PERSISTENCE_JAR = "com.ericsson.nms.data:DataPersistence-jar:jar";
    public static final String COM_ERICSSON_OSS_DATA_DATA_PERSISTENCE_API = "com.ericsson.nms.data:DataPersistence-api:jar";

    //
    // Constants associated with the MS-CORE
    //

    public static final String COM_ERICSSON_OSS_MEDIATION_CORE_MEDIATION_MODELS_JAR = "com.ericsson.nms.mediation:core-mediation-models-api:jar";

    /**
     * 
     * @return
     */
    public static MavenDependencyResolver getMavenResolver() {
        return DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
    }
}