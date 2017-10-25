DBSelect Mediator
=================

The DBSelect Mediator can execute an arbitrary SQL select statement and then set a resulting values as an OM-typed local message property on the message context. The DB connection used may be looked up from an external data source or specified inline. In this case, an Apache DBCP connection pool is established and used.

Unlike standard [DBLookup Mediator](https://docs.wso2.org/display/ESB481/DBLookup+Mediator) DBSelect can return a result set with multiple rows.
 
**Usage:**

Build the mediator and put its JAR into :

```
$ESB_HOME/dropins
```

**Syntax:**

```JSON
<dbselect>
        <connection>
            <pool>
                <dsName>dataSource</dsName>
            </pool>
        </connection>
        <statement>
            <sql><![CDATA[Select * from table where column = ?]]></sql>
            <parameter expression="get-property('column')" type="CHAR"/>
            <result column="column1" name="column1"/>
             <result column="column2" name="column2"/>
            <result column="column3" name="column3"/>
            
        </statement>
    </dbselect>
    <log level="custom">
        <property expression="fn:concat('get the JSON DATA as DB_SELECT_RESULT => ', get-property('DB_SELECT_RESULT')) " name="message"/>
    </log>
```
Result set would be stored as DB_SELECT_RESULT property.

**TODO:**:
* UI configuration

