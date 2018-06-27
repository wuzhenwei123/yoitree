<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE sqlMap PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN" "http://www.ibatis.com/dtd/sql-map-2.dtd">
<sqlMap namespace="${domainName?cap_first}">
	<typeAlias alias="${domainName?cap_first}" type="${packageName}.model.${domainName?cap_first}"/>
	
    <sql id="get${domainName?cap_first}List_body">
    	<dynamic prepend="WHERE">
		<#list columns as item>
       		<isNotNull prepend="AND" property="${item.name}"> a.${item.name}=#${item.name}# </isNotNull>
		</#list>
		</dynamic>
        <isNotNull prepend="" property="sortColumn"> order by $sortColumn$ </isNotNull>
    </sql>

    <select id="get${domainName?cap_first}ListCount" resultClass="int">
         SELECT COUNT(*) FROM ${tableName} a
		<include refid="get${domainName?cap_first}List_body" />
    </select>

    <select id="get${domainName?cap_first}List" parameterClass="${domainName?cap_first}" resultClass="${domainName?cap_first}">
    	SELECT <#rt>
		<#list columns as item>
			<#if (item_index+1) != (columns?size)>
             a.${item.name},<#t>
        	</#if>
			<#if (item_index+1) == (columns?size)>
             a.${item.name}<#t>
        	</#if>
		</#list>
        FROM ${tableName} a
		<include refid="get${domainName?cap_first}List_body" />
        limit #rowStart# , #rowCount# 
    </select>

    <select id="get${domainName?cap_first}" parameterClass="${domainName?cap_first}" resultClass="${domainName?cap_first}">
        SELECT <#rt>
		<#list columns as item>
			<#if (item_index+1) != (columns?size)>
             a.${item.name},<#t>
        	</#if>
			<#if (item_index+1) == (columns?size)>
             a.${item.name}<#t>
        	</#if>
		</#list>
        FROM ${tableName} a
		<include refid="get${domainName?cap_first}List_body" />
    </select>

    <insert id="insert${domainName?cap_first}">
        INSERT INTO ${tableName} ( 
        <dynamic prepend="">
        <#list columns as item>
        	<#if item.name != pkName>
        	<#if item_index == 1 >
			<isNotNull prepend="" property="${item.name}"> ${item.name} </isNotNull>
        	</#if>
        	<#if item_index != 1 >
			<isNotNull prepend="," property="${item.name}"> ${item.name} </isNotNull>
        	</#if>
        	</#if>
		</#list>
        </dynamic>
        )
        VALUES (
        <dynamic prepend="">
        <#list columns as item>
        	<#if item.name != pkName>
        	<#if item_index == 1 >
		    <isNotNull prepend="" property="${item.name}"> #${item.name}# </isNotNull>
		    </#if>
        	<#if item_index != 1 >
		    <isNotNull prepend="," property="${item.name}"> #${item.name}# </isNotNull>
		    </#if>
		    </#if>
		</#list>
        </dynamic>
        )
        <selectKey keyProperty="${pkName}" resultClass="${pkType}">
			SELECT @@IDENTITY AS ${pkName} 
		</selectKey>
    </insert>

    <update id="update${domainName?cap_first}">
        UPDATE ${tableName}
        <dynamic prepend="SET">
        <#list columns as item>
        	<#if item.name != pkName>
		    <isNotNull prepend="," property="${item.name}"> ${item.name}=#${item.name}# </isNotNull>
        	</#if>
		</#list>
        </dynamic>
    	where ${pkName} = #${pkName}#
    </update>
    
    <delete id="remove${domainName?cap_first}">
        delete from ${tableName} where ${pkName} = #${pkName}#
    </delete>
</sqlMap>