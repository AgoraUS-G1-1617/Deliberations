
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rank;
import services.RankService;

@Component
@Transactional
public class StringToRankConverter implements Converter<String, Rank> {
	
	@Autowired
	private RankService rankService;

	
	public Rank convert(String arg0) {
		return rankService.findOne(Integer.valueOf(arg0));
	}
}
