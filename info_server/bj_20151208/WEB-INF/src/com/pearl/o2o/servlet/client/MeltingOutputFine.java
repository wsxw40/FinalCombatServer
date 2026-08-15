package com.pearl.o2o.servlet.client;

import java.util.List;
import java.util.concurrent.Callable;

import org.lilystudio.smarty4j.Context;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;

public class MeltingOutputFine extends BaseClientServlet {
	private static final long serialVersionUID = 1519975207181077962L;
	private static final String[] paramNames = {};

	protected String innerService(String... args) {
		try {
			return viewLocalCache.get("c_melting_output_list", new Callable<String>() {
				@Override
				public String call() throws Exception {
					int[] meltingresultitem = MeltingConstants.MeltingResultItem;
					List<SysItem> list = Lists.transform(Ints.asList(meltingresultitem), new Function<Integer, SysItem>() {
						@Override
						public SysItem apply(Integer input) {
							try {
								return getService.getSysItemByItemId(input);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						}
					});
					
					Context ctx	= new Context();
					ctx.set("list", list);
					return Converter.smartTemplate("MeltingOutputList.st",ctx);
				}
			});
		} catch (Exception e) {
			ServiceLocator.meltingLog.warn("Error in GetOnlineAward: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
