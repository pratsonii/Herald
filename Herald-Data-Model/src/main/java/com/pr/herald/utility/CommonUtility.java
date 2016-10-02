package com.pr.herald.utility;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.pr.herald.base.BaseException;
import com.pr.herald.contants.Constants;

@Component
public class CommonUtility 
{

	public void checkEmpty(List<String> fields) throws BaseException
	{
		for(String s : fields)
		{
			if((s == null) || StringUtils.isEmpty(s))
			{
				throw new BaseException(s + " "+Constants.cantEmpty);
			}
		}
	}
}
