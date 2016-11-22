
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Karma;
import services.KarmaService;

@Component
@Transactional
public class StringToKarmaConverter implements Converter<String, Karma> {
	
	@Autowired
	private KarmaService karmaService;

	
	public Karma convert(String arg0) {
		return karmaService.findOne(Integer.valueOf(arg0));
	}
}
