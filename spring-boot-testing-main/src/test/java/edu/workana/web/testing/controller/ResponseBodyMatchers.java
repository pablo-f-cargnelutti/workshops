package edu.workana.web.testing.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.workana.web.testing.model.ValidationError;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultMatcher;

public class ResponseBodyMatchers {

  static ResponseBodyMatchers responseBody() {
    return new ResponseBodyMatchers();
  }

  public <T> ResultMatcher containsObjectAsJson(T expected, Class<T> target) {
    return mvcResult -> {
      var jsonResponseAsString = mvcResult.getResponse().getContentAsString();
      T actualObject = new ObjectMapper().readValue(jsonResponseAsString, target);
      assertThat(actualObject, Matchers.equalToObject(expected));
    };
  }

  public ResultMatcher containsError(String expectedFieldName, String expectedMessage) {
    return mvcResult -> {
      var jsonResponseAsString = mvcResult.getResponse().getContentAsString();
      var validationError = new ObjectMapper().readValue(jsonResponseAsString, ValidationError.class);
      var errors = validationError.getErrors();
      var errorMessage = errors.getOrDefault(expectedFieldName, null);
      assertThat(errorMessage, is(expectedMessage));
    };
  }
}
