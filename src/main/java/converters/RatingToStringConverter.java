
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Rating;

@Component
@Transactional
public class RatingToStringConverter implements Converter<Rating, String> {
	
	public String convert(Rating arg0) {
		return String.valueOf(arg0.getId());
	}
}
