/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.integration;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.itpf.sdk.config.provider.*;

@ApplicationScoped
public class MockConfigurationProvider implements ConfigurationPropertyProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockConfigurationProvider.class);

    public final static Map<String, List<String>> VALUES = new HashMap<String, List<String>>();
    public final static Map<String, Class<?>> VALUE_TYPE = new HashMap<String, Class<?>>();

    static {

        List<String> supervisionPOs = new ArrayList<>();

        supervisionPOs.add("CM");
        supervisionPOs.add("streaming");
        supervisionPOs.add("FM");
        supervisionPOs.add("PM");

        VALUES.put("MediationClient.supervisionPOs", supervisionPOs);

        List<String> supervisionActive = new ArrayList<>();

        supervisionActive.add("false");
        supervisionActive.add("true");
        supervisionActive.add("false");
        supervisionActive.add("false");

        VALUES.put("MediationClient.actives", supervisionActive);

        VALUE_TYPE.put("MediationClient.supervisionPOs", String[].class);
        VALUE_TYPE.put("MediationClient.actives", String[].class);

        LOGGER.info("prepared static propery map! size={}", VALUES.size());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ericsson.oss.itpf.sdk.config.provider.ConfigurationPropertyProvider #resolveProperty(java.lang.String)
     */
    @Override
    public ProvidedProperty resolveProperty(final String propertyName) {
        LOGGER.info("resolveProperty() propertyName={}", propertyName);
        if (VALUES.containsKey(propertyName)) {
            Class<?> type = VALUE_TYPE.get(propertyName);
            LOGGER.info("resolveProperty() propertyName={} returning={}", propertyName, VALUES.get(propertyName));
            final ProvidedProperty property = new ProvidedProperty(propertyName, PropertyScope.GLOBAL, VALUES.get(propertyName), type);
            return property;
        } else {
            LOGGER.info("resolveProperty() propertyName={} returning null", propertyName);
            return null;
        }
    }

}
