package org.ssm.crm520.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 菜单对象
 * @author 李璨
 *
 */
public class Menu extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String text;//菜单名字
	private String url;//菜单url
	private String description;//菜单描述
	private String iconCls;//图标;
	private Menu parent;
	private Map<String,Object> attributes = new HashMap<String,Object>();
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	@Override
	public String toString() {
		return "Menu [text=" + text + ", url=" + url + ", description=" + description
				+ ", iconCls=" + iconCls + ", parent=" + parent + ", attributes=" + attributes
				+ ", id=" + id + "]";
	}

}
