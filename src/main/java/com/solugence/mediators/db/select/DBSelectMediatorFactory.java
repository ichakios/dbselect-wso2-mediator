package com.solugence.mediators.db.select;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractDBMediatorFactory;

import javax.xml.namespace.QName;
import java.util.Properties;

/**
 * 
 * @author user
 *
 */
public class DBSelectMediatorFactory extends AbstractDBMediatorFactory
{

	@Override
	protected Mediator createSpecificMediator(OMElement element, Properties properties)
	{
		DBSelectMediator mediator = new DBSelectMediator();
		buildDataSource(element, mediator);
		processStatements(element, mediator);
		return mediator;
	}

	/**
	 * Reads the data source configuration for all mediators based on the
	 * <code>AbstractDBMediator</code> and stores the configuration in the
	 * mediator for datasource initialization and de-serialization.
	 *
	 */
	@Override
	public QName getTagQName()
	{
		return DBSelectMediatorConstants.DB_SELECT_QNAME;
	}
}
