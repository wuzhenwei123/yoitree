package ${packageName}.${pak};

import java.util.Date;

import com.base.model.BaseModel;
/**
 * ${tableComment}	
 * ${description}
 * @time	${date}
 */
public class ${domainName?cap_first} extends BaseModel implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	<#list columns as item>
		/** ${item.remarks} **/
		private ${item.type} ${item.name};
	</#list>
		
	<#list columns as item>
		/**
		 * ${item.remarks}
		 * @return ${item.name}
		 */
		public ${item.type} get${item.name?cap_first}() {
			return ${item.name};
		}
		/**
		 * ${item.remarks}
		 */
		public void set${item.name?cap_first}(${item.type} ${item.name}) {
			this.${item.name} = ${item.name};
		}
	</#list>
}