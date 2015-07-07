package org.ssm.crm520.page;
/**
 * 菜单的查询对象;
 * @author 李璨
 *
 */
public class MenuQuery extends BaseQuery {
	private String text;
	private String url;
	private String description;
	private String iconCls;
	private String parentId;

	public String getText() {
		if(text!=null){
			return "%"+text+"%";
		}
		return null;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		if(url!=null){
			return "%"+url+"%";
		}
		return null;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		if(description!=null){
			return "%"+description+"%";
		}
		return null;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconCls() {
		if(iconCls!=null){
			return "%"+iconCls+"%";
		}
		return null;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
