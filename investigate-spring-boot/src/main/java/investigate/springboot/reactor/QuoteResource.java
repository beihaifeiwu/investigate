package investigate.springboot.reactor;

import lombok.Data;

@Data
public class QuoteResource {
  String type;
  Quote value;
}