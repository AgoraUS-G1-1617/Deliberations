
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rank;

@Component
@Transactional
public class RankToStringConverter implements Converter<Rank, String> {
	
	public String convert(Rank arg0) {
		return String.valueOf(arg0.getId());
	}
}
