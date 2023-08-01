package jw.org.asignaciones.repository.postgres

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
class PostgresDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    fun postgresDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.postgres.hikari")
    fun postgresDataSource(): DataSource {
        return postgresDataSourceProperties()
            .initializeDataSourceBuilder()
            .build()
    }
}