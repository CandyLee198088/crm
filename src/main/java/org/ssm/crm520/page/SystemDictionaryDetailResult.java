package org.ssm.crm520.page;

import java.util.List;

import org.ssm.crm520.domain.SystemDictionaryDetail;

public class SystemDictionaryDetailResult extends PageResult<SystemDictionaryDetail>{

	public SystemDictionaryDetailResult(List<SystemDictionaryDetail> systemDictionaryDetails,Long totalCount) {
		super(systemDictionaryDetails, totalCount);
	}
}
