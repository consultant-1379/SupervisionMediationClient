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
package com.ericsson.nms.mediation.core.channels;

import com.ericsson.oss.itpf.sdk.modeling.eventbus.channel.annotation.ModeledChannelDefinition;
import com.ericsson.oss.itpf.sdk.modeling.eventbus.channel.annotation.ModeledChannelType;

/**
 * This class defines the channel which will be used by <code>EventBasedMediationClientBean</code> to receive <code>MediationTaskRequest</code> events.
 * 
 * 
 */
@ModeledChannelDefinition(channelId = "EventBasedMediationClient", channelURI = "jms:/queue/MediationClientConsumerQueue", channelType = ModeledChannelType.POINT_TO_POINT)
public class EventBasedMediationClientChannel {
}
