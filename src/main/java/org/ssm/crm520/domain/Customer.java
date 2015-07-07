package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Customer extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//基本信息
	private String name;//客户姓名
	private Integer age;//客户年龄
	private Integer gender=3;//客户性别   	 1为”男“，2为”女“，3为”未知“
	
	//联系方式
	private String tel;//电话
	private String email;//邮箱
	private String qq;//qq
	private String weChat;//微信
	
	//扩展信息
	private SystemDictionaryDetail job;//职业
	private SystemDictionaryDetail salaryLevel;//收入水平
	private SystemDictionaryDetail source;//客户来源
	
	private Employee seller;//创建人
	private Date time=new Date();//创建时间
	
	private Integer status=0;// 0为正常，-1作废
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getGender() {
		if(gender==null){
			return 3;
		}
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public SystemDictionaryDetail getJob() {
		return job;
	}
	public void setJob(SystemDictionaryDetail job) {
		this.job = job;
	}
	public SystemDictionaryDetail getSalaryLevel() {
		return salaryLevel;
	}
	public void setSalaryLevel(SystemDictionaryDetail salaryLevel) {
		this.salaryLevel = salaryLevel;
	}
	public SystemDictionaryDetail getSource() {
		return source;
	}
	public void setSource(SystemDictionaryDetail source) {
		this.source = source;
	}
	public Employee getSeller() {
		return seller;
	}
	public void setSeller(Employee seller) {
		this.seller = seller;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", gender=" + gender
				+ ", tel=" + tel + ", email=" + email + ", qq=" + qq
				+ ", weChat=" + weChat + ", job=" + job + ", salaryLevel="
				+ salaryLevel + ", source=" + source + ", seller=" + seller
				+ ", time=" + time + ", status=" + status + ", id=" + id + "]";
	}
	
	
	
	
	
}
