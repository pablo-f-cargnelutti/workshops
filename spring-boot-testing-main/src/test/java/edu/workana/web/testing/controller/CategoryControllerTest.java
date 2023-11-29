package edu.workana.web.testing.controller;

import static edu.workana.web.testing.controller.ResponseBodyMatchers.responseBody;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

import com.github.javafaker.Faker;
import edu.workana.web.testing.model.ApiError;
import edu.workana.web.testing.model.Category;
import edu.workana.web.testing.model.CategoryRequest;
import edu.workana.web.testing.service.design.CategoryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

class CategoryControllerTest extends AbstractSpringTest {

  private static Faker faker;

  private final String API_URL = "/api/v1/categories";

  @MockBean
  private CategoryService categoryService;

  @BeforeAll
  static void initializeFaker() {
    faker = new Faker(Locale.ENGLISH);
  }

  @Test
  void shouldReturnAllCategories() throws Exception {
    // Given
    var expectedCategories = dummyCategories();
    when(categoryService.findAll()).thenReturn(expectedCategories);

    // When
    var mvcResult = mockMvc.perform(
            get(API_URL)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

    // Then
    var actualResponseAsString = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseAsString, equalToCompressingWhiteSpace(objectMapper.writeValueAsString(expectedCategories)));
  }

  @Test
  void shouldReturnCategoryWhenValidId() throws Exception {
    // given
    var category = new Category(UUID.randomUUID().toString(), "random name", "random category description");
    when(categoryService.findById(any())).thenReturn(category);

    // when and then
    mockMvc.perform(
            get(API_URL + "/{id}", "123456")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(responseBody().containsObjectAsJson(category, Category.class));
  }

  @Test
  void shouldThrowAnExceptionWhenInvalidId() throws Exception {
    // given
    when(categoryService.findById(any())).thenReturn(null);

    // when
    var mvcResult = mockMvc.perform(
            get(API_URL + "/{id}", "123456")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(status().isBadRequest())
        .andReturn();

    // then
    var actualResponseAsString = mvcResult.getResponse().getContentAsString();
    var actualApiError = objectMapper.readValue(actualResponseAsString, ApiError.class);
    var expectedApiError = expectedInvalidIdApiError();
    assertThat(actualApiError, equalToObject(expectedApiError));
  }

  @Test
  void shouldSaveCategoryWhenValidData() throws Exception {
    // given
    var categoryRequest = new CategoryRequest("name", "some random description");
    var category = new Category(UUID.randomUUID().toString(), categoryRequest.getName(), categoryRequest.getDescription());
    when(categoryService.save(any())).thenReturn(category);

    // when
    var mvcResult = mockMvc.perform(
            post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest))
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(status().isCreated())
        .andReturn();

    // then
    var actualResponseAsString = mvcResult.getResponse().getContentAsString();
    var actualCategory = objectMapper.readValue(actualResponseAsString, Category.class);
    assertThat(category, equalToObject(actualCategory));
  }

  @Test
  void shouldThrowAnExceptionWhenInvalidData() throws Exception {
    // given
    var categoryRequest = new CategoryRequest("", "");

    // when and then
    mockMvc.perform(
            post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequest))
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(status().isBadRequest())
        .andExpect(responseBody().containsError("name", "must not be blank"))
        .andExpect(responseBody().containsError("description", "must not be blank"))
        .andReturn();
  }

  private List<Category> dummyCategories() {
    return Stream.generate(this::ramdomCategory)
        .limit(5)
        .toList();
  }

  private Category ramdomCategory() {
    return Category.builder()
        .id(UUID.randomUUID().toString())
        .name(faker.commerce().department())
        .description(faker.funnyName().name())
        .build();
  }

  private ApiError expectedInvalidIdApiError() {
    return new ApiError("INVALID_REQUEST", "Category not found with this id: 123456", "uri=" + API_URL + "/123456");
  }

}