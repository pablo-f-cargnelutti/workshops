package edu.workana.web.testing.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

import com.github.javafaker.Faker;
import edu.workana.web.testing.model.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class CategoryRepositoryTest extends BaseMongoContainer {

  private static Faker faker;

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeAll
  static void initializeFaker() {
    faker = new Faker(Locale.ENGLISH);
  }

  @Test
  void shouldSaveCategories() {
    // Given
    var categories = dummyCategories();

    // When
    categoryRepository.saveAll(categories);

    // Then
    assertThat(categoryRepository.findAll(), hasSize(categories.size()));
  }

  private List<Category> dummyCategories() {
    return Stream.generate(this::randomCategory)
        .limit(5)
        .distinct()
        .toList();
  }

  private Category randomCategory() {
    return Category.builder()
        .id(UUID.randomUUID().toString())
        .name(faker.commerce().department())
        .description(faker.funnyName().name())
        .build();
  }

}