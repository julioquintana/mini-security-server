package cl.minisecurityserver.securityservertest.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.SimpleDateFormat;

public class ObjectMapperConfig {
  public static final ObjectMapper objectMapper =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .setSerializationInclusion(Include.NON_NULL)
          .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
}
