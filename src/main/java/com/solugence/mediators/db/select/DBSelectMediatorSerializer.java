package com.solugence.mediators.db.select;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractDBMediatorSerializer;

/**
 * 
 * @author user
 *
 */
public class DBSelectMediatorSerializer extends AbstractDBMediatorSerializer
{
	@Override
	protected OMElement serializeSpecificMediator(Mediator mediator)
	{
		if (!(mediator instanceof DBSelectMediator))
		{
			handleException("Unsupported mediator passed in for serialization : " + mediator.getType());
		}
		DBSelectMediator dbSelectMediator = (DBSelectMediator) mediator;
		OMElement dbSelectElement = fac.createOMElement(DBSelectMediatorConstants.DB_SELECT_TAG_NAME, synNS);
		saveTracingState(dbSelectElement, mediator);
		serializeDBInformation(dbSelectMediator, dbSelectElement);
		return dbSelectElement;

	}

	@Override
	public String getMediatorClassName()
	{
		return DBSelectMediator.class.getName();
	}
}
