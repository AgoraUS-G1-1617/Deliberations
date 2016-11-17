
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.ThreadService;
import domain.Thread;

@Component
@Transactional
public class StringToThreadConverter implements Converter<String, Thread> {
	
	@Autowired
	private ThreadService threadService;


	public Thread convert(String arg0) {
		return threadService.findOne(Integer.valueOf(arg0));
	}
}
