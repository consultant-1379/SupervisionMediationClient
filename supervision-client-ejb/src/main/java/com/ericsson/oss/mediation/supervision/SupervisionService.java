package com.ericsson.oss.mediation.supervision;

import javax.ejb.Local;

@Local
public interface SupervisionService {
	void initAllSupervisions();
}
