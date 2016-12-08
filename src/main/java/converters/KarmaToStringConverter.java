
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Karma;

@Component
@Transactional
public class KarmaToStringConverter implements Converter<Karma, String> {
	
	public String convert(Karma arg0) {
		return String.valueOf(arg0.getId());
	}
}
