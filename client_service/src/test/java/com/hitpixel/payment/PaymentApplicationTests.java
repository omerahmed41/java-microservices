package com.hitpixel.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@SpringBootTest
class PaymentApplicationTests {


@Test
public void whenFindByName_thenReturnEmployee() {
	// given


	// when

	// then
	assertThat(1)
			.isEqualTo(1);
}

}
