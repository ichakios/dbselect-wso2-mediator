package com.solugence.mediators.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.db.AbstractDBMediator;
import org.apache.synapse.mediators.db.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author user
 *
 */
public class DBSelectMediator extends AbstractDBMediator
{

	protected void processStatement(Statement stmnt, MessageContext msgCtx)
	{
		Connection con = null;
		ResultSet rs = null;
		try
		{
			con = this.getDataSource().getConnection();
			PreparedStatement ps = getPreparedStatement(stmnt, con, msgCtx);
			rs = ps.executeQuery();

			JSONArray resultASJSONARR 	= new JSONArray();
			JSONObject jsonObject		= null;
			String propValue = null;
			while (rs.next())
			{
				jsonObject = new JSONObject();
				for (String propName : stmnt.getResultsMap().keySet())
				{
					propValue = rs.getString(propName);
					jsonObject.put(propName,propValue==null?"":propValue);
				}
				resultASJSONARR.put(jsonObject);
			}
			msgCtx.setProperty(DBSelectMediatorConstants.DB_SELECT_PROPERTY_NAME, resultASJSONARR.toString());
		} 
		catch (SQLException e)
		{
			log.error("Error executing SQL statement : " + stmnt.getRawStatement() + " against DataSource : "
					+ getDSName(), e);
		} finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				} catch (SQLException e)
				{
					log.warn("Error occurred while trying to close result set.", e);
				}
			}
			if (con != null)
			{
				try
				{
					con.close();
				} catch (SQLException e)
				{
					log.warn("Error occurred while trying to close connection.", e);
				}
			}
		}
	}
}
