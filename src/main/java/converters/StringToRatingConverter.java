
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rating;
import services.RatingService;

@Component
@Transactional
public class StringToRatingConverter implements Converter<String, Rating> {
	@Autowired
	private RatingService ratingService;

	
	public Rating convert(String arg0) {
		return ratingService.findOne(Integer.valueOf(arg0));
	}
}
