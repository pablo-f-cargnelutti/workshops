package edu.workana.web.testing.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

import com.github.javafaker.Faker;
import edu.workana.web.testing.model.Category;
import edu.workana.web.testing.model.CategoryRequest;
import edu.workana.web.testing.repository.CategoryRepository;
import edu.workana.web.testing.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  private static Faker faker;

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryServiceImpl categoryService;

  @BeforeAll
  static void initializeFaker() {
    faker = new Faker(Locale.ENGLISH);
  }

  @Test
  void shouldReturnCategories() {
    // given
    var categories = dummyCategories();
    when(categoryRepository.findAll()).thenReturn(categories);

    // when
    var retrievedCategories = categoryService.findAll();

    // then
    assertThat(retrievedCategories, allOf(notNullValue(), hasSize(categories.size()), everyItem(notNullValue())));
  }

  @Test
  void shouldReturnCategoryByValidCategoryId() {

  }

  @Test
  void shouldSaveCategoryWithValidData() {
    
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

  private CategoryRequest populateRandomCategoryRequest() {
    return new CategoryRequest(faker.commerce().department(), faker.funnyName().name());
  }

  private Category populateSavedCategory(CategoryRequest categoryRequest) {
    return Category.builder()
        .id(UUID.randomUUID().toString())
        .name(categoryRequest.getName())
        .description(categoryRequest.getDescription())
        .build();
  }
}