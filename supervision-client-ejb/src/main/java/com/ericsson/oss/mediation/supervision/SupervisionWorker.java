package com.ericsson.oss.mediation.supervision;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

import com.ericsson.oss.data.api.PersistentObject;

/**
 * 
 * @author emaomic
 * 
 */
@Local
public interface SupervisionWorker {

	@Asynchronous
	void initSupervision(PersistentObject persistentObject);
}
