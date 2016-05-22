package com.freetmp.investigate.springboot.reactor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
  Long id;
  String quote;
}